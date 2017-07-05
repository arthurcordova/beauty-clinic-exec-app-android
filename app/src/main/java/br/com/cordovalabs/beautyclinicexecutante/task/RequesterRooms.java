package br.com.cordovalabs.beautyclinicexecutante.task;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterExecution;
import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterRoom;
import br.com.cordovalabs.beautyclinicexecutante.dto.Execution;
import br.com.cordovalabs.beautyclinicexecutante.dto.Room;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterRooms extends RequesterPattern {

    private static RequesterRooms request = new RequesterRooms();

    public static void request(final Context context, final RecyclerView recyclerView, final SwipeRefreshLayout refreshLayout) {
        JsonArrayRequest jsonArrayRequest = null;
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                request.url.concat("/listarsalas/3"), null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<Room> list = (ArrayList<Room>) new Gson().fromJson(response.toString(),
                                new TypeToken<ArrayList<Room>>() {
                                }.getType());

                        AdapterRoom adapter = new AdapterRoom(list);

                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(adapter);

                        if (refreshLayout != null) {
                            refreshLayout.setRefreshing(false);
                        }
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
