package dev.yuritorquato.microsservicos_spring.dto;

import dev.yuritorquato.microsservicos_spring.model.User;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String nome,
        String cpf,
        String endereco,
        String email,
        String telefone,
        LocalDateTime dataCadastro
) {
    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(user.getId(), user.getNome(), user.getCpf(), user.getEndereco(), user.getEmail(), user.getTelefone(), user.getDataCadastro());
    }
}
