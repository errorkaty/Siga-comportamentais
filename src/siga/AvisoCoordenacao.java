package siga;

public class AvisoCoordenacao {

    public void enviar(String codigoTurma) {
        throw new IllegalStateException(
                "servico de aviso indisponivel para " + codigoTurma);
    }
}
