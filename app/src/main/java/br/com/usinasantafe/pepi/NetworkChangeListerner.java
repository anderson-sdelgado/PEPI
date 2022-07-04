package br.com.usinasantafe.pepi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pepi.util.ConnectNetwork;
import br.com.usinasantafe.pepi.util.EnvioDadosServ;
import br.com.usinasantafe.pepi.util.Tempo;
import br.com.usinasantafe.pepi.model.pst.DatabaseHelper;
import br.com.usinasantafe.pepi.view.ActivityGeneric;


/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 * @author ricardo
 * 
 */
public class NetworkChangeListerner extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(Tempo.getInstance().getDatahora() != null) {
			Tempo.getInstance().getDatahora().setTime(Tempo.getInstance().getDatahora().getTime() + 60000L);
		}

		if(ConnectNetwork.isConnected(context)) {
			ActivityGeneric.connectNetwork = true;
			if (EnvioDadosServ.status == 1) {
				EnvioDadosServ.getInstance().envioDados();
			}
		} else {
			ActivityGeneric.connectNetwork = false;
		}

	}

}