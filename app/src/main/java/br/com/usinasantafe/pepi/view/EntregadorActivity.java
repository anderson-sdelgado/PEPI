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

public class EntregadorActivity extends ActivityGeneric {

    public static final int REQUEST_CODE = 0;
    private TextView txResult;
    private FuncBean funcBean;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregador);

        pepiContext = (PEPIContext) getApplication();
        funcBean = new FuncBean();
        funcBean.setNomeFunc("");

        txResult = findViewById(R.id.txResult);
        Button buttonOkEntregador = findViewById(R.id.buttonOkEntregador);
        Button buttonCancEntregador = findViewById(R.id.buttonCancEntregador);

        buttonOkEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!funcBean.getNomeFunc().equals("")) {
                    pepiContext.getEntregaEPICTR().setMatricEntregador(funcBean.getMatricFunc());
                    Intent it = new Intent(EntregadorActivity.this, ListaApontaActivity.class);
                    startActivity(it);
                    finish();
                }

            }

        });

        buttonCancEntregador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(EntregadorActivity.this, ListaApontaActivity.class);
                startActivity(it);
                finish();

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
            String matricString = data.getStringExtra("SCAN_RESULT");
            if(matricString.length() == 8){
                funcBean.setMatricFunc(Long.parseLong(matricString.substring(0,7)));
                if (pepiContext.getEntregaEPICTR().verFunc(funcBean.getMatricFunc())) {
                    funcBean = pepiContext.getEntregaEPICTR().getFunc(funcBean.getMatricFunc());
                    txResult.setText("ENTREGADOR\nMATRICULA: " + funcBean.getMatricFunc() + "\n" + funcBean.getNomeFunc());
                }
            }
        }

    }

    public void onBackPressed()  {
    }

}
