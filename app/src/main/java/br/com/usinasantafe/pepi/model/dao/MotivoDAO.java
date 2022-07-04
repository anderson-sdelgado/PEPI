package br.com.usinasantafe.pepi.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pepi.model.bean.estaticas.MotivoBean;

public class MotivoDAO {

    public MotivoDAO() {
    }

    public List<MotivoBean> motivoList(){
        MotivoBean motivoBean = new MotivoBean();
        return motivoBean.all();
    }

}
