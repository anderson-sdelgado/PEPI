package br.com.usinasantafe.pepi.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;

import br.com.usinasantafe.pepi.model.pst.GenericRecordable;
import br.com.usinasantafe.pepi.util.conHttp.GetBDGenerico;
import br.com.usinasantafe.pepi.util.conHttp.UrlsConexaoHttp;

public class AtualDadosServ {

	private ArrayList tabAtualArrayList;
	private static AtualDadosServ instance = null;
	private int contAtualizaBD = 0;
	private String classe = "";
	private ProgressDialog progressDialog;
	private int qtdeBD = 0;
	private GenericRecordable genericRecordable;
	private Context telaAtual;
	private Class telaProx;
	private int tipoReceb;
	private UrlsConexaoHttp urlsConexaoHttp;
	
	public AtualDadosServ() {
		genericRecordable = new GenericRecordable();
	}
	
    public static AtualDadosServ getInstance() {
        if (instance == null)
        instance = new AtualDadosServ();
        return instance;
    }
	
	@SuppressWarnings("unchecked")
	public void manipularDadosHttp(String tipo, String result){
		
		if(!result.equals("")){
		
			if(tipo.equals("datahorahttp")){
				Tempo.getInstance().manipDataHora(result);
			}
			else{
				
				try{

					Log.i("pepi", "TIPO -> " + tipo);
					Log.i("pepi", "RESULT -> " + result);
					
					JSONObject jObj = new JSONObject(result);
					JSONArray jsonArray = jObj.getJSONArray("dados");
					Class classe = Class.forName(manipLocalClasse(tipo));
					genericRecordable.deleteAll(classe);
					
					for(int i = 0; i < jsonArray.length(); i++){
						
						JSONObject objeto = jsonArray.getJSONObject(i);
						Gson gson = new Gson();
						genericRecordable.insert(gson.fromJson(objeto.toString(), classe), classe);
						
					}
					
					Log.i("pepi", " SALVOU DADOS ");
					
					if(contAtualizaBD > 0){
						atualizandoBD();
					}
				
				} 
				catch (Exception e) {
				Log.i("ERRO", "Erro Manip = " + e);
				}	
				
			}
			
		}
		else{
			encerrar();
		}
		
	}
	

	public void atualTodasTabBD(Context telaAtual, ProgressDialog progressDialog){
		
		try {
			
			this.tipoReceb = 1;
			this.telaAtual = telaAtual;
			this.progressDialog = progressDialog;
			tabAtualArrayList = new ArrayList();
	        Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl); 

	        for (Field field : retClasse.getDeclaredFields()) {
	            String campo = field.getName();
	            Log.i("ERRO", "Campo = " + campo);
	            if(campo.contains("Bean")){
	            	tabAtualArrayList.add(campo);
	            }
	            
	        }
	        
	        classe = (String) tabAtualArrayList.get(contAtualizaBD);
			
	        String[] url = {classe};
			
		    contAtualizaBD++;

	        GetBDGenerico getBDGenerico = new GetBDGenerico();
	        getBDGenerico.execute(url);
	        
		} catch (Exception e) {
			Log.i("ERRO", "Erro Manip2 = " + e);
		}
        
	}
	
	public void atualizandoBD(){

		if(this.tipoReceb == 1){
		
			qtdeBD = tabAtualArrayList.size();
			
			if(contAtualizaBD < tabAtualArrayList.size()){
				
				this.progressDialog.setProgress((contAtualizaBD * 100) / qtdeBD);
		        classe = (String) tabAtualArrayList.get(contAtualizaBD);
				String[] url = {classe};
				contAtualizaBD++;

				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			}
			else
			{
				this.progressDialog.dismiss();
				contAtualizaBD = 0;
				AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
				alerta.setTitle("ATENCAO");
				alerta.setMessage("FOI ATUALIZADO COM SUCESSO OS DADOS.");
				alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
	
					}
				});
				
				alerta.show();
			}
		
		}
		else if(this.tipoReceb == 2){
			
			qtdeBD = tabAtualArrayList.size();
			
			if(contAtualizaBD < tabAtualArrayList.size()){
				
		        classe = (String) tabAtualArrayList.get(contAtualizaBD);
				String[] url = {classe};
				contAtualizaBD++;

				GetBDGenerico getBDGenerico = new GetBDGenerico();
		        getBDGenerico.execute(url);
		        
			}
			else
			{
				contAtualizaBD = 0;
			}
			
		}

	}
	
	
	public void encerrar(){
		
		if(this.tipoReceb == 1){
			
			this.progressDialog.dismiss();
			AlertDialog.Builder alerta = new AlertDialog.Builder(this.telaAtual);
			alerta.setTitle("ATENCAO");
			alerta.setMessage("FALHA NA CONEXAO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
			alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
	
				}
			});
			
			alerta.show();
			
		}
	}

	public String manipLocalClasse(String classe){
	    if(classe.contains("Bean")){
	    	classe = urlsConexaoHttp.localPSTEstatica + classe;
	    }
		return classe;
	}
	
}
