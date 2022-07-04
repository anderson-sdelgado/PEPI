package br.com.usinasantafe.pepi.util.conHttp;

import br.com.usinasantafe.pepi.PEPIContext;

public class UrlsConexaoHttp {

	public static String versao = "versao_" + PEPIContext.versaoWS.replace(".", "_");

	public static String url = "https://www.usinasantafe.com.br/pepidev/view/";
//	public static String url = "https://www.usinasantafe.com.br/pepiqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/pepiprod/" + versao + "/view/";

	public static String localPSTEstatica = "br.com.usinasantafe.pepi.model.bean.estaticas.";
	public static String localUrl = "br.com.usinasantafe.pepi.util.conHttp.UrlsConexaoHttp";
	
	public static String EPIBean = url + "epi.php";
	public static String FuncBean = url + "func.php";
	public static String MotivoBean = url + "motivo.php";
	
	public UrlsConexaoHttp() {
	}

	public String getsEntregaEPI() {
		return url + "insentregaepi.php";
	}

	public String urlVerifica(String classe) {
		String retorno = "";
		if (classe.equals("OS")) {
			retorno = url + "veros.php";
		} else if (classe.equals("Ativ")) {
			retorno = url + "atualosativ.php";
		} else if (classe.equals("Atualiza")) {
			retorno = url + "atualaplic.php";
		}
		return retorno;
	}

}
