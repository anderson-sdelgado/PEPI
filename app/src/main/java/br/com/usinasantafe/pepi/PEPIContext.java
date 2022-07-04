package br.com.usinasantafe.pepi;

import android.app.Application;

import br.com.usinasantafe.pepi.control.ConfigCTR;
import br.com.usinasantafe.pepi.control.EntregaEPICTR;
import br.com.usinasantafe.pepi.model.bean.variaveis.EntregaEPIBean;

/**
 * Created by anderson on 13/10/2016.
 */
public class PEPIContext extends Application {

    private ConfigCTR configCTR;
    private EntregaEPICTR entregaEPICTR;
    public static String versaoAPP = "2.00";
    public static String versaoWS = "2.00";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ConfigCTR getConfigCTR() {
        if(configCTR == null)
            configCTR = new ConfigCTR();
        return configCTR;
    }

    public EntregaEPICTR getEntregaEPICTR() {
        if(entregaEPICTR == null)
            entregaEPICTR = new EntregaEPICTR();
        return entregaEPICTR;
    }
}
