package siga;

import java.util.HashMap;
import java.util.Map;

public class EstatisticaOcupacao implements OuvinteMatricula {
    private final Map<String, Integer> matriculasPorTurma = new HashMap<>();

    @Override
    public void aoMatricular(Matricula matricula) {
        String codigoTurma = matricula.getCodigoTurma();
        int total = matriculasPorTurma.merge(codigoTurma, 1, Integer::sum);
        System.out.printf("   [estatistica] %s: %d matricula(s)%n", codigoTurma, total);
    }

    public int getTotalMatriculas(String codigoTurma) {
        return matriculasPorTurma.getOrDefault(codigoTurma, 0);
    }
}
