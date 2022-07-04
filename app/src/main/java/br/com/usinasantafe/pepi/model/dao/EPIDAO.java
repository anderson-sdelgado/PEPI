package br.com.usinasantafe.pepi.model.dao;

import java.util.List;

import br.com.usinasantafe.pepi.model.bean.estaticas.EPIBean;

public class EPIDAO {

    public EPIDAO() {
    }

    private List getEPIList(String codEPI){
        EPIBean epiBean = new EPIBean();
        return epiBean.get("codEPI", codEPI);
    }

    public boolean verEPI(String codEPI){
        List epiList = getEPIList(codEPI);
        boolean ret = epiList.size() > 0;
        epiList.clear();
        return ret;
    }

    public EPIBean getEPI(String codEPI){
        List epiList = getEPIList(codEPI);
        EPIBean equipBean = (EPIBean) epiList.get(0);
        epiList.clear();
        return equipBean;
    }

}
