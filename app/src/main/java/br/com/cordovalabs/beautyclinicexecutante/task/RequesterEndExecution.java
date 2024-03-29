package br.com.cordovalabs.beautyclinicexecutante.task;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterExecution;
import br.com.cordovalabs.beautyclinicexecutante.dto.Execution;
import br.com.cordovalabs.beautyclinicexecutante.dto.ExecutionMessage;
import br.com.cordovalabs.beautyclinicexecutante.dto.User;
import br.com.cordovalabs.beautyclinicexecutante.util.SessionManager;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterEndExecution extends RequesterPattern {

    private static RequesterEndExecution request = new RequesterEndExecution();

    public static void request(final Context context,
                               final View root,
                               final String code) {

        JsonObjectRequest jsonObjectRequest = null;

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                request.url.concat("/finalizarexecucao/"+code), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        ExecutionMessage message = new Gson().fromJson(response.toString(), ExecutionMessage.class);

                        Snackbar.make(root, message.getMensagem(), Snackbar.LENGTH_SHORT).show();

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

        RequestQueue queue = Requester.getInstance(context).getRequestQueue();
        queue.add(jsonObjectRequest);

    }

}
