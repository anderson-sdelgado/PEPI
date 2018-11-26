package br.com.usinasantafe.pepi;

import android.app.Application;

import br.com.usinasantafe.pepi.to.tb.variaveis.EntregaEPITO;

/**
 * Created by anderson on 13/10/2016.
 */
public class PEPIContext extends Application {

    private EntregaEPITO entregaEPITO;
    public static String versaoAplic = "1.6";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public EntregaEPITO getEntregaEPITO() {
        if(entregaEPITO == null)
            entregaEPITO = new EntregaEPITO();
        return entregaEPITO;
    }

}
