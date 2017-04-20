package br.com.cordovalabs.beautyclinicexecutante.task;

import android.content.Context;
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
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterExecution;
import br.com.cordovalabs.beautyclinicexecutante.model.Execution;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterExecutions extends RequesterPattern {

    private static RequesterExecutions request = new RequesterExecutions();

    public static void request(final Context context, final RecyclerView recyclerView, final SwipeRefreshLayout refreshLayout) {

        JsonArrayRequest jsonArrayRequest = null;
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                request.url.concat("/getexecucoes/19-04-2017/4"), null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("NEWUSER", response.toString());
                        List<Execution> list = (ArrayList<Execution>) new Gson().fromJson(response.toString(),
                                new TypeToken<ArrayList<Execution>>() {
                                }.getType());

                        AdapterExecution adapter = new AdapterExecution(list);


                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(adapter);

                        if (refreshLayout != null) {
                            refreshLayout.setRefreshing(false);
                        }

//                        recyclerView.setLayoutAnimation(AnimationUtils.slideInLeft(rv.getContext()));
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
        queue.add(jsonArrayRequest);

    }

}
