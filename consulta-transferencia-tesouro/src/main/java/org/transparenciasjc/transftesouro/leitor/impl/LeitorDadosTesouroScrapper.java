package org.transparenciasjc.transftesouro.leitor.impl;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.transparenciasjc.transftesouro.leitor.LeitorDadosTesouro;
import org.transparenciasjc.transftesouro.leitor.TesouroScrapper;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.dto.DadosTransferencia;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;

/**
 * 
 * Um leitor de dados de transferência do site do tesouro que realiza a leitura
 * diretamente da página WEB
 * 
 * @author wsiqueir
 *
 */
@TesouroScrapper
public class LeitorDadosTesouroScrapper implements LeitorDadosTesouro {

	static private NumberFormat nfPtBr;
	static {
		Locale ptBr = new Locale("pt", "BR");
		nfPtBr = NumberFormat.getNumberInstance(ptBr);
	}

	@Override
	public TransferenciaDTO leDadosMunicipio(Municipio municipio) {
		Connection conexao = Jsoup.connect(URL_BASE_TESOURO);
		Map<String, String> params = configuraParametos(municipio);
		conexao.data(params).timeout(5000).method(Connection.Method.POST);
		TransferenciaDTO transferencia = new TransferenciaDTO();
		transferencia.setEstado(municipio.getEstado().getSigla());
		transferencia.setMunicipio(municipio.getNome());
		List<DadosTransferencia> dadosTransferencia = new ArrayList<DadosTransferencia>();
		try {
			Elements tabelaDados = conexao.execute().parse()
					.select("#Table5 tbody tr");
			Elements bases = tabelaDados.get(0).children();
			tabelaDados
					.stream()
					.skip(1)
					.forEach(el -> {
						Elements celulas = el.children();
						String mes = celulas.get(0).text();
						String ano = celulas.get(1).text();
						if (!mes.trim().isEmpty() && !ano.trim().isEmpty()) {
							// lendo os dados para cada base
							for (int i = 2; i < 11; i++) {
								DadosTransferencia dados = new DadosTransferencia();
								dados.setAno(Integer.parseInt(ano));
								dados.setMes(Integer.parseInt(mes));
								dados.setTipo(bases.get(i).text());
								Float valor = transformaEmFloat(celulas.get(i).text());
								dados.setValor(valor);
								dadosTransferencia.add(dados);
							}
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		transferencia.setDadosTransferencia(dadosTransferencia);
		return transferencia;
	}

	private Map<String, String> configuraParametos(Municipio municipio) {
		Map<String, String> params = parametrosPadroes();
		String sigla = municipio.getEstado().getSigla();
		String id = String.format("%04d", municipio.getId());
		params.put(PARAM_FORMATO, "TELA");
		params.put(PARAM_UF, sigla);
		params.put(PARAM_MUN_ID, id);
		return params;
	}
	
	private static Float transformaEmFloat(String txt) {
		try {
			return nfPtBr.parse(txt).floatValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

}
