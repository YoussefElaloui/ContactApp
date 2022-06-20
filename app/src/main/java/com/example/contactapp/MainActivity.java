package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView lst;
    Db db;
    ArrayList<Contact> cc;
    Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst=findViewById(R.id.lst);
        btn_add=findViewById(R.id.btn_addNew);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,AddContact.class);
                startActivity(i);
            }
        });
        db=new Db(this);


        cc=new ArrayList<>();
        cc.add(new Contact("Youness","male","0673284936",R.drawable.male));
        cc.add(new Contact("hmido","male","0602884936",R.drawable.male));
        cc.add(new Contact("kaido","male","0013700936",R.drawable.male));
        cc.add(new Contact("podingo","female","31903847",R.drawable.female));
        cc.add(new Contact("pinasu","male","47403403",R.drawable.male));
        cc.add(new Contact("omanko","female","077393622",R.drawable.female));
        cc.add(new Contact("opaiyo","female","1102472",R.drawable.female));
        for (Contact c:cc)
            if (Db.insertContact(db.getWritableDatabase(),c)==-1)
            Toast.makeText(this, "Cannot insert data into database", Toast.LENGTH_LONG).show();
        cc=Db.getAllContact(db.getReadableDatabase());
        ArrayList<HashMap<String,Object>> data=new ArrayList<>();
        for (Contact c:cc){
            HashMap<String,Object> hm=new HashMap<>();
            hm.put("name",c.getName());
            hm.put("number",c.getNumber());
            hm.put("pic",c.getPicture());
            data.add(hm);
        }
        String[] from={"name","number","pic"};
        int[] to={R.id.lbl_name,R.id.lbl_number,R.id.pic};
        SimpleAdapter ad=new SimpleAdapter(this,data,R.layout.contact,from,to);
        lst.setAdapter(ad);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact con=cc.get(i);
                Intent in=new Intent(MainActivity.this,Details.class);
                in.putExtra("contact",con);
                startActivity(in);
            }
        });
    }

    public void ddolpa(View view) {
        Intent i=new Intent(MainActivity.this,DeleteActivity.class);
        startActivity(i);
    }
}