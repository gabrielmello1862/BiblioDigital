package br.edu.atitus.biblioDigital.utils;

import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.services.LivroService;
import java.util.List;

public class BiblioUtils {

    public static final String MENU = """
            ========== BIBLIODIGITAL ==========
            1 - Cadastrar novo Livro
            2 - Pesquisar Livro por título
            3 - Pesquisar Livro por autor
            4 - Alterar dados do Livro
            5 - Excluir Livro
            0 - Sair
            ===================================
            """;

    public static void pesquisarLivroPorTitulo(LivroService service) {
        String pesquisa = IO.readln("Pesquisa por título: ");
        List<Livro> livrosEncontrados = service.findByTitulo(pesquisa);

        if (livrosEncontrados.isEmpty()) {
            IO.println("Nenhum livro encontrado");
        } else {
            for (Livro livro : livrosEncontrados) {
                IO.println("------------------------------------");
                IO.println(livro);
            }
            IO.println("------------------------------------");
            IO.readln("Pressione enter para continuar");
        }
    }

    public static void pesquisarLivroPorAutor(LivroService service) {
        String pesquisa = IO.readln("Pesquisa por autor: ");
        List<Livro> autoresEncontrados = service.findByAutor(pesquisa);

        if (autoresEncontrados.isEmpty()) {
            IO.println("Nenhum autor encontrado");
        } else {
            IO.println("------------------------------------");
            for (Livro livro : autoresEncontrados) {
                IO.println("------------------------------------");
                IO.println(livro);
            }
            IO.println("------------------------------------");
            IO.readln("Pressione enter para continuar");
        }
    }
}