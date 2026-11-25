package padroescomportamentais.memento;

public class SituacaoArquivado implements SituacaoProcesso {

    private SituacaoArquivado() {};
    private static SituacaoArquivado instance = new SituacaoArquivado();
    public static SituacaoArquivado getInstance() {
        return instance;
    }

    public String getNomeSituacao() {
        return "Arquivado";
    }

}
