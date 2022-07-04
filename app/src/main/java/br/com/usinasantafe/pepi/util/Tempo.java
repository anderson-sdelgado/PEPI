package br.com.usinasantafe.pepi.util;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import br.com.usinasantafe.pepi.model.bean.estaticas.DataBean;

public class Tempo {

    private Date datahora;
    private static Tempo instance = null;

    public Tempo() {
    }

    public static Tempo getInstance() {
        if (instance == null)
            instance = new Tempo();
        return instance;
    }

    public void manipDataHora(String data){

        try{

            JSONObject jObj = new JSONObject(data.trim());
            JSONArray jsonArray = jObj.getJSONArray("dados");

            JSONObject objeto = jsonArray.getJSONObject(0);
            Gson gson = new Gson();
            DataBean dataTO = gson.fromJson(objeto.toString(), DataBean.class);

            StringBuffer dtServ = new StringBuffer(dataTO.getData());

            Log.i("PMM", "DATA HORA SERV: " + dtServ);

            dtServ.delete(10, 11);
            dtServ.insert(10, " ");

            String dtStr = String.valueOf(dtServ);

            String diaStr = dtStr.substring(0, 2);
            String mesStr = dtStr.substring(3, 5);
            String anoStr = dtStr.substring(6, 10);
            String horaStr= dtStr.substring(11, 13);
            String minutoStr= dtStr.substring(14, 16);

            Log.i("PMM", "Dia: "+ diaStr);
            Log.i("PMM", "Mes: "+ mesStr);
            Log.i("PMM", "Ano: "+ anoStr);
            Log.i("PMM", "Hora: "+ horaStr);
            Log.i("PMM", "Minuto: "+ minutoStr);

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(diaStr));
            cal.set(Calendar.MONTH, Integer.parseInt(mesStr) - 1);
            cal.set(Calendar.YEAR, Integer.parseInt(anoStr));
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaStr));
            cal.set(Calendar.MINUTE, Integer.parseInt(minutoStr));

            Date dataServ = cal.getTime();
            datahora = dataServ;

            dataTO.insert();

        }
        catch (Exception e) {
            Log.i("PMM", "Erro Manip = " + e);
        }

    }


    public String data(){

        String dataCerta = "";

        Calendar cal = Calendar.getInstance();

        datahora = new Date();

        cal.setTime(datahora);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        String horasStr = "";
        if(horas < 10){
            horasStr = "0" + horas;
        }
        else{
            horasStr = String.valueOf(horas);
        }

        String minutosStr = "";
        if(minutos < 10){
            minutosStr = "0" + minutos;
        }
        else{
            minutosStr = String.valueOf(minutos);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano+" "+horasStr+":"+minutosStr;

        return dataCerta;

    }

    public String dataSHora(){

        String dataCerta = "";

        datahora = new Date();

        Calendar cal = Calendar.getInstance();

        cal.setTime(datahora);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        mes = mes + 1;

        String mesStr = "";
        if(mes < 10){
            mesStr = "0" + mes;
        }
        else{
            mesStr = String.valueOf(mes);
        }

        String diaStr = "";
        if(dia < 10){
            diaStr = "0" + dia;
        }
        else{
            diaStr = String.valueOf(dia);
        }

        dataCerta = ""+diaStr+"/"+mesStr+"/"+ano;

        return dataCerta;

    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }
}
