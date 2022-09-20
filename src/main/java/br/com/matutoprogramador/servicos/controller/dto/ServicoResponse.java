package br.com.matutoprogramador.servicos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor @AllArgsConstructor
public class ServicoResponse {

    private String descricao;
    private String preco;
    private String data;
    private Long idCliente;
}
