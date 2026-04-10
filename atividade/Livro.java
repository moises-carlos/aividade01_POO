package atividade;

public class Livro extends Material {
    private String autor;

    public Livro(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String autor) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.autor = autor;
    }

    public String getAutor() { return autor; }

    @Override
    public String getDetalhesEspecificos() {
        return "Autor: " + autor;
    }

    @Override
    public String exibirResumo() {
        return "[LIVRO] " + getTitulo() + " (" + getAnoPublicacao() + ") - " + getDetalhesEspecificos() + " | Qtd: " + getQuantidadeDisponivel();
    }
}