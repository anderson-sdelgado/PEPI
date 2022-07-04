package br.com.usinasantafe.pepi.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.pepi.PEPIContext;
import br.com.usinasantafe.pepi.R;
import br.com.usinasantafe.pepi.util.EnvioDadosServ;

public class ListaApontaActivity extends ActivityGeneric {

    private ListView apontaListView;
    private PEPIContext pepiContext;
    private Handler customHandler = new Handler();
    private TextView textViewProcesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aponta);

        Button buttonRetApontamento = findViewById(R.id.buttonRetApontamento);
        textViewProcesso = findViewById(R.id.textViewProcesso);

        pepiContext = (PEPIContext) getApplication();

        customHandler.postDelayed(updateTimerThread, 0);

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("CADASTRAR ENTREGADOR");
        itens.add("ENTREGAR EPI");

        AdapterList adapterList = new AdapterList(this, itens);
        apontaListView = findViewById(R.id.listViewApontamento);
        apontaListView.setAdapter(adapterList);

        apontaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                TextView textView = v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("CADASTRAR ENTREGADOR")) {
                    Intent it = new Intent(ListaApontaActivity.this, EntregadorActivity.class);
                    startActivity(it);
                    finish();
                } else if (text.equals("ENTREGAR EPI")) {
                    if(pepiContext.getEntregaEPICTR().getMatricEntregador() > 0) {
                        Intent it = new Intent(ListaApontaActivity.this, RecebedorActivity.class);
                        startActivity(it);
                        finish();
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
                Intent it = new Intent(ListaApontaActivity.this, MenuInicialActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed()  {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (EnvioDadosServ.status == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (EnvioDadosServ.status == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (EnvioDadosServ.status == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };

}
