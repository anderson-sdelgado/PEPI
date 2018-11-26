package br.com.usinasantafe.pepi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pepi.bo.ManipDadosEnvio;

public class ListaApontaActivity extends ActivityGeneric {

    private ListView lista;
    private PEPIContext pepiContext;
    private Handler customHandler = new Handler();
    private TextView textViewProcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aponta);

        Button buttonRetApontamento = (Button) findViewById(R.id.buttonRetApontamento);
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        pepiContext = (PEPIContext) getApplication();

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("CADASTRAR ENTREGADOR");
        itens.add("ENTREGAR EPI");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewApontamento);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("CADASTRAR ENTREGADOR")) {
                    Intent it = new Intent(ListaApontaActivity.this, EntregadorActivity.class);
                    startActivity(it);
                } else if (text.equals("ENTREGAR EPI")) {
                    if(pepiContext.getEntregaEPITO().getMatricEntregador() != null) {
                        Intent it = new Intent(ListaApontaActivity.this, RecebedorActivity.class);
                        startActivity(it);
                    }
                    else{
                        AlertDialog.Builder alerta = new AlertDialog.Builder( ListaApontaActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("FALTA DE ENTREGADOR. POR FAVOR, CADASTRE UM NOVO ENTREGADOR!");
                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        alerta.show();
                    }
                }
            }

        });

        buttonRetApontamento.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(ListaApontaActivity.this, PrincipalActivity.class);
                startActivity(it);
            }

        });

    }

    public void onBackPressed()  {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (ManipDadosEnvio.getInstance().getStatusEnvio() == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };


}
