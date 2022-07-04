package br.com.usinasantafe.pepi.view;

//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pepi.PEPIContext;
import br.com.usinasantafe.pepi.R;
import br.com.usinasantafe.pepi.model.bean.estaticas.FuncBean;
import br.com.usinasantafe.pepi.model.bean.variaveis.EntregaEPIBean;

public class RecebedorActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txResult;
    private FuncBean funcBean;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebedor);

        pepiContext = (PEPIContext) getApplication();
        funcBean = new FuncBean();
        funcBean.setNomeFunc("");

        txResult = findViewById(R.id.txResult);
        Button buttonOkRecebedor = findViewById(R.id.buttonOkRecebedor);
        Button buttonCancRecebedor = findViewById(R.id.buttonCancRecebedor);

        buttonOkRecebedor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!funcBean.getNomeFunc().equals("")) {
                    pepiContext.getEntregaEPICTR().setEntregaEPIBean(new EntregaEPIBean());
                    pepiContext.getEntregaEPICTR().getEntregaEPIBean().setMatricRecebedor(funcBean.getMatricFunc());
                    Intent it = new Intent(RecebedorActivity.this, EPIActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonCancRecebedor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(RecebedorActivity.this, ListaApontaActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void callZXing(View view){
        Intent it = new Intent(RecebedorActivity.this, br.com.usinasantafe.pepi.zxing.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            String matricString = data.getStringExtra("SCAN_RESULT");
            if(matricString.length() == 8){
                funcBean.setMatricFunc(Long.parseLong(matricString.substring(0,7)));
                if (pepiContext.getEntregaEPICTR().verFunc(funcBean.getMatricFunc())) {
                    funcBean = pepiContext.getEntregaEPICTR().getFunc(funcBean.getMatricFunc());
                    txResult.setText("RECEBEDOR\nMATRICULA: " + funcBean.getMatricFunc() + "\n" + funcBean.getNomeFunc());
                }
            }
        }
    }

    public void onBackPressed()  {
    }

}
