package siga;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PublicadorMatricula {
    private final List<OuvinteMatricula> ouvintes = new ArrayList<>();

    public void registrar(OuvinteMatricula ouvinte) {
        Objects.requireNonNull(ouvinte);
        if (!ouvintes.contains(ouvinte)) {
            ouvintes.add(ouvinte);
        }
    }

    public void remover(OuvinteMatricula ouvinte) {
        ouvintes.remove(ouvinte);
    }

    public void publicar(Matricula matricula) {
        for (OuvinteMatricula ouvinte : new ArrayList<>(ouvintes)) {
            try {
                ouvinte.aoMatricular(matricula);
            } catch (RuntimeException excecao) {
                System.out.printf("   [erro] %s: %s%n", ouvinte.getClass().getSimpleName(),
                        excecao.getMessage());
            }
        }
    }
}
