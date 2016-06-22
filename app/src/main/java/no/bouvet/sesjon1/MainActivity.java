package no.bouvet.sesjon1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(this.getLocalClassName(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(this.getLocalClassName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.getLocalClassName(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getLocalClassName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(this.getLocalClassName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(this.getLocalClassName(), "onDestroy");
    }
}
