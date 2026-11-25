package padroescomportamentais.memento;

public class SituacaoEmAndamento implements SituacaoProcesso {

    private SituacaoEmAndamento() {};
    private static SituacaoEmAndamento instance = new SituacaoEmAndamento();
    public static SituacaoEmAndamento getInstance() {
        return instance;
    }

    public String getNomeSituacao() {
        return "Em andamento";
    }

}
