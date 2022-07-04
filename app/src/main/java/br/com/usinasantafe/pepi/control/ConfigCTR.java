package br.com.usinasantafe.pepi.control;

import android.app.ProgressDialog;
import android.content.Context;

import br.com.usinasantafe.pepi.model.bean.variaveis.ConfigBean;
import br.com.usinasantafe.pepi.model.dao.ConfigDAO;
import br.com.usinasantafe.pepi.util.AtualDadosServ;

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

    public void salvarConfig(String senha){
        ConfigDAO configDAO = new ConfigDAO();
        configDAO.salvarConfig(senha);
    }

    public void atualTodasTabelas(Context tela, ProgressDialog progressDialog){
        AtualDadosServ.getInstance().atualTodasTabBD(tela, progressDialog);
    }

}
