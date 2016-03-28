package org.transparenciasjc.transftesouro.carga;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.transparenciasjc.transftesouro.model.Estado;
import org.transparenciasjc.transftesouro.model.Municipio;
import org.transparenciasjc.transftesouro.model.Fundo;
import org.transparenciasjc.transftesouro.service.impl.EstadoService;
import org.transparenciasjc.transftesouro.service.impl.MunicipioService;
import org.transparenciasjc.transftesouro.service.impl.FundoService;

/**
 * 
 * Realiza a carga de dados base do sistema
 * 
 * @author wsiqueir
 *
 */
@Startup
@Singleton
public class CargaDadosBase {

	private static final String FUNDO_PROP = "/dados/fundos.properties";

	private static final String MUNICIPIOS_CSV = "/dados/municipios.csv";

	private static final String SEPARADOR_MUN = "\\,";

	@Inject
	EstadoService estadoService;

	@Inject
	MunicipioService municipioService;

	@Inject
	FundoService fundoService;

	@PostConstruct
	public void cargaDadosBase() throws URISyntaxException, IOException {
		carregaMunicipios();
		carregaTipoTransferencias();
	}

	private void carregaTipoTransferencias() throws IOException {
		Properties tiposTransfProp = new Properties();
		InputStream tiposTransf = getClass().getResourceAsStream(FUNDO_PROP);
		tiposTransfProp.load(tiposTransf);
		tiposTransfProp.forEach(this::salvaTransferencia);

	}

	private void salvaTransferencia(Object nome, Object desc) {
		Fundo fundo = new Fundo();
		fundo.setNome(nome.toString());
		fundo.setDescricao(desc.toString());
		fundoService.buscaFundoPorNomeOuCria(nome.toString(), () -> fundo);
	}

	private void carregaMunicipios() {
		InputStream municipios = getClass().getResourceAsStream(MUNICIPIOS_CSV);
		Scanner sc = new Scanner(municipios);
		sc.useDelimiter("\\n").forEachRemaining(this::salvaMunicipio);
		sc.close();
	}

	public void salvaMunicipio(String linha) {
		String colunas[] = linha.split(SEPARADOR_MUN);
		Municipio municipio = leMunicipio(colunas);
		municipioService.atualizar(municipio);
	}

	private Municipio leMunicipio(String[] colunas) {
		Estado estado = leEstado(colunas);
		long id = Long.parseLong(colunas[0]);
		String nome = colunas[1];
		return new Municipio(id, nome, estado);
	}

	private Estado leEstado(String[] colunas) {
		String sigla = colunas[2];
		String nome = colunas[3];
		Estado estado = new Estado(sigla, nome);
		return salvaEstado(estado);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private Estado salvaEstado(Estado estado) {
		String sigla = estado.getSigla();
		return estadoService.buscaEstadoPorSiglaOuCria(sigla, () -> estado);
	}

}