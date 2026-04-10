package src.biblioteca;

import src.models.Emprestimo;
import src.models.materiais.Ebook;
import src.models.materiais.Livro;
import src.models.materiais.Revista;
import src.models.usuarios.Aluno;
import src.models.usuarios.Professor;
import src.services.Biblioteca;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1: cadastrarAluno(); break;
                    case 2: cadastrarProfessor(); break;
                    case 3: cadastrarLivro(); break;
                    case 4: cadastrarRevista(); break;
                    case 5: cadastrarEbook(); break;
                    case 6: listarTudo(); break;
                    case 7: realizarEmprestimo(); break;
                    case 8: registrarDevolucao(); break;
                    case 9: exibirSituacaoFinal(); break;
                    case 10: executarSimulacaoPadrao(); break;
                    case 0: System.out.println("Encerrando o sistema..."); break;
                    default: System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTÃO DE BIBLIOTECA");
        System.out.println("========================================");
        System.out.println("1.  Cadastrar Aluno");
        System.out.println("2.  Cadastrar Professor");
        System.out.println("3.  Cadastrar Livro");
        System.out.println("4.  Cadastrar Revista");
        System.out.println("5.  Cadastrar Ebook");
        System.out.println("6.  Listar Usuários e Materiais");
        System.out.println("7.  Realizar Empréstimo");
        System.out.println("8.  Registrar Devolução (com/sem atraso)");
        System.out.println("9.  Relatório de Situação Final");
        System.out.println("10. Executar Simulação Completa (Dados do Roteiro)");
        System.out.println("0.  Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno() {
        System.out.println("\n--- Cadastro de Aluno ---");
        System.out.print("Código: ");
        int cod = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        biblioteca.cadastrarUsuario(new Aluno(cod, nome, email, 3, 7, 2.50));
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void cadastrarProfessor() {
        System.out.println("\n--- Cadastro de Professor ---");
        System.out.print("Código: ");
        int cod = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        biblioteca.cadastrarUsuario(new Professor(cod, nome, email, 5, 14, 1.00));
        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void cadastrarLivro() {
        System.out.println("\n--- Cadastro de Livro ---");
        System.out.print("Código: ");
        int cod = Integer.parseInt(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(scanner.nextLine());
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        biblioteca.cadastrarMaterial(new Livro(cod, titulo, ano, qtd, autor));
        System.out.println("Livro cadastrado!");
    }

    private static void cadastrarRevista() {
        System.out.println("\n--- Cadastro de Revista ---");
        System.out.print("Código: ");
        int cod = Integer.parseInt(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Quantidade: ");
        int qtd = Integer.parseInt(scanner.nextLine());
        System.out.print("Edição: ");
        String edicao = scanner.nextLine();
        biblioteca.cadastrarMaterial(new Revista(cod, titulo, ano, qtd, edicao));
        System.out.println("Revista cadastrada!");
    }

    private static void cadastrarEbook() {
        System.out.println("\n--- Cadastro de Ebook ---");
        System.out.print("Código: ");
        int cod = Integer.parseInt(scanner.nextLine());
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Formato: ");
        String formato = scanner.nextLine();
        System.out.print("Tamanho (MB): ");
        double tam = Double.parseDouble(scanner.nextLine());
        biblioteca.cadastrarMaterial(new Ebook(cod, titulo, ano, 100, formato, tam));
        System.out.println("Ebook cadastrado!");
    }

    private static void listarTudo() {
        System.out.println("\n--- LISTAGEM DE CADASTROS ---");
        System.out.println("USUÁRIOS:");
        biblioteca.listarUsuarios();
        System.out.println("\nMATERIAIS:");
        biblioteca.listarMaterias();
    }

    private static void realizarEmprestimo() {
        System.out.println("\n--- Novo Empréstimo ---");
        System.out.print("ID do Empréstimo: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Código do Usuário: ");
        int codUser = Integer.parseInt(scanner.nextLine());
        System.out.print("Código do Material: ");
        int codMat = Integer.parseInt(scanner.nextLine());

        try {
            biblioteca.realizarEmprestimo(id, biblioteca.buscarUsuario(codUser), biblioteca.buscarMaterial(codMat));
            System.out.println("Empréstimo realizado com sucesso!");
        } catch (Exception e) {
            System.out.println("REGRA DE NEGÓCIO: " + e.getMessage());
        }
    }

    private static void registrarDevolucao() {
        System.out.println("\n--- Registrar Devolução ---");
        System.out.print("ID do Empréstimo: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Simular quantos dias de atraso? (0 para no prazo): ");
        int dias = Integer.parseInt(scanner.nextLine());

        try {
            Emprestimo emp = biblioteca.buscarEmprestimo(id);
            LocalDate dataDevolucao = emp.getDataPrevistaDevolucao().plusDays(dias);
            
            if (dias <= 0) dataDevolucao = emp.getDataPrevistaDevolucao();

            biblioteca.registrarDevolucao(id, dataDevolucao);
            System.out.println("Devolução registrada!");
            if (emp.calcularMulta() > 0) {
                System.out.println("MULTA POR ATRASO: R$ " + String.format("%.2f", emp.calcularMulta()));
            } else {
                System.out.println("Devolução sem atraso. Multa: R$ 0,00");
            }
        } catch (Exception e) {
            System.out.println("REGRA DE NEGÓCIO: " + e.getMessage());
        }
    }

    private static void exibirSituacaoFinal() {
        System.out.println("\n========================================");
        System.out.println("           SITUAÇÃO FINAL");
        System.out.println("========================================");
        System.out.println("MATERIAIS ATUALIZADOS:");
        biblioteca.listarMaterias();
        System.out.println("\nEMPRÉSTIMOS FINALIZADOS:");
        biblioteca.listarEmprestimosFinalizados();
        System.out.println("\nEMPRÉSTIMOS EM ANDAMENTO:");
        biblioteca.listarEmprestimosEmAndamento();
    }

    private static void executarSimulacaoPadrao() {
        System.out.println("\n--- Iniciando Simulação Automática ---");
        try {
            Aluno ana = new Aluno(101, "Ana Silva", "ana@email.com", 3, 7, 2.50);
            Professor carlos = new Professor(201, "Carlos Oliveira", "carlos@email.com", 5, 14, 1.00);
            biblioteca.cadastrarUsuario(ana);
            biblioteca.cadastrarUsuario(carlos);

            Livro l1 = new Livro(1, "Java Básico", 2023, 2, "Gerson");
            Livro l2 = new Livro(2, "Estruturas de Dados", 2022, 1, "Clairton");
            Revista r1 = new Revista(3, "Revista Tech", 2024, 1, "Edição 42");
            Ebook e1 = new Ebook(4, "POO em Java", 2024, 10, "PDF", 5.5);
            biblioteca.cadastrarMaterial(l1);
            biblioteca.cadastrarMaterial(l2);
            biblioteca.cadastrarMaterial(r1);
            biblioteca.cadastrarMaterial(e1);

            biblioteca.realizarEmprestimo(1001, ana, l1);
            biblioteca.realizarEmprestimo(1002, ana, l2);
            biblioteca.realizarEmprestimo(1003, ana, r1);
            biblioteca.realizarEmprestimo(1004, carlos, e1);

            System.out.println("Usuários e materiais cadastrados.");
            System.out.println("4 empréstimos iniciais realizados.");
            System.out.println("Simulação de dados concluída!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
