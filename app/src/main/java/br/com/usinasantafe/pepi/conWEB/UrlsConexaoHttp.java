package br.com.usinasantafe.pepi.conWEB;

public class UrlsConexaoHttp {

	private int tipoEnvio = 1;


	public static String urlPrincipal = "http://www.usinasantafe.com.br/pepi/";

	public static String urlPrincEnvio = "http://www.usinasantafe.com.br/pepi/";

	//public static String localPSTVariavel = "br.com.usinasantafe.ecm.to.tb.variaveis.";
	public static String localPSTEstatica = "br.com.usinasantafe.pepi.to.tb.estaticas.";
	public static String localUrl = "br.com.usinasantafe.pepi.conWEB.UrlsConexaoHttp";
	
	public static String EPITO = urlPrincipal + "epi.php";
	public static String FuncTO = urlPrincipal + "func.php";
	public static String MotivoTO = urlPrincipal + "motivo.php";
	
	public UrlsConexaoHttp() {
		// TODO Auto-generated constructor stub
	}

	public String getsEntregaEPI() {
		if(tipoEnvio == 1)
		return urlPrincEnvio + "insentregaepi.php";
		else
		return urlPrincEnvio + "insentregaepiteste.php";
	}

	public String urlVerifica(String classe) {
		String retorno = "";
		if (classe.equals("OS")) {
			retorno = urlPrincEnvio + "veros.php";
		} else if (classe.equals("Ativ")) {
			retorno = urlPrincEnvio + "atualosativ.php";
		} else if (classe.equals("Atualiza")) {
			retorno = urlPrincEnvio + "atualizaaplic.php";
		}
		return retorno;
	}

}
