import br.edu.atitus.biblioDigital.entities.AudioLivro;
import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.entities.LivroDigital;
import br.edu.atitus.biblioDigital.entities.LivroFisico;
import br.edu.atitus.biblioDigital.services.LivroService;

import java.util.ArrayList;
import java.util.List;
private final LivroService service = new LivroService();
void main() {
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

        switch (opcao) {
            case 1 -> {
    IO.println("\nQual tipo de livro deseja cadastrar?");
    IO.println("1 - Físico");
    IO.println("2 - Digital");
    IO.println("3 - Audiolivro");

    int tipo = Integer.parseInt(IO.readln("Escolha uma opção: "));

    // Validação inicial para não pedir dados se o tipo for inexistente
    if (tipo < 1 || tipo > 3) {
        IO.println("Opção de tipo de livro inválida!\n");
    } else {
        // Dados comuns a todos os livros
        String titulo = IO.readln("Digite o título: ");
        String autor = IO.readln("Digite o autor: ");
        int ano = Integer.parseInt(IO.readln("Digite o ano de publicação: "));

        Livro novoLivro = null;

        // Dados específicos conforme o tipo escolhido
        if (tipo == 1) {
            int exemplares = Integer.parseInt(IO.readln("Digite a quantidade de exemplares: "));
            String localizacao = IO.readln("Digite a localização na biblioteca: ");
            novoLivro = new LivroFisico(titulo, autor, ano, exemplares, localizacao);
        
        
        } else if (tipo == 2) {
            String formato = IO.readln("Digite o formato do arquivo (ex: PDF, EPUB): ");
            double tamanhoMB = Double.parseDouble(IO.readln("Digite o tamanho do arquivo em MB: "));
            novoLivro = new LivroDigital(titulo, autor, ano, formato, tamanhoMB);
        
        
        } else if (tipo == 3) {
            String formato = IO.readln("Digite o formato do áudio (ex: MP3, M4B): ");
            double tamanhoMB = Double.parseDouble(IO.readln("Digite o tamanho do arquivo em MB: "));
            int duracao = Integer.parseInt(IO.readln("Digite a duração em minutos: "));
            String narrador = IO.readln("Digite o nome do narrador: ");
            novoLivro = new AudioLivro(titulo, autor, ano, formato, tamanhoMB, duracao, narrador);
}          // Guarda o livro instanciado na lista
        if (novoLivro != null) {
            livros.add(novoLivro);
            IO.println("br.edu.atitus.biblioDigital.entities.Livro cadastrado com sucesso!\n");
        }
    }
}
                
            
            case 2 -> {
        String pesquisa = IO.readln("pesquisa por titulo: ");
                LivroService service;
                List<Livro> livrosEncontrados = service.findByTitulo(pesquisa);
        if (livrosEncontrados.isEmpty())
            IO.println("nenhum livro encontrado");
        else {
            for (Livro livro : livrosEncontrados) {
                IO.println(livro);
            }
        }
                
            }
            case 3 -> {
                String pesquisa = IO.readln("pesquisa por autor: ");
                LivroService service;
                List<Livro> autoresEncontrados = service.findByAutor(pesquisa);
                if (autoresEncontrados.isEmpty())
                    IO.println("nenhum autor encontrado");
                else {
                    for (Livro livro : autoresEncontrados) {
                        IO.println(livro);
                    }
                }

            }
            case 0 -> {
                IO.println("Encerrando o sistema...");
            }
            default -> {
                IO.println("Opção inválida!");
            }
        }

    } while (opcao != 0);
}