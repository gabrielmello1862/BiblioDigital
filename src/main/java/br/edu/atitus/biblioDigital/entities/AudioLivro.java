package br.edu.atitus.biblioDigital.entities;

public class AudioLivro extends LivroDigital {
    private int duracao;
    private String narrador;

    public AudioLivro(String titulo, String autor, int anoPublicacao, String formato, double tamanhoArquivo, int duracao, String narrador) {
        super(titulo, autor, anoPublicacao, formato, tamanhoArquivo);
        this.duracao = duracao;
        this.narrador = narrador;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getNarrador() {
        return narrador;
    }

    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDuração: %d minutos\nNarrador: %s".formatted(duracao, narrador);
    }
}