package com.example.jonatan.jonanavigationdrawer.modelo;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jonatan.jonanavigationdrawer.adapter.NoticiaVO;
import com.example.jonatan.jonanavigationdrawer.adapter.NoticiaVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WebServiceFusap {

    private RequestQueue request;
    private JsonObjectRequest jsonObjectRequest;
    private StringRequest stringRequest;
    private Context context;
    private FragmentCallback callback;

    private ArrayList<NoticiaVO> listaDeNoticias = new ArrayList<NoticiaVO>();

    public WebServiceFusap(FragmentCallback callback){
        this.callback = callback;
    }

    public interface FragmentCallback{

        void onResultsReady(ArrayList<NoticiaVO> news);

    }

    public void getNoticias(Context context) {

        this.context=context;

        listaDeNoticias.clear();

        request= Volley.newRequestQueue(context);

        consultarWebService();

    }


    private void consultarWebService() {

        String url = "http://runity.fusap.com.ar/api/v1/news?count=5&newsID=0&direction=1";

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.optJSONArray("Data");

                            mapearJsonArrayALaLista(jsonArray);

                            callback.onResultsReady(listaDeNoticias);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //Failure Callback
                    }
                })
        {

            /** Passing some request headers* */
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap();
                headers.put("AppID", "C1F5687A-785A-43BD-91F2-88436B2FCB15");
                headers.put("Token", "6a6ed3d0-790a-434d-96d3-6560aa48a533");
                return headers;
            }
        };

        request.add(jsonObjectRequest);

    }


    private void mapearJsonArrayALaLista(JSONArray json) throws JSONException {
        NoticiaVO noticia=null;

        for(int i=0;i<json.length();i++){
            noticia = new NoticiaVO();
            JSONObject jsonObject = null;
            jsonObject= json.getJSONObject(i);
            noticia.setUserName(jsonObject.optString("UserName"));
            noticia.setAvatarUrl(jsonObject.optString("AvatarUrl"));
            noticia.setDescription(jsonObject.optString("Description"));
            listaDeNoticias.add(noticia);
        }
        listaDeNoticias.size();
    }

}