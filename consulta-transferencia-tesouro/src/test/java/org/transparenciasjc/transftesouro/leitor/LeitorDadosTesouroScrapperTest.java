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
		List<DadosTransferencia> dadosFPM = filtraPorBase("FPM");
		float valorInicial = valorInicial(dadosFPM);
		float valorFinal = valorFinal(dadosFPM);
		System.out.println(valorInicial + " " + valorFinal) ;
		assertEquals(589,248.20f, valorInicial);
		assertEquals(3,400207.85f, valorFinal);
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