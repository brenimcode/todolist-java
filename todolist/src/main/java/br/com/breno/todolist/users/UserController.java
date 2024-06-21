package br.com.breno.todolist.users;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // o que estou to recebendo aqui dentro vem do body
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
        System.out.println("kk");
    }
}
