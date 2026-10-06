package dev.yuritorquato.microsservicos_spring.service;

import dev.yuritorquato.microsservicos_spring.dto.UserRequestDTO;
import dev.yuritorquato.microsservicos_spring.dto.UserResponseDTO;
import dev.yuritorquato.microsservicos_spring.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO::from)
                .toList();
    }

    public UserResponseDTO findById(long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado"));

        return UserResponseDTO.from(user);
    }

    public UserResponseDTO save(UserRequestDTO userRequestDTO) {
        var user = userRepository.save(userRequestDTO.toEntity());

        return UserResponseDTO.from(user);
    }

    public UserResponseDTO delete(long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + userId + " não encontrado"));

        userRepository.delete(user);

        return UserResponseDTO.from(user);
    }

    public UserResponseDTO findByCpf(String cpf) {
        var user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Usuário com CPF " + cpf + " não encontrado"));

        return UserResponseDTO.from(user);
    }

    public List<UserResponseDTO> queryByName(String name) {
        var users = userRepository.queryByNomeLike(name);

        return users.stream()
                .map(UserResponseDTO::from)
                .toList();
    }

    public UserResponseDTO editUser(long userId, UserRequestDTO userRequestDTO) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + userId + " não encontrado"));

        if (userRequestDTO.email() != null && !userRequestDTO.email().equals(user.getEmail())) {
            user.setEmail(userRequestDTO.email());
        }

        if (userRequestDTO.telefone() != null && !userRequestDTO.telefone().equals(user.getTelefone())) {
            user.setTelefone(userRequestDTO.telefone());
        }

        if (userRequestDTO.endereco() != null && !userRequestDTO.endereco().equals(user.getEndereco())) {
            user.setEndereco(userRequestDTO.endereco());
        }

        user = userRepository.save(user);

        return UserResponseDTO.from(user);
    }

    public Page<UserResponseDTO> getAllPage(Pageable page) {
        return userRepository.findAll(page)
                .map(UserResponseDTO::from);
    }
}
