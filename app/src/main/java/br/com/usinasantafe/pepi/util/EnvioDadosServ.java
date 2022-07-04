package br.com.usinasantafe.pepi.util;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.usinasantafe.pepi.control.EntregaEPICTR;
import br.com.usinasantafe.pepi.model.bean.variaveis.EntregaEPIBean;
import br.com.usinasantafe.pepi.util.conHttp.PostCadGenerico;
import br.com.usinasantafe.pepi.util.conHttp.UrlsConexaoHttp;
import br.com.usinasantafe.pepi.view.ActivityGeneric;

public class EnvioDadosServ {
	
	private static EnvioDadosServ instance = null;
	private UrlsConexaoHttp urlsConexaoHttp;
	public static int status; //1 - Enviando; 2 - Existe Dados para Enviar; 3 - Todos os Dados Enviados
	
	public EnvioDadosServ() {
		urlsConexaoHttp = new UrlsConexaoHttp();
	}
	
    public static EnvioDadosServ getInstance() {
    	if (instance == null){
        	instance = new EnvioDadosServ();
        }
        return instance;
    }

	public boolean verifDadosEntregaEPI() {
		EntregaEPICTR entregaEPICTR = new EntregaEPICTR();
		return entregaEPICTR.verEntregaEPI();
	}

	public void envioEntregaEPI(){
		
        JsonArray jsonArray = new JsonArray();

		EntregaEPICTR entregaEPICTR = new EntregaEPICTR();
		List<EntregaEPIBean> entregaEPIBeanList = entregaEPICTR.entregaEPIList();

		for(EntregaEPIBean entregaEPIBean : entregaEPIBeanList){
			Gson gson = new Gson();
			jsonArray.add(gson.toJsonTree(entregaEPIBean, entregaEPIBean.getClass()));
		}
        
        JsonObject json = new JsonObject();
        json.add("dados", jsonArray);
		
        Log.i("pepi", "DADOS ENVIO = " + json);

		Log.i("pepi", "url = " + urlsConexaoHttp.getsEntregaEPI());
		String[] url = {urlsConexaoHttp.getsEntregaEPI()};
		Map<String, Object> parametrosPost = new HashMap<>();
		parametrosPost.put("dados", json.toString());

		PostCadGenerico postCadGenerico = new PostCadGenerico();
		postCadGenerico.setParametrosPost(parametrosPost);
		postCadGenerico.execute(url);

	}

	public void recDados(String result){
		if(result.trim().equals("GRAVOU")){
			EntregaEPICTR entregaEPICTR = new EntregaEPICTR();
			entregaEPICTR.delEntregaEPI();
		}
		else{
			status = 1;
		}
	}

	public void envioDados() {
		status = 1;
		if(ActivityGeneric.connectNetwork) {
			status = 2;
			envioEntregaEPI();
		} else {
			status = 3;
		}
	}

	public boolean verifDadosEnvio() {
		if (!verifDadosEntregaEPI()){
			return false;
		} else {
			return true;
		}
	}

}
