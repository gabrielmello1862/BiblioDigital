package br.edu.atitus.biblioDigital.entities;

public class LivroDigital extends Livro {
    private String formato;
    private double tamanhoArquivo;

    public LivroDigital(String titulo, String autor, int anoPublicacao, String formato, double tamanhoArquivo) {
        super(titulo, autor, anoPublicacao);
        this.formato = formato;
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    @Override
    public String toString() {
        return super.toString() + "\nFormato: %s\nTamanho: %.2f MB".formatted(formato, tamanhoArquivo);
    }
}