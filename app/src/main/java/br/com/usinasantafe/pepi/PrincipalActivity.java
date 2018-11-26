package br.com.usinasantafe.pepi;

//import android.support.v7.app.AppCompatActivity;
import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.usinasantafe.pepi.bo.ConexaoWeb;
import br.com.usinasantafe.pepi.bo.ManipDadosEnvio;
import br.com.usinasantafe.pepi.bo.ManipDadosReceb;
import br.com.usinasantafe.pepi.bo.ManipDadosVerif;
import br.com.usinasantafe.pepi.to.tb.estaticas.FuncTO;
import br.com.usinasantafe.pepi.to.tb.variaveis.AtualizaTO;
import br.com.usinasantafe.pepi.to.tb.variaveis.ConfiguracaoTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;
    private PEPIContext pepiContext;
    private ProgressDialog progressBar;
    private TextView textViewProcesso;
    private Handler customHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        pepiContext = (PEPIContext) getApplication();
        textViewProcesso = (TextView) findViewById(R.id.textViewProcesso);

        if(!checkPermission(Manifest.permission.CAMERA)){
            String[] PERMISSIONS = {android.Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        if(!checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions((Activity) this, PERMISSIONS, 112);
        }

        customHandler.postDelayed(updateTimerThread, 0);

        ConexaoWeb conexaoWeb = new ConexaoWeb();

        progressBar = new ProgressDialog(this);

        if(conexaoWeb.verificaConexao(this))
        {

            progressBar.setCancelable(true);
            progressBar.setMessage("Buscando Atualização...");
            progressBar.show();

            ConfiguracaoTO configuracaoTO = new ConfiguracaoTO();
            List configList = configuracaoTO.all();
            if(configList.size() > 0){
                configuracaoTO = (ConfiguracaoTO) configList.get(0);
                AtualizaTO atualizaTO = new AtualizaTO();
                atualizaTO.setIdCelularAtual(configuracaoTO.getNumLinha());
                atualizaTO.setVersaoAtual(pepiContext.versaoAplic);
                ManipDadosVerif.getInstance().verAtualizacao(atualizaTO, this, progressBar);
            }
            else{
                startTimer();
            }

        }
        else{
            startTimer();
        }

        listarMenuInicial();

    }

    public void listarMenuInicial(){

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub

                TextView textView = (TextView) v.findViewById(R.id.textViewItemList);
                String text = textView.getText().toString();

                if (text.equals("APONTAMENTO")) {
                    FuncTO funcTO = new FuncTO();
                    if(funcTO.hasElements()){
                        Intent it = new Intent(PrincipalActivity.this, ListaApontaActivity.class);
                        startActivity(it);
                    }
                } else if (text.equals("CONFIGURAÇÃO")) {
                    Intent it = new Intent(PrincipalActivity.this, ConfiguracaoActivity.class);
                    startActivity(it);
                } else if (text.equals("SAIR")) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

        });

    }

    public void startTimer() {

        boolean alarmeAtivo = (PendingIntent.getBroadcast(this, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);

        if(progressBar.isShowing()){
            progressBar.dismiss();
        }

        if(alarmeAtivo){


            Log.i("PMM", "NOVO TIMER");

            Intent intent = new Intent("EXECUTAR_ALARME");
            PendingIntent p = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.SECOND, 1);

            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), 60000, p);

        }
        else{
            Log.i("PMM", "TIMER já ativo");
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void onBackPressed()  {
    }

    private Runnable updateTimerThread = new Runnable() {

        public void run() {

            if (ManipDadosEnvio.getInstance().getStatusEnvio() == 1) {
                textViewProcesso.setTextColor(Color.YELLOW);
                textViewProcesso.setText("Enviando Dados...");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 2) {
                textViewProcesso.setTextColor(Color.RED);
                textViewProcesso.setText("Existem Dados para serem Enviados");
            } else if (ManipDadosEnvio.getInstance().getStatusEnvio() == 3) {
                textViewProcesso.setTextColor(Color.GREEN);
                textViewProcesso.setText("Todos os Dados já foram Enviados");
            }

            customHandler.postDelayed(this, 10000);
        }
    };

}
