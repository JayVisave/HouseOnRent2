package com.example.hp.houseonrent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ShowAlert {
    public AlertDialog.Builder builder;    public AlertDialog alertDialog;

    public void Alert(final Context context)
    {
        builder = new AlertDialog.Builder(context,R.style.AlertDialogStyle);
        builder.setMessage("Are You Sure ?")
                .setTitle("Quit...");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) context).finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setIcon(R.drawable.icon);
        alertDialog = builder.create();
        alertDialog.show();
    }
}
