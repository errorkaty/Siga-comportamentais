package siga;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FachadaMatricula {
    private final List<Matricula> armazem = new ArrayList<>();
    private final PublicadorMatricula publicador;

    public FachadaMatricula(PublicadorMatricula publicador) {
        this.publicador = Objects.requireNonNull(publicador);
    }

    public Matricula matricular(Aluno aluno, String codigoTurma, boolean turmaLotada,
            PoliticaDesconto politica) {
        Objects.requireNonNull(codigoTurma);
        if (codigoTurma.isBlank()) {
            throw new IllegalArgumentException("O codigo da turma deve ser informado.");
        }
        Matricula nova = new Matricula(aluno, codigoTurma, turmaLotada, politica);
        armazem.add(nova);
        publicador.publicar(nova);
        return nova;
    }

    public List<Matricula> listar() {
        return new ArrayList<>(armazem);
    }
}
