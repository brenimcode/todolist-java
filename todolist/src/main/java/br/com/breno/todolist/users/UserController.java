package br.com.breno.todolist.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired // Spring vai gerenciar essa parte.
    private IUserRepository userRepository;

    // o que estou to recebendo aqui dentro vem do body
    @PostMapping("/")
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel){
        var user  = this.userRepository.findByUsername(userModel.getUsername());
        if(user != null){
            // msg de erro, status Code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        var passwordHashred = BCrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHashred);
        
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.ok().body(userCreated);
    }
}
