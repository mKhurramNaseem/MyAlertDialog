package com.pkg.myalertdialogue;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class MainActivity extends AppCompatActivity {
    AppCompatButton btn_click_new,btn_doIt,btn_custom,btn_date,btn_second_activity,btn_exit;
    AppCompatButton btn_submit;
    EditText et_username,et_password;
    String username,password;
    ImageView img_close;
    TextView view_time,view_date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click_new = findViewById(R.id.btn_click);
        btn_doIt = findViewById(R.id.btn_doIt);
        btn_custom = findViewById(R.id.btn_form);
        btn_date = findViewById(R.id.btn_date);
        view_time = findViewById(R.id.time_view);
        view_date = findViewById(R.id.view_date);
        btn_second_activity = findViewById(R.id.second_activity);
        btn_exit = findViewById(R.id.btn_exit);
        btn_click_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Warning")
                        .setMessage("Do you want to exit ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Toast.makeText(MainActivity.this, "Application Ended on btn Click", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "Application Continued", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
            }
        });
        btn_doIt.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setTitleText("Timer")
                        .build();
                timePicker.show(getSupportFragmentManager(),"time");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view_time.setText(timePicker.getHour() + " : " + timePicker.getMinute() + " : ");
                    }
                });
            }
        });

        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.my_custom_dialog);
                dialog.show();
                et_username = dialog.findViewById(R.id.et_username);
                et_password = dialog.findViewById(R.id.et_password);
                btn_submit = dialog.findViewById(R.id.btn_submit);
                img_close = dialog.findViewById(R.id.img_close);

                password = et_password.getText().toString();
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!et_username.getText().toString().equals("") && !et_password.getText().toString().equals("")){
                            username = et_username.getText().toString();
                            Toast.makeText(MainActivity.this, username + "'s form submitted successfully", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "required fields are empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            dialog.dismiss();
                    }
                });
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this);
                datePicker.show();
                datePicker.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                       view_date.setText(date + " / " + (month+1) + " / " + year);
                    }
                });
            }
        });

        btn_second_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
