package dev.yuritorquato.microsservicos_spring.repository;

import dev.yuritorquato.microsservicos_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf);
    List<User> queryByNomeLike(String name);
}
