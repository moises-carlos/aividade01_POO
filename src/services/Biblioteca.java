package src.services;

import src.models.Emprestimo;
import src.models.materiais.Material;
import src.models.usuarios.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Material> materiais = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.materiais = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public Biblioteca(List<Usuario> usuarios, List<Material> materiais, List<Emprestimo> emprestimos) {
        this.usuarios = usuarios;
        this.materiais = materiais;
        this.emprestimos = emprestimos;
    }

    public void cadastrarUsuario(Usuario usuario){
        if (usuario == null){
            throw new IllegalArgumentException("Usuário não pode ser nulo!");
        }
        usuarios.add(usuario);
    }

    public void cadastrarMaterial(Material material){
        if (material == null){
            throw new IllegalArgumentException("Material não pode ser nulo!");
        }
        materiais.add(material);
    }

    public void realizarEmprestimo(int id, Usuario usuario, Material material){
        if (usuario == null || material == null){
            throw new IllegalArgumentException("Usuário ou material inexistente");
        }

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getId() == id) {
                throw new IllegalArgumentException("Já existe um empréstimo com esse ID");
            }
        }

        if (!material.verificarDisponibilidade()) {
            throw new IllegalStateException("Material sem exemplar disponível!");
        }

        int emprestimosAtivos = contarEmprestimosAtivos(usuario);
        if (emprestimosAtivos >= usuario.getLimiteEmprestimos()) {
            throw new IllegalStateException("Limite de empréstimos atingido para este usuário!");
        }
        emprestimos.add( new Emprestimo(id, usuario, material));
    }

    public Usuario buscarUsuario(int codigo){
        for (Usuario usuario : usuarios){
            if (usuario.getCodigo() == codigo){
                return usuario;
            }
        }
        throw new IllegalArgumentException("Usuário não encontrado");
    }

    public Material buscarMaterial(int codigo){
        for (Material material : materiais){
            if (material.getCodigo() == codigo){
                return material;
            }
        }
        throw new IllegalArgumentException("Material não encontrado");
    }

    public Emprestimo buscarEmprestimo(int id){
        for (Emprestimo emprestimo : emprestimos){
            if (emprestimo.getId() == id){
                return emprestimo;
            }
        }
        throw new IllegalArgumentException("Empréstimo não encontrado");
    }

   public int contarEmprestimosAtivos(Usuario usuario) {
        int contador = 0;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario) && !emprestimo.isFinalizado()) {
                contador++;
            }
        }
        return contador;
    }

    public void registrarDevolucao(int id){
        registrarDevolucao(id, LocalDate.now());
    }

    public void registrarDevolucao(int id, LocalDate dataDevolucao){
        Emprestimo emprestimo = buscarEmprestimo(id);
        emprestimo.registrarDevolucao(dataDevolucao);
    }

    public void listarUsuarios(){
        if (usuarios.isEmpty()){
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios){
            System.out.println(usuario.exibirResumo());
        }
    }
    public void listarMaterias(){
        if(materiais.isEmpty()){
            System.out.println("Nenhum material encontrado");
        }
        for (Material material : materiais){
            System.out.println(material.exibirResumo());
        }
    }

    public void listarEmprestimosEmAndamento(){
       for(Emprestimo emprestimo : emprestimos){
           if (emprestimo.getDataRealDevolucao() == null){
               System.out.println(emprestimo.exibirResumo());
           }
       }
    }
    public void listarEmprestimosFinalizados(){
        for (Emprestimo emprestimo : emprestimos){
            if (emprestimo.isFinalizado()){
                System.out.println(emprestimo.exibirResumo());
            }
        }
    }

    public void contarEmprestimosAtivosUsuario(Usuario usuario) {
        int total = 0;

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario) && !emprestimo.isFinalizado()){
                total++;
            }
        }
        System.out.println("Empréstimos ativos: " + total);
    }








}
