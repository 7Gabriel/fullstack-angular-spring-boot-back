package br.com.matutoprogramador.servicos.controller;

import br.com.matutoprogramador.servicos.controller.dto.UserCreateRequest;
import br.com.matutoprogramador.servicos.controller.dto.UserLoginRequest;
import br.com.matutoprogramador.servicos.controller.exceptions.UsuarioCadastradoException;
import br.com.matutoprogramador.servicos.model.entity.Usuario;
import br.com.matutoprogramador.servicos.model.repository.UserRepository;
import br.com.matutoprogramador.servicos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid UserCreateRequest request){
        Usuario user = Usuario.builder()
                .password(request.getPassword())
                .username(request.getUsername()).build();
        try{
            usuarioService.save(user);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PostMapping({"/login"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void login(@RequestBody @Valid UserLoginRequest request){
        Usuario user = Usuario.builder()
                .password(request.getPassword())
                .username(request.getUsername()).build();
        usuarioService.loadUserByUsername(user.getUsername());
    }

}
