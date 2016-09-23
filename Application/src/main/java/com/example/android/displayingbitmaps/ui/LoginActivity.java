package com.example.android.displayingbitmaps.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    private final static int REQUEST_EXTERNAL_STORAGE = 1;
    private boolean hasShownRationale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
     * Implementer denne metoden. Først må det sjekkes at man har rettigheter til å lagre på
     * external storage.
     * Dersom man ikke har det, skal man kalle showExternalStoragePermissionRationale(). Dialogen vil kalle
     * gotoImageGridActivity igjen når dialogen lukkes.
     *
     * Når man har vist informasjon om hvorfor man trenger rettigheter, skal man be om rettighetene.
     *
     * Når man har fått rettigheter, skal man lage en intent som åpner ImageGridActivity.
     */
    private void gotoImageGridActivity() {
        boolean hasExternalStoragePermission = checkExternalStoragePermission();
        if (hasExternalStoragePermission) {
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivity(intent);
        }
    }

    private boolean checkExternalStoragePermission() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {

            // Skal vi vise en forklaring
            if (!hasShownRationale && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showExternalStoragePermissionRationale();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
            }
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gotoImageGridActivity();
            }
        }
    }

    /**
     * Viser en dialog med forklaring til hvorfor man trenger internet permission. Kaller gotoImageGridActivity
     * dersom brukeren trykke "OK".
     */
    private void showExternalStoragePermissionRationale() {
        hasShownRationale = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        gotoImageGridActivity();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

