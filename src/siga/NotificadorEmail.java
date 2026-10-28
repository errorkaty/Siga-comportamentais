package siga;

public class NotificadorEmail {

    public void enviarConfirmacao(Matricula matricula) {
        System.out.println("   [e-mail] confirmacao para "
                + matricula.getAluno().getNome());
    }
}
