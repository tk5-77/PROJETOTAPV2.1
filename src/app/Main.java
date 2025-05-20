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
import java.util.*;

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
            System.out.println("[3] Criar Agendamento Real");
            System.out.println("[4] Listar Agendamentos");
            System.out.println("[5] Emitir Recibo com base em Agendamento");
            System.out.println("[6] Listar Recibos");
            System.out.println("[7] Guardar dados");
            System.out.println("[8] Gestão de Quiosques Khikhita");
            System.out.println("[9] Gestão de Quiosques Khikhivi");
            System.out.println("[10] Tabela de Preços");
            System.out.println("[11] Gerar Relatório de Recibos");
            System.out.println("[12] Gerar relatório de agendamentos (.txt)");
            System.out.println("[0] Sair");
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> menuUtilizadores();
                case "2" -> menuKhikhipa();
                case "3" -> criarAgendamentoReal();
                case "4" -> listarAgendamentos();
                case "5" -> emitirReciboReal();
                case "6" -> listarRecibos();
                case "7" -> guardarDados();
                case "8" -> menuKhikhita();
                case "9" -> menuKhikhivi();
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
        System.out.print("Contacto: ");
        String contacto = scanner.nextLine();

        for (Utilizador u : utilizadores) {
            if (u.getNif().equals(nif) && u.getContacto().equals(contacto)) {
                System.out.println("Autenticação bem-sucedida. Bem-vindo, " + u.getNome() + "!");
                return u;
            }
        }
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

        Utilizador u = escolherUtilizador();
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

        Utilizador u = escolherUtilizador();
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

        Utilizador u = escolherUtilizador();

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
        Utilizador u1 = escolherUtilizador();

        System.out.println("Escolha o utilizador a adicionar como cliente/parceiro:");
        Utilizador u2 = escolherUtilizador();

        if (u1 == u2) {
            System.out.println("Não pode adicionar o mesmo utilizador.");
            return;
        }

        System.out.print("Adicionar como (c)liente ou (p)arceiro? ");
        String tipo = scanner.nextLine();

        if (tipo.equalsIgnoreCase("c")) {
            u1.getClientes().add(u2);
            System.out.println("Cliente adicionado com sucesso.");
        } else if (tipo.equalsIgnoreCase("p")) {
            u1.getParceiros().add(u2);
            System.out.println("Parceiro adicionado com sucesso.");
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

        Utilizador u = escolherUtilizador();

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

    // --------------------------Khikhivi--------------------------//
    private static void menuKhikhipa() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu Khikhipa ---");
            System.out.println("[1] Listar Khikhipas");
            System.out.println("[2] Adicionar Khikhipa (exemplo)");
            System.out.println("[3] Editar Khikhipa");
            System.out.println("[4] Remover Khikhipa");
            System.out.println("[0] Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1" -> listarKhikhipas();
                case "2" -> adicionarKhikhipaExemplo();
                case "3" -> editarKhikhipa();
                case "4" -> removerKhikhipa();
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

        Khikhipa k = khikhipas.get(0); // ou escolher de lista se quiseres
        System.out.print("Novo nome (atual: " + k.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) {
            // Simular edição por recriação (supondo setters não existem)
            Morada morada = k.getMorada();
            List<Compartimento> compartimentos = k.getCompartimentos();
            Responsavel manutencao = k.getManutencao();
            Responsavel higiene = k.getHigiene();
            Khikhipa editado = new Khikhipa(k.getId(), nome, morada, compartimentos, manutencao, higiene);
            khikhipas.set(khikhipas.indexOf(k), editado);
            System.out.println("Khikhipa atualizado.");
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

    // --------------------------Khikhitas--------------------------//

    private static void menuKhikhita() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- Menu Khikhita ---");
            System.out.println("[1] Listar Khikhitas");
            System.out.println("[2] Adicionar Khikhita (exemplo)");
            System.out.println("[3] Consultar morada do funcionário");
            System.out.println("[0] Voltar");
            System.out.print("Opção: ");
            String op = scanner.nextLine();
            switch (op) {
                case "1" -> listarKhikhitas();
                case "2" -> adicionarKhikhitaExemplo();
                case "3" -> consultarMoradaFuncionario();
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
        for (Khikhita k : khikhitas) {
            System.out.println("- " + k.getNome() + " | Máx por tipo: " + k.getMaximoPorTipo());
        }
    }

    private static void adicionarKhikhitaExemplo() {
        Morada morada = new Morada("Rua Tabaco", 5, "3500051", "Viseu");
        Funcionario f = new Funcionario("Luciana", morada);
        Map<String, Integer> limites = new HashMap<>();
        limites.put("S", 5);
        limites.put("M", 3);
        Khikhita novo = new Khikhita("KHITA001", "Tabacaria Viseu", morada, f, limites);
        khikhitas.add(novo);
        System.out.println("Khikhita adicionado.");
    }

    private static void consultarMoradaFuncionario() {
        if (khikhitas.isEmpty()) {
            System.out.println("Nenhum Khikhita disponível.");
            return;
        }
        System.out.println("Funcionário de " + khikhitas.get(0).getNome() + ": " +
                khikhitas.get(0).getFuncionario().getMorada().getRua());
    }

    private static void listarKhikhipas() {
        if (khikhipas.isEmpty()) {
            System.out.println("Nenhum Khikhipa disponível.");
            return;
        }
        for (Khikhipa k : khikhipas) {
            System.out.println("- " + k.getNome() + " | Compartimentos: " + k.getCompartimentos().size());
        }
    }

    private static void adicionarKhikhipaExemplo() {
        Morada morada = new Morada("Rua Exemplo", 10, "3500050", "Viseu");
        Responsavel man = new Responsavel("Carlos", "912345000");
        Responsavel hig = new Responsavel("Ana", "913456000");
        Compartimento c1 = new Compartimento("3504054200101", true, false, "PUB001", "PRIV001");
        List<Compartimento> compartimentos = List.of(c1);
        Khikhipa novo = new Khikhipa("350405420", "Khikhipa Demo", morada, compartimentos, man, hig);
        khikhipas.add(novo);
        System.out.println("Khikhipa de exemplo adicionado.");
    }

    // --------------------------Agendamentos--------------------------//
    private static void criarAgendamentoReal() {
        if (utilizadores.size() < 2) {
            System.out.println("É necessário pelo menos dois utilizadores (cliente e prestador).");
            return;
        }

        System.out.println("Tipo de quiosque para agendar:");
        System.out.println("[1] Khikhipa");
        System.out.println("[2] Khikhita");
        System.out.println("[3] Khikhivi");
        System.out.print("Escolha: ");
        String tipo = scanner.nextLine();

        System.out.println("Escolher utilizador A (cliente):");
        Utilizador a = escolherUtilizador();
        System.out.println("Escolher utilizador B (prestador):");
        Utilizador b = escolherUtilizador();

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

        System.out.print("Data entrega 1 (AAAA-MM-DD): ");
        LocalDate e1 = LocalDate.parse(scanner.nextLine());
        System.out.print("Data levantamento 1 (AAAA-MM-DD): ");
        LocalDate l1 = LocalDate.parse(scanner.nextLine());
        System.out.print("Data entrega 2 (AAAA-MM-DD): ");
        LocalDate e2 = LocalDate.parse(scanner.nextLine());
        System.out.print("Data levantamento 2 (AAAA-MM-DD): ");
        LocalDate l2 = LocalDate.parse(scanner.nextLine());

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

    // --------------------------Métodos auxiliares--------------------------//

    private static Utilizador escolherUtilizador() {
        for (int i = 0; i < utilizadores.size(); i++) {
            System.out.println("[" + i + "] " + utilizadores.get(i).getNome());
        }
        System.out.print("Escolha: ");
        int idx = Integer.parseInt(scanner.nextLine());
        return utilizadores.get(idx);
    }

    private static void guardarDados() {
        try {
            new ObjectOutputStream(new FileOutputStream("utilizadores.dat")).writeObject(utilizadores);
            new ObjectOutputStream(new FileOutputStream("khikhipas.dat")).writeObject(khikhipas);
            new ObjectOutputStream(new FileOutputStream("khikhitas.dat")).writeObject(khikhitas);
            new ObjectOutputStream(new FileOutputStream("khikhivis.dat")).writeObject(khikhivis);
            new ObjectOutputStream(new FileOutputStream("agendamentos.dat")).writeObject(agendamentos);
            new ObjectOutputStream(new FileOutputStream("recibos.dat")).writeObject(recibos);
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

            if (f1.exists())
                utilizadores = (List<Utilizador>) new ObjectInputStream(new FileInputStream(f1)).readObject();
            if (f2.exists())
                khikhipas = (List<Khikhipa>) new ObjectInputStream(new FileInputStream(f2)).readObject();
            if (f3.exists())
                khikhitas = (List<Khikhita>) new ObjectInputStream(new FileInputStream(f3)).readObject();
            if (f4.exists())
                khikhivis = (List<Khikhivi>) new ObjectInputStream(new FileInputStream(f4)).readObject();
            if (f5.exists())
                agendamentos = (List<TicketAgendamento>) new ObjectInputStream(new FileInputStream(f5)).readObject();
            if (f6.exists())
                recibos = (List<Recibo>) new ObjectInputStream(new FileInputStream(f6)).readObject();

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
