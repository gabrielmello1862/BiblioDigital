package br.edu.atitus.biblioDigital.entities;

public class LivroFisico extends Livro {
    private int quantidadeExemplares;
    private String localizacao;

    public LivroFisico(String titulo, String autor, int anoPublicacao, int quantidadeExemplares, String localizacao) {
        super(titulo, autor, anoPublicacao);
        this.quantidadeExemplares = quantidadeExemplares;
        this.localizacao = localizacao;
    }

    public int getQuantidadeExemplares() {
        return quantidadeExemplares;
    }

    public void setQuantidadeExemplares(int quantidadeExemplares) {
        this.quantidadeExemplares = quantidadeExemplares;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return super.toString() + "\nQuantidade de Exemplares: %d\nLocalização: %s".formatted(quantidadeExemplares, localizacao);
    }
}