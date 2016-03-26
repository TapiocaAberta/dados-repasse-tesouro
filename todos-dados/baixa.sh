for UF in 'GO' 'MT' 'MS' 'DF' 'AM' 'AC' 'RO' 'RR' 'AP' 'TO' 'PA' 'MA' 'PI' 'CE' 'RN' 'PB' 'PE' 'SE' 'AL' 'BA' 'SP' 'MG' 'RJ' 'ES' 'PR' 'SC' 'RS' 
do 
	wget --post-data "P_UF=$UF&nome=&ente=%25&P_FUNDOS=TODOS&P_ANO=%25&P_MES=%25&ORMATO=DOWNLOAD&SUBMIT.x=16&SUBMIT.y=1" -O "$UF.xls" http://www3.tesouro.gov.br/ESTADOS_MUNICIPIOS/municipios_novosite.asp
done
zip arquivos_repasse_1996_2015.zip *xls
rm *xls
