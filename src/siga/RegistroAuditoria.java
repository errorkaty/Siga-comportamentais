package siga;

public class RegistroAuditoria {

    public void registrar(String operacao, String matriculaAluno) {
        System.out.println("   [auditoria] " + operacao + " - " + matriculaAluno);
    }
}
