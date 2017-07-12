package br.com.cordovalabs.beautyclinicexecutante.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import br.com.cordovalabs.beautyclinicexecutante.R;
import br.com.cordovalabs.beautyclinicexecutante.dto.User;
import br.com.cordovalabs.beautyclinicexecutante.fragment.ExecutionFragment;
import br.com.cordovalabs.beautyclinicexecutante.fragment.RoomFragment;
import br.com.cordovalabs.beautyclinicexecutante.task.RequesterLogin;
import br.com.cordovalabs.beautyclinicexecutante.util.SessionManager;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ExecutionFragment executionFragment = new ExecutionFragment();
            switch (item.getItemId()) {
                case R.id.navigation_rooms:
                    inflateLayout(new RoomFragment());
                    return true;
                case R.id.navigation_notifications:
                    inflateLayout(executionFragment);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_rooms);

        User user = null;
        try {
            user = (User)getIntent().getSerializableExtra(RequesterLogin.PARAM_USER);
            SessionManager sm = new SessionManager(this);
            sm.createSessionLogin(user);
        } catch (Exception e){
            Log.e("USERERROR", "Nenhum usuário logado encontrado.");
        }

    }

    private void inflateLayout(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment).commit();
    }

}
