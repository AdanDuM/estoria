package br.ufsc.ine.leb.projetos.estoria.testes;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.FiltradorDeMetodos;
import br.ufsc.ine.leb.projetos.estoria.testes.figuracao.classes.ClasseVazia;

public final class TesteFiltradorDeMetodosClasseVazia {

	private FiltradorDeMetodos filtrador;

	@Before
	public void prepararCenario() {
		filtrador = new FiltradorDeMetodos(ClasseVazia.class);
	}

	@Test
	public void classeVazia() throws Exception {
		List<Method> metodos = filtrador.obterMetodos();
		assertEquals(0, metodos.size());
	}

}
