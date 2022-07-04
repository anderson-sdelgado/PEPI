package br.com.usinasantafe.pepi.view;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.pepi.PEPIContext;
import br.com.usinasantafe.pepi.R;
import br.com.usinasantafe.pepi.model.bean.estaticas.EPIBean;

public class EPIActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txResult;
    private EPIBean epiBean;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epi);

        epiBean = new EPIBean();
        epiBean.setDescrEPI("");
        pepiContext = (PEPIContext) getApplication();
        txResult = findViewById(R.id.txResult);

        Button buttonOkEPI = findViewById(R.id.buttonOkEPI);
        Button buttonCancEPI = findViewById(R.id.buttonCancEPI);

        buttonOkEPI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!epiBean.getDescrEPI().equals("")){
                    pepiContext.getEntregaEPICTR().getEntregaEPIBean().setEpi(epiBean.getIdEPI());
                    Intent it = new Intent(EPIActivity.this, MotivoActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonCancEPI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(EPIActivity.this, RecebedorActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void callZXing(View view){
        Intent it = new Intent(EPIActivity.this, br.com.usinasantafe.pepi.zxing.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            epiBean.setCodEPI(data.getStringExtra("SCAN_RESULT"));
            if (pepiContext.getEntregaEPICTR().verEPI(epiBean.getCodEPI())) {
                epiBean = pepiContext.getEntregaEPICTR().getEPI(epiBean.getCodEPI());
                txResult.setText("EPI:\nCODIGO: " + epiBean.getCodEPI() + "\n" + epiBean.getDescrEPI());
            }
        }
    }

    public void onBackPressed()  {
    }

}
