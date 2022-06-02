package com.example.contactapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    RadioGroup rg;
    ImageView iv;
    TextView t_name,t_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        rg=findViewById(R.id.rg);
        iv=findViewById(R.id.iv);
        t_name=findViewById(R.id.t_name);
        t_number=findViewById(R.id.t_number);

        Contact c=(Contact)getIntent().getExtras().get("contact");
        iv.setImageResource(c.getPicture());
        if(c.getGender().equals("female"))
            rg.check(R.id.rb_female);
        else
            rg.check(R.id.rb_male);
        t_name.setText(c.getName());
        t_number.setText(c.getNumber());

    }
}