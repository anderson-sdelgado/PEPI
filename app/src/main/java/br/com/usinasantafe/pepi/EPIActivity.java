package br.com.usinasantafe.pepi;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pepi.to.tb.estaticas.EPITO;

public class EPIActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txResult;
    private EPITO epiTO;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epi);

        epiTO = new EPITO();
        epiTO.setIdEPI(0L);
        pepiContext = (PEPIContext) getApplication();
        txResult = (TextView) findViewById(R.id.txResult);

        Button buttonOkEPI = (Button) findViewById(R.id.buttonOkEPI);
        Button buttonCancEPI = (Button) findViewById(R.id.buttonCancEPI);

        buttonOkEPI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(epiTO.getIdEPI() > 0){

                    pepiContext.getEntregaEPITO().setEpi(epiTO.getIdEPI());
                    Intent it = new Intent(EPIActivity.this, MotivoActivity.class);
                    startActivity(it);

                }

            }

        });

        buttonCancEPI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(EPIActivity.this, RecebedorActivity.class);
                startActivity(it);

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

            epiTO.setCodigoEPI(data.getStringExtra("SCAN_RESULT"));
            List listEPI = epiTO.get("codigoEPI", epiTO.getCodigoEPI());

            if (listEPI.size() > 0) {

                epiTO = (EPITO) listEPI.get(0);
                txResult.setText("EPI:\nCODIGO: " + epiTO.getCodigoEPI() + "\n" + epiTO.getDescricaoEPI());

            }

        }
    }

    public void onBackPressed()  {
    }

}
