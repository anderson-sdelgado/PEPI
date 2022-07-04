package br.com.usinasantafe.pepi.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.model.pst.Entidade;

/**
 * Created by anderson on 26/08/2016.
 */
@DatabaseTable(tableName="tbmotivoest")
public class MotivoBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long idMotivo;
    @DatabaseField
    private Long codMotivo;
    @DatabaseField
    private String descrMotivo;

    public MotivoBean() {
    }

    public Long getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Long getCodMotivo() {
        return codMotivo;
    }

    public void setCodMotivo(Long codMotivo) {
        this.codMotivo = codMotivo;
    }

    public String getDescrMotivo() {
        return descrMotivo;
    }

    public void setDescrMotivo(String descrMotivo) {
        this.descrMotivo = descrMotivo;
    }
}
