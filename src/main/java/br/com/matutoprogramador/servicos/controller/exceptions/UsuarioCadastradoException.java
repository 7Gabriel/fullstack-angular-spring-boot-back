package br.com.matutoprogramador.servicos.controller.exceptions;

public class UsuarioCadastradoException extends RuntimeException {

    public UsuarioCadastradoException(String login){
        super("Usuario já cadastrado "+ login);
    }
}
