package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null); // TABLO OLUŞTUR
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY , name VARCHAR ,age INT)"); //  TABLO VERİLERİNİN TÜRÜ

            database.execSQL("INSERT INTO musicians(name,age) VALUES ('Mert',21)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES ('Yasemin',20)");
            database.execSQL("INSERT INTO musicians(name,age) VALUES ('Azra',30)"); // VERİ TANIMLA


            database.execSQL("UPDATE musicians SET age = 40 WHERE name = 'Yasemin' "); // GÜNCELLEME İŞLEMİ

            database.execSQL("DELETE FROM musicians WHERE id = 2"); // ID 2 OLANI SİLER


            Cursor cursor =  database.rawQuery("SELECT * FROM musicians WHERE age > 15", null); // VERİTABANINDA SORGU YAPAR - FİLTRELEME

            /* Cursor cursor =  database.rawQuery("SELECT * FROM musicians WHERE name LIKE '%t' ", null);

            burada sonu t ile bitenleri getirir


            Cursor cursor =  database.rawQuery("SELECT * FROM musicians WHERE name LIKE 'M%' ", null);

            burada  M ile başlayanları getirir


             */


            int nameIX  = cursor.getColumnIndex("name"); // name kısmını sorgular
            int ageIX = cursor.getColumnIndex("age"); // age kısmını sorgular
            int idIX = cursor.getColumnIndex("id"); // id otomatik atar

            while (cursor.moveToNext()){   // tek tek bütün verileri gezer
                System.out.println("Name: " +cursor.getString(nameIX));  // ÇIKTI VERECEK VERİTABANINDAN
                System.out.println("Age: " +cursor.getInt(ageIX));
                System.out.println("Id: " +cursor.getInt(idIX));
            }

            cursor.close();


        }catch (Exception e){
            e.printStackTrace();

        }
    }
}