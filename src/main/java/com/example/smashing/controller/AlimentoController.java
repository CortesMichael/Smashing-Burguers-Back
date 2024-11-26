package com.example.smashing.controller;

import com.example.smashing.model.Alimento;
import com.example.smashing.repository.AlimentoRepository;
import com.example.smashing.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AlimentoController {

    private final AlimentoService alimentoService;
    private final AlimentoRepository repositorio;

    @Autowired
    public AlimentoController(AlimentoRepository repositorio, AlimentoService alimentoService) {
        this.repositorio = repositorio;
        this.alimentoService = alimentoService;
    }

    @GetMapping
    public ResponseEntity<List<Alimento>> listarTodosAlimentos() {
        List<Alimento> alimentos = alimentoService.listarTodos();
        return new ResponseEntity<>(alimentos, HttpStatus.OK);
    }

    @GetMapping("/alimentos/{id}")
    public ResponseEntity<Alimento> getAlimentoById(@PathVariable Long id) {
        Alimento alimento = alimentoService.obterAlimentoPorId(id);
        return new ResponseEntity<>(alimento, HttpStatus.OK);
    }

    @PostMapping("/alimentos")
    public ResponseEntity<Alimento> adicionarAlimento(@RequestBody Alimento alimento) {
        Alimento novoAlimento = alimentoService.adicionarAlimento(alimento);
        return new ResponseEntity<>(novoAlimento, HttpStatus.CREATED);
    }

    @PutMapping("/alimentos/{id}")
    public ResponseEntity<Alimento> atualizarAlimento(@PathVariable Long id, @RequestBody Alimento alimento) {
        Alimento alimentoEditado = alimentoService.atualizarAlimento(id, alimento);
        return new ResponseEntity<>(alimentoEditado, HttpStatus.OK);
    }

    @DeleteMapping("/alimentos/{id}")
    public ResponseEntity<Void> excluirAlimento(@PathVariable Long id) {
        alimentoService.excluirAlimento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
