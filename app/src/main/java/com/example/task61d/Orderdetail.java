package com.example.task61d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task61d.Database.DatabaseHelper;

public class Orderdetail extends AppCompatActivity {
    TextView Weight,Length,Width,Height,goodstype,quantity;
    TextView Sender,Receiver,Pickuptime,Dropofftime;
    ImageView photo;
    Button estimate;
    String weight1,length1,width1,height1,goodstype1,quantity1;
    String year,month,day,hour,minute,sender,receiver,hour2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oderdetail);
        findid();
        DatabaseHelper dbHelper = new DatabaseHelper(this, "database", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //创建游标
        Cursor cursor = db.query("orders", null, "id=?", new String[]{String.valueOf(Global.orderid)}, null, null, null);
        cursor.moveToFirst();
        Cursor cursor2 = db.query("user", null, "id=?", new String[]{String.valueOf(Global.id)}, null, null, null);
        cursor2.moveToFirst();
        sender=cursor.getString(cursor2.getColumnIndexOrThrow("fullname"));
        String type1=cursor.getString(cursor.getColumnIndexOrThrow("cartype"));
        switch (type1)
        {
            case "Truck":
                photo.setImageResource(R.drawable.truck_1);
                break;
            case "Van":
                photo.setImageResource(R.drawable.truck_2);
                break;
            case "Refrigeratedtruck":
                photo.setImageResource(R.drawable.truck_3);
                break;
            case "Minitruck":
                photo.setImageResource(R.drawable.truck_4);
                break;
            case "Other":
                photo.setImageResource(R.drawable.truck_5);
                break;
        }
        weight1= cursor.getString(cursor.getColumnIndexOrThrow("weight"));
        length1= cursor.getString(cursor.getColumnIndexOrThrow("length"));
        width1= cursor.getString(cursor.getColumnIndexOrThrow("weight"));
        height1= cursor.getString(cursor.getColumnIndexOrThrow("height"));
        goodstype1= cursor.getString(cursor.getColumnIndexOrThrow("itemtype"));
        year= cursor.getString(cursor.getColumnIndexOrThrow("year"));
        hour= cursor.getString(cursor.getColumnIndexOrThrow("hour"));
        day= cursor.getString(cursor.getColumnIndexOrThrow("day"));
        minute= cursor.getString(cursor.getColumnIndexOrThrow("minute"));
        month= cursor.getString(cursor.getColumnIndexOrThrow("month"));
        receiver= cursor.getString(cursor.getColumnIndexOrThrow("receiver"));
        hour2= String.valueOf(Integer.valueOf(hour)+1);
        setvalue();
        estimate=findViewById(R.id.estimate);
        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Orderdetail.this,Googlemap.class);
                startActivity(intent);
            }
        });
        }

    public void findid(){
         Weight=findViewById(R.id.Weight);
         Length=findViewById(R.id.Length);;
         Width=findViewById(R.id.Width);;
         Height=findViewById(R.id.Height);;
         goodstype=findViewById(R.id.Type);
         quantity=findViewById(R.id.Quantity);;
         photo=findViewById(R.id.imageView);;
         Sender=findViewById(R.id.sender);
         Receiver =findViewById(R.id.receiver);
         Pickuptime=findViewById(R.id.pickuptime);
         Dropofftime=findViewById(R.id.Dropofftime);
    }
    public void setvalue(){
        Weight.setText("Weight:                   "+weight1+"kg");
        Height.setText("Height:                   "+height1+"m");
        Width.setText("Width:                   "+width1+"m");
        goodstype.setText("goodstype:                   "+goodstype1);
        Length.setText("Length:                   "+length1+"m");
        quantity.setText("quantity:                    1");
        Sender.setText(sender);
        Receiver.setText(receiver);
        Pickuptime.setText(day+" "+month+" "+year);
        Dropofftime.setText(day+" "+month+" "+year);
    }
}


