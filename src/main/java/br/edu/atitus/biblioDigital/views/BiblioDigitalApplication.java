package br.edu.atitus.biblioDigital.views;


import br.edu.atitus.biblioDigital.services.LivroService;
import br.edu.atitus.biblioDigital.utils.BiblioUtils;

import java.util.ArrayList;
import java.util.List;

import static br.edu.atitus.biblioDigital.utils.BiblioUtils.alterarLivro;

public class BiblioDigitalApplication {
    private final LivroService service = new LivroService();

    void main() {
        try {
            int opcao;

            do {
                IO.println(BiblioUtils.MENU);
                opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));

                switch (opcao) {
                    case 1 -> BiblioUtils.cadastrarLivro(service);
                    case 2 ->  BiblioUtils.pesquisarLivroPorTitulo(service);
                    case 3 -> BiblioUtils.pesquisarLivroPorAutor(service);
                    case 4 -> alterarLivro(service);
                    case 5 -> {}
                    case 0 -> IO.println("Saindo do sistema...");
                    default -> IO.println("Opção inválida!");
                }

            } while (opcao != 0);

        } catch (Exception ex) {
            IO.println("Erro: " + ex.getMessage());
        }
    }
}