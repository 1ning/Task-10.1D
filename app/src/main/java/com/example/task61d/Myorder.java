package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.task61d.Database.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Myorder extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    Bitmap mBitmap;
    Menu menu;
    FloatingActionButton create;
    MenuItem item;
    private SQLiteDatabase db;
    private RecyclerView mRecyclerView;
    public List<Truck>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        dbHelper = new DatabaseHelper(this, "database", null, 1);
        db = dbHelper.getWritableDatabase();
        mRecyclerView = findViewById(R.id.recycler_view);
        create=findViewById(R.id.Create);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Truck> list = queryAllTrucksData();
        if (this != null) {
            MyorderAdapter myorderAdapter = new MyorderAdapter(this, list);
            mRecyclerView.setAdapter(myorderAdapter);
        }
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Myorder.this,Createorder.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                Intent intent2=new Intent(Myorder.this,Home.class);
                startActivity(intent2);
                break;
            case R.id.account:
                Intent intent=new Intent(Myorder.this,Account.class);
                startActivity(intent);
                break;
            case R.id.order:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public List<Truck> queryAllTrucksData(){
        Cursor cursor = db.query("orders",null,"userid=?",new String[]{Global.id+""},null,null,null);
        List<Truck> list = new ArrayList<>();
        if(cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String type = cursor.getString(cursor.getColumnIndexOrThrow("cartype"));
                Cursor cursor2 = db.query("truck",null,"type=?",new String[]{type},null,null,null,"0,1");
                cursor2.moveToFirst();
                Truck model = new Truck();
                model.id= cursor.getString(cursor.getColumnIndexOrThrow("id"));
                model.type=type;
                model.context=cursor2.getString(cursor2.getColumnIndexOrThrow("context"));
                list.add(model);
                cursor.moveToNext();
                cursor2.close();
            }
        }
        cursor.close();
        db.close();
        return list;
    }

}
