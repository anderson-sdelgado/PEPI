package br.com.usinasantafe.pepi;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pepi.bo.ManipDadosEnvio;
import br.com.usinasantafe.pepi.bo.Tempo;

public class StatusActivity extends ActivityGeneric {

    private ListView lista;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        pepiContext = (PEPIContext) getApplication();

        Button buttonRetStatus = (Button) findViewById(R.id.buttonRetStatus);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("ENTREGA");
        itens.add("REPOSIÇÃO");
        itens.add("PERDA");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewStatus);
        lista.setAdapter(adapterList);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                if (position == 0) {
                    pepiContext.getEntregaEPITO().setStatus(0L);
                } else if (position == 1) {
                    pepiContext.getEntregaEPITO().setStatus(2L);
                } else if (position == 2) {
                    pepiContext.getEntregaEPITO().setStatus(3L);
                }

                Tempo tempo = new Tempo();
                pepiContext.getEntregaEPITO().setDataEntrega(tempo.data());

                Log.i("PEPI", "ENTREGA EPI");
                Log.i("PEPI", "MATRICULA ENTREGADOR = " + pepiContext.getEntregaEPITO().getMatricEntregador());
                Log.i("PEPI", "MATRICULA RECEBEDOR = " + pepiContext.getEntregaEPITO().getMatricRecebedor());
                Log.i("PEPI", "EPI = " + pepiContext.getEntregaEPITO().getEpi());
                Log.i("PEPI", "MOTIVO = " + pepiContext.getEntregaEPITO().getMotivo());
                Log.i("PEPI", "STATUS = " + pepiContext.getEntregaEPITO().getStatus());
                Log.i("PEPI", "DATA ENTREGA = " + pepiContext.getEntregaEPITO().getDataEntrega());

                pepiContext.getEntregaEPITO().insert();

                Intent it = new Intent(StatusActivity.this, ListaApontaActivity.class);
                startActivity(it);

            }

        });

        buttonRetStatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent( StatusActivity.this, MotivoActivity.class);
                startActivity(it);

            }

        });

    }

    public void onBackPressed()  {
    }

}
