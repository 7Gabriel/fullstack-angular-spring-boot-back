package br.com.matutoprogramador.servicos.controller;

import br.com.matutoprogramador.servicos.controller.dto.ServicoClienteResponse;
import br.com.matutoprogramador.servicos.controller.dto.ServicoRequest;
import br.com.matutoprogramador.servicos.controller.dto.ServicoResponse;
import br.com.matutoprogramador.servicos.model.entity.Cliente;
import br.com.matutoprogramador.servicos.model.entity.Servico;
import br.com.matutoprogramador.servicos.model.repository.ClienteRepository;
import br.com.matutoprogramador.servicos.model.repository.ServicoRepository;
import br.com.matutoprogramador.servicos.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/servicos/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ServicoController {

    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;
    private final BigDecimalConverter converter;

    @PostMapping
    public ServicoResponse saveServico(@RequestBody @Valid ServicoRequest request) {

        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe"));

        Servico servico = getServico(request, cliente);
        Servico servicoSave = servicoRepository.save(servico);

        return getServicoResponse(servicoSave);

    }

    @GetMapping
    private List<ServicoResponse> getServicos(){
        List<Servico> servicos = servicoRepository.findAll();
        List<ServicoResponse> responses = new ArrayList<>();

       servicos.stream().forEach( serv -> responses.add(getServicoResponse(serv)));
       return responses;
    }

    @GetMapping("/{idCliente}/servicos")
    public List<ServicoClienteResponse> getServicosPorCliente(@PathVariable Long idCliente){
        List<Servico> servicos = servicoRepository.findByIdCliente(idCliente);
        List<ServicoClienteResponse> responses = new ArrayList<>();

        servicos.stream().forEach(servico -> responses.add(convertServicoResponse(servico)));

        return responses;
    }

    @GetMapping({"/search"})
    public List<ServicoResponse> searchService(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes){

        List<ServicoResponse> responses = new ArrayList<>();
        List<Servico>  servicos = servicoRepository.findByNomeClienteAndMes(  "%" + nome + "%", mes);
        servicos.stream().forEach(serv -> responses.add(getServicoResponse(serv)));
        return responses;
    }

    private ServicoClienteResponse convertServicoResponse(Servico servico) {
        return ServicoClienteResponse.builder()
                .cpfCliente(servico.getCliente().getCpf())
                .nomeCliente(servico.getCliente().getName())
                .preco(servico.getValor().toString())
                .descricao(servico.getDescricao())
                .data(servico.getDataServico().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }

    private Servico getServico(ServicoRequest request, Cliente cliente) {
        LocalDate data = LocalDate.parse(request.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return Servico.builder()
                .dataServico(data)
                .descricao(request.getDescricao())
                .cliente(cliente)
                .valor(converter.convert(request.getPreco()))
                .build();
    }

    private static ServicoResponse getServicoResponse(Servico servicoSave) {
        return ServicoResponse
                .builder()
                .data(servicoSave.getDataServico().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .descricao(servicoSave.getDescricao())
                .preco(servicoSave.getValor().toString())
                .idCliente(servicoSave.getCliente().getId())
                .build();
    }

}
