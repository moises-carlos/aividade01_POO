package src.models.materiais;

public class Ebook extends Material {
    private String formato;
    private double tamanhoArquivo; // em MB

    public Ebook(int codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String formato, double tamanhoArquivo) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.formato = formato;
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormato() { return formato; }
    public double getTamanhoArquivo() { return tamanhoArquivo; }

    @Override
    public String getDetalhesEspecificos() {
        return "Formato: " + formato + " | Tamanho: " + tamanhoArquivo + "MB";
    }

    @Override
    public String exibirResumo() {
        return "[EBOOK] " + getTitulo() + " - " + getDetalhesEspecificos();
    }
}