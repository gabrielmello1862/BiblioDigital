package br.edu.atitus.biblioDigital.utils;

import br.edu.atitus.biblioDigital.entities.AudioLivro;
import br.edu.atitus.biblioDigital.entities.Livro;
import br.edu.atitus.biblioDigital.entities.LivroDigital;
import br.edu.atitus.biblioDigital.entities.LivroFisico;
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

    public static void cadastrarLivro(LivroService service) {
        IO.println("""
                
                Qual tipo de livro deseja cadastrar?
                1 - Físico
                2 - Digital
                3 - Audiolivro""");

        int tipo = Integer.parseInt(IO.readln("Escolha uma opção: "));

        if (tipo < 1 || tipo > 3) {
            IO.println("Opção de tipo de livro inválida!\n");
            return;
        }

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
            for (Livro livro : autoresEncontrados) {
                IO.println("------------------------------------");
                IO.println(livro);
            }
            IO.println("------------------------------------");
            IO.readln("Pressione enter para continuar");
        }
    }

    public static void alterarLivro(LivroService service) {
        List<Livro> livros = service.findAll();

        if (livros.isEmpty()) {
            IO.println("Nenhum livro cadastrado no acervo.");
            return;
        }

        IO.println("======= Livros cadastrados =======");
        for (int i = 0; i < livros.size(); i++) {
            IO.println((i + 1) + " - " + livros.get(i).getTitulo());
        }

        int livroEscolhido = Integer.parseInt(IO.readln("Informe o numero do livro que deseja alterar: "));

        if (livroEscolhido < 1 || livroEscolhido > livros.size()) {
            IO.println("Numero Invalido!!");
            return;
        }

        Livro livroSelecionado = livros.get(livroEscolhido - 1);
        IO.println("\nDados atuais do livro:");
        IO.println(livroSelecionado);

        String novoTitulo = IO.readln("Digite o novo titulo: ");
        String novoAutor = IO.readln("Digite o novo autor: ");
        int novoAno = Integer.parseInt(IO.readln("Digite o novo ano de publicação: "));

        livroSelecionado.setTitulo(novoTitulo);
        livroSelecionado.setAutor(novoAutor);
        livroSelecionado.setAnoPublicacao(novoAno);

        if (livroSelecionado instanceof LivroFisico) {
            int novoExemplar = Integer.parseInt(IO.readln("Digite a nova quantidade de exemplares: "));
            String novaLoc = IO.readln("Digite a nova localização na biblioteca: ");

            ((LivroFisico) livroSelecionado).setQuantidadeExemplares(novoExemplar);
            ((LivroFisico) livroSelecionado).setLocalizacao(novaLoc);

        } else if (livroSelecionado instanceof AudioLivro) {
            String novoFormato = IO.readln("Digite o novo formato do áudio (ex: MP3, M4B): ");
            double novoTamanho = Double.parseDouble(IO.readln("Digite o novo tamanho em MB: "));
            int novaDuracao = Integer.parseInt(IO.readln("Digite a nova duração em minutos: "));
            String novoNarrador = IO.readln("Digite o novo nome do narrador: ");

            ((AudioLivro) livroSelecionado).setFormato(novoFormato);
            ((AudioLivro) livroSelecionado).setTamanhoArquivo(novoTamanho);
            ((AudioLivro) livroSelecionado).setDuracao(novaDuracao);
            ((AudioLivro) livroSelecionado).setNarrador(novoNarrador);

        } else if (livroSelecionado instanceof LivroDigital) {
            String novoFormato = IO.readln("Digite o novo formato do arquivo (ex: PDF, EPUB): ");
            double novoTamanho = Double.parseDouble(IO.readln("Digite o novo tamanho em MB: "));

            ((LivroDigital) livroSelecionado).setFormato(novoFormato);
            ((LivroDigital) livroSelecionado).setTamanhoArquivo(novoTamanho);
        }

        service.alterar(livroSelecionado);
        IO.println("Livro alterado com sucesso!\n");
    }
}