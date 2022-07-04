package br.com.usinasantafe.pepi.model.dao;

import java.util.List;

import br.com.usinasantafe.pepi.model.bean.variaveis.EntregaEPIBean;
import br.com.usinasantafe.pepi.util.Tempo;

public class EntregaEPIDAO {

    public void salvarEntregaEPI(EntregaEPIBean entregaEPIBean){
        Tempo tempo = new Tempo();
        entregaEPIBean.setDataEntrega(tempo.data());
        entregaEPIBean.insert();
    }

    public void delEntregaEPI(){
        EntregaEPIBean entregaEPIBean = new EntregaEPIBean();
        entregaEPIBean.deleteAll();
    }

    public boolean verEntregaEPI(){
        EntregaEPIBean entregaEPIBean = new EntregaEPIBean();
        return entregaEPIBean.hasElements();
    }

    public List<EntregaEPIBean> entregaEPIList(){
        EntregaEPIBean entregaEPIBean = new EntregaEPIBean();
        List<EntregaEPIBean> entregaEPIBeanList = entregaEPIBean.all();
        return entregaEPIBeanList;
    }

}
