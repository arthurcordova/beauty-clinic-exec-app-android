package br.com.cordovalabs.beautyclinicexecutante.util;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import br.com.cordovalabs.beautyclinicexecutante.dto.User;

/**
 * Created by arthurcordova on 7/5/16.
 */

public final class SessionManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private final String PREF_NAME = "pref_session";

    private final String KEY_NAME = "name";
    private final String KEY_CODE = "user_code";

    public SessionManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createSessionLogin(User user) {
        editor.putString(KEY_NAME, user.getNome());
        editor.putInt(KEY_CODE, user.getCodigo());
        editor.commit();
    }

    public void destroySessionLogin() {
        editor.clear();
        editor.commit();
    }

    public void redirectToTarget(Class clazz) {
        Intent i = new Intent(context, clazz);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        ((AppCompatActivity) context).finish();
    }

    public User getSessionUser() {

        User user = new User();
        user.setNome(preferences.getString(KEY_NAME, null));
        user.setCodigo(preferences.getInt(KEY_CODE, 0));

        return user;

    }
}
