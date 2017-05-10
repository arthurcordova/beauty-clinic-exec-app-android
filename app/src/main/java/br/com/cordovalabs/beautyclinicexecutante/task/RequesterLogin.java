package br.com.cordovalabs.beautyclinicexecutante.task;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.cordovalabs.beautyclinicexecutante.activity.LoginActivity;
import br.com.cordovalabs.beautyclinicexecutante.activity.MainActivity;
import br.com.cordovalabs.beautyclinicexecutante.model.User;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterLogin extends RequesterPattern {

    private static RequesterLogin request = new RequesterLogin();
    public static String PARAM_USER = "model_user";

    public static void request(final View root, String login, String pwd) {

        JsonObjectRequest jsonObjReq = null;
        jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                request.url.concat("/dologin/".concat(login).concat("/").concat(pwd)), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Login", response.toString());
                        User user = new Gson().fromJson(response.toString(), User.class);

                        Intent it = new Intent(root.getContext(), MainActivity.class);
                        it.putExtra(PARAM_USER, user);
                        root.getContext().startActivity(it);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("NEWUSER", "REQUEST ERROR");
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }


        };
        RequestQueue queue = Requester.getInstance(root.getContext()).getRequestQueue();
        queue.add(jsonObjReq);

    }

}
