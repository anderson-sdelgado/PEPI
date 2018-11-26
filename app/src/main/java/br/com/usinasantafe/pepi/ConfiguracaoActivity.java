package br.com.usinasantafe.pepi;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.usinasantafe.pepi.bo.ConexaoWeb;
import br.com.usinasantafe.pepi.bo.ManipDadosReceb;
import br.com.usinasantafe.pepi.to.tb.variaveis.ConfiguracaoTO;

public class ConfiguracaoActivity extends ActivityGeneric {

    private Context context;
    private ProgressDialog progressBar;
    private EditText editTextLinhaConfig;
    private ConfiguracaoTO configuracaoTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        context = this;

        Button buttonAtualizarBD = (Button) findViewById(R.id.buttonAtualizarBD);
        Button buttonSalvarConfig =  (Button) findViewById(R.id.buttonSalvarConfig);
        Button buttonCancConfig = (Button) findViewById(R.id.buttonCancConfig);
        editTextLinhaConfig = (EditText) findViewById(R.id.editTextLinhaConfig);

        configuracaoTO = new ConfiguracaoTO();

        if(!configuracaoTO.hasElements()) {
            editTextLinhaConfig.setText("");
        }
        else {
            List configList = configuracaoTO.all();
            configuracaoTO = (ConfiguracaoTO) configList.get(0);
            editTextLinhaConfig.setText(String.valueOf(configuracaoTO.getNumLinha()));
        }

        buttonAtualizarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if(conexaoWeb.verificaConexao(context)){
                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();
                    ManipDadosReceb.getInstance().atualizarBD(progressBar);
                    ManipDadosReceb.getInstance().setContext(ConfiguracaoActivity.this);
                }
                else{
                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();
                }
            }
        });

        buttonSalvarConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextLinhaConfig.getText().toString().equals("")) {

                    configuracaoTO = new ConfiguracaoTO();
                    configuracaoTO.deleteAll();
                    configuracaoTO.setNumLinha(Long.valueOf(editTextLinhaConfig.getText().toString()));
                    configuracaoTO.insert();
                    configuracaoTO.commit();

                    Intent it = new Intent(ConfiguracaoActivity.this, PrincipalActivity.class);
                    startActivity(it);
                    finish();

                }
                else{

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ConfiguracaoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR! DIGITE O NUMERO DA LINHA.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                        }
                    });
                    alerta.show();

                }

            }
        });

        buttonCancConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent it = new Intent(ConfiguracaoActivity.this, PrincipalActivity.class);
                startActivity(it);
                finish();

            }
        });

    }

    public void onBackPressed()  {
    }

}
