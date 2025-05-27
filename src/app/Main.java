package app;

import utilizadores.*;
import caixas.*;
import suporte.*;
import quiosques.*;
import areas.*;
import agendamentos.*;
import recibos.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.lang.reflect.Field;


public class Main {
    static List<Utilizador> utilizadores = new ArrayList<>();
    static List<Khikhipa> khikhipas = new ArrayList<>();
    static List<Khikhita> khikhitas = new ArrayList<>();
    static List<Khikhivi> khikhivis = new ArrayList<>();
    static List<TicketAgendamento> agendamentos = new ArrayList<>();
    static List<Recibo> recibos = new ArrayList<>();
    
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        carregarDados();

        System.out.println("===== Bem-vindo à Plataforma Khikhi =====");
        if (utilizadores.isEmpty()) {
            System.out.println("Nenhum utilizador encontrado. Por favor, adicione um primeiro.");
        } else {
            Utilizador utilizadorAtual = autenticarUtilizador();
            if (utilizadorAtual == null) {
                System.out.println("Autenticação falhada. A sair...");
                return;
            }
        }

        boolean sair = false;
        while (!sair) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("[1] Gestão de Utilizadores");
            System.out.println("[2] Gestão de Quiosques Khikhipa");
            System.out.println("[3] Gestão de Quiosques Khikhita");
            System.out.println("[4] Gestão de Quiosques Khikhivi");
            System.out.println("[5] Criar Agendamento Real");
            System.out.println("[6] Listar Agendamentos");
            System.out.println("[7] Emitir Recibo com base em Agendamento");
            System.out.println("[8] Listar Recibos");
            System.out.println("[9] Guardar dados");
            System.out.println("[10] Tabela de Preços");
            System.out.println("[11] Gerar Relatório de Recibos");
            System.out.println("[12] Gerar relatório de agendamentos (.txt)");
            System.out.println("[0] Sair");
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> menuUtilizadores();
                case "2" -> menuKhikhipa();
                case "3" -> menuKhikhita();
                case "4" -> menuKhikhivi();
                case "5" -> criarAgendamentoReal();
                case "6" -> listarAgendamentos();
                case "7" -> emitirReciboReal();
                case "8" -> listarRecibos();
                case "9" -> guardarDados();
                case "10" -> mostrarTabelaPrecos();
                case "11" -> gerarRelatorioRecibos();
                case "12" -> gerarRelatorioAgendamentos();
                case "0" -> sair = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static Utilizador autenticarUtilizador() {
        System.out.print("NIF: ");
        String nif = scanner.nextLine();

        for (Utilizador u : utilizadores) {
            if (u.getNif().equals(nif)) {
                System.out.println("Autenticação bem-sucedida. Bem-vindo, " + u.getNome() + "!");
                return u;
            }
        }

        System.out.println("NIF não encontrado. A sair...");
        return null;
    }

    // --------------------------Utilizadores--------------------------//
    /**
     * Menu de gestão de utilizadores.
     * Permite listar, adicionar, editar e remover utilizadores.
     */

    private static void menuUtilizadores() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu Utilizadores ---");
            System.out.println("[1] Listar utilizadores");
            System.out.println("[2] Adicionar utilizador");
            System.out.println("[3] Editar utilizador");
            System.out.println("[4] Remover utilizador");
            System.out.println("[5] Listar caixas do utilizador");
            System.out.println("[6] Adicionar cliente/parceiro");
            System.out.println("[7] Listar clientes/parceiros");
            System.out.println("[0] Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1" -> listarUtilizadores();
                case "2" -> adicionarUtilizador();
                case "3" -> editarUtilizador();
                case "4" -> removerUtilizador();
                case "5" -> listarCaixasDoUtilizador();
                case "6" -> adicionarClienteOuParceiro();
                case "7" -> listarClientesEParceiros();
                case "0" -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarUtilizadores() {
        if (utilizadores.isEmpty()) {
            System.out.println("Nenhum utilizador encontrado.");
            return;
        }
        for (Utilizador u : utilizadores) {
            System.out.println("- " + u.getNome() + " (NIF: " + u.getNif() + ")");
        }
    }

    private static void adicionarUtilizador() {
        String nif;
        while (true) {
            System.out.print("NIF (9 dígitos): ");
            nif = scanner.nextLine();

            // Validar formato
            if (!nif.matches("\\d{9}")) {
                System.out.println("NIF inválido. Deve conter exatamente 9 dígitos numéricos.");
                continue;
            }

            // Verificar duplicado
            boolean nifRepetido = false;
            for (Utilizador u : utilizadores) {
                if (u.getNif().equals(nif)) {
                    nifRepetido = true;
                    break;
                }
            }
            if (nifRepetido) {
                System.out.println("Já existe um utilizador com este NIF.");
                continue;
            }

            break;
        }
        String nome;
        while (true) {
            System.out.print("Nome: ");
            nome = scanner.nextLine().trim();
            if (nome.matches("^[A-Za-zÀ-ÿ][A-Za-zÀ-ÿ ]{1,}$")) {
                break;
            }
            System.out.println("Nome inválido. Deve começar por uma letra e conter apenas letras e espaços.");
        }
        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();

            // Validar formato
            if (!(email.contains("@") && email.indexOf('.') > email.indexOf('@'))) {
                System.out.println("Email inválido. Deve conter '@' e '.' após o '@'.");
                continue;
            }

            // Verificar duplicado
            boolean emailRepetido = false;
            for (Utilizador u : utilizadores) {
                if (u.getEmail().equalsIgnoreCase(email)) {
                    emailRepetido = true;
                    break;
                }
            }
            if (emailRepetido) {
                System.out.println("Já existe um utilizador com este email.");
                continue;
            }

            break;
        }
        String contacto;
        while (true) {
            System.out.print("Contacto (91/92/93/96 + 7 dígitos): ");
            contacto = scanner.nextLine();

            // Validar formato
            if (!contacto.matches("^(91|92|93|96)\\d{7}$")) {
                System.out.println("Contacto inválido. Deve ter 9 dígitos e começar por 91, 92, 93 ou 96.");
                continue;
            }

            // Verificar duplicado
            boolean contactoRepetido = false;
            for (Utilizador u : utilizadores) {
                if (u.getContacto().equals(contacto)) {
                    contactoRepetido = true;
                    break;
                }
            }
            if (contactoRepetido) {
                System.out.println("Já existe um utilizador com este contacto.");
                continue;
            }

            break;
        }
        System.out.print("Rua: ");
        String rua = scanner.nextLine();
        int porta;
        while (true) {
            System.out.print("Porta (número): ");
            String input = scanner.nextLine();
            try {
                porta = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Introduza apenas um número inteiro para a porta.");
            }
        }
        String codPostal;
        while (true) {
            System.out.print("Código Postal (formato NNNN-NNN): ");
            codPostal = scanner.nextLine();
            if (codPostal.matches("\\d{4}-\\d{3}")) {
                break;
            }
            System.out.println("Código postal inválido. Ex: 3500-123");
        }
        System.out.print("Localidade: ");
        String localidade = scanner.nextLine();
        Morada morada = new Morada(rua, porta, codPostal, localidade);

        System.out.print("Tem atividade? (s/n): ");
        String temAtv = scanner.nextLine();
        Atividade atividade = null;
        if (temAtv.equalsIgnoreCase("s")) {
            System.out.print("Nome atividade: ");
            String nomeAt = scanner.nextLine();
            System.out.print("Descrição: ");
            String desc = scanner.nextLine();
            System.out.print("Tempo médio (h): ");
            double tempo = Double.parseDouble(scanner.nextLine());
            System.out.print("Custo médio/hora: ");
            double custo = Double.parseDouble(scanner.nextLine());
            atividade = new Atividade(nomeAt, desc, tempo, custo);
        }

        utilizadores.add(new Utilizador(nif, nome, email, morada, contacto, atividade));
        System.out.println("Utilizador adicionado com sucesso.");
    }

