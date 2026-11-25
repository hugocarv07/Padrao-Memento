package padroescomportamentais.memento;

public class SituacaoDistribuido implements SituacaoProcesso {

    private SituacaoDistribuido() {};
    private static SituacaoDistribuido instance = new SituacaoDistribuido();
    public static SituacaoDistribuido getInstance() {
        return instance;
    }

    public String getNomeSituacao() {
        return "Distribuído";
    }

}
