package br.com.usinasantafe.pepi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.model.pst.Entidade;

/**
 * Created by anderson on 26/08/2016.
 */

@DatabaseTable(tableName="tbfuncest")
public class FuncBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long matricFunc;
    @DatabaseField
    private String nomeFunc;

    public FuncBean() {
    }

    public Long getMatricFunc() {
        return matricFunc;
    }

    public void setMatricFunc(Long matricFunc) {
        this.matricFunc = matricFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }
}
