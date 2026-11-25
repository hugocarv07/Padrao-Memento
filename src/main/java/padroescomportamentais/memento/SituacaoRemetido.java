package padroescomportamentais.memento;

public class SituacaoRemetido implements SituacaoProcesso {

    private SituacaoRemetido() {};
    private static SituacaoRemetido instance = new SituacaoRemetido();
    public static SituacaoRemetido getInstance() {
        return instance;
    }

    public String getNomeSituacao() {
        return "Remetido a outra instância";
    }

}
