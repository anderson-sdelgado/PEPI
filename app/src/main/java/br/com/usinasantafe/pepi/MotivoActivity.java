package br.com.usinasantafe.pepi;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pepi.to.tb.estaticas.MotivoTO;

public class MotivoActivity extends ActivityGeneric {

    private ListView lista;
    List<MotivoTO> listMotivo;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivo);

        pepiContext = (PEPIContext) getApplication();

        Button buttonRetMotivo = (Button) findViewById(R.id.buttonRetMotivo);

        ArrayList<String> itens = new ArrayList<String>();

        MotivoTO motivoTO = new MotivoTO();
        listMotivo =  motivoTO.all();

        for(MotivoTO m : listMotivo){
            itens.add(m.getDescMotivo());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listViewMotivo);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                MotivoTO motivoTO = (MotivoTO) listMotivo.get(position);
                pepiContext.getEntregaEPITO().setMotivo(motivoTO.getIdMotivo());
                Intent it = new Intent(MotivoActivity.this, StatusActivity.class);
                startActivity(it);

            }

        });

        buttonRetMotivo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent( MotivoActivity.this, EPIActivity.class);
                startActivity(it);

            }

        });

    }

    public void onBackPressed()  {
    }

}
