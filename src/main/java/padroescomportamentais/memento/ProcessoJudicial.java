package padroescomportamentais.memento;

import java.util.ArrayList;
import java.util.List;

public class ProcessoJudicial {

    private String numero;
    private SituacaoProcesso situacao;
    private List<SituacaoProcesso> memento = new ArrayList<SituacaoProcesso>();

    public ProcessoJudicial(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return this.numero;
    }

    public SituacaoProcesso getSituacao() {
        return this.situacao;
    }

    public void setSituacao(SituacaoProcesso situacao) {
        if (situacao == null) {
            throw new IllegalArgumentException("Situação inválida");
        }
        this.situacao = situacao;
        this.memento.add(this.situacao);
    }

    public void restauraSituacao(int indice) {
        if (indice < 0 || indice > this.memento.size() - 1) {
            throw new IllegalArgumentException("Índice inválido");
        }
        this.situacao = this.memento.get(indice);
    }

    public List<SituacaoProcesso> getSituacoes() {
        return this.memento;
    }
}
