package atividade;

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
        return 0;
    }

    @Override
    public int getPrazoDevolucaoDias() {
        return 0;
    }

    @Override
    public double getValorMultaDiaria() {
        return 0;
    }

    @Override
    public String getTipoUsuario() {
        return "";
    }

    @Override
    public String exibirResumo() {
        return "Tipo: " + getTipoUsuario() +
                ", Nome: " + getNome() +
                ", Email: " + getEmail() +
                ", Limite: " + getLimiteEmprestimos();
    }
}
