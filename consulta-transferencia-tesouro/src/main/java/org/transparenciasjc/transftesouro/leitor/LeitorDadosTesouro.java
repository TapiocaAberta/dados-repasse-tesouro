package org.transparenciasjc.transftesouro.leitor;

import java.util.HashMap;
import java.util.Map;

import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.dto.TransferenciaDTO;

/**
 * 
 * Interface para ler os dados de transferência do site do tesouro (dados podem
 * vir da página diretamente ou de um XLS)
 * 
 * @author wsiqueir
 *
 */
public interface LeitorDadosTesouro {

	/**
	 * A URL para ler dados do servidor
	 */
	static final String URL_BASE_TESOURO = "http://www3.tesouro.gov.br/ESTADOS_MUNICIPIOS/municipios_novosite.asp";

	/**
	 * Parâmetro da requisição com a sigla do município. exemplo de valores: SP,
	 * RJ, PE...
	 */
	static final String PARAM_UF = "P_UF";
	/**
	 * Parâmetro nome. Sepre vazio.
	 */
	static final String PARAM_NOME = "nome";
	/**
	 * O ID do município
	 */
	static final String PARAM_MUN_ID = "ente";
	/**
	 * O fundo que está se consultando. Valor padrão = TODOS
	 */
	static final String PARAM_FUNDOS = "P_FUNDOS";
	/**
	 * O ano da requisição. Usar "%" para todos
	 */
	static final String PARAM_ANO = "P_ANO";
	/**
	 * O mês da requisição. Usar "%" para todos
	 */
	static final String PARAM_MES = "P_MES";
	/**
	 * O formato da resposta. TELA para uma página HTML e DOWNLOAD para XLS
	 */
	static final String PARAM_FORMATO = "ORMATO";
	/**
	 * Pos X do mouse, usar sempre 1 por exemplo
	 */
	static final String PARAM_SUBMITX = "SUBMIT.x";
	/**
	 * Pos Y do mouse, usar sempre 1 por exemplo
	 */
	static final String PARAM_SUBMITY = "SUBMIT.y";

	public TransferenciaDTO leDadosMunicipio(Municipio municipio);
	
	public default Map<String, String> parametrosPadroes() {
		Map<String, String> params = new HashMap<String, String>();
		params.put(PARAM_FUNDOS, "TODOS");
		params.put(PARAM_NOME, "");
		params.put(PARAM_ANO, "%");
		params.put(PARAM_MES, "%");
		params.put(PARAM_SUBMITX, "1");
		params.put(PARAM_SUBMITY, "1");
		return params;
	}

}
