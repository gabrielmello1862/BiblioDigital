package br.edu.atitus.biblioDigital.views;

import br.edu.atitus.biblioDigital.entities.AudioLivro;
import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.entities.LivroDigital;
import br.edu.atitus.biblioDigital.entities.LivroFisico;
import br.edu.atitus.biblioDigital.services.LivroService;
import br.edu.atitus.biblioDigital.utils.BiblioUtils;

import java.util.ArrayList;
import java.util.List;

public class BiblioDigitalApplication {
    private final LivroService service = new LivroService();

    void main() {
        try {
            List<Livro> livros = new ArrayList<>();
            int opcao;

            do {
                IO.println(BiblioUtils.MENU);
                opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));

                switch (opcao) {
                    case 1 -> {
                        IO.println("\nQual tipo de livro deseja cadastrar?");
                        IO.println("1 - Físico");
                        IO.println("2 - Digital");
                        IO.println("3 - Audiolivro");
                        int tipo = Integer.parseInt(IO.readln("Escolha uma opção: "));
                        String titulo = IO.readln("Digite o título: ");
                        String autor = IO.readln("Digite o autor: ");
                        int ano = Integer.parseInt(IO.readln("Digite o ano de publicação: "));
                        Livro novoLivro = null;

                        switch (tipo) {
                            case 1 -> {
                                int exemplares = Integer.parseInt(IO.readln("Digite a quantidade de exemplares: "));
                                String localizacao = IO.readln("Digite a localização na biblioteca: ");
                                novoLivro = new LivroFisico(titulo, autor, ano, exemplares, localizacao);
                            }
                            case 2 -> {
                                String formato = IO.readln("Digite o formato do arquivo (ex: PDF, EPUB): ");
                                double tamanhoMB = Double.parseDouble(IO.readln("Digite o tamanho do arquivo em MB: "));
                                novoLivro = new LivroDigital(titulo, autor, ano, formato, tamanhoMB);
                            }
                            case 3 -> {
                                String formato = IO.readln("Digite o formato do áudio (ex: MP3, M4B): ");
                                double tamanhoMB = Double.parseDouble(IO.readln("Digite o tamanho do arquivo em MB: "));
                                int duracao = Integer.parseInt(IO.readln("Digite a duração em minutos: "));
                                String narrador = IO.readln("Digite o nome do narrador: ");
                                novoLivro = new AudioLivro(titulo, autor, ano, formato, tamanhoMB, duracao, narrador);
                            }
                        }

                        if (novoLivro != null) {
                            service.inserir(novoLivro);
                            IO.println("Cadastrado com sucesso!\n");
                        }
                    }
                    case 2 -> { BiblioUtils.pesquisarLivroPorTitulo(service);}
                    case 3 -> {BiblioUtils.pesquisarLivroPorAutor(service);}
                    case 4 -> {



                    }
                    case 0 -> IO.println("Saindo do sistema...");
                    default -> IO.println("Opção inválida!");
                }

            } while (opcao != 0);

        } catch (Exception ex) {
            IO.println("Erro: " + ex.getMessage());
        }
    }
}