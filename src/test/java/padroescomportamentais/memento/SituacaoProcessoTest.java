package padroescomportamentais.memento;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class SituacaoProcessoTest {

    @Test
    void cadaSituacaoTemSeuNome() {
        assertEquals("Distribuído", SituacaoDistribuido.getInstance().getNomeSituacao());
        assertEquals("Em andamento", SituacaoEmAndamento.getInstance().getNomeSituacao());
        assertEquals("Suspenso", SituacaoSuspenso.getInstance().getNomeSituacao());
        assertEquals("Sentenciado", SituacaoSentenciado.getInstance().getNomeSituacao());
        assertEquals("Arquivado", SituacaoArquivado.getInstance().getNomeSituacao());
        assertEquals("Remetido a outra instância", SituacaoRemetido.getInstance().getNomeSituacao());
    }

    @Test
    void situacoesSaoSingletons() {
        assertSame(SituacaoDistribuido.getInstance(), SituacaoDistribuido.getInstance());
        assertSame(SituacaoEmAndamento.getInstance(), SituacaoEmAndamento.getInstance());
        assertSame(SituacaoSuspenso.getInstance(), SituacaoSuspenso.getInstance());
        assertSame(SituacaoSentenciado.getInstance(), SituacaoSentenciado.getInstance());
        assertSame(SituacaoArquivado.getInstance(), SituacaoArquivado.getInstance());
        assertSame(SituacaoRemetido.getInstance(), SituacaoRemetido.getInstance());
    }

    @Test
    void situacoesDiferentesNaoSaoIguais() {
        assertNotEquals(SituacaoDistribuido.getInstance(), SituacaoArquivado.getInstance());
    }

    @Test
    void construtoresSaoPrivados() {
        Class<?>[] classes = {SituacaoDistribuido.class, SituacaoEmAndamento.class, SituacaoSuspenso.class,
                SituacaoSentenciado.class, SituacaoArquivado.class, SituacaoRemetido.class};
        for (Class<?> classe : classes) {
            Constructor<?>[] construtores = classe.getDeclaredConstructors();
            assertEquals(1, construtores.length);
            assertTrue(Modifier.isPrivate(construtores[0].getModifiers()));
        }
    }
}
