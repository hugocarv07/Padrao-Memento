package padroescomportamentais.memento;

public class SituacaoSuspenso implements SituacaoProcesso {

    private SituacaoSuspenso() {};
    private static SituacaoSuspenso instance = new SituacaoSuspenso();
    public static SituacaoSuspenso getInstance() {
        return instance;
    }

    public String getNomeSituacao() {
        return "Suspenso";
    }

}
