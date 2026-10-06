package dev.yuritorquato.microsservicos_spring.controller;

import java.util.ArrayList;
import java.util.List;

import dev.yuritorquato.microsservicos_spring.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDTO> usuarios = new ArrayList<UserDTO>();

    @PostConstruct
    public void initializeList() {
        var userDTO1 = new UserDTO("Yuri", "123", "Rua A", "yurictorquato@gmail.com", "1234-5678");
        var userDTO2 = new UserDTO("Luiz", "456", "Rua B", "luiz@gmail.com", "1234-4321");
        var userDTO3 = new UserDTO("Maiana", "789", "Rua A", "maiana@gmail.com", "5678-1234");

        usuarios.add(userDTO1);
        usuarios.add(userDTO2);
        usuarios.add(userDTO3);
    }


    @GetMapping("/")
    public List<UserDTO> getMessage() {
        return usuarios;
    }

    @GetMapping("/{cpf}")
    public UserDTO getUsersFiltro(@PathVariable String cpf) {
        return usuarios.stream().filter(userDTO -> userDTO.cpf().equals(cpf)).findFirst().orElseThrow(() -> new RuntimeException("User not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO inserir(@RequestBody @Valid UserDTO userDTO) {
        var user = new UserDTO(userDTO.nome(), userDTO.cpf(), userDTO.endereco(), userDTO.email(), userDTO.telefone());

        usuarios.add(user);

        return user;
    }

    @DeleteMapping("/{cpf}")
    public boolean remover(@PathVariable String cpf) {
        return usuarios.removeIf(userDTO -> userDTO.cpf().equals(cpf));
    }
}
