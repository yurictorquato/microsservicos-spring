package dev.yuritorquato.microsservicos_spring.model;

import dev.yuritorquato.microsservicos_spring.dto.UserDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    @Column(name = "data_cadastro", insertable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private LocalDateTime dataCadastro;

    public User(String nome, String cpf, String endereco, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

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

    public Long getId() {
        return id;
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

}
