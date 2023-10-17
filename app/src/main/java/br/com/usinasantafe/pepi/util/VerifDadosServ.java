package br.com.usinasantafe.pepi.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import br.com.usinasantafe.pepi.control.ConfigCTR;
import br.com.usinasantafe.pepi.model.dao.ConfigDAO;
import br.com.usinasantafe.pepi.util.conHttp.PostVerGenerico;
import br.com.usinasantafe.pepi.view.MenuInicialActivity;
import br.com.usinasantafe.pepi.model.bean.AtualAplicBean;
import br.com.usinasantafe.pepi.util.conHttp.UrlsConexaoHttp;

/**
 * Created by anderson on 16/11/2015.
 */
public class VerifDadosServ {

    private static VerifDadosServ instance = null;
    private UrlsConexaoHttp urlsConexaoHttp;
    private Context telaAtual;
    private Class telaProx;
    private ProgressDialog progressDialog;
    private String dados;
    private String classe;
    private MenuInicialActivity menuInicialActivity;
    private PostVerGenerico postVerGenerico;
    public static int status; //1 - Existe Dados para Enviar; 2 - Enviando; 3 - Todos os Dados Foram Enviados;

    public static VerifDadosServ getInstance() {
        if (instance == null)
            instance = new VerifDadosServ();
        return instance;
    }

    public void manipularDadosHttp(String result) {

        if (!result.equals("")) {
            retornoVerifNormal(result);
        }

    }

    public void retornoVerifNormal(String result) {

        try {
            ConfigCTR configCTR = new ConfigCTR();
            if(this.classe.equals("Token")) {
                configCTR.recToken(result.trim(), this.telaAtual, this.progressDialog);
            }
        } catch (Exception e) {
            Log.i("PMM", "Erro Manip = " + e);
        }

    }

    public void salvarToken(String dados, Context telaAtual, ProgressDialog progressDialog) {

        urlsConexaoHttp = new UrlsConexaoHttp();
        this.classe = "Token";
        this.telaAtual = telaAtual;
        this.progressDialog = progressDialog;
        this.dados = dados;

        envioVerif();

    }

    public void envioVerif() {

        this.urlsConexaoHttp = new UrlsConexaoHttp();
        String[] url = {urlsConexaoHttp.urlVerifica(classe)};
        Map<String, Object> parametrosPost = new HashMap<String, Object>();
        parametrosPost.put("dado", this.dados);

        Log.i("PCO", "postVerGenerico.execute('" + urlsConexaoHttp.urlVerifica(classe) + "'); - Dados de Envio = " + this.dados);
        postVerGenerico = new PostVerGenerico();
        postVerGenerico.setParametrosPost(parametrosPost);
        postVerGenerico.execute(url);

    }

}
