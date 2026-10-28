package siga;

public class GeradorBoleto {
    public double calcularValorDoBoleto(Matricula matricula, double valorBase) {
        return matricula.calcularValor(valorBase);
    }

    public void emitir(Matricula matricula, double valorBase) {
        System.out.printf("   [boleto] %s: R$ %.2f%n", matricula.getAluno().getNome(),
                calcularValorDoBoleto(matricula, valorBase));
    }
}
