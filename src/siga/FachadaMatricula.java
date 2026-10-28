package siga;

import java.util.ArrayList;
import java.util.List;

public class FachadaMatricula {

    private final List<Matricula> armazem = new ArrayList<>();

    private final NotificadorEmail notificadorEmail = new NotificadorEmail();
    private final PainelVagas painelVagas = new PainelVagas();
    private final RegistroAuditoria registroAuditoria = new RegistroAuditoria();
    private final AvisoCoordenacao avisoCoordenacao = new AvisoCoordenacao();

    public Matricula matricular(Aluno aluno, String codigoTurma, boolean turmaLotada) {
        Matricula nova = new Matricula(aluno, codigoTurma);
        armazem.add(nova);

        notificadorEmail.enviarConfirmacao(nova);
        painelVagas.atualizar(codigoTurma);
        if (turmaLotada) {
            avisoCoordenacao.enviar(codigoTurma);
        }
        registroAuditoria.registrar("MATRICULA", aluno.getMatricula());

        return nova;
    }

    public List<Matricula> listar() {
        return new ArrayList<>(armazem);
    }
}
