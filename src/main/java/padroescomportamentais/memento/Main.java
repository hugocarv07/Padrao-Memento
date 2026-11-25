package padroescomportamentais.memento;

public class Main {

    public static void main(String[] args) {
        ProcessoJudicial processo = new ProcessoJudicial("0001234-56.2026.8.13.0145");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoEmAndamento.getInstance());
        processo.setSituacao(SituacaoSuspenso.getInstance());
        processo.setSituacao(SituacaoArquivado.getInstance());

        System.out.println("Situação atual: " + processo.getSituacao().getNomeSituacao());

        processo.restauraSituacao(1);
        System.out.println("Arquivamento lançado por engano, restaurada: " + processo.getSituacao().getNomeSituacao());

        System.out.println("Histórico:");
        for (SituacaoProcesso situacao : processo.getSituacoes()) {
            System.out.println(" - " + situacao.getNomeSituacao());
        }
    }
}
