package src.models.usuarios;

public abstract class Usuario implements Exibivel {
    private int codigo;
    private String nome;
    private String email;

    public Usuario(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }

    public abstract int getLimiteEmprestimos();
    public abstract int getPrazoDevolucaoDias();
    public abstract double getValorMultaDiaria();
    public abstract String getTipoUsuario();
}

