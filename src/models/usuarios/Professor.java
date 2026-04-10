package src.models.usuarios;

public class Professor extends Usuario{
    private int maxEmprestimos = 5;
    private int prazoDias = 14;
    private double multaPorDia = 1.00;

    public Professor(int codigo, String nome, String email, int maxEmprestimos, int prazoDias, double multaPorDia) {
        super(codigo, nome, email);
        this.maxEmprestimos = maxEmprestimos;
        this.prazoDias = prazoDias;
        this.multaPorDia = multaPorDia;
    }

    @Override
    public int getLimiteEmprestimos() {
        return maxEmprestimos;
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return prazoDias;
    }

    @Override
    public double getValorMultaDiaria() {
        return multaPorDia;
    }

    @Override
    public String getTipoUsuario() {
        return "Professor";
    }

    @Override
    public String exibirResumo() {
        return "Tipo: " + getTipoUsuario() +
                ", Nome: " + getNome() +
                ", Email: " + getEmail() +
                ", Limite: " + getLimiteEmprestimos();
    }
}
