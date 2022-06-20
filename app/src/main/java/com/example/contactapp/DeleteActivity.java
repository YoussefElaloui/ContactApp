package com.example.contactapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    Spinner cmb;
    Button btn_delete,btn_cancel;
    Db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        cmb=findViewById(R.id.cmb);
        db=new Db(this);
        ArrayList<Contact> cc=Db.getAllContact(db.getReadableDatabase());
        ArrayList<String> ss=new ArrayList<>();
        for (Contact c:cc){
            ss.add(c.getName()+" "+c.getNumber());
        }
        ArrayAdapter<String> ad=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,ss);
        cmb.setAdapter(ad);
        btn_delete=findViewById(R.id.ddlete);
        btn_cancel=findViewById(R.id.ddncel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(DeleteActivity.this);
                alert.setTitle("Deleting");
                alert.setMessage("Are you sure about deleting this item");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Db.deleteContact(db.getWritableDatabase(),cc.get(cmb.getId()).getNumber());
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alert.show();
            }
        });
    }
}