package br.ufsc.ine.leb.projetos.estoria.testes.figuracao.testes.diversos;

import org.junit.*;

public class UmTesteFalhandoExcecaoEsperadaSubclasse {

	@Test(expected = UnsupportedOperationException.class)
	public void testar() {
		throw new RuntimeException();
	}

}