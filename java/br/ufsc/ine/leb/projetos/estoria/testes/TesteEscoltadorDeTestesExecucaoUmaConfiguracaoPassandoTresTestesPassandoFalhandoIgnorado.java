package br.ufsc.ine.leb.projetos.estoria.testes;

import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteFalha;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteFinalizado;
import static br.ufsc.ine.leb.projetos.estoria.CombinadorDeNotificacao.combinaComTesteIgnorado;
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

public final class TesteEscoltadorDeTestesExecucaoUmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado {

	private Iterator<Notificacao> notificacoes;

	@Before
	public void prepararCenario() {
		SuiteDeTeste suite = new SuiteDeTeste(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class);
		EscoltadorDeTestes escoltador = new EscoltadorDeTestes(suite);
		RunNotifier mensageiroDeEscolta = new RunNotifier();
		EspiaoDeEscolta espiaoDeEscolta = new EspiaoDeEscolta();
		mensageiroDeEscolta.addFirstListener(espiaoDeEscolta);
		escoltador.run(mensageiroDeEscolta);
		notificacoes = espiaoDeEscolta.obterNotificacoes().iterator();
	}

	@Test
	public void testar() throws Exception {
		assertThat(notificacoes.next(), combinaComTestesIniciados(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class));
		assertThat(notificacoes.next(), combinaComTesteIgnorado(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class, "testar3"));
		assertThat(notificacoes.next(), combinaComTesteIniciado(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class, "testar1"));
		assertThat(notificacoes.next(), combinaComTesteFinalizado(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class, "testar1"));
		assertThat(notificacoes.next(), combinaComTesteIniciado(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class, "testar2"));
		assertThat(notificacoes.next(), combinaComTesteFalha(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class, "testar2", AssertionError.class));
		assertThat(notificacoes.next(), combinaComTesteFinalizado(UmaConfiguracaoPassandoTresTestesPassandoFalhandoIgnorado.class, "testar2"));
		assertThat(notificacoes.next(), combinaComTestesFinalizados(2, 1, 1));
		assertFalse(notificacoes.hasNext());
	}

}
