package dev.yuritorquato.microsservicos_spring.service;

import dev.yuritorquato.microsservicos_spring.dto.UserDTO;
import dev.yuritorquato.microsservicos_spring.model.User;
import dev.yuritorquato.microsservicos_spring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDTO::convert)
                .toList();
    }

    public UserDTO findById(long id) {
        var usuario = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + id + " não encontrado"));

        return UserDTO.convert(usuario);
    }

    public UserDTO save(UserDTO userDTO) {
        var usuario_temp = User.convert(userDTO);
        usuario_temp.setDataCadastro(LocalDateTime.now());

        var usuario = userRepository.save(User.convert(userDTO));

        return UserDTO.convert(usuario);
    }
}
