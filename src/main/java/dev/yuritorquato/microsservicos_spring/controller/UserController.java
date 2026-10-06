package dev.yuritorquato.microsservicos_spring.controller;

import dev.yuritorquato.microsservicos_spring.dto.UserRequestDTO;
import dev.yuritorquato.microsservicos_spring.dto.UserResponseDTO;
import dev.yuritorquato.microsservicos_spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserResponseDTO> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO newUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.save(userRequestDTO);
    }

    @GetMapping("/{cpf}/cpf")
    public UserResponseDTO findByCpf(@PathVariable String cpf) {
        return userService.findByCpf(cpf);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws UserNotFoundException {
        userService.delete(id);
    }

    @GetMapping("/search")
    public List<UserResponseDTO> queryByName(
            @RequestParam(name = "nome", required = true) String nome
    ) {
        return userService.queryByName(nome);
    }

    @PatchMapping("/{id}")
    public UserResponseDTO editUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.editUser(id, userRequestDTO);
    }

    @GetMapping("/peageble")
    public Page<UserResponseDTO> getUsersPage(Pageable pageable) {
        return userService.getAllPage(pageable);
    }
}