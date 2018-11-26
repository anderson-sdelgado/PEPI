package br.com.usinasantafe.pepi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import br.com.usinasantafe.pepi.bo.ManipDadosEnvio;
import br.com.usinasantafe.pepi.bo.Tempo;
import br.com.usinasantafe.pepi.pst.DatabaseHelper;


/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 * @author ricardo
 * 
 */
public class ReceberAlarme extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if(DatabaseHelper.getInstance() == null){
			new DatabaseHelper(context);
		}

		if(Tempo.getInstance().getDatahora() != null) {
			Tempo.getInstance().getDatahora().setTime(Tempo.getInstance().getDatahora().getTime() + 60000L);
		}

		Log.i("PRU", "DATA HORA = " + Tempo.getInstance().data());
		if(ManipDadosEnvio.getInstance().verifDadosEnvio()){
			Log.i("PRU", "ENVIANDO");
			ManipDadosEnvio.getInstance().envioDados(context);
		}
	}

}