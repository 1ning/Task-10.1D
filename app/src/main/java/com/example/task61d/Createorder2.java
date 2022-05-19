package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task61d.Database.DatabaseHelper;

public class Createorder2 extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    EditText Weight;
    EditText Longth;
    EditText Width;
    EditText Height;
    Button Create;
    String name,location,itemtype,cartype;
    int year,month,day,hour,minute;
    Double Weight1,Longth1,Width1,Height1;
    Button Furniture,Drygoods,Foods,Buildingmaterial,Other;
    Button Trucks,Van,Refrigeratedtruck,Minitruck,Other2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createorder2);
        findId();
        getvalue();
        dbHelper = new DatabaseHelper(this, "database", null, 1);
        class ButtonListener implements View.OnClickListener {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Furniture:
                        itemtype="Furniture";
                        resetbackground();
                        Furniture.setSelected(true);
                        break;
                    case R.id.Drygoods:
                        itemtype="Drygoods";
                        resetbackground();
                        Drygoods.setSelected(true);
                        break;
                    case R.id.Foods:
                        itemtype="Foods";
                        resetbackground();
                        Foods.setSelected(true);
                        break;
                    case R.id.Buildingmaterial:
                        itemtype="Buildingmaterial";
                        resetbackground();
                        Buildingmaterial.setSelected(true);
                        break;
                    case R.id.Other:
                        itemtype="Other";
                        resetbackground();
                        Other.setSelected(true);
                        break;
                    case R.id.Trucks:
                        cartype="Truck";
                        resetcarbackground();
                        Trucks.setSelected(true);
                        break;
                    case R.id.Refrigeratedtruck:
                        cartype="Refrigeratedtruck";
                        resetcarbackground();
                        Refrigeratedtruck.setSelected(true);
                        break;
                    case R.id.Van:
                        cartype="Van";
                        resetcarbackground();
                        Van.setSelected(true);
                        break;
                    case R.id.Minitruck:
                        cartype="Minitruck";
                        resetcarbackground();
                        Minitruck.setSelected(true);
                        break;
                    case R.id.Other2:
                        cartype="Other";
                        resetcarbackground();
                        Other2.setSelected(true);
                        break;
                    default:
                        break;
                }
            }
        }
                  Furniture.setOnClickListener(new ButtonListener());
                  Drygoods.setOnClickListener(new ButtonListener());
                  Foods.setOnClickListener(new ButtonListener());
                  Buildingmaterial.setOnClickListener(new ButtonListener());
                  Other.setOnClickListener(new ButtonListener());
                  Trucks.setOnClickListener(new ButtonListener());
                  Van.setOnClickListener(new ButtonListener());
                  Refrigeratedtruck.setOnClickListener(new ButtonListener());
                  Minitruck.setOnClickListener(new ButtonListener());
                  Other2.setOnClickListener(new ButtonListener());
        Create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Weight1= Double.valueOf(Weight.getText().toString());
                Height1=Double.valueOf(Height.getText().toString());
                Longth1=Double.valueOf(Longth.getText().toString());
                Width1=Double.valueOf(Width.getText().toString());
                if(cartype!=null&&itemtype!=null&&Weight1!=null&&Height1!=null&&Longth1!=null&&Width1!=null){
                    db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    putvalue(values);
                    long result = db.insert("orders", null, values);
                    db.close();
                    dbHelper.close();
                    if (result > 0) {
                        Toast.makeText(Createorder2.this, "Order created successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Createorder2.this, Home.class);
                        startActivity(intent);
                        finish(); }
                }
                else{
                    Toast.makeText(Createorder2.this, "Bad", Toast.LENGTH_SHORT).show();
                }
                 }
            });
    }
      public void findId(){
          Furniture=findViewById(R.id.Furniture);
          Drygoods=findViewById(R.id.Drygoods);
          Foods=findViewById(R.id.Foods);
          Buildingmaterial=findViewById(R.id.Buildingmaterial);
          Other=findViewById(R.id.Other);
          Trucks=findViewById(R.id.Trucks);
          Create=findViewById(R.id.Create);
          Van=findViewById(R.id.Van);
          Refrigeratedtruck=findViewById(R.id.Refrigeratedtruck);
          Minitruck=findViewById(R.id.Minitruck);
          Weight=findViewById(R.id.Weight);
          Height=findViewById(R.id.Height);
          Width=findViewById(R.id.Width);
          Longth=findViewById(R.id.Length);
           Other2=findViewById(R.id.Other2);
      }
      public void getvalue(){
          Intent getIntent = getIntent();
          name = getIntent.getStringExtra("name");
          location = getIntent.getStringExtra("location");
          year= getIntent.getIntExtra("year",0);
          month= getIntent.getIntExtra("month",0);
          day= getIntent.getIntExtra("day",0);
          hour= getIntent.getIntExtra("hour",0);
          minute= getIntent.getIntExtra("minute",0);
      }
    public void resetbackground(){
        Furniture.setSelected(false);
        Drygoods.setSelected(false);
        Foods.setSelected(false);
        Buildingmaterial.setSelected(false);
        Other.setSelected(false);
    }
    public void resetcarbackground(){
        Trucks.setSelected(false);
        Refrigeratedtruck.setSelected(false);
        Minitruck.setSelected(false);
        Van.setSelected(false);
        Other2.setSelected(false);
    }
    public void putvalue(ContentValues values){
        values.put("cartype", cartype);
        values.put("itemtype", itemtype);
        values.put("weight", Weight1);
        values.put("receiver", name);
        values.put("height", Height1);
        values.put("length", Longth1);
        values.put("width", Width1);
        values.put("year", year);
        values.put("month", month);
        values.put("day", day);
        values.put("hour", hour);
        values.put("minute", minute);
        values.put("userid", Global.id);
    }
}