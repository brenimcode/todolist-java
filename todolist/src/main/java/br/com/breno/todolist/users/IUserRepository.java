package br.com.breno.todolist.users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

    // Verifica se existe um usuário com o username especificado
    boolean existsByUsername(String username);

    // Busca um usuário pelo username
    UserModel findByUsername(String username);
}
