package siga;

public class SemDesconto implements PoliticaDesconto {
    @Override
    public double aplicar(double valorBase) {
        return valorBase * 1.0;
    }

    @Override
    public String getDescricao() {
        return "Sem desconto";
    }
}
