package dev.yuritorquato.microsservicos_spring.model;

import dev.yuritorquato.microsservicos_spring.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataCadastro;

    public User(long id, String nome, String cpf, String endereco, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

//    public User(UserDTO userDTO) {
//        this.nome = userDTO.nome();
//        this.cpf = userDTO.cpf();
//        this.endereco = userDTO.endereco();
//        this.email = userDTO.email();
//        this.telefone = userDTO.telefone();
//        this.dataCadastro = userDTO.dataCadastro();
//    }

    public User() {
    }

    public static User convert(UserDTO userDTO) {
        var user = new User();

        user.setNome(userDTO.nome());
        user.setCpf(userDTO.cpf());
        user.setEndereco(userDTO.endereco());
        user.setEmail(userDTO.email());
        user.setTelefone(userDTO.telefone());

        return user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
