package com.izk.retrofit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText button;
    private Button btn_ok;
    private Button btn_ok2;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_birth;
    private TextView tv_area;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.tv_click);
        btn_ok = findViewById(R.id.btn_ok);
        btn_ok2 = findViewById(R.id.btn_ok2);
        tv_name = findViewById(R.id.tv_name);
        tv_sex = findViewById(R.id.tv_sex);
        tv_birth = findViewById(R.id.tv_birth);
        tv_area = findViewById(R.id.tv_area);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://apis.juhe.cn/idcard/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api = retrofit.create(Api.class);

        Retrofit retrofit2 = new Retrofit.Builder().baseUrl("http://v.juhe.cn/toutiao/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final Api api2 = retrofit2.create(Api.class);

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("type", "top");
        map1.put("key", "897af1512910c56d9302e601612a2f05");
        api2.getPostData(map1).enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                String name = response.body().getResult().getData().get(0).getAuthor_name();
                Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {
                t.printStackTrace();
            }
        });


        button.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                content = button.getText().toString().trim();
            }
        });


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> map = new HashMap<>();
                map.put("key", "e6de57fb8b8ba61b6af71d8124aa68a5");
                map.put("cardno", content);

                api.getData(map).enqueue(new Callback<Info>() {
                    @Override
                    public void onResponse(Call<Info> call, Response<Info> response) {
                        Info body1 = response.body();
                        Info.ResultBean result = body1.getResult();

                        String sex = result.getSex();
                        String birthday = result.getBirthday();
                        String area = result.getArea();

                        tv_name.setText("你猜");
                        tv_sex.setText(sex);
                        tv_birth.setText(birthday);
                        tv_area.setText(area);
                    }

                    @Override
                    public void onFailure(Call<Info> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        });




        /*********************  Retrofit2 与RxJava 配合使用 示例 *********************/
        btn_ok2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RxActivity.class));
            }
        });

    }
}
