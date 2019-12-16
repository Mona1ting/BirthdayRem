package com.example.birthdayrem;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birthdayrem.lili.factory.BirthdayDaoFactory;
import com.example.birthdayrem.lili.pojo.Birthday;

public class AddActivity extends AppCompatActivity {
    private Button button1, button2, button3, button4;
    private EditText editText;
    private Calendar bir, c, time;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    public void init() {
        button1 = (Button) findViewById(R.id.add_button1);
        button2 = (Button) findViewById(R.id.add_button2);
        button3 = (Button) findViewById(R.id.add_button3);
        button4 = (Button) findViewById(R.id.add_button4);
        editText = (EditText) findViewById(R.id.add_edittext);
        button1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                //指定需要获取的时间点
                Calendar c = Calendar.getInstance();
                bir = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        bir.set(Calendar.YEAR, i);
                        bir.set(Calendar.MONTH, i1);
                        bir.set(Calendar.DAY_OF_MONTH, i2);
                        bir.set(Calendar.HOUR_OF_DAY, 0);
                        bir.set(Calendar.MINUTE, 0);
                        bir.set(Calendar.SECOND, 0);
                    }

                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivityForResult(intent, 0);
                try {
                    Birthday birthday = new Birthday();
                    birthday.setName(editText.getText().toString());
                    birthday.setYear(bir.get(Calendar.YEAR));
                    birthday.setMonth(bir.get(Calendar.MONTH));
                    birthday.setDay(bir.get(Calendar.DAY_OF_MONTH));
                    Long _id = BirthdayDaoFactory.getEmptyBirthdayDaoInstance(AddActivity.this).addBirthday(birthday);
                    Log.e("_id", _id + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                time = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        time.set(Calendar.YEAR, i);
                        time.set(Calendar.MONTH, i1);
                        time.set(Calendar.DAY_OF_MONTH, i2);
                        time.set(Calendar.HOUR_OF_DAY, 0);
                        time.set(Calendar.MINUTE, 0);
                        time.set(Calendar.SECOND, 0);


                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if (time == null) {
                    Toast.makeText(AddActivity.this, "请设置提醒日期", Toast.LENGTH_SHORT).show();
                    return;
                }
                name = editText.getText().toString();
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                //pendingIntent:不是立刻发生，而是在合适的条件下触发Intent
                Intent intent = new Intent(AddActivity.this, MyIntentService.class);
                intent.putExtra("name", name);
                PendingIntent pendingIntent = PendingIntent.getService(AddActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(), pendingIntent);


            }
        });


    }
}
