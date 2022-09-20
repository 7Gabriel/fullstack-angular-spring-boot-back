package br.com.matutoprogramador.servicos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ServicoRequest {

    @NotEmpty(message = "{field.descricao.obrigatorio}")
    private String descricao;
    @NotEmpty(message = "{field.preco.obrigatorio}")
    private String preco;
    @NotEmpty(message = "{field.data.obrigatorio}")
    private String data;
    @NotNull(message = "{field.cliente.obrigatorio}")
    private Long idCliente;
}
