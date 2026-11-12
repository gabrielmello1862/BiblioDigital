package br.edu.atitus.biblioDigital.repositories;

import br.edu.atitus.biblioDigital.entities.Livro;


import java.util.ArrayList;
import java.util.List;


public class LivroRepository {
    private List<Livro> acervo = new ArrayList<>();

    public List<Livro> findAll() {
        return acervo;
    }

    public void inserir(Livro livro) {
        acervo.add(livro);
    }

    public List<Livro> findByTitulo(String parametro) {
        List<Livro> resultado = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toUpperCase().contains(parametro.toUpperCase())) {
                resultado.add(livro);
            }
        }
        return resultado;
    }

    public List<Livro> findByAutor(String autor) {
        List<Livro> result = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().toUpperCase().contains(autor.toUpperCase())) {
                result.add(livro);
                    }


        }
        return result;
    }
}