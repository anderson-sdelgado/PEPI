package br.com.usinasantafe.pepi.model.dao;

import java.util.List;

import br.com.usinasantafe.pepi.model.bean.estaticas.FuncBean;

public class FuncDAO {

    public FuncDAO() {
    }

    public boolean hasElements(){
        FuncBean funcBean = new FuncBean();
        return funcBean.hasElements();
    }

    private List getFuncList(Long matricFunc){
        FuncBean funcBean = new FuncBean();
        return funcBean.get("matricFunc", matricFunc);
    }

    public boolean verFunc(Long matricFunc){
        List funcList = getFuncList(matricFunc);
        boolean ret = funcList.size() > 0;
        funcList.clear();
        return ret;
    }

    public FuncBean getFunc(Long matricFunc){
        List funcList = getFuncList(matricFunc);
        FuncBean funcBean = (FuncBean) funcList.get(0);
        funcList.clear();
        return funcBean;
    }

}
