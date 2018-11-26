package br.com.usinasantafe.pepi.to.tb.variaveis;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.pepi.pst.Entidade;

/**
 * Created by anderson on 13/10/2016.
 */
@DatabaseTable(tableName="tbentregaepivar")
public class EntregaEPITO extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(generatedId=true)
    private Long idEntrega;
    @DatabaseField
    private Long matricEntregador;
    @DatabaseField
    private String dataEntrega;
    @DatabaseField
    private Long epi;
    @DatabaseField
    private Long qtde;
    @DatabaseField
    private Long motivo;
    @DatabaseField
    private Long status;
    @DatabaseField
    private Long matricRecebedor;

    public EntregaEPITO() {
    }

    public Long getIdEntrega() {
        return idEntrega;
    }

    public Long getMatricEntregador() {
        return matricEntregador;
    }

    public void setMatricEntregador(Long matricEntregador) {
        this.matricEntregador = matricEntregador;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Long getEpi() {
        return epi;
    }

    public void setEpi(Long epi) {
        this.epi = epi;
    }

    public Long getQtde() {
        return qtde;
    }

    public void setQtde(Long qtde) {
        this.qtde = qtde;
    }

    public Long getMotivo() {
        return motivo;
    }

    public void setMotivo(Long motivo) {
        this.motivo = motivo;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getMatricRecebedor() {
        return matricRecebedor;
    }

    public void setMatricRecebedor(Long matricRecebedor) {
        this.matricRecebedor = matricRecebedor;
    }

}
