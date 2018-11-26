package br.com.usinasantafe.pepi.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.pst.Entidade;

/**
 * Created by anderson on 26/08/2016.
 */
@DatabaseTable(tableName="tbepiest")
public class EPITO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idEPI;
    @DatabaseField
    private String codigoEPI;
    @DatabaseField
    private String descricaoEPI;

    public EPITO() {
    }

    public Long getIdEPI() {
        return idEPI;
    }

    public void setIdEPI(Long idEPI) {
        this.idEPI = idEPI;
    }

    public String getCodigoEPI() {
        return codigoEPI;
    }

    public void setCodigoEPI(String codigoEPI) {
        this.codigoEPI = codigoEPI;
    }

    public String getDescricaoEPI() {
        return descricaoEPI;
    }

    public void setDescricaoEPI(String descricaoEPI) {
        this.descricaoEPI = descricaoEPI;
    }
}
