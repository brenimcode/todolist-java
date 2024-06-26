package br.com.breno.todolist.users;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<UserModel,UUID>{

    // buscara por String username nas tabelas.
    UserModel findByUsername(String username);
}
