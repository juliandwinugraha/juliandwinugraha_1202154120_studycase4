package com.example.android.juliandwinugraha_1202154120_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void allList(View view) {
        //untuk berpindah kelas ke DaftarListMahasiswa
        Intent x = new Intent(this, DaftarListMahasiswa.class);
        startActivity(x);
    }

    public void lihatGambar(View view) {
        //untuk berpindah kelas ke LihatGambarUrl
        Intent y = new Intent(this, LihatGambarUrl.class);
        startActivity(y);
    }
}
