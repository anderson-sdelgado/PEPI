package br.com.usinasantafe.pepi.view;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import br.com.usinasantafe.pepi.NetworkChangeListerner;
import br.com.usinasantafe.pepi.R;
import br.com.usinasantafe.pepi.model.pst.DatabaseHelper;

/**
 * Created by anderson on 13/10/2016.
 */
public class ActivityGeneric extends OrmLiteBaseActivity<DatabaseHelper> {

    public EditText editTextPadrao;
    public static boolean connectNetwork;

    NetworkChangeListerner networkChangeListerner = new NetworkChangeListerner();

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListerner, intentFilter);
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHelper();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(findViewById(R.id.buttonNum0) != null){
            Button buttonNum0 = findViewById(R.id.buttonNum0);
            buttonNum0.setOnClickListener(new EventoBotao(0));
        }

        if(findViewById(R.id.buttonNum1) != null){
            Button buttonNum1 = findViewById(R.id.buttonNum1);
            buttonNum1.setOnClickListener(new EventoBotao(1));
        }

        if(findViewById(R.id.buttonNum2) != null){
            Button buttonNum2 = findViewById(R.id.buttonNum2);
            buttonNum2.setOnClickListener(new EventoBotao(2));
        }

        if(findViewById(R.id.buttonNum3) != null){
            Button buttonNum3 = findViewById(R.id.buttonNum3);
            buttonNum3.setOnClickListener(new EventoBotao(3));
        }

        if(findViewById(R.id.buttonNum4) != null){
            Button buttonNum4 = findViewById(R.id.buttonNum4);
            buttonNum4.setOnClickListener(new EventoBotao(4));
        }

        if(findViewById(R.id.buttonNum5) != null){
            Button buttonNum5 = findViewById(R.id.buttonNum5);
            buttonNum5.setOnClickListener(new EventoBotao(5));
        }

        if(findViewById(R.id.buttonNum6) != null){
            Button buttonNum6 = findViewById(R.id.buttonNum6);
            buttonNum6.setOnClickListener(new EventoBotao(6));
        }

        if(findViewById(R.id.buttonNum7) != null){
            Button buttonNum7 = findViewById(R.id.buttonNum7);
            buttonNum7.setOnClickListener(new EventoBotao(7));
        }

        if(findViewById(R.id.buttonNum8) != null){
            Button buttonNum8 = findViewById(R.id.buttonNum8);
            buttonNum8.setOnClickListener(new EventoBotao(8));
        }

        if(findViewById(R.id.buttonNum9) != null){
            Button buttonNum9 = findViewById(R.id.buttonNum9);
            buttonNum9.setOnClickListener(new EventoBotao(9));
        }

    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListerner);
        super.onStop();
    }

    private class EventoBotao implements View.OnClickListener{

        private int numBotao;

        public EventoBotao(int numBotao) {
            this.numBotao = numBotao;
        }

        @Override
        public void onClick(View v) {
            editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
        }

    }

}
