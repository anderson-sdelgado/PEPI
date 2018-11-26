package br.com.usinasantafe.pepi.to.tb.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.pst.Entidade;

/**
 * Created by anderson on 26/08/2016.
 */

@DatabaseTable(tableName="tbfuncest")
public class FuncTO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long matriculaFunc;
    @DatabaseField
    private String nomeFunc;

    public FuncTO() {
    }

    public Long getMatriculaFunc() {
        return matriculaFunc;
    }

    public void setMatriculaFunc(Long matriculaFunc) {
        this.matriculaFunc = matriculaFunc;
    }

    public String getNomeFunc() {
        return nomeFunc;
    }

    public void setNomeFunc(String nomeFunc) {
        this.nomeFunc = nomeFunc;
    }
}
