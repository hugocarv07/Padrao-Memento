package padroescomportamentais.memento;

public class SituacaoSentenciado implements SituacaoProcesso {

    private SituacaoSentenciado() {};
    private static SituacaoSentenciado instance = new SituacaoSentenciado();
    public static SituacaoSentenciado getInstance() {
        return instance;
    }

    public String getNomeSituacao() {
        return "Sentenciado";
    }

}
