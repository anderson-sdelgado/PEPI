package br.com.usinasantafe.pepi.view;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pepi.PEPIContext;
import br.com.usinasantafe.pepi.R;
import br.com.usinasantafe.pepi.model.bean.estaticas.MotivoBean;

public class MotivoActivity extends ActivityGeneric {

    private ListView lista;
    List<MotivoBean> motivoList;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivo);

        pepiContext = (PEPIContext) getApplication();

        Button buttonRetMotivo = findViewById(R.id.buttonRetMotivo);

        ArrayList<String> itens = new ArrayList<String>();

        motivoList =  pepiContext.getEntregaEPICTR().motivoList();

        for(MotivoBean motivoBean : motivoList){
            itens.add(motivoBean.getDescrMotivo());
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listViewMotivo);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                MotivoBean motivoBean = motivoList.get(position);
                pepiContext.getEntregaEPICTR().getEntregaEPIBean().setMotivo(motivoBean.getIdMotivo());
                Intent it = new Intent(MotivoActivity.this, StatusActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonRetMotivo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent( MotivoActivity.this, EPIActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void onBackPressed()  {
    }

}
