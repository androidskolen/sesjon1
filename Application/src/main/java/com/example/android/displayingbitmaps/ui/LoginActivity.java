package com.example.android.displayingbitmaps.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.displayingbitmaps.R;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    private boolean hasShownRationale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EditText passwordView = (EditText) findViewById(R.id.password);
        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    gotoImageGridActivity();
                    return true;
                }
                return false;
            }
        });

        Button emailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        emailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoImageGridActivity();
            }
        });
    }


    /**
     * Implementer denne metoden. Først må det sjekkes at man har rettigheter til å bruke internett.
     * Dersom man ikke har det, skal man kalle showWhyWeNeedInternetPermissionDialog(). Dialogen vil kalle
     * gotoImageGridActivity igjen når dialogen lukkes.
     *
     * Når man har vist informasjon om hvorfor man trenger rettigheter, skal man be om rettighetene.
     *
     * Når man har fått rettigheter, skal man lage en intent som åpner ImageGridActivity.
     */
    private void gotoImageGridActivity() {


    }

    /**
     * Viser en dialog med forklaring til hvorfor man trenger internet permission. Kaller gotoImageGridActivity
     * dersom brukeren trykke "OK".
     */
    private void showExternalStoragePermissionDialog() {
        hasShownRationale = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gotoImageGridActivity();
                    }
                }).setNegativeButton(R.string.negative_button, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

