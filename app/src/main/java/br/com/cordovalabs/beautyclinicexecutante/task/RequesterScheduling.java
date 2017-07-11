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

import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterRoom;
import br.com.cordovalabs.beautyclinicexecutante.adapter.AdapterSchedule;
import br.com.cordovalabs.beautyclinicexecutante.dto.Room;
import br.com.cordovalabs.beautyclinicexecutante.dto.Schedule;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterScheduling extends RequesterPattern {

    private static RequesterScheduling request = new RequesterScheduling();

    public static void request(final Context context, final RecyclerView recyclerView,
                               final SwipeRefreshLayout refreshLayout, final String roomId,
                               final View imgNull, final View tvNull) {

        Calendar calendar = Calendar.getInstance();
        calendar.get(Calendar.DAY_OF_MONTH);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(Calendar.getInstance().getTime());

        JsonArrayRequest jsonArrayRequest = null;
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                request.url.concat("/getagendamentos/"+date+"/3/"+roomId), null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<Schedule> list = (ArrayList<Schedule>) new Gson().fromJson(response.toString(),
                                new TypeToken<ArrayList<Schedule>>() {
                                }.getType());

                        if (list != null) {
                            if (list.size() > 0) {
                                imgNull.setVisibility(View.INVISIBLE);
                                tvNull.setVisibility(View.INVISIBLE);

                                AdapterSchedule adapter = new AdapterSchedule(list);

                                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                                recyclerView.setAdapter(adapter);
                            } else {
                                imgNull.setVisibility(View.VISIBLE);
                                tvNull.setVisibility(View.VISIBLE);
                            }
                        } else {
                            imgNull.setVisibility(View.VISIBLE);
                            tvNull.setVisibility(View.VISIBLE);
                        }

                        if (refreshLayout != null) {
                            refreshLayout.setRefreshing(false);
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("NEWUSER", "REQUEST ERROR");
                imgNull.setVisibility(View.VISIBLE);
                tvNull.setVisibility(View.VISIBLE);
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
