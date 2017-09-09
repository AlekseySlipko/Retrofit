package com.exam.aleksey.myapplicationrrrrrr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String a, b, answerHTTP;
    private final String server = "";
    private TextView textView;
    private EditText editText;
    private EditText editText2;
    private Button button;

    private Gson gson = new GsonBuilder().create();

    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(server)
            .build();


    private Request req = retrofit.create(Request.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = editText.getText().toString();
                b = editText2.getText().toString();
                HashMap<String, String> postDataParams = new HashMap<String, String>();
                postDataParams.put("a", a);
                postDataParams.put("b", b);
                Call<Object> call = req.performPostCall(postDataParams);

                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        HashMap<String, Double> map =
                                gson.fromJson(response.body().toString(), HashMap.class);

                        answerHTTP = Double.toString(map.get("c"));
                        textView.setText(answerHTTP);
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        textView.setText("Request error");
                    }


                });
            }
        });
    }
}