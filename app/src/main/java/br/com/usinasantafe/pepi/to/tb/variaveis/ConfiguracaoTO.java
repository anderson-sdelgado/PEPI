package br.com.usinasantafe.pepi.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.pst.Entidade;


/**
 * Created by anderson on 22/11/2017.
 */
@DatabaseTable(tableName="tbconfigvar")
public class ConfiguracaoTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long numLinha;

    public ConfiguracaoTO() {
    }

    public Long getNumLinha() {
        return numLinha;
    }

    public void setNumLinha(Long numLinha) {
        this.numLinha = numLinha;
    }
    
}
