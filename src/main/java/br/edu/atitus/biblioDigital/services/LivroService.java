package br.edu.atitus.biblioDigital.services;

import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.repositories.LivroRepository;
import java.util.List;

public class LivroService {
    private final LivroRepository repository = new LivroRepository();

    private void validar(Livro livro) {
        if (livro == null)
            throw new RuntimeException("Objeto nulo");
        if (livro.getTitulo() == null || livro.getTitulo().isBlank())
            throw new RuntimeException("Título inválido");
        if (livro.getAutor() == null || livro.getAutor().isBlank())
            throw new RuntimeException("Autor inválido");

        livro.setTitulo(livro.getTitulo().trim());
        livro.setAutor(livro.getAutor().trim());
    }

    public void inserir(Livro livro) {
        validar(livro);
        repository.inserir(livro);
    }

    public void alterar(Livro livro) {
        validar(livro);
    }

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public List<Livro> findByTitulo(String parametro) {
        if (parametro == null || parametro.isBlank())
            throw new RuntimeException("Parâmetro inválido");
        return repository.findByTitulo(parametro);
    }

    public List<Livro> findByAutor(String autor) {
        if (autor == null || autor.isBlank())
            throw new RuntimeException("Autor inválido");
        return repository.findByAutor(autor);
    }
}