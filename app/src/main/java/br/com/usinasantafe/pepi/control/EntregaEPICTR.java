package br.com.usinasantafe.pepi.control;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pepi.model.bean.estaticas.EPIBean;
import br.com.usinasantafe.pepi.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pepi.model.bean.estaticas.MotivoBean;
import br.com.usinasantafe.pepi.model.bean.variaveis.EntregaEPIBean;
import br.com.usinasantafe.pepi.model.dao.EPIDAO;
import br.com.usinasantafe.pepi.model.dao.EntregaEPIDAO;
import br.com.usinasantafe.pepi.model.dao.FuncDAO;
import br.com.usinasantafe.pepi.model.dao.MotivoDAO;
import br.com.usinasantafe.pepi.util.AtualDadosServ;
import br.com.usinasantafe.pepi.util.EnvioDadosServ;

public class EntregaEPICTR {

    private EntregaEPIBean entregaEPIBean;
    private Long matricEntregador;

    public EntregaEPICTR() {
    }

    public void salvarEntregaEPI(){
        EntregaEPIDAO entregaEPIDAO = new EntregaEPIDAO();
        EPIDAO epiDAO = new EPIDAO();
        entregaEPIBean.setMatricEntregador(matricEntregador);
        epiDAO.updateQtdeEPI(entregaEPIBean.getEpi());
        entregaEPIDAO.salvarEntregaEPI(entregaEPIBean);
        EnvioDadosServ.getInstance().envioDados();
    }

    public void delEntregaEPI(){
        EntregaEPIDAO entregaEPIDAO = new EntregaEPIDAO();
        entregaEPIDAO.delEntregaEPI();
    }

    /////////////////////////////////// VER DADOS ///////////////////////////////////////////

    public boolean hasElemFunc(){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.hasElements();
    }

    public boolean verFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.verFunc(matricFunc);
    }

    public boolean verEPI(String codEPI){
        EPIDAO epiDAO = new EPIDAO();
        return epiDAO.verEPI(codEPI);
    }

    public boolean verQtdeEPI(String codEPI){
        EPIDAO epiDAO = new EPIDAO();
        return epiDAO.verQtdeEPI(codEPI);
    }

    public boolean verEntregaEPI(){
        EntregaEPIDAO entregaEPIDAO = new EntregaEPIDAO();
        return entregaEPIDAO.verEntregaEPI();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// GET DADOS ///////////////////////////////////////////

    public EntregaEPIBean getEntregaEPIBean() {
        return entregaEPIBean;
    }

    public FuncBean getFunc(Long matricFunc){
        FuncDAO funcDAO = new FuncDAO();
        return funcDAO.getFunc(matricFunc);
    }

    public EPIBean getEPI(String codEPI){
        EPIDAO epiDAO = new EPIDAO();
        return epiDAO.getEPI(codEPI);
    }

    public Long getMatricEntregador() {
        return matricEntregador;
    }

    public List<MotivoBean> motivoList(){
        MotivoDAO motivoDAO = new MotivoDAO();
        return motivoDAO.motivoList();
    }

    public List<EntregaEPIBean> entregaEPIList(){
        EntregaEPIDAO entregaEPIDAO = new EntregaEPIDAO();
        return entregaEPIDAO.entregaEPIList();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// SET DADOS ///////////////////////////////////////////

    public void setEntregaEPIBean(EntregaEPIBean entregaEPIBean) {
        this.entregaEPIBean = entregaEPIBean;
    }

    public void setMatricEntregador(Long matricEntregador) {
        this.matricEntregador = matricEntregador;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public void atualEpi(Context telaAtual, ProgressDialog progressDialog) {
        AtualDadosServ.getInstance().atualEpiDados(classeEPI(), telaAtual, progressDialog);
    }

    public ArrayList<String> classeEPI() {
        ArrayList<String> classeArrayList = new ArrayList();
        classeArrayList.add("EPIBean");
        return classeArrayList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

}
