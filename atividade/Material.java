package atividade;

    public abstract class Material implements Exibivel {
    private int codigo;
    private String titulo;
    private int anoPublicacao;
    private int quantidadeDisponivel;

    public Material(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public int getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public int getAnoPublicacao() { return anoPublicacao; }
    public int getQuantidadeDisponivel() { return quantidadeDisponivel; }

    public boolean verificarDisponibilidade() {
        return this.quantidadeDisponivel > 0;
    }

    public void reduzirQuantidade() {
        if (verificarDisponibilidade()) {
            this.quantidadeDisponivel--;
        }
    }

    public void aumentarQuantidade() {
        this.quantidadeDisponivel++;
    }

    public abstract String getDetalhesEspecificos();
}

