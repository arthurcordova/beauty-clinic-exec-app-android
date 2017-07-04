package br.com.cordovalabs.beautyclinicexecutante.task;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.cordovalabs.beautyclinicexecutante.dto.User;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterStartExecution extends RequesterPattern {

    private static RequesterStartExecution request = new RequesterStartExecution();

    public static void request(final Context context, final User user, final Long idAgenda, final TextView tvStatus) {

        JsonObjectRequest jsonObjectRequest = null;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                request.url.concat("/iniciarexecucao/"+idAgenda+"/"+user.getCodigo()), null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            tvStatus.setText(response.getString("mensagem"));
                        } catch (JSONException e) {

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
        queue.add(jsonObjectRequest);

    }

}
