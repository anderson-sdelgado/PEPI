package br.com.usinasantafe.pepi.model.dao;

import java.util.List;

import br.com.usinasantafe.pepi.model.bean.estaticas.EPIBean;

public class EPIDAO {

    public EPIDAO() {
    }

    public EPIBean updateQtdeEPI(Long idEPI){
        List<EPIBean> epiList = getEPIListId(idEPI);
        EPIBean equipBean = epiList.get(0);
        equipBean.setQtdeEPI(equipBean.getQtdeEPI() - 1);
        equipBean.update();
        epiList.clear();
        return equipBean;
    }

    private List<EPIBean> getEPIListCod(String codEPI){
        EPIBean epiBean = new EPIBean();
        return epiBean.get("codEPI", codEPI);
    }

    private List<EPIBean> getEPIListId(Long idEPI){
        EPIBean epiBean = new EPIBean();
        return epiBean.get("idEPI", idEPI);
    }

    public boolean verEPI(String codEPI){
        List<EPIBean> epiList = getEPIListCod(codEPI);
        boolean ret = epiList.size() > 0;
        epiList.clear();
        return ret;
    }

    public  boolean verQtdeEPI(String codEPI){
        List epiList = getEPIListCod(codEPI);
        EPIBean equipBean = (EPIBean) epiList.get(0);
        boolean ret = equipBean.getQtdeEPI() > 0;
        epiList.clear();
        return ret;
    }

    public EPIBean getEPI(String codEPI){
        List<EPIBean> epiList = getEPIListCod(codEPI);
        EPIBean equipBean = epiList.get(0);
        epiList.clear();
        return equipBean;
    }


}
