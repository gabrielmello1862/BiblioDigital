package br.edu.atitus.biblioDigital.views;

import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.entities.LivroFisico;
import br.edu.atitus.biblioDigital.services.LivroService;

import java.util.ArrayList;
import java.util.List;

public class BiblioDigitalApplication {
    private final LivroService service = new LivroService();
    void main() {
        try {
            List<Livro> livros = new ArrayList<>();
            int opcao;
            String menu = """
        ========== BIBLIODIGITAL ==========
        1 - Cadastrar novo Livro
        2 - Pesquisar Livro por título
        3 - Pesquisar Livro por autor
        4 - Alterar dados do Livro
        5 - Excluir Livro
        0 - Sair
        ===================================
        """;
            do {
                IO.println(menu);

                opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));

                String titulo = IO.readln("digite um titulo: ");
                Livro novo = new LivroFisico(titulo, null, 0, 0, null);
                service.inserir(novo);
                List<Livro> all = service.findAll();
                for (Livro livro : all) {
                    IO.println(livro);
                }

                String pesquisa = IO.readln("pesquisa por titulo: ");
                List<Livro> livrosEncontrados = service.findByTitulo(pesquisa);
                if (livrosEncontrados.isEmpty())
                    IO.println("nenhum livro encontrado");
                else {
                    for (Livro livro : livrosEncontrados) {
                        IO.println(livro);
                    }
                }

            } while (opcao != 0);

        } catch (Exception ex) {
            IO.println("erro: " + ex.getMessage());
        }
    }
}
