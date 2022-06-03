package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText name,number;
    RadioGroup rg;
    Button btn_save,btn_cancel;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        db=new Db(this);
        name=findViewById(R.id.tt_name);
        number=findViewById(R.id.tt_number);
        rg=findViewById(R.id.rdg);
        btn_save=findViewById(R.id.b_eng);
        btn_cancel=findViewById(R.id.b_cnl);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact c=new Contact();
                c.setName(name.getText().toString());
                c.setNumber(number.getText().toString());
                if(rg.getCheckedRadioButtonId()==R.id.rd1){
                    c.setGender("male");
                    c.setPicture(R.drawable.male);
                }else{
                    c.setGender("female");
                    c.setPicture(R.drawable.female);
                }
                if(Db.insertContact(db.getWritableDatabase(),c)==-1)
                {
                    Toast.makeText(AddContact.this, "Cannot add data", Toast.LENGTH_LONG).show();
                    return;
                }
                finish();
            }
        });

    }
}