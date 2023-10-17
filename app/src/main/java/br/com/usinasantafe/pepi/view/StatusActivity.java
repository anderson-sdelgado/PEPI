package br.com.usinasantafe.pepi.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.pepi.PEPIContext;
import br.com.usinasantafe.pepi.R;

public class StatusActivity extends ActivityGeneric {

    private ListView lista;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        pepiContext = (PEPIContext) getApplication();

        Button buttonRetStatus = findViewById(R.id.buttonRetStatus);

        ArrayList<String> itens = new ArrayList<>();

        itens.add("ENTREGA");
        itens.add("REPOSIÇÃO");
        itens.add("PERDA");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listViewStatus);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener((l, v, position, id) -> {

            if (position == 0) {
                pepiContext.getEntregaEPICTR().getEntregaEPIBean().setStatus(0L);
            } else if (position == 1) {
                pepiContext.getEntregaEPICTR().getEntregaEPIBean().setStatus(2L);
            } else if (position == 2) {
                pepiContext.getEntregaEPICTR().getEntregaEPIBean().setStatus(3L);
            }

            pepiContext.getEntregaEPICTR().salvarEntregaEPI();

            Intent it = new Intent(StatusActivity.this, ListaApontActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetStatus.setOnClickListener(v -> {

            Intent it = new Intent( StatusActivity.this, MotivoActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed()  {
    }

}
