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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterExecution;
import br.com.cordovalabs.beautyclinicexecutante.dto.Execution;
import br.com.cordovalabs.beautyclinicexecutante.dto.User;
import br.com.cordovalabs.beautyclinicexecutante.util.SessionManager;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterExecutions extends RequesterPattern {

    private static RequesterExecutions request = new RequesterExecutions();

    public static void request(final Context context,
                               final RecyclerView recyclerView,
                               final SwipeRefreshLayout refreshLayout,
                               final boolean isPending,
                               final View view) {

        JsonArrayRequest jsonArrayRequest = null;
        String status = isPending ? "A" : "F";
        SessionManager sm = new SessionManager(context);
        User user = sm.getSessionUser();

        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                request.url.concat("/getexecucoes/"+user.getCodigo()+"/3/" + status), null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<Execution> list = (ArrayList<Execution>) new Gson().fromJson(response.toString(),
                                new TypeToken<ArrayList<Execution>>() {
                                }.getType());

                        AdapterExecution adapter = new AdapterExecution(list, view);

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
