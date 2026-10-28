package siga;

import java.util.Objects;

public class Matricula {
    private final Aluno aluno;
    private final String codigoTurma;
    private final boolean turmaLotada;
    private final PoliticaDesconto politica;

    public Matricula(Aluno aluno, String codigoTurma, boolean turmaLotada, PoliticaDesconto politica) {
        this.aluno = Objects.requireNonNull(aluno);
        this.codigoTurma = Objects.requireNonNull(codigoTurma);
        this.turmaLotada = turmaLotada;
        this.politica = Objects.requireNonNull(politica);
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public boolean isTurmaLotada() {
        return turmaLotada;
    }

    public PoliticaDesconto getPolitica() {
        return politica;
    }

    public double calcularValor(double valorBase) {
        return politica.aplicar(valorBase);
    }

    @Override
    public String toString() {
        return aluno.getNome() + " em " + codigoTurma;
    }
}
