package com.example.prova.service;

import com.example.prova.entity.Tarefa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private List<Tarefa> tarefas;

    public TarefaService(){
        tarefas = new ArrayList<>();
    }

    public List<Tarefa> buscarTarefas(){
        return tarefas;
    }

    public Tarefa criarTarefa(Tarefa tarefa) throws Exception {
        tarefas.add(tarefa);
        return tarefa;
    }

    public Tarefa buscarTarefa(Long codigo) throws Exception {
        Optional<Tarefa> tarefa = tarefas.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(tarefa.isPresent()){
            return tarefa.get();
        } else {
            throw new Exception("Tarefa não encontrado!");
        }
    }

    public Tarefa alterarTarefa(Long codigo, Tarefa tarefa) throws Exception {
        Optional<Tarefa> tarefaDia = tarefas.stream().filter
                (e -> e.getCodigo() == codigo).findFirst();
        if(tarefaDia.isPresent()){
            tarefaDia.get().setNome(tarefa.getNome());
            tarefaDia.get().setStatus(tarefa.getStatus());
            tarefaDia.get().setNumero(tarefa.getNumero());


            return tarefa;
        } else {
            throw new Exception("tarefa não encontrado!");
        }
    }

    public void removerTarefa(Long codigo) {
        tarefas.removeIf(tarefa -> tarefa.getCodigo() == codigo);
    }
}


