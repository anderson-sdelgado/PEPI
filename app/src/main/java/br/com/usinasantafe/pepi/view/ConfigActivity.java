package br.com.usinasantafe.pepi.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import br.com.usinasantafe.pepi.BuildConfig;
import br.com.usinasantafe.pepi.PEPIContext;
import br.com.usinasantafe.pepi.R;
import br.com.usinasantafe.pepi.util.AtualDadosServ;

public class ConfigActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private EditText editTextNroAparelhoConfig;
    private PEPIContext pepiContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        Button buttonAtualizarBD = findViewById(R.id.buttonAtualizarBD);
        Button buttonSalvarConfig = findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = findViewById(R.id.buttonCancConfig);
        editTextNroAparelhoConfig = findViewById(R.id.editTextNroAparelhoConfig);

        pepiContext = (PEPIContext) getApplication();

        if(pepiContext.getConfigCTR().hasElements()) {
            editTextNroAparelhoConfig.setText(String.valueOf(pepiContext.getConfigCTR().getConfig().getNroAparelhoConfig()));
        }

        buttonAtualizarBD.setOnClickListener(v -> {

            if(connectNetwork){

                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("ATUALIZANDO ...");
                progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressBar.setProgress(0);
                progressBar.setMax(100);
                progressBar.show();
                AtualDadosServ.getInstance().atualTodasTabBD(ConfigActivity.this, progressBar);

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });

                alerta.show();

            }
        });

        buttonSalvarConfig.setOnClickListener(v -> {

            if(!editTextNroAparelhoConfig.getText().toString().equals("")) {


                progressBar = new ProgressDialog(v.getContext());
                progressBar.setCancelable(true);
                progressBar.setMessage("Salvando Configurações Inicial...");
                progressBar.show();
                pepiContext.getConfigCTR().salvarToken(BuildConfig.VERSION_NAME, Long.valueOf(editTextNroAparelhoConfig.getText().toString()), ConfigActivity.this, progressBar);

//                pepiContext.getConfigCTR().salvarConfig(editTextNroAparelhoConfig.getText().toString());
//
//                Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
//                startActivity(it);
//                finish();

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ConfigActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });
                alerta.show();

            }

        });

        buttonCancConfig.setOnClickListener(v -> {

            Intent it = new Intent(ConfigActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();

        });

    }

    public void onBackPressed()  {
    }

}
