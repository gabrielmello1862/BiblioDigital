package br.edu.atitus.biblioDigital.services;

import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.repositories.LivroRepository;

import java.util.List;

public class LivroService {
    private final LivroRepository repository = new LivroRepository();

    public void inserir (Livro livro) {
        if (livro == null)
            throw new RuntimeException("objeto nulo");
        if (livro.getTitulo() == null || livro.getTitulo().isBlank())
            throw new RuntimeException("titulo invalido");
        livro.setTitulo(livro.getTitulo().trim());
        repository.inserir(livro);

    }
    public List<Livro> findAll() {
        return repository.findAll();
    }

    public List<Livro> findByTitulo (String parametro) {
        if (parametro == null || parametro.isBlank())
            throw new RuntimeException("parametro invalido");
        return repository.findByTitulo(parametro);
    }
    public List<Livro> findByAutor (String autor) {
        if (autor == null || autor.isBlank())
            throw new RuntimeException("autor inválido");
        return repository.findByAutor(autor);
    }
}
