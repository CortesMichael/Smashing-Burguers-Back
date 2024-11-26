package com.example.smashing.service;

import com.example.smashing.model.Alimento;
import com.example.smashing.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentoService {

    private final AlimentoRepository repositorio;

    @Autowired
    public AlimentoService(AlimentoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Alimento> listarTodos() {
        return repositorio.findAll();
    }

    public Alimento adicionarAlimento(Alimento alimento) {
        return repositorio.save(alimento);
    }

    public Alimento atualizarAlimento(Long id, Alimento alimento) {
        Optional<Alimento> alimentoExistente = repositorio.findById(id);
        if (alimentoExistente.isPresent()) {
            Alimento a = alimentoExistente.get();
            a.setNome(alimento.getNome());
            a.setDescricao(alimento.getDescricao());
            a.setImagem(alimento.getImagem());
            a.setValor(alimento.getValor());
            a.setCategoria(alimento.getCategoria());
            return repositorio.save(a);
        } else {
            throw new RuntimeException("Alimento não encontrado para o ID :: " + id);
        }
    }

    public void excluirAlimento(Long id) {
        repositorio.deleteById(id);
    }

    public Alimento obterAlimentoPorId(Long id) {
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("Alimento não encontrado para o ID :: " + id));
    }
}
