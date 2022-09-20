package br.com.matutoprogramador.servicos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class ServicoClienteResponse {

    private String descricao;
    private String preco;
    private String data;
    private String nomeCliente;
    private String cpfCliente;
}
