package org.transparenciasjc.transftesouro.leitor;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.BeforeClass;
import org.junit.Test;
import org.transparenciasjc.transftesouro.leitor.impl.LeitorDadosTesouroScrapper;
import org.transparenciasjc.transftesouro.model.Estado;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.dto.DadosTransferencia;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;

public class LeitorDadosTesouroScrapperTest {
	
	private static final int SJC_ID = 7099;
	private static final String EST_TEST = "SP";
	private static final String MUN_TEST = "São José dos Campos";
	private static TransferenciaDTO dadosMunicipio;

	@BeforeClass
	public static void coletaDados() {
		LeitorDadosTesouroScrapper leitor = new LeitorDadosTesouroScrapper();
		Municipio m = new Municipio(SJC_ID, MUN_TEST, new Estado(EST_TEST));
		dadosMunicipio = leitor.leDadosMunicipio(m);
	}
	
	@Test
	public void testaLeituraDadosBase() {
		assertEquals(MUN_TEST, dadosMunicipio.getMunicipio());
		assertEquals(EST_TEST, dadosMunicipio.getEstado());
	}
	
	@Test
	public void testaValoresFPM() {
		testaValores("FPM", 589248.20f, 3400207.85f);
	}
	
	@Test
	public void testaValoresITR() {
		testaValores("ITR", 0.74f, 1094.55f);
	}
	
	@Test
	public void testaValoresIOF() {
		testaValores("IOF", 0, 0);
	}
	
	@Test
	public void testaValoresLC8796() {
		testaValores("LC 87/96", 0, 0);
	}
	
	@Test
	public void testaValoresLC87961579() {
		testaValores("LC 87/96-1579", 0, 0);
	}
	
	@Test
	public void testaValoresCIDE() {
		testaValores("CIDE", 0, 0);
	}
	
	@Test
	public void testaValoresFEX() {
		testaValores("FEX", 0, 0);
	}
	
	@Test
	public void testaValoresFUNDEF() {
		testaValores("FUNDEF", 0, 0);
	}
	
	@Test
	public void testaValoresFUNDEB() {
		testaValores("FUNDEB", 0, 17473269.75f);
	}
	
	public void testaValores(String base, float valorInicialExperado, float valorFinalEsperado) {
		List<DadosTransferencia> dados = filtraPorBase(base);
		float valorInicial = valorInicial(dados);
		float valorFinal = valorFinal(dados);
		assertEquals(valorInicialExperado, valorInicial, 0.1);
		assertEquals(valorFinalEsperado, valorFinal, 0.1);
	}

	private float valorInicial(List<DadosTransferencia> dados) {
		return dados.stream().filter(d -> d.getAno() == 1996 && d.getMes() == 1).findFirst().get().getValor();
	}
	private float valorFinal(List<DadosTransferencia> dados) {
		return dados.stream().filter(d -> d.getAno() == 2016 && d.getMes() == 2).findFirst().get().getValor();
	}

	private List<DadosTransferencia> filtraPorBase(String base) {
		return dadosMunicipio.getDadosTransferencia().stream().peek(d -> System.out.println(d.getTipo())).filter(d -> d.getTipo().equals(base)).collect(Collectors.toList());
	}

}