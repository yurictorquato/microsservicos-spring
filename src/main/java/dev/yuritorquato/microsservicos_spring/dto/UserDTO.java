package dev.yuritorquato.microsservicos_spring.dto;

import dev.yuritorquato.microsservicos_spring.model.User;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank(message = "Nome é obrigatório") String nome,
                      @NotBlank(message = "CPF é obrigatório") String cpf, String endereco,
                      @NotBlank(message = "E-mail é obrigatório") String email, String telefone) {
    public static UserDTO convert(User user) {
        return new UserDTO(user.getNome(), user.getCpf(), user.getEndereco(), user.getEmail(), user.getTelefone());
    }
}
