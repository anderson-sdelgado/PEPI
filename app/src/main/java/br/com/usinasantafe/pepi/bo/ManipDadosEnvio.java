package br.com.usinasantafe.pepi.bo;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pepi.conWEB.ConHttpPostCadGenerico;
import br.com.usinasantafe.pepi.conWEB.UrlsConexaoHttp;
import br.com.usinasantafe.pepi.to.tb.variaveis.EntregaEPITO;

public class ManipDadosEnvio {
	
	private static ManipDadosEnvio instance = null;
	private UrlsConexaoHttp urlsConexaoHttp;
	private int statusEnvio; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
	private boolean enviando = false;
	
	public ManipDadosEnvio() {
		// TODO Auto-generated constructor stub
		urlsConexaoHttp = new UrlsConexaoHttp();
	}
	
    public static ManipDadosEnvio getInstance() {
    	if (instance == null){
        	instance = new ManipDadosEnvio();
        }
        return instance;
    }


	public void delEntregaEPI(){
		EntregaEPITO entregaEPITO = new EntregaEPITO();
		entregaEPITO.deleteAll();
	}

	public List dadosEntregaEPI(){
		EntregaEPITO entregaEPITO = new EntregaEPITO();
		List listEntregaEPI = entregaEPITO.all();
		return listEntregaEPI;
	}

	public Boolean verifDadosEntregaEPI() {
		return dadosEntregaEPI().size() > 0;
	}

	public void envioEntregaEPI(){
		
        JsonArray jsonArray = new JsonArray();

		List<EntregaEPITO> listEntregaEPI = dadosEntregaEPI();

		for(EntregaEPITO entregaEPITO : listEntregaEPI){
			Gson gson = new Gson();
			jsonArray.add(gson.toJsonTree(entregaEPITO, entregaEPITO.getClass()));
		}
        
        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);
		
        Log.i("pepi", "LISTA = " + json.toString());

		Log.i("pepi", "url = " + urlsConexaoHttp.getsEntregaEPI());
		String[] url = {urlsConexaoHttp.getsEntregaEPI()};
		Map<String, Object> parametrosPost = new HashMap<String, Object>();
		parametrosPost.put("dados", json.toString());

		ConHttpPostCadGenerico conHttpPostCadGenerico = new ConHttpPostCadGenerico();
		conHttpPostCadGenerico.setParametrosPost(parametrosPost);
		conHttpPostCadGenerico.execute(url);

	}

	public void envioDados(Context context) {
		ConexaoWeb conexaoWeb = new ConexaoWeb();
		if (conexaoWeb.verificaConexao(context)) {
			envioEntregaEPI();
		}
	}

	public boolean verifDadosEnvio() {
		if (!verifDadosEntregaEPI()){
			enviando = false;
			return false;
		} else {
			return true;
		}
	}

	public int getStatusEnvio() {
		if (enviando) {
			statusEnvio = 1;
		} else {
			if (!verifDadosEnvio()) {
				statusEnvio = 3;
			} else {
				statusEnvio = 2;
			}
		}
		return statusEnvio;
	}

	public void setEnviando(boolean enviando) {
		this.enviando = enviando;
	}

	public void setStatusEnvio(int statusEnvio) {
		this.statusEnvio = statusEnvio;
	}

}
