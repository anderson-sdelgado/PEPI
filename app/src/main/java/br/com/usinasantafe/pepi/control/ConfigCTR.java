package br.com.usinasantafe.pepi.control;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.usinasantafe.pepi.model.bean.AtualAplicBean;
import br.com.usinasantafe.pepi.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pepi.model.dao.AtualAplicDAO;
import br.com.usinasantafe.pepi.model.dao.ConfigDAO;
import br.com.usinasantafe.pepi.util.AtualDadosServ;
import br.com.usinasantafe.pepi.util.VerifDadosServ;

public class ConfigCTR {

    public ConfigCTR() {
    }

    public boolean hasElements(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.hasElements();
    }

    public ConfigBean getConfig(){
        ConfigDAO configDAO = new ConfigDAO();
        return configDAO.getConfig();
    }

    public void salvarConfig(Long nroAparelho){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(nroAparelho);
    }

    public void salvarToken(String versao, Long nroAparelho, Context telaAtual, ProgressDialog progressDialog){
        AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
        VerifDadosServ.getInstance().salvarToken(atualAplicDAO.dadosAplic(nroAparelho, versao), telaAtual, progressDialog);
    }

    public void recToken(String result, Context telaAtual, ProgressDialog progressDialog) {

        AtualAplicBean atualAplicBean = new AtualAplicBean();

        try {

            progressDialog.dismiss();

            JSONObject jObj = new JSONObject(result);
            JSONArray jsonArray = jObj.getJSONArray("dados");

            if (jsonArray.length() > 0) {
                AtualAplicDAO atualAplicDAO = new AtualAplicDAO();
                atualAplicBean = atualAplicDAO.recAparelho(jsonArray);
            }

            salvarConfig(atualAplicBean.getNroAparelho());
            progressDialog = new ProgressDialog(telaAtual);
            progressDialog.setCancelable(true);
            progressDialog.setMessage("ATUALIZANDO ...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.show();

            AtualDadosServ.getInstance().atualTodasTabBD(telaAtual, progressDialog);

        } catch (Exception e) {
            VerifDadosServ.status = 1;
        }
    }


}
