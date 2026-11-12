package br.edu.atitus.biblioDigital.services;

import br.edu.atitus.biblioDigital.entities.AudioLivro;
import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.entities.LivroDigital;
import br.edu.atitus.biblioDigital.entities.LivroFisico;
import br.edu.atitus.biblioDigital.repositories.LivroRepository;
import java.util.List;

public final class LivroService {
    private final LivroRepository repository = new LivroRepository();

    private void validarDados(Livro livro, Livro livroIgnoradoNaDuplicidade) {
        if (livro == null)
            throw new RuntimeException("O livro não pode ser nulo.");

        if (livro.getTitulo() == null || livro.getTitulo().isBlank())
            throw new RuntimeException("O título do livro não pode ser vazio.");

        if (livro.getAutor() == null || livro.getAutor().isBlank())
            throw new RuntimeException("O autor do livro não pode ser vazio.");

        if (livro.getAnoPublicacao() < 1900)
            throw new RuntimeException("O ano de publicação não pode ser inferior a 1900.");

        livro.setTitulo(livro.getTitulo().trim());
        livro.setAutor(livro.getAutor().trim());

        if (livro instanceof LivroFisico fisico) {
            if (fisico.getLocalizacao() == null || fisico.getLocalizacao().isBlank())
                throw new RuntimeException("A localização não pode ser vazia.");
            fisico.setLocalizacao(fisico.getLocalizacao().trim());
        } else if (livro instanceof LivroDigital digital) {
            if (digital.getFormato() == null || digital.getFormato().isBlank())
                throw new RuntimeException("O formato do arquivo não pode ser vazio.");
            digital.setFormato(digital.getFormato().trim());

            if (livro instanceof AudioLivro audio) {
                if (audio.getNarrador() == null || audio.getNarrador().isBlank())
                    throw new RuntimeException("O nome do narrador não pode ser vazio.");
                audio.setNarrador(audio.getNarrador().trim());
            }
        }

        for (Livro existente : repository.findAll()) {
            if (existente != livroIgnoradoNaDuplicidade &&
                    existente.getTitulo().equalsIgnoreCase(livro.getTitulo())) {
                throw new RuntimeException("Já existe um livro cadastrado com este título.");
            }
        }
    }

    public void inserir(Livro livro) {
        validarDados(livro, null);
        repository.inserir(livro);
    }

    public void alterar(int indice, Livro livroOriginal, Livro livroAtualizado) {
        validarDados(livroAtualizado, livroOriginal);
        repository.alterar(indice, livroAtualizado);
    }

    public void excluir(Livro livro) {
        if (livro == null)
            throw new RuntimeException("Livro inválido para exclusão.");
        repository.excluir(livro);
    }

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public List<Livro> findByTitulo(String parametro) {
        if (parametro == null || parametro.isBlank())
            throw new RuntimeException("Informe um título válido para a pesquisa.");
        return repository.findByTitulo(parametro.trim());
    }

    public List<Livro> findByAutor(String autor) {
        if (autor == null || autor.isBlank())
            throw new RuntimeException("Informe um autor válido para a pesquisa.");
        return repository.findByAutor(autor.trim());
    }
}