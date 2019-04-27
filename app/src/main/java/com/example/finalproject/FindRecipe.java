package com.example.finalproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class FindRecipe {
    public static void main(String[] args) {
        int five = 5;
    }
    public void function() {
                try {
            HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/4632/summary")
                    .header("X-RapidAPI-Host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "c83d1ec8c7msh7f1109b5f61a271p1aaba4jsn5e8a96f21206")
                    .asJson();
            JsonNode node = response.getBody();
            System.out.println(node.getObject().getJSONObject("title"));
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
