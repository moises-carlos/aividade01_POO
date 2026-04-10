package src.models.materiais;

public class Revista extends Material {
    private String edicao;

    public Revista(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String edicao) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.edicao = edicao;
    }

    public String getEdicao() { return edicao; }

    @Override
    public String getDetalhesEspecificos() {
        return "Edição: " + edicao;
    }

    @Override
    public String exibirResumo() {
        return "[REVISTA] " + getTitulo() + " - " + getDetalhesEspecificos() + " | Qtd: " + getQuantidadeDisponivel();
    }
}