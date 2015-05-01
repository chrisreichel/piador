package br.tur.reservafacil.piador;

import org.junit.Test;

import br.tur.reservafacil.piador.pio.Pio;

import static org.junit.Assert.assertEquals;

public class PioTest {

	@Test
	public void pioNaoDeveTerMaisQueQuantidadeCaracteresPermitido() {

		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < 150; i++) {
			stringBuilder.append("a");
		}
		Pio pio = new Pio("@linngallo", stringBuilder.toString());

		assertEquals(pio.getConteudo().length(), 140);

	}

}
