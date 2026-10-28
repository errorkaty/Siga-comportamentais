package siga;

public class Matricula {

    private final Aluno aluno;
    private final String codigoTurma;

    public Matricula(Aluno aluno, String codigoTurma) {
        this.aluno = aluno;
        this.codigoTurma = codigoTurma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public double calcularValor(double valorBase) {
        if (aluno.isBolsista()) {
            return valorBase * 0.50;
        } else if (aluno.isServidor()) {
            return valorBase * 0.80;
        } else if (aluno.isEgresso()) {
            return valorBase * 0.90;
        }
        return valorBase;
    }

    @Override
    public String toString() {
        return aluno.getNome() + " em " + codigoTurma;
    }
}
