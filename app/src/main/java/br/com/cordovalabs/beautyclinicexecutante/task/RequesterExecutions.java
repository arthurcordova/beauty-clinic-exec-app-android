package br.com.cordovalabs.beautyclinicexecutante.task;

import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterExecutions extends RequesterPattern {

    private static RequesterExecutions request = new RequesterExecutions();

    public static void request(final View root) {

        JsonArrayRequest jsonArrayRequest = null;
        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                request.url.concat("/getexecucoes/19-04-2017/4"), null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("NEWUSER", response.toString());
//                            try {
//                                Log.d("NEWUSER", response.toString());
////                                pb.setVisibility(View.INVISIBLE);
////                                Beneficiary beneficiary = new Gson().fromJson(response.getJSONObject("beneficiary").toString(), Beneficiary.class);
////                                SharedBeneficiaryLogin.getInstance(root.getContext()).save(beneficiary);
////
////                                root.getContext().startActivity(new Intent(root.getContext(), MainActivity.class));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
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
        queue.add(jsonArrayRequest);

    }

}
