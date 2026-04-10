package src.models.usuarios;

public class Aluno extends Usuario{
    private int maxEmprestimos = 3;
    private int prazoDias = 7;
    private double multaPorDia = 2.50;

    public Aluno(int codigo, String nome, String email, int maxEmprestimos, int prazoDias, double multaPorDia) {
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
        return "src.models.usuarios.Aluno";
    }
    @Override
    public String exibirResumo() {
        return "Tipo: " + getTipoUsuario() +
                ", Nome: " + getNome() +
                ", Email: " + getEmail() +
                ", Limite: " + getLimiteEmprestimos();
    }
}