    private static void editarUtilizador() {
        if (utilizadores.isEmpty()) {
            System.out.println("Nenhum utilizador disponível.");
            return;
        }

        Utilizador u = escolherUtilizador(null);
        System.out.print("Novo nome (atual: " + u.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank())
            u.setNome(nome);

        System.out.print("Novo email (atual: " + u.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isBlank())
            u.setEmail(email);

        System.out.print("Novo contacto (atual: " + u.getContacto() + "): ");
        String contacto = scanner.nextLine();
        if (!contacto.isBlank())
            u.setContacto(contacto);

        System.out.println("Utilizador atualizado.");
    }

    private static void removerUtilizador() {
        if (utilizadores.isEmpty()) {
            System.out.println("Nenhum utilizador disponível.");
            return;
        }

        Utilizador u = escolherUtilizador(null);
        utilizadores.remove(u);
        System.out.println("Utilizador removido.");
    }

    /**
     * Lista todas as caixas associadas a um utilizador escolhido.
     */
    private static void listarCaixasDoUtilizador() {
        if (utilizadores.isEmpty()) {
            System.out.println("Nenhum utilizador disponível.");
            return;
        }

        Utilizador u = escolherUtilizador(null);

        List<Caixa> caixas = u.getCaixas();
        if (caixas.isEmpty()) {
            System.out.println("Este utilizador não tem caixas.");
            return;
        }

        System.out.println("Caixas do utilizador " + u.getNome() + ":");
        for (Caixa c : caixas) {
            System.out.println("- ID: " + c.getId() +
                    ", Tipo: " + c.getTipo().getId() +
                    ", Ativa: " + (c.isAtiva() ? "Sim" : "Não"));
        }
    }

