package br.com.breno.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.breno.todolist.utils.Utils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    /**
     * Cria uma nova tarefa.
     * 
     * @param taskModel O objeto TaskModel recebido no corpo da requisição
     * @param request   O HttpServletRequest contendo o id do usuário autenticado
     * @return ResponseEntity com a tarefa criada ou mensagem de erro se as validações falharem
     */
    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        if (idUser == null) {
            throw new IllegalArgumentException("User ID not found in request attributes");
        }
        taskModel.setIdUser(idUser);

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start date must be in the future");
        }
        if (currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date must be in the future");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date must be after start date");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    /**
     * Lista todas as tarefas do usuário autenticado.
     * 
     * @param request O HttpServletRequest contendo o id do usuário autenticado
     * @return Lista de TaskModel associadas ao usuário
     */
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        UUID idUser = (UUID) request.getAttribute("idUser");
        return this.taskRepository.findByIdUser(idUser);
    }

    /**
     * Atualiza uma tarefa existente.
     * 
     * @param taskModel O objeto TaskModel com os novos dados da tarefa
     * @param request   O HttpServletRequest contendo o id do usuário autenticado
     * @param id        O UUID da tarefa a ser atualizada
     * @return ResponseEntity com a tarefa atualizada ou mensagem de erro se as validações falharem
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {

        // Busca a tarefa pelo ID
        var task = this.taskRepository.findById(id).orElse(null);
        if (task == null) {
            return ResponseEntity.badRequest().body("Task not found!");
        }

        // Verifica se o usuário tem permissão para modificar a tarefa
        var idUser = request.getAttribute("idUser");
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.badRequest().body("User does not have permission to change this task");
        }

        // Copia as propriedades não nulas da taskModel para a task existente
        Utils.copyNonNullProperties(taskModel, task);

        // Salva e retorna a tarefa atualizada
        return ResponseEntity.ok().body(this.taskRepository.save(task));
    }

}
