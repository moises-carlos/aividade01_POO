package atividade;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Emprestimo implements Exibivel, CalculavelMulta {

    private int id;
    private Usuario usuario;
    private Material material;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;

    public Emprestimo(int id, Usuario usuario, Material material,
                      LocalDate dataEmprestimo) {
        this.id = id;
        this.usuario = usuario;
        this.material = material;
        this.dataEmprestimo = dataEmprestimo;

        this.dataPrevistaDevolucao =
                dataEmprestimo.plusDays(usuario.getPrazoDevolucaoDias());
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        this.dataRealDevolucao = dataDevolucao;
    }

    public boolean isFinalizado() {
        return dataRealDevolucao != null;
    }

    public long calcularDiasAtraso() {
        if (!isFinalizado()) return 0;

        if (dataRealDevolucao.isAfter(dataPrevistaDevolucao)) {
            return ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataRealDevolucao);
        }

        return 0;
    }

    @Override
    public double calcularMulta() {
        if (!isFinalizado()) return 0;
        return calcularDiasAtraso() * usuario.getValorMultaDiaria();
    }

    @Override
    public String exibirResumo() {
        return "Empréstimo ID: " + id +
                "\nUsuário: " + usuario.getNome() +
                "\nMaterial: " + material.getTitulo() +
                "\nData empréstimo: " + dataEmprestimo +
                "\nData prevista: " + dataPrevistaDevolucao +
                "\nData devolução: " + dataRealDevolucao +
                "\nDias de atraso: " + calcularDiasAtraso() +
                "\nMulta: R$ " + calcularMulta();
    }
}