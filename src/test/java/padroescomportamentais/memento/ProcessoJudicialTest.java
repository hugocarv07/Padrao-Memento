package padroescomportamentais.memento;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessoJudicialTest {

    @Test
    void deveArmazenarSituacoes() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoSentenciado.getInstance());
        assertEquals(2, processo.getSituacoes().size());
    }

    @Test
    void deveRetornarSituacaoInicial() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoSentenciado.getInstance());
        processo.restauraSituacao(0);
        assertEquals(SituacaoDistribuido.getInstance(), processo.getSituacao());
    }

    @Test
    void deveRetornarSituacaoAnterior() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoSuspenso.getInstance());
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoSentenciado.getInstance());
        processo.restauraSituacao(2);
        assertEquals(SituacaoDistribuido.getInstance(), processo.getSituacao());
    }

    @Test
    void deveRetornarExcecaoIndiceInvalido() {
        try {
            ProcessoJudicial processo = new ProcessoJudicial("0001");
            processo.restauraSituacao(0);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Índice inválido", e.getMessage());
        }
    }

    @Test
    void deveRejeitarIndiceNegativoEIndiceAlemDoHistorico() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoEmAndamento.getInstance());

        IllegalArgumentException negativo = assertThrows(IllegalArgumentException.class,
                () -> processo.restauraSituacao(-1));
        IllegalArgumentException alem = assertThrows(IllegalArgumentException.class,
                () -> processo.restauraSituacao(2));

        assertEquals("Índice inválido", negativo.getMessage());
        assertEquals("Índice inválido", alem.getMessage());
        assertEquals(SituacaoEmAndamento.getInstance(), processo.getSituacao());
    }

    @Test
    void situacaoInicialEhNulaEHistoricoVazio() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");

        assertNull(processo.getSituacao());
        assertTrue(processo.getSituacoes().isEmpty());
    }

    @Test
    void historicoMantemAOrdemDosLancamentos() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoEmAndamento.getInstance());
        processo.setSituacao(SituacaoArquivado.getInstance());

        assertEquals(List.of(SituacaoDistribuido.getInstance(), SituacaoEmAndamento.getInstance(),
                SituacaoArquivado.getInstance()), processo.getSituacoes());
    }

    @Test
    void restaurarNaoAlteraOHistorico() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoArquivado.getInstance());

        processo.restauraSituacao(0);

        assertEquals(2, processo.getSituacoes().size());
        assertEquals(SituacaoDistribuido.getInstance(), processo.getSituacao());
    }

    @Test
    void novaSituacaoAposRestauracaoEhAdicionadaAoFinalDoHistorico() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());
        processo.setSituacao(SituacaoArquivado.getInstance());
        processo.restauraSituacao(0);

        processo.setSituacao(SituacaoRemetido.getInstance());

        assertEquals(3, processo.getSituacoes().size());
        assertEquals(SituacaoRemetido.getInstance(), processo.getSituacao());
    }

    @Test
    void situacaoNulaEhRejeitadaESemAlterarOHistorico() {
        ProcessoJudicial processo = new ProcessoJudicial("0001");
        processo.setSituacao(SituacaoDistribuido.getInstance());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> processo.setSituacao(null));

        assertEquals("Situação inválida", ex.getMessage());
        assertEquals(1, processo.getSituacoes().size());
        assertEquals(SituacaoDistribuido.getInstance(), processo.getSituacao());
    }

    @Test
    void processosTemHistoricosIndependentes() {
        ProcessoJudicial a = new ProcessoJudicial("0001");
        ProcessoJudicial b = new ProcessoJudicial("0002");

        a.setSituacao(SituacaoDistribuido.getInstance());
        a.setSituacao(SituacaoArquivado.getInstance());
        b.setSituacao(SituacaoSuspenso.getInstance());

        assertEquals("0001", a.getNumero());
        assertEquals(2, a.getSituacoes().size());
        assertEquals(1, b.getSituacoes().size());
        assertEquals(SituacaoSuspenso.getInstance(), b.getSituacao());
    }
}
