package br.com.usinasantafe.pepi.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.pst.Entidade;

/**
 * Created by anderson on 26/08/2016.
 */
@DatabaseTable(tableName="tbmotivoest")
public class MotivoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idMotivo;
    @DatabaseField
    private Long codigoMotivo;
    @DatabaseField
    private String descMotivo;

    public MotivoTO() {
    }

    public Long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Long getCodigoMotivo() {
        return codigoMotivo;
    }

    public void setCodigoMotivo(Long codigoMotivo) {
        this.codigoMotivo = codigoMotivo;
    }

    public String getDescMotivo() {
        return descMotivo;
    }

    public void setDescMotivo(String descMotivo) {
        this.descMotivo = descMotivo;
    }
}
