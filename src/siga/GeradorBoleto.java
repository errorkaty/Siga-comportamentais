package siga;

public class GeradorBoleto {

    public double calcularValorDoBoleto(Aluno aluno, double valorBase) {
        if (aluno.isBolsista()) {
            return valorBase * 0.50;
        } else if (aluno.isServidor()) {
            return valorBase * 0.80;
        }
        return valorBase;
    }

    public void emitir(Aluno aluno, double valorBase) {
        double valor = calcularValorDoBoleto(aluno, valorBase);
        System.out.printf("   [boleto] %s: R$ %.2f%n", aluno.getNome(), valor);
    }
}
