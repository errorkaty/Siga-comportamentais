package siga;

import java.util.Locale;

public class Main {
    public static void main(String[] argumentos) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR"));
        System.out.println("=== SIGA - Aula 12: Strategy e Observer ===");

        Aluno bolsista = new Aluno("2024001", "Maria Silva");
        bolsista.setBolsista(true);
        Aluno servidor = new Aluno("2024002", "Joao Souza");
        servidor.setServidor(true);
        Aluno egresso = new Aluno("2024003", "Ana Pereira");
        egresso.setEgresso(true);
        Aluno regular = new Aluno("2024004", "Pedro Santos");

        PublicadorMatricula publicador = new PublicadorMatricula();
        publicador.registrar(new NotificadorEmail());
        publicador.registrar(new PainelVagas());
        publicador.registrar(new AvisoCoordenacao());
        publicador.registrar(new RegistroAuditoria());
        publicador.registrar(new EstatisticaOcupacao());
        FachadaMatricula fachada = new FachadaMatricula(publicador);
        GeradorBoleto gerador = new GeradorBoleto();
        double valorBase = 800.00;

        demonstrar(fachada, gerador, bolsista, new DescontoBolsista(), false, valorBase);
        demonstrar(fachada, gerador, servidor, new DescontoServidor(), false, valorBase);
        demonstrar(fachada, gerador, egresso, new DescontoEgresso(), true, valorBase);
        demonstrar(fachada, gerador, regular, new SemDesconto(), false, valorBase);
        System.out.printf("%nMatriculas registradas: %d%n", fachada.listar().size());
    }

    private static void demonstrar(FachadaMatricula fachada, GeradorBoleto gerador,
            Aluno aluno, PoliticaDesconto politica, boolean turmaLotada, double valorBase) {
        System.out.printf("%n%s - %s%n", aluno.getNome(), politica.getDescricao());
        Matricula matricula = fachada.matricular(aluno, "DSM-2A", turmaLotada, politica);
        System.out.printf("   [matricula] R$ %.2f%n", matricula.calcularValor(valorBase));
        gerador.emitir(matricula, valorBase);
    }
}