    /**
     * Adiciona uma relação de cliente ou parceiro a um utilizador.
     */
    private static void adicionarClienteOuParceiro() {
        if (utilizadores.size() < 2) {
            System.out.println("É necessário pelo menos 2 utilizadores.");
            return;
        }

        System.out.println("Escolha o utilizador principal:");
        Utilizador u1 = escolherUtilizador(null);

        System.out.println("Escolha o utilizador a adicionar como cliente/parceiro:");
        Utilizador u2 = escolherUtilizador(null);

        if (u1 == u2) {
            System.out.println("Não pode adicionar o mesmo utilizador a si próprio.");
            return;
        }

        System.out.print("Adicionar como (c)liente ou (p)arceiro? ");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("c")) {
            boolean jaExiste = u1.getClientes().stream()
                    .anyMatch(u -> u.getNif().equals(u2.getNif()));
            if (jaExiste) {
                System.out.println("Este utilizador já está associado como cliente.");
            } else {
                u1.getClientes().add(u2);
                System.out.println("Cliente adicionado com sucesso.");
            }
        } else if (tipo.equalsIgnoreCase("p")) {
            boolean jaExiste = u1.getParceiros().stream()
                    .anyMatch(u -> u.getNif().equals(u2.getNif()));
            if (jaExiste) {
                System.out.println("Este utilizador já está associado como parceiro.");
            } else {
                u1.getParceiros().add(u2);
                System.out.println("Parceiro adicionado com sucesso.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    /**
     * Lista os clientes e parceiros de um utilizador.
     */
    private static void listarClientesEParceiros() {
        if (utilizadores.isEmpty()) {
            System.out.println("Nenhum utilizador disponível.");
            return;
        }

        Utilizador u = escolherUtilizador(null);

        System.out.println("Clientes de " + u.getNome() + ":");
        if (u.getClientes().isEmpty()) {
            System.out.println(" - Nenhum cliente associado.");
        } else {
            for (Utilizador c : u.getClientes()) {
                System.out.println(" - " + c.getNome() + " (NIF: " + c.getNif() + ")");
            }
        }

        System.out.println("Parceiros de " + u.getNome() + ":");
        if (u.getParceiros().isEmpty()) {
            System.out.println(" - Nenhum parceiro associado.");
        } else {
            for (Utilizador p : u.getParceiros()) {
                System.out.println(" - " + p.getNome() + " (NIF: " + p.getNif() + ")");
            }
        }
    }

    // --------------------------Khikhipa--------------------------//
    private static void menuKhikhipa() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu Khikhipa ---");
            System.out.println("[1] Listar Khikhipas");
            System.out.println("[2] Adicionar Khikhipa");
            System.out.println("[3] Editar Khikhipa");
            System.out.println("[4] Remover Khikhipa");
            System.out.println("[5] Editar compartimentos do Khikhipa");
            System.out.println("[0] Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1" -> listarKhikhipas();
                case "2" -> adicionarKhikhipa();
                case "3" -> editarKhikhipa();
                case "4" -> removerKhikhipa();
                case "5" -> editarCompartimentoKhikhipa();
                case "0" -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void editarKhikhipa() {
        if (khikhipas.isEmpty()) {
            System.out.println("Nenhum Khikhipa disponível.");
            return;
        }

        System.out.println("\n=== Editar Khikhipa ===");
        for (int i = 0; i < khikhipas.size(); i++) {
            Khikhipa k = khikhipas.get(i);
            System.out.printf("[%d] %s (ID: %s)%n", i, k.getNome(), k.getId());
        }

        System.out.print("Escolha o índice do Khikhipa a editar: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine());
            if (index < 0 || index >= khikhipas.size()) {
                System.out.println("Índice inválido.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        Khikhipa k = khikhipas.get(index);

        System.out.print("Novo nome (Enter para manter '" + k.getNome() + "'): ");
        String novoNome = scanner.nextLine();
        if (!novoNome.isBlank()) {
            // ⚠️ Atualizar diretamente
            setPrivateField(k, "nome", novoNome);
        }

        System.out.print("Novo nome do responsável de manutenção (Enter para manter): ");
        String novoNomeManut = scanner.nextLine();
        if (!novoNomeManut.isBlank()) {
            k.getManutencao().setNome(novoNomeManut);
        }

        System.out.print("Novo contacto de manutenção (Enter para manter): ");
        String novoContactoManut = scanner.nextLine();
        if (!novoContactoManut.isBlank()) {
            k.getManutencao().setContacto(novoContactoManut);
        }

        System.out.print("Novo nome do responsável de higiene (Enter para manter): ");
        String novoNomeHig = scanner.nextLine();
        if (!novoNomeHig.isBlank()) {
            k.getHigiene().setNome(novoNomeHig);
        }

        System.out.print("Novo contacto de higiene (Enter para manter): ");
        String novoContactoHig = scanner.nextLine();
        if (!novoContactoHig.isBlank()) {
            k.getHigiene().setContacto(novoContactoHig);
        }

        System.out.println("✅ Khikhipa atualizado com sucesso.");
    }

    private static void setPrivateField(Object obj, String fieldName, String newValue) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, newValue);
        } catch (Exception e) {
            System.out.println("Erro ao atualizar campo '" + fieldName + "': " + e.getMessage());
        }
    }

    private static void removerKhikhipa() {
        if (khikhipas.isEmpty()) {
            System.out.println("Nenhum Khikhipa disponível.");
            return;
        }

        Khikhipa k = khikhipas.get(0); // ou escolhes da lista
        khikhipas.remove(k);
        System.out.println("Khikhipa removido.");
    }

    private static void listarKhikhipas() {
        if (khikhipas.isEmpty()) {
            System.out.println("Nenhum Khikhipa disponível.");
            return;
        }
        for (Khikhipa k : khikhipas) {
            System.out.println("\n-------------------------");
            System.out.println("Nome: " + k.getNome());
            System.out.println("ID: " + k.getId());
            System.out.println("Localização: " + k.getMorada().toString());
            System.out.println(
                    "Responsável Manutenção: " + k.getManutencao().getNome() + " | " + k.getManutencao().getContacto());
            System.out
                    .println("Responsável Higiene: " + k.getHigiene().getNome() + " | " + k.getHigiene().getContacto());
            System.out.println("Horário: " + k.horarioFuncionamento());

            List<Compartimento> compartimentos = k.getCompartimentos();
            System.out.println("Total de compartimentos: " + compartimentos.size());
            System.out.println("--- Compartimentos ---");
            for (Compartimento c : compartimentos) {
                System.out.printf(
                        "  - ID: %s | Linha: %d | Coluna: %d | Disponível: %s | Modo Entrega: %s | Clipe Aberto: %s%n",
                        c.getId(),
                        c.getLinha(),
                        c.getColuna(),
                        c.isDisponivel() ? "Sim" : "Não",
                        c.isModoEntrega() ? "Sim" : "Não",
                        c.isClipeAberto() ? "Sim" : "Não");
            }
        }
    }

    private static void adicionarKhikhipa() {
        System.out.println("\n=== Adicionar Khikhipa ===");

        System.out.print("Nome do quiosque: ");
        String nome = scanner.nextLine();

        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        System.out.print("Número da porta: ");
        int porta = Integer.parseInt(scanner.nextLine());

        System.out.print("Código Postal (7 dígitos, ex: 3504054): ");
        String codigoPostal = scanner.nextLine();

        System.out.print("Localidade: ");
        String localidade = scanner.nextLine();

        Morada morada = new Morada(rua, porta, codigoPostal, localidade);

        System.out.print("Capacidade total (número de compartimentos): ");
        int capacidade = Integer.parseInt(scanner.nextLine());

        // Responsável de manutenção
        System.out.print("Nome do responsável de manutenção: ");
        String nomeManutencao = scanner.nextLine();
        System.out.print("Contacto de manutenção: ");
        String contactoManutencao = scanner.nextLine();
        Responsavel manutencao = new Responsavel(nomeManutencao, contactoManutencao);

        // Responsável de higiene
        System.out.print("Nome do responsável de higiene: ");
        String nomeHigiene = scanner.nextLine();
        System.out.print("Contacto de higiene: ");
        String contactoHigiene = scanner.nextLine();
        Responsavel higiene = new Responsavel(nomeHigiene, contactoHigiene);

        // ------------------ Gerar Compartimentos ------------------ //
        System.out.print("Número de linhas de compartimentos: ");
        int linhas = Integer.parseInt(scanner.nextLine());
        System.out.print("Número de colunas de compartimentos: ");
        int colunas = Integer.parseInt(scanner.nextLine());

        List<Compartimento> compartimentos = new ArrayList<>();
        System.out.println("Inserir dados das chaves para cada compartimento:");

        for (int l = 1; l <= linhas; l++) {
            for (int c = 1; c <= colunas; c++) {
                System.out.printf("Compartimento [%02d,%02d]:%n", l, c);

                System.out.print("Modo entrega? (true/false): ");
                boolean modoEntrega = Boolean.parseBoolean(scanner.nextLine());

                System.out.print("Chave pública (ID utilizador B): ");
                String chavePub = scanner.nextLine();

                System.out.print("Chave privada (ID utilizador A): ");
                String chavePriv = scanner.nextLine();

                compartimentos.add(
                        new Compartimento(codigoPostal + String.format("%02d", capacidade), l, c, modoEntrega, chavePub,
                                chavePriv));
            }
        }

        // Criar quiosque (ID gerado automaticamente na classe)
        Khikhipa novo = new Khikhipa(
                nome,
                morada,
                compartimentos,
                manutencao,
                higiene,
                codigoPostal,
                capacidade);

        khikhipas.add(novo);
        System.out.println("✅ Khikhipa adicionado com sucesso. ID: " + novo.getId());
    }

    private static void editarCompartimentoKhikhipa() {
        if (khikhipas.isEmpty()) {
            System.out.println("Nenhum Khikhipa disponível.");
            return;
        }

        System.out.println("\n=== Escolha o Khikhipa ===");
        for (int i = 0; i < khikhipas.size(); i++) {
            Khikhipa k = khikhipas.get(i);
            System.out.printf("[%d] %s (ID: %s)%n", i, k.getNome(), k.getId());
        }

        System.out.print("Índice do Khikhipa: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index < 0 || index >= khikhipas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Khikhipa k = khikhipas.get(index);
        List<Compartimento> compartimentos = k.getCompartimentos();

        if (compartimentos.isEmpty()) {
            System.out.println("Este Khikhipa não tem compartimentos.");
            return;
        }

        System.out.println("\n--- Compartimentos ---");
        for (int i = 0; i < compartimentos.size(); i++) {
            Compartimento c = compartimentos.get(i);
            System.out.printf("[%d] ID: %s | Modo Entrega: %s | Disponível: %s | Clipe Aberto: %s%n",
                    i,
                    c.getId(),
                    c.isModoEntrega() ? "Sim" : "Não",
                    c.isDisponivel() ? "Sim" : "Não",
                    c.isClipeAberto() ? "Sim" : "Não");
        }

        System.out.print("Escolha o índice do compartimento a editar: ");
        int compIndex = Integer.parseInt(scanner.nextLine());
        if (compIndex < 0 || compIndex >= compartimentos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Compartimento c = compartimentos.get(compIndex);

        System.out.print("Novo modo entrega (true/false, Enter para manter): ");
        String inputModo = scanner.nextLine();
        if (!inputModo.isBlank())
            c.setModoEntrega(Boolean.parseBoolean(inputModo));

        System.out.print("Nova chave pública (Enter para manter): ");
        String novaChavePub = scanner.nextLine();
        if (!novaChavePub.isBlank())
            c.setChavePublica(novaChavePub);

        System.out.print("Nova chave privada (Enter para manter): ");
        String novaChavePriv = scanner.nextLine();
        if (!novaChavePriv.isBlank())
            c.setChavePrivada(novaChavePriv);

        System.out.print("Alterar disponibilidade (true/false, Enter para manter): ");
        String inputDisp = scanner.nextLine();
        if (!inputDisp.isBlank())
            c.setDisponivel(Boolean.parseBoolean(inputDisp));

        System.out.print("Abrir clipe (1), Fechar clipe (2), ou nada (Enter): ");
        String acaoClipe = scanner.nextLine();
        if (acaoClipe.equals("1")) {
            System.out.print("Digite a chave privada: ");
            String chave = scanner.nextLine();
            boolean ok = c.abrirClipe(chave);
            System.out.println(ok ? "Clipe aberto com sucesso." : "Erro ao abrir clipe.");
        } else if (acaoClipe.equals("2")) {
            System.out.print("Digite a chave pública: ");
            String chave = scanner.nextLine();
            boolean ok = c.fecharClipe(chave);
            System.out.println(ok ? "Clipe fechado com sucesso." : "Erro ao fechar clipe.");
        }

        System.out.println("✅ Compartimento atualizado.");
    }

    // --------------------------Khikhivi--------------------------//

    private static void menuKhikhivi() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu Khikhivi ---");
            System.out.println("[1] Listar Khikhivis");
            System.out.println("[2] Adicionar Khikhivi com áreas");
            System.out.println("[3] Verificar estado das áreas");
            System.out.println("[0] Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1" -> listarKhikhivis();
                case "2" -> adicionarKhikhivi();
                case "3" -> verificarAreas();
                case "0" -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void listarKhikhivis() {
        if (khikhivis.isEmpty()) {
            System.out.println("Nenhum Khikhivi disponível.");
            return;
        }
        for (Khikhivi k : khikhivis) {
            System.out.println("- " + k.getNome() + " com " + k.getAreasTrabalho().size() + " áreas.");
        }
    }

    private static void adicionarKhikhivi() {
        Morada morada = new Morada("Rua Oficina", 12, "3500052", "Viseu");
        Responsavel man = new Responsavel("Vítor", "911000000");
        Responsavel hig = new Responsavel("Rita", "914000000");

        // Criar as 4 áreas fixas com alguns itens operacionais e outros avariados
        List<AreaTrabalho> areas = new ArrayList<>();

        areas.add(new AreaTrabalho("auto", Arrays.asList(
                new Item("Elevador Auto", true),
                new Item("Ferramentas Auto", false))));

        areas.add(new AreaTrabalho("carpintaria", Arrays.asList(
                new Item("Mesa Carpintaria", true),
                new Item("Serra Circular", true))));

        areas.add(new AreaTrabalho("moto", Arrays.asList(
                new Item("Bancada Moto", false),
                new Item("Compressor", true))));

        areas.add(new AreaTrabalho("eletrodoméstico", Arrays.asList(
                new Item("Multímetro", true),
                new Item("Chave Isolada", true))));

        Khikhivi novo = new Khikhivi("KHIVI" + (khikhivis.size() + 1), "Khikhivi Oficinas", morada, areas, man, hig);
        khikhivis.add(novo);
        System.out.println("Khikhivi com 4 áreas adicionado.");
    }

    private static void verificarAreas() {
        if (khikhivis.isEmpty()) {
            System.out.println("Nenhum Khikhivi disponível.");
            return;
        }

        for (AreaTrabalho a : khikhivis.get(0).getAreasTrabalho()) {
            System.out.println("Área: " + a.getNome());
            for (Item i : a.getItens()) {
                System.out.println(" - " + i.getNome() + ": " + (i.isOperacional() ? "Operacional" : "Avariado"));
            }
        }
    }

    private static boolean areaEstaOperacional(AreaTrabalho area) {
        for (Item i : area.getItens()) {
            if (!i.isOperacional()) {
                return false;
            }
        }
        return true;
    }

    // --------------------------------------------------------------//
    // --------------------------Khikhitas--------------------------//
    /// Menu de gestão de Khikhitas
    /**
     * Permite listar, adicionar e consultar morada do funcionário.
     */

    private static void menuKhikhita() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu Khikhita ---");
            System.out.println("[1] Listar Khikhitas");
            System.out.println("[2] Adicionar Khikhita");
            System.out.println("[4] Editar Khikhita");
            System.out.println("[5] Agendar caixa em Khikhita");
            System.out.println("[3] Consultar morada do funcionário");
            System.out.println("[0] Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1" -> listarKhikhitas();
                case "2" -> adicionarKhikhita();
                case "3" -> consultarMoradaFuncionario();
                case "5" -> agendarCaixaKhikhita();
                case "4" -> editarKhikhita();
                case "0" -> voltar = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

   private static void listarKhikhitas() {
    if (khikhitas.isEmpty()) {
        System.out.println("Nenhum Khikhita disponível.");
        return;
    }

    System.out.println("\n=== Lista de Quiosques Khikhita ===");

    for (Khikhita k : khikhitas) {
        System.out.println("\n------------------------------");
        System.out.println("Nome da tabacaria: " + k.getNome());
        System.out.println("ID: " + k.getId());
        System.out.println("Morada: " + k.getMorada().toString());

        Funcionario f = k.getFuncionario();
        System.out.println("Funcionário: " + f.getNome() + " | Contacto: " + f.getContacto());

        System.out.printf("Horário de funcionamento: %s às %s%n",
                k.getAbertura(), k.getEncerramento());

        System.out.println("Capacidade por tipo de caixa:");
        for (Map.Entry<String, Integer> entry : k.getMaximoPorTipo().entrySet()) {
            String tipo = entry.getKey();
            int maximo = entry.getValue();
            int atual = k.getAtuaisPorTipo().getOrDefault(tipo, 0);
            System.out.printf("  Tipo %s → %d / %d usadas%n", tipo, atual, maximo);
        }
    }
}

private static void agendarCaixaKhikhita() {
    if (khikhitas.isEmpty()) {
        System.out.println("Nenhum Khikhita disponível.");
        return;
    }

    System.out.println("\n=== Agendamento em Khikhita ===");
    for (int i = 0; i < khikhitas.size(); i++) {
        Khikhita k = khikhitas.get(i);
        System.out.printf("[%d] %s (ID: %s)%n", i, k.getNome(), k.getId());
    }

    System.out.print("Escolha o índice do Khikhita: ");
    int index = Integer.parseInt(scanner.nextLine());
    if (index < 0 || index >= khikhitas.size()) {
        System.out.println("Índice inválido.");
        return;
    }

    Khikhita k = khikhitas.get(index);

    // Verifica horário
    LocalTime agora = LocalTime.of(9, 0); // Data fixa para testes, como pedido no enunciado
    if (!k.isAberto(agora)) {
        System.out.println("Este quiosque está encerrado neste horário.");
        return;
    }

    System.out.println("Tipos de caixa: S, M, L, XL");
    System.out.print("Tipo de caixa: ");
    String tipo = scanner.nextLine().toUpperCase();

    if (!k.getMaximoPorTipo().containsKey(tipo)) {
        System.out.println("Tipo inválido.");
        return;
    }

    if (!k.podeReceberCaixa(tipo)) {
        System.out.println("Capacidade esgotada para este tipo de caixa.");
        return;
    }

    System.out.print("ID do utilizador A (solicitante): ");
    String idA = scanner.nextLine();

    System.out.print("ID do utilizador B (prestador): ");
    String idB = scanner.nextLine();

    System.out.println("=== Inserir datas do agendamento ===");
    System.out.print("Data de entrega 1 (cliente -> quiosque): ");
    LocalDate entrega1 = LocalDate.parse(scanner.nextLine());

    System.out.print("Data de levantamento 1 (prestador vai buscar): ");
    LocalDate levantamento1 = LocalDate.parse(scanner.nextLine());

    System.out.print("Data de entrega 2 (prestador devolve): ");
    LocalDate entrega2 = LocalDate.parse(scanner.nextLine());

    System.out.print("Data de levantamento 2 (cliente final vai buscar): ");
    LocalDate levantamento2 = LocalDate.parse(scanner.nextLine());

    TicketAgendamento ticket = new TicketAgendamento(
        k.getId(), "-", idA, idB, tipo,
        entrega1, levantamento1, entrega2, levantamento2
    );

    Agendamento ag = new Agendamento(ticket, entrega1, false); // Data principal = entrega1
    agendamentos.add(ticket);
    k.registarEntradaCaixa(tipo);

    System.out.println("✅ Agendamento criado com sucesso.");
    System.out.println("💰 Custo base + 1.50€ pela operação do funcionário da tabacaria.");
}


private static void editarKhikhita() {
    if (khikhitas.isEmpty()) {
        System.out.println("Nenhum Khikhita disponível.");
        return;
    }

    System.out.println("\n=== Editar Khikhita ===");
    for (int i = 0; i < khikhitas.size(); i++) {
        System.out.printf("[%d] %s (ID: %s)%n", i, khikhitas.get(i).getNome(), khikhitas.get(i).getId());
    }

    System.out.print("Escolha o índice do Khikhita: ");
    int index = Integer.parseInt(scanner.nextLine());

    if (index < 0 || index >= khikhitas.size()) {
        System.out.println("Índice inválido.");
        return;
    }

    Khikhita k = khikhitas.get(index);

    System.out.print("Novo nome da tabacaria (Enter para manter): ");
    String novoNome = scanner.nextLine();
    if (!novoNome.isBlank()) setPrivateField(k, "nome", novoNome);

    // Atualizar funcionário
    System.out.print("Novo nome do funcionário (Enter para manter): ");
    String nomeFunc = scanner.nextLine();
    if (!nomeFunc.isBlank()) k.getFuncionario().setNome(nomeFunc);

    System.out.print("Novo contacto do funcionário (Enter para manter): ");
    String contactoFunc = scanner.nextLine();
    if (!contactoFunc.isBlank()) k.getFuncionario().setContacto(contactoFunc);

    // Atualizar horário
    System.out.print("Nova hora de abertura (HH:mm, Enter para manter): ");
    String horaAbertura = scanner.nextLine();
    if (!horaAbertura.isBlank()) {
        k.setAbertura(LocalTime.parse(horaAbertura));
    }

    System.out.print("Nova hora de encerramento (HH:mm, Enter para manter): ");
    String horaFecho = scanner.nextLine();
    if (!horaFecho.isBlank()) {
        k.setEncerramento(LocalTime.parse(horaFecho));
    }

    // Atualizar limites por tipo
    System.out.println("Deseja alterar limites por tipo de caixa? (s/n)");
    if (scanner.nextLine().trim().equalsIgnoreCase("s")) {
        Map<String, Integer> novosLimites = new HashMap<>();
        for (String tipo : k.getMaximoPorTipo().keySet()) {
            System.out.printf("Novo limite para tipo %s (Enter para manter %d): ", tipo, k.getMaximoPorTipo().get(tipo));
            String input = scanner.nextLine();
            if (input.isBlank()) {
                novosLimites.put(tipo, k.getMaximoPorTipo().get(tipo));
            } else {
                novosLimites.put(tipo, Integer.parseInt(input));
            }
        }
        k.setMaximoPorTipo(novosLimites);
    }

    System.out.println("✅ Khikhita atualizado com sucesso.");
}


    /**
     * Verifica se o número máximo de caixas do tipo especificado foi atingido.
     */
    private static void adicionarKhikhita() {
        System.out.println("\n=== Adicionar Khikhita ===");

        System.out.print("Nome da tabacaria: ");
        String nome = scanner.nextLine();

        System.out.print("Rua: ");
        String rua = scanner.nextLine();

        System.out.print("Número da porta: ");
        int porta = Integer.parseInt(scanner.nextLine());

        System.out.print("Código Postal (7 dígitos, ex: 3504054): ");
        String codigoPostal = scanner.nextLine();

        System.out.print("Localidade: ");
        String localidade = scanner.nextLine();

        Morada morada = new Morada(rua, porta, codigoPostal, localidade);

        System.out.print("Capacidade total (máximo número de caixas no total): ");
        int capacidade = Integer.parseInt(scanner.nextLine());

        // Horário de funcionamento
        System.out.print("Hora de abertura (HH:mm): ");
        LocalTime abertura = LocalTime.parse(scanner.nextLine());

        System.out.print("Hora de encerramento (HH:mm): ");
        LocalTime encerramento = LocalTime.parse(scanner.nextLine());

        // Funcionário responsável
        System.out.print("Nome do funcionário: ");
        String nomeFuncionario = scanner.nextLine();

        System.out.print("Contacto do funcionário: ");
        String contactoFuncionario = scanner.nextLine();

        // Supondo que o construtor correto é Funcionario(String nome, String contacto,
        // Morada morada)
        Funcionario funcionario = new Funcionario(nomeFuncionario, contactoFuncionario);

        // Máximo por tipo de caixa
        Map<String, Integer> limites = new HashMap<>();
        System.out.println("Indique o número máximo de caixas por tipo:");
        System.out.print("Tipo S: ");
        limites.put("S", Integer.parseInt(scanner.nextLine()));
        System.out.print("Tipo M: ");
        limites.put("M", Integer.parseInt(scanner.nextLine()));
        System.out.print("Tipo L: ");
        limites.put("L", Integer.parseInt(scanner.nextLine()));
        System.out.print("Tipo XL: ");
        limites.put("XL", Integer.parseInt(scanner.nextLine()));

        // Criar Khikhita
        Khikhita novo = new Khikhita(
                nome,
                morada,
                funcionario,
                limites,
                codigoPostal,
                capacidade,
                abertura,
                encerramento);

        khikhitas.add(novo);
        System.out.println("✅ Khikhita adicionado com sucesso. ID: " + novo.getId());
    }

    private static void consultarMoradaFuncionario() {
        if (khikhitas.isEmpty()) {
            System.out.println("Nenhum Khikhita disponível.");
            return;
        }
        System.out.println("Funcionário de " + khikhitas.get(0).getNome() + ": " +
                khikhitas.get(0).getFuncionario().getMorada().getRua());
    }

    // --------------------------Agendamentos--------------------------//

    /**
     * Lê uma data com formato AAAA-MM-DD e valida que
     * a data mínima (dataMinima) não seja ultrapassada.
     * Pede repetidamente até o utilizador inserir uma data válida.
     */
    private static LocalDate lerDataComValidacao(String mensagem, LocalDate dataMinima) {
        LocalDate data = null;
        while (data == null) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                LocalDate temp = LocalDate.parse(entrada);
                if (dataMinima != null && temp.isBefore(dataMinima)) {
                    System.out.println("Data inválida: não pode ser anterior a " + dataMinima);
                    continue;
                }
                data = temp;
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println("Data inválida. Use o formato AAAA-MM-DD, ex: 2025-05-01");
            }
        }
        return data;
    }

    private static void criarAgendamentoReal() {
        if (utilizadores.size() < 2) {
            System.out.println("É necessário pelo menos dois utilizadores (cliente e parceiro).");
            return;
        }

        System.out.println("Tipo de quiosque para agendar:");
        System.out.println("[1] Khikhipa");
        System.out.println("[2] Khikhita");
        System.out.println("[3] Khikhivi");
        System.out.print("Escolha: ");
        String tipo = scanner.nextLine();

        System.out.println("Escolher utilizador A (cliente):");
        Utilizador a = escolherUtilizador(tipo);
        System.out.println("Escolher utilizador B (parceiro):");
        Utilizador b = escolherUtilizador(tipo  );

        String idQuiosque = "";
        String idCompartimentoOuArea = "";

        // Escolha e validação do tipo de quiosque
        switch (tipo) {
            case "1" -> {
                if (khikhipas.isEmpty()) {
                    System.out.println("Nenhum Khikhipa disponível.");
                    return;
                }
                Khikhipa k = khikhipas.get(0);
                Compartimento c = k.getCompartimentos().get(0);
                idQuiosque = k.getId();
                idCompartimentoOuArea = c.getId();
            }

            case "2" -> {
                if (khikhitas.isEmpty()) {
                    System.out.println("Nenhum Khikhita disponível.");
                    return;
                }
                Khikhita k = khikhitas.get(0);
                String tipoCaixa = "S"; // neste exemplo fixo
                if (!podeAdicionarCaixaKhikhita(k, tipoCaixa)) {
                    System.out.println("Limite de caixas tipo " + tipoCaixa + " atingido para este Khikhita.");
                    return;
                }
                idQuiosque = k.getId();
                idCompartimentoOuArea = "N/A";
            }

            case "3" -> {
                if (khikhivis.isEmpty()) {
                    System.out.println("Nenhum Khikhivi disponível.");
                    return;
                }
                Khikhivi k = khikhivis.get(0);
                AreaTrabalho area = k.getAreasTrabalho().get(0); // exemplo: escolher primeira
                if (!areaEstaOperacional(area)) {
                    System.out.println("Área '" + area.getNome() + "' tem itens avariados. Agendamento impossível.");
                    return;
                }
                idQuiosque = k.getId();
                idCompartimentoOuArea = area.getNome();
            }

            default -> {
                System.out.println("Tipo inválido.");
                return;
            }
        }

        // Ler as datas com validação da ordem (entrega ≤ levantamento)
        LocalDate e1 = lerDataComValidacao("Data entrega 1 (AAAA-MM-DD): ", null);
        LocalDate l1 = lerDataComValidacao("Data levantamento 1 (AAAA-MM-DD): ", e1);
        LocalDate e2 = lerDataComValidacao("Data entrega 2 (AAAA-MM-DD): ", null);
        LocalDate l2 = lerDataComValidacao("Data levantamento 2 (AAAA-MM-DD): ", e2);

        TicketAgendamento t = new TicketAgendamento(
                idQuiosque, idCompartimentoOuArea, a.getNif(), b.getNif(),
                "S", e1, l1, e2, l2);
        agendamentos.add(t);
        System.out.println("Agendamento criado com sucesso.");
    }

    private static void listarAgendamentos() {
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
            return;
        }

        LocalDate hoje = Config.DATA_HOJE;
        agendamentos.forEach(t -> {
            String tipo;
            if (t.getDataLevantamento2().isBefore(hoje))
                tipo = "ANTERIOR";
            else if (t.getDataLevantamento2().isEqual(hoje))
                tipo = "HOJE";
            else
                tipo = "POSTERIOR";

            System.out.println("[" + tipo + "] " + t.getIdQuiosque() + " - " + t.getDataLevantamento2());
        });
    }

    // --------------------------Recibos--------------------------//

    private static void emitirReciboReal() {
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento disponível.");
            return;
        }

        // Mostrar lista de agendamentos
        for (int i = 0; i < agendamentos.size(); i++) {
            TicketAgendamento t = agendamentos.get(i);
            System.out.println("[" + i + "] Quiosque: " + t.getIdQuiosque() + ", Data: " + t.getDataLevantamento2());
        }

        System.out.print("Escolha o agendamento para emitir recibo: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index < 0 || index >= agendamentos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        TicketAgendamento t = agendamentos.get(index);
        Map<String, Double> valores = new HashMap<>();

        // Adiciona custo base do serviço (exemplo genérico)
        valores.put("Serviço base", 20.0);

        // Lógica de custo por tipo de quiosque
        if (t.getIdQuiosque().startsWith("350")) { // Khikhipa
            valores.put("Uso compartimento", 2.4);
        } else if (t.getIdQuiosque().startsWith("KHITA")) { // Khikhita
            valores.put("Comissão tabacaria", 5.0);
        } else if (t.getIdQuiosque().startsWith("KHIVI")) { // Khikhivi
            valores.put("Uso da área de trabalho", 7.5);
        }

        double subtotal = valores.values().stream().mapToDouble(Double::doubleValue).sum();
        double iva = subtotal * 0.23;
        double total = subtotal + iva;

        Recibo r = new Recibo("R" + (recibos.size() + 1), Config.DATA_HOJE, t, valores, iva, total);
        recibos.add(r);
        System.out.println("Recibo emitido com total: " + total + " EUR");
    }

    /**
     * Gera um recibo com base no agendamento selecionado.
     * O recibo inclui os detalhes do agendamento e os valores associados.
     */
    private static void gerarRelatorioAgendamentos() {
        File relatorio = new File("relatorio_agendamentos.txt");
        try (PrintWriter writer = new PrintWriter(relatorio)) {
            writer.println("===== RELATÓRIO DE AGENDAMENTOS =====");
            for (TicketAgendamento t : agendamentos) {
                writer.println("Quiosque: " + t.getIdQuiosque());
                writer.println("Compartimento/Área: " + t.getIdCompartimentoOuArea());
                writer.println("Cliente: " + t.getIdSolicitante());
                writer.println("Prestador: " + t.getIdPrestador());
                writer.println("Tipo de Caixa: " + t.getTipoCaixa());
                writer.println("Entrega 1: " + t.getDataEntrega1());
                writer.println("Levantamento 1: " + t.getDataLevantamento1());
                writer.println("Entrega 2: " + t.getDataEntrega2());
                writer.println("Levantamento 2: " + t.getDataLevantamento2());
                writer.println("--------------------------------------");
            }
            writer.println("Total de agendamentos: " + agendamentos.size());
            System.out.println("Relatório de agendamentos gerado com sucesso: " + relatorio.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao escrever o relatório de agendamentos: " + e.getMessage());
        }
    }

    /**
     * Lista todos os recibos emitidos no sistema, com os detalhes
     * de cada valor incluído e o total com IVA.
     */
    private static void listarRecibos() {
        if (recibos.isEmpty()) {
            System.out.println("Nenhum recibo emitido.");
            return;
        }

        System.out.println("\n===== RECIBOS EMITIDOS =====");

        for (Recibo r : recibos) {
            System.out.println("Recibo ID: " + r.getId());
            System.out.println("Data: " + r.getDataEmissao());
            System.out.println("Quiosque: " + r.getTicket().getIdQuiosque());
            System.out.println("Detalhes:");

            for (Map.Entry<String, Double> entry : r.getValores().entrySet()) {
                System.out.printf(" - %s: %.2f €%n", entry.getKey(), entry.getValue());
            }

            System.out.printf("IVA: %.2f €%n", r.getIva());
            System.out.printf("Total com IVA: %.2f €%n", r.getTotal());
            System.out.println("-----------------------------------");
        }
    }

    /**
     * Gera um relatório em ficheiro .txt com todos os recibos emitidos.
     * O ficheiro será criado na raiz do projeto com o nome 'relatorio_recibos.txt'.
     */
    private static void gerarRelatorioRecibos() {
        File relatorio = new File("relatorio_recibos.txt");
        try (PrintWriter writer = new PrintWriter(relatorio)) {
            writer.println("===== RELATÓRIO DE RECIBOS EMITIDOS =====");
            for (Recibo r : recibos) {
                writer.println("Recibo ID: " + r.getId());
                writer.println("Data: " + r.getDataEmissao());
                writer.println("Quiosque: " + r.getTicket().getIdQuiosque());
                writer.println("Detalhes:");
                for (Map.Entry<String, Double> entry : r.getValores().entrySet()) {
                    writer.printf(" - %s: %.2f €%n", entry.getKey(), entry.getValue());
                }
                writer.printf("IVA: %.2f €%n", r.getIva());
                writer.printf("Total com IVA: %.2f €%n", r.getTotal());
                writer.println("--------------------------------------");
            }
            writer.println("Total de recibos emitidos: " + recibos.size());
            System.out.println("Relatório gerado com sucesso: " + relatorio.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Erro ao escrever o relatório: " + e.getMessage());
        }
    }
    // --------------------------Tabela de Preços--------------------------//

    /**
     * Mostra a tabela de preços predefinida do sistema Khikhi.
     * Pode ser usada para consulta rápida dos custos aplicados
     * nos diferentes tipos de quiosques e serviços.
     */
    private static void mostrarTabelaPrecos() {
        System.out.println("\n===== TABELA DE PREÇOS =====");

        System.out.println("\nTipos de Caixa:");
        System.out.println(" - Tipo S: 20.00 € (custo fixo), 0.10 €/hora");
        System.out.println(" - Tipo M: 30.00 € (custo fixo), 0.15 €/hora");
        System.out.println(" - Tipo L: 45.00 € (custo fixo), 0.25 €/hora");

        System.out.println("\nComissão Tabacaria (Khikhita):");
        System.out.println(" - Valor fixo por agendamento: 5.00 €");

        System.out.println("\nCusto de utilização da área de trabalho (Khikhivi):");
        System.out.println(" - Valor fixo por agendamento: 7.50 €");

        System.out.println("\nIVA aplicado:");
        System.out.println(" - Taxa: 23%");

        System.out.println("========================================");
    }

    // --------------------------Métodos de guardar
    // Dados--------------------------//

   private static Utilizador escolherUtilizador(String titulo) {
    if (utilizadores.isEmpty()) {
        System.out.println("Nenhum utilizador disponível.");
        return null;
    }

    System.out.println("Escolher " + titulo + ":");
    for (int i = 0; i < utilizadores.size(); i++) {
        System.out.println("[" + i + "] " + utilizadores.get(i).getNome());
    }

    System.out.print("Escolha: ");
    try {
        int index = Integer.parseInt(scanner.nextLine());

        if (index < 0 || index >= utilizadores.size()) {
            System.out.println("❌ Índice inválido.");
            return null;
        }

        return utilizadores.get(index);

    } catch (NumberFormatException e) {
        System.out.println("❌ Entrada inválida.");
        return null;
    }
}


    private static void guardarDados() {
        try (
                ObjectOutputStream outUtilizadores = new ObjectOutputStream(new FileOutputStream("utilizadores.dat"));
                ObjectOutputStream outKhikhipas = new ObjectOutputStream(new FileOutputStream("khikhipas.dat"));
                ObjectOutputStream outKhikhitas = new ObjectOutputStream(new FileOutputStream("khikhitas.dat"));
                ObjectOutputStream outKhikhivis = new ObjectOutputStream(new FileOutputStream("khikhivis.dat"));
                ObjectOutputStream outAgendamentos = new ObjectOutputStream(new FileOutputStream("agendamentos.dat"));
                ObjectOutputStream outRecibos = new ObjectOutputStream(new FileOutputStream("recibos.dat"))) {
            outUtilizadores.writeObject(utilizadores);
            outKhikhipas.writeObject(khikhipas);
            outKhikhitas.writeObject(khikhitas);
            outKhikhivis.writeObject(khikhivis);
            outAgendamentos.writeObject(agendamentos);
            outRecibos.writeObject(recibos);
            System.out.println("Todos os dados foram guardados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao guardar dados: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static void carregarDados() {
        try {
            File f1 = new File("utilizadores.dat");
            File f2 = new File("khikhipas.dat");
            File f3 = new File("khikhitas.dat");
            File f4 = new File("khikhivis.dat");
            File f5 = new File("agendamentos.dat");
            File f6 = new File("recibos.dat");

            if (f1.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f1))) {
                    utilizadores = (List<Utilizador>) in.readObject();
                }
            }
            if (f2.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f2))) {
                    khikhipas = (List<Khikhipa>) in.readObject();
                }
            }
            if (f3.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f3))) {
                    khikhitas = (List<Khikhita>) in.readObject();
                }
            }
            if (f4.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f4))) {
                    khikhivis = (List<Khikhivi>) in.readObject();
                }
            }
            if (f5.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f5))) {
                    agendamentos = (List<TicketAgendamento>) in.readObject();
                }
            }
            if (f6.exists()) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(f6))) {
                    recibos = (List<Recibo>) in.readObject();
                }
            }

            System.out.println("Dados carregados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private static boolean podeAdicionarCaixaKhikhita(Khikhita khikhita, String tipoCaixa) {
        Map<String, Integer> maximos = khikhita.getMaximoPorTipo();
        int max = maximos.getOrDefault(tipoCaixa, 0);
        int usados = 0;

        for (Utilizador u : utilizadores) {
            for (Caixa c : u.getCaixas()) {
                if (c.getTipo().getId().equals(tipoCaixa)) {
                    usados++;
                }
            }
        }

        return usados < max;
    }
}

// --------------------------FIM--------------------------//
