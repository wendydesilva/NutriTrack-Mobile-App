package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class CalorySearch extends AppCompatActivity {

    EditText foodItem;
    Button caloryBtn;
    TextView fibre,tFat,sFat,sodium,potassium,cholesterol,carbohydrates,
             sugar,message;

    final String APP_ID = "b011861c";
    final String APP_KEY = "e4c44f00cfea440c2f6403cdbcfa292d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calory_search);
        foodItem = findViewById(R.id.caloryET);
        caloryBtn = findViewById(R.id.calaryBtn);
        tFat = findViewById(R.id.fatTotal);
        fibre = findViewById(R.id.fibre);
        sFat = findViewById(R.id.satFat);
        sodium = findViewById(R.id.sodium);
        potassium = findViewById(R.id.potassium);
        cholesterol = findViewById(R.id.cholesterol);
        carbohydrates = findViewById(R.id.carbs);
        sugar = findViewById(R.id.sugar);
        message = findViewById(R.id.errorMessage);



    }
    public void fetchNutritionData(View view) {
        String food = foodItem.getText().toString();
        if (food.trim().isEmpty()) {
            message.setText("Please enter a food item.");
            return;
        }
        OkHttpClient client = new OkHttpClient();

        String encodedFood = food.replace(" ", "%20");
        String url = "https://api.edamam.com/api/nutrition-data?app_id=" + APP_ID +
                "&app_key=" + APP_KEY +
                "&ingr=" + encodedFood;

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                runOnUiThread(() -> message.setText("No data found."));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String json = response.body().string();
                Log.d("API_RESPONSE", json);


                try {
                    JSONObject obj = new JSONObject(json);
                    JSONObject totalNutrients = obj
                            .getJSONArray("ingredients").getJSONObject(0)
                            .getJSONArray("parsed").getJSONObject(0)
                            .getJSONObject("nutrients");

                    double sugarVal = totalNutrients.getJSONObject("SUGAR").getDouble("quantity");
                    double fatVal = totalNutrients.getJSONObject("FAT").getDouble("quantity");
                    double satFatVal = totalNutrients.getJSONObject("FASAT").getDouble("quantity");
                    double sodiumVal = totalNutrients.getJSONObject("NA").getDouble("quantity");
                    double potassiumVal = totalNutrients.getJSONObject("K").getDouble("quantity");
                    double cholesterolVal = totalNutrients.getJSONObject("CHOLE").getDouble("quantity");
                    double carbsVal = totalNutrients.getJSONObject("CHOCDF").getDouble("quantity");
                    double fiberVal = totalNutrients.getJSONObject("FIBTG").getDouble("quantity");

                    runOnUiThread(() -> {
                        tFat.setText(String.format("%.1f g", fatVal));
                        sFat.setText(String.format("%.1f g", satFatVal));
                        sodium.setText(String.format("%.1f mg", sodiumVal));
                        potassium.setText(String.format("%.1f mg", potassiumVal));
                        cholesterol.setText(String.format("%.1f mg", cholesterolVal));
                        carbohydrates.setText(String.format("%.1f g", carbsVal));
                        fibre.setText(String.format("%.1f g", fiberVal));
                        sugar.setText(String.format("%.1f g", sugarVal));

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> message.setText("Failed to parse nutrition data."));
                }
            }
        });
    }

}