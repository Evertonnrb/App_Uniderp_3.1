package br.com.nrbsistemas.uniderp_31.util;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Everton on 24/05/2017.
 */

public class Mensagens {

    public static final String TAG = "_info";

    public static void MsgToastC(Activity activity, String msg){
        Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
    }
    public static void MsgToastL(Activity activity, String msg){
        Toast.makeText(activity,msg,Toast.LENGTH_LONG).show();
    }
    public static void alertaSimplesButton(Activity activity,String titulo,String msg){
        AlertDialog.Builder alerta = new AlertDialog.Builder(activity);
        alerta.setTitle(titulo)
                .setMessage(msg)
                .setNeutralButton("Ok",null);
    }
}
