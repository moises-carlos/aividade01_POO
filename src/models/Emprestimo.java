package src.models;
import src.interfaces.CalculavelMulta;
import src.interfaces.Exibivel;
import src.models.materiais.Material;
import src.models.usuarios.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Emprestimo implements Exibivel, CalculavelMulta {

    private int id;
    private Usuario usuario;
    private Material material;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataRealDevolucao;

    public Emprestimo(int id, Usuario usuario, Material material) {
        this.id = id;
        this.usuario = usuario;
        this.material = material;
        dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao =
                dataEmprestimo.plusDays(usuario.getPrazoDevolucaoDias());
        material.reduzirQuantidade();
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public LocalDate getDataRealDevolucao() {
        return dataRealDevolucao;
    }

    public void setDataRealDevolucao(LocalDate dataRealDevolucao) {
        this.dataRealDevolucao = dataRealDevolucao;
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {
        if (isFinalizado()) {
            throw new IllegalStateException("Empréstimo já foi devolvido!");
        }
        this.dataRealDevolucao = dataDevolucao;
        this.material.aumentarQuantidade();
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