package com.example.prova.controller;


import com.example.prova.entity.Tarefa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.prova.service.TarefaService;

import java.util.List;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("todos")
    public ResponseEntity<?> buscarTarefas(){
        try {
            List<Tarefa> lista =
                    tarefaService.buscarTarefas();
            return new ResponseEntity(lista, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Erro na requisição",
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa){
        try{
            tarefa = tarefaService.criarTarefa(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarTarefa(@PathVariable("codigo") Long codigo){
        try {
            Tarefa tarefa = tarefaService.buscarTarefa(codigo);
            return new ResponseEntity(tarefa, HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>
                    ("Erro na requisição",
                            HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("alterar/{codigo}")
    public ResponseEntity<?> alterarTarefa(@RequestBody Tarefa tarefa, @PathVariable("codigo") Long codigo){
        try{
            tarefa = tarefaService.alterarTarefa(codigo, tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("remover/{codigo}")
    public ResponseEntity<?> removerTarefa(@PathVariable("codigo") Long codigo){
        try {
            tarefaService.removerTarefa(codigo);
            return new ResponseEntity("tarefa Removida com Sucesso", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


