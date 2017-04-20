package br.com.cordovalabs.beautyclinicexecutante.task;

import android.util.Log;
import android.view.View;

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

/**
 * Created by acstapassoli on 30/11/2016.
 */

public class RequesterLogin extends RequesterPattern {

    private static RequesterLogin requestLogin = new RequesterLogin();

    public static void request(final View root) {

//        String newUser = "{\n" +
////                "\t\"nrCpf\":\"06048472978\",\n" +
//                "\t\"nrCpf\":\"\",\n" +
//                "\t\"nrIdentidade\":\"\",\n" +
//                "\t\"cdRfc\":\"\",\n" +
//                "\t\"nmPessoaFisica\":\"\",\n" +
//                "\t\"dsEmail\":\"\",\n" +
//                "\t\"dtNascimento\":\"\",\n" +
//                "\t\"cdUsuarioPlano\":\"\",\n" +
//                "\t\"nrDddCelular\":\"\",\n" +
//                "\t\"nrTelefoneCelular\":\"\",\n" +
//                "\t\"cdCep\":\"\",\n" +
//                "\t\"dsEndereco\":\"\"\n" +
//                "}";

        String login = "{\n" +
                "  \"login\": \"tst02\",\n" +
                "  \"name\": \"tst02\",\n" +
                "  \"email\": \"tst02@mail.com\",\n" +
                "  \"authenticationType\": \"PRINCIPAL_WITH_PASSWORD_OR_BARCODE\",\n" +
                "  \"password\":\"123mudar\",\n" +
                "  \"alternativeLogin\": \"tst02\",\n" +
                "  \"replacementLogin\": \"tst02\"\n" +
                "}\n";


        JsonObjectRequest jsonObjReq = null;
        try {
            jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                    requestLogin.url.concat("/resources/user"), new JSONObject(login),
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
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
        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestQueue queue = Requester.getInstance(root.getContext()).getRequestQueue();
        queue.add(jsonObjReq);

    }

}
