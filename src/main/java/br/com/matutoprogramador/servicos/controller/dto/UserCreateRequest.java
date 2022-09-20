package br.com.matutoprogramador.servicos.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserCreateRequest {

    @NotEmpty(message = "{field.user.username}")
    private String username;
    @NotEmpty(message = "{field.user.password}")
    private String password;
}
