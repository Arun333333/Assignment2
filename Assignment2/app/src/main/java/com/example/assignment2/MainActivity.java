package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
//import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText edittextuser;
//    TextView textviewdate;
//    TextView textviewcondition;
    EditText edittextsystolic;
    EditText edittextdiastolic;
    Button submitButton;

//    Button recentsButton;
    DatabaseReference databasemembers;

    Button buttonAddStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasemembers = FirebaseDatabase.getInstance().getReference("members");
         edittextuser =findViewById(R.id.userid);
         edittextsystolic =findViewById(R.id.systolicreading);
         edittextdiastolic =findViewById(R.id.diastolicreading);


         submitButton = findViewById(R.id.Submitbutton);
//         recentsButton = findViewById(R.id.ViewRecents);
//         recentsButton.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View view) {
//                 Intent i = new Intent(this , ListMembers.class);
//                 startActivity(i);
//             }
//         });
         submitButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
             addMembers();
//                String firstname = edittextuser.getText().toString().trim();
//                Toast.makeText(this , firstname,Toast.LENGTH_LONG).show();
            }
        });

        }
        private void addMembers() {
            String firstname = edittextuser.getText().toString().trim();
            String systolic = edittextsystolic.getText().toString().trim();
            String diastolic = edittextdiastolic.getText().toString().trim();
            Integer sys = Integer.parseInt(systolic);
            Integer dia = Integer.parseInt(diastolic);
//        String diastolic = edittextdiastolic.getText().toString().trim();
            if (TextUtils.isEmpty(firstname)) {
                Toast.makeText(this, "You must enter a first name.", Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(systolic)) {
                Toast.makeText(this, "You must enter a given field", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(diastolic)) {
                Toast.makeText(this, "You must enter a given field", Toast.LENGTH_LONG).show();
                return;
            }
            Date dt = new Date();
            String currentDate = dt.toString();

            String id = databasemembers.push().getKey();
            Member mem = new Member(firstname, sys, dia, currentDate);

            Task setValueTask = databasemembers.child(id).setValue(mem);
            setValueTask.addOnSuccessListener(new OnSuccessListener() {
                @Override
                public void onSuccess(Object o) {
                   Toast.makeText(MainActivity.this,
                            "Member added.", Toast.LENGTH_LONG).show();

                    edittextuser.setText("");
                    edittextsystolic.setText("");
                    edittextdiastolic.setText("");


                }
            });
            setValueTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this,
                            "something went wrong.\n" + e.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            });
//            textviewcondition= findViewById(R.id.some);
            if(sys>108 ||dia>120){
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("You should see your doctor as soon as possible.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });



                AlertDialog alert11 = builder1.create();
                alert11.show();

            }

//            Toast.makeText(this, currentdate, Toast.LENGTH_SHORT).show();
        }
}
