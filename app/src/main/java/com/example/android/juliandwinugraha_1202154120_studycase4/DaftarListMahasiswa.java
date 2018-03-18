package com.example.android.juliandwinugraha_1202154120_studycase4;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class DaftarListMahasiswa extends AppCompatActivity {

    public ListView List;
    private Button Button;
    private ProgressBar Bar;
    private ItemList itemlist;
    private String[] mahasiswa = {"Juju", "Nunu", "Lian", "Diego", "Dora", "Ac Milan", "Boston", "Celtics"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        List = findViewById(R.id.listView);
        Button = findViewById(R.id.btnAsync);
        Bar = findViewById(R.id.bar);

        //class yang mengatur item-item pada ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        List.setAdapter(adapter);
    }



    public void startAsync(View view) {
        itemlist = new ItemList();
        itemlist.execute();
    }


    public class ItemList extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int mCounter = 1;
        ProgressDialog mDialog = new ProgressDialog(DaftarListMahasiswa.this);


        @Override
        //Method ini dipanggil sebelum task dikerjakan, biasanya digunakan untuk inisiasi users
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) List.getAdapter();
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //untuk mengatur spinner style di progress dialog
            mDialog.setTitle("Silahkan Menunggu");//Komponen ini digunakan untuk mengatur judul dialog progress
            mDialog.setProgress(0); //set progress pada ProgressDialog
            mDialog.show();
        }

        @Override
        //method ini proses thread berjalan, proses pengiriman/pengambilan data terjadi disini
        protected Void doInBackground(Void... params) {
            for (String data : mahasiswa){
                publishProgress(data);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        //method ini untuk pemberitahuan bahwa pengambilan/pengiriman data sedang berlangsung
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            //menghitung persen di dialog
            Integer status = (int) ((mCounter / (float) mahasiswa.length) * 100);
            Bar.setProgress(status);

            //set status pada ProgressDialog
            mDialog.setProgress(status);

            //set message will not working when using horizontal loading. Komponen ini menampilkan pesan yang dibutuhkan dalam progress dialog
            mDialog.setMessage(String.valueOf(status + "%"));
            mCounter++;
        }
        @Override
        //Method ini digunakan untuk mengupdate User Interface
        protected void onPostExecute(Void aVoid) {
            Bar.setVisibility(View.GONE); //menyembunyikan progreebar

            mDialog.dismiss(); //menghapus progress dialog
            List.setVisibility(View.VISIBLE);
        }
    }
}
