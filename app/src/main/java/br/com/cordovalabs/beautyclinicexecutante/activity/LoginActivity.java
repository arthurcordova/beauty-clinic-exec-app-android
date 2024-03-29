package br.com.cordovalabs.beautyclinicexecutante.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterLogin;

public class LoginActivity extends AppCompatActivity {

    TextView mTvLogin;
    TextView mTvPwd;
    ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTvLogin = (TextView) findViewById(R.id.login);
        mTvPwd = (TextView) findViewById(R.id.pwd);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);

        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RequesterLogin.request(findViewById(R.id.sign_in_button), mProgress, mTvLogin.getText().toString(), mTvPwd.getText().toString());

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
