package com.example.android.juliandwinugraha_1202154120_studycase4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso; //menampilkan gambar dari lokasi eksternal


public class LihatGambarUrl extends AppCompatActivity {


    private EditText URL;
    private ImageView Image;
    private String ImageLink;
    private Button btnImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_gambar);
        URL = findViewById(R.id.linkImg);
        Image = findViewById(R.id.imageResult);
        btnImage = findViewById(R.id.btnSearch);

    }

    public void searchPic(View view) {
        ImageLink = URL.getText().toString();
        ImageDownloader  imageDownloader = new ImageDownloader();
        imageDownloader.execute(ImageLink);
    }


    public class ImageDownloader extends AsyncTask<String, Void, String>{

        @Override
        //method ini proses thread berjalan, proses pengiriman/pengambilan data terjadi disini. Prosesnya berjalan dibackground.Method ini langsung berjalan setelah onPreExecute() berjalan.
        protected String doInBackground(String... params) {
            return params[0];
        }
        @Override
        //Method ini digunakan untuk mengupdate User Interface ketika proses doInBackground telah selesai
        protected void onPostExecute(String ImageLink) {
            super.onPostExecute(ImageLink);
            Picasso.get().load(ImageLink).into(Image);

        }
    }
}
