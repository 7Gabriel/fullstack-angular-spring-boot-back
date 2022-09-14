package br.com.matutoprogramador.servicos.controller;


import br.com.matutoprogramador.servicos.model.entity.Cliente;
import br.com.matutoprogramador.servicos.model.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Cliente save(@RequestBody Cliente cliente ){
        return clienteRepository.save(cliente);
    }

    @GetMapping("/{id}")
    private Cliente getCliente(@PathVariable Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteCliente(@PathVariable Long id){
        clienteRepository.findById(id)
                .map( cliente -> {
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    private void updateCliente(@PathVariable Long id, @RequestBody Cliente clienteUpdated){
        clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setName(clienteUpdated.getName());
                    cliente.setCpf(clienteUpdated.getCpf());
                    clienteRepository.save(cliente);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
