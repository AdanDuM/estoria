package br.ufsc.ine.leb.projetos.estoria.testes;

import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteFalha;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteFinalizado;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteIniciado;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTestesFinalizados;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTestesIniciados;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.*;

import org.junit.*;
import org.junit.runner.notification.*;

import br.ufsc.ine.leb.projetos.estoria.*;
import br.ufsc.ine.leb.projetos.estoria.testes.figuracao.testes.classesComConfiguracao.*;

public final class TesteEscoltadorDeTestesExecucaoUmaConfiguracaoFalhandoUmTesteFalhando {

	private Iterator<Notificacao> notificacoes;

	@Before
	public void prepararCenario() {
		SuiteDeTeste suite = new SuiteDeTeste(UmaConfiguracaoFalhandoUmTesteFalhando.class);
		EscoltadorDeTestes escoltador = new EscoltadorDeTestes(suite);
		RunNotifier mensageiroDeEscolta = new RunNotifier();
		EspiaoDeEscolta espiaoDeEscolta = new EspiaoDeEscolta();
		mensageiroDeEscolta.addFirstListener(espiaoDeEscolta);
		escoltador.run(mensageiroDeEscolta);
		notificacoes = espiaoDeEscolta.obterNotificacoes().iterator();
	}

	@Test
	public void testar() throws Exception {
		assertThat(notificacoes.next(), combinaComTestesIniciados(UmaConfiguracaoFalhandoUmTesteFalhando.class));
		assertThat(notificacoes.next(), combinaComTesteIniciado(UmaConfiguracaoFalhandoUmTesteFalhando.class, "testar"));
		assertThat(notificacoes.next(), combinaComTesteFalha(UmaConfiguracaoFalhandoUmTesteFalhando.class, "testar", AssertionError.class, "Falha no before"));
		assertThat(notificacoes.next(), combinaComTesteFalha(UmaConfiguracaoFalhandoUmTesteFalhando.class, "testar", AssertionError.class, "Falha no test"));
		assertThat(notificacoes.next(), combinaComTesteFinalizado(UmaConfiguracaoFalhandoUmTesteFalhando.class, "testar"));
		assertThat(notificacoes.next(), combinaComTestesFinalizados(1, 2, 0));
		assertFalse(notificacoes.hasNext());
	}

}
