package br.pega.oganeson.helpers;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import br.pega.oganeson.R;

public class DialogHelper {

    public static void Alert(Context context, String title, String message){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton(context.getResources().getString(R.string.ok), (dialog, which) -> dialog.cancel());
        alert.create();
        alert.show();
    }
}
