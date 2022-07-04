package br.com.usinasantafe.pepi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.model.pst.Entidade;

/**
 * Created by anderson on 26/08/2016.
 */
@DatabaseTable(tableName="tbepiest")
public class EPIBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEPI;
    @DatabaseField
    private String codEPI;
    @DatabaseField
    private String descrEPI;

    public EPIBean() {
    }

    public Long getIdEPI() {
        return idEPI;
    }

    public void setIdEPI(Long idEPI) {
        this.idEPI = idEPI;
    }

    public String getCodEPI() {
        return codEPI;
    }

    public void setCodEPI(String codEPI) {
        this.codEPI = codEPI;
    }

    public String getDescrEPI() {
        return descrEPI;
    }

    public void setDescrEPI(String descrEPI) {
        this.descrEPI = descrEPI;
    }
}
