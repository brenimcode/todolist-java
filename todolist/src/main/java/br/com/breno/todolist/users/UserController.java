package br.com.breno.todolist.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel) {
        // Verifica se o usuário já existe
        if (userRepository.existsByUsername(userModel.getUsername())) {
            return ResponseEntity.badRequest().body(userModel); // Retorna erro se existir
        }

        // Hash da senha antes de salvar
        String hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(hashedPassword);

        // Salva o usuário e retorna sucesso
        UserModel createdUser = userRepository.save(userModel);
        return ResponseEntity.ok().body(createdUser);
    }
}
