package com.example.finalproject;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView data;
    private RequestQueue queue;
    String firstIngridient, secondIngridient, thirdIngridient;

    EditText firstIngridientInput, secondIngridientInput, thirdIngridientInput;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the Input Boxes.
        firstIngridientInput = (EditText) findViewById(R.id.add_First);
        secondIngridientInput = (EditText) findViewById(R.id.add_Second);
        thirdIngridientInput = (EditText) findViewById(R.id.add_Third);

        //Get the Search Button
        Button searchButton = findViewById(R.id.searching);
        data = (TextView) findViewById(R.id.fetchedData);
        queue = Volley.newRequestQueue(getApplicationContext());
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the user input.
                firstIngridient = firstIngridientInput.getText().toString();
                secondIngridient = secondIngridientInput.getText().toString();
                thirdIngridient = thirdIngridientInput.getText().toString();


                // The Parsing stuff.
                jsonParse(1);

                 //Request a string response from the provided URL.
            }

            private void jsonParse(final int i) {
                String url = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" + i + "/summary";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String summary = response.getString("summary");


                            //data.append(summary + "," + String.valueOf(summary) + "\n\n");
                            if (summary.toLowerCase().contains(firstIngridient.toLowerCase()) &&
                                    summary.toLowerCase().contains(secondIngridient.toLowerCase()) &&
                                    summary.toLowerCase().contains(thirdIngridient.toLowerCase())) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    data.setText(Html.fromHtml(summary, Html.FROM_HTML_MODE_LEGACY));
                                } else {
                                    data.setText(Html.fromHtml(summary));
                                }
                            } else {
                                if (i == 1107049) {
                                    data.setText("No Recipies for you, just call Jimmy John's");
                                    return;
                                }
                                jsonParse(i + 1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
                        params.put("X-RapidAPI-Key", "c83d1ec8c7msh7f1109b5f61a271p1aaba4jsn5e8a96f21206");
                        return params;
                    }
                };
                queue.add(request);
            }
            ;
        });
    }
}
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                data.setText("Response is: " + response.toString());
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        data.setText("Response is: " + error.toString());
//                    }
//                }) {
//                    @Override
//                    public Map<String, String> getHeaders() {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com");
//                        params.put("X-RapidAPI-Key", "c83d1ec8c7msh7f1109b5f61a271p1aaba4jsn5e8a96f21206");
//                        return params;
//                    }
//                };
//
//                // Add the request to the RequestQueue.
//                queue.add(stringRequest);
