package br.com.usinasantafe.pepi;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.com.usinasantafe.pepi.to.tb.estaticas.FuncTO;

public class EntregadorActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txResult;
    private String matricula;
    private String nome;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregador);

        pepiContext = (PEPIContext) getApplication();
        nome = null;

        txResult = (TextView) findViewById(R.id.txResult);
        Button buttonOkEntregador = (Button) findViewById(R.id.buttonOkEntregador);
        Button buttonCancEntregador = (Button) findViewById(R.id.buttonCancEntregador);

        buttonOkEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(nome != null) {
                    pepiContext.getEntregaEPITO().setMatricEntregador(Long.parseLong(matricula));
                    Intent it = new Intent(EntregadorActivity.this, ListaApontaActivity.class);
                    startActivity(it);
                }

            }

        });

        buttonCancEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(EntregadorActivity.this, ListaApontaActivity.class);
                startActivity(it);

            }

        });

    }

    public void callZXing(View view){
        Intent it = new Intent(EntregadorActivity.this, br.com.usinasantafe.pepi.zxing.CaptureActivity.class);
        startActivityForResult(it, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            matricula = data.getStringExtra("SCAN_RESULT");
            if(matricula.length() == 8){
                matricula = matricula.substring(0,7);
                FuncTO funcTO = new FuncTO();
                List listFunc = funcTO.get("matriculaFunc", Long.parseLong(matricula));
                if (listFunc.size() > 0) {
                    funcTO = (FuncTO) listFunc.get(0);
                    nome = funcTO.getNomeFunc();
                    txResult.setText("ENTREGADOR\nMATRICULA: " + matricula + "\n" + nome);
                }
            }
        }

    }

    public void onBackPressed()  {
    }

}
