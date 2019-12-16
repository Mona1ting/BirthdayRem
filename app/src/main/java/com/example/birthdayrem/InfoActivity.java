package com.example.birthdayrem;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.birthdayrem.lili.factory.BirthdayDaoFactory;
import com.example.birthdayrem.lili.pojo.Birthday;
import com.example.birthdayrem.lili.util.DateUtil;

public class InfoActivity extends AppCompatActivity {
    private Birthday birthday;
    private EditText editText;
    private TextView textView;
    private Button button1, button2, button3;
    private boolean flag = false;
    private Calendar c;
    private long _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        _id = intent.getLongExtra("_id", 0);
        if (_id > 0) {
            //从数据库里面按照id查询当前记录
            try {
                birthday = BirthdayDaoFactory.getEmptyBirthdayDaoInstance(this).selectOneBirthdayById(_id);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        init();
        button1.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(InfoActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        c.set(Calendar.YEAR, i);
                        c.set(Calendar.MONTH, i1);
                        c.set(Calendar.DAY_OF_MONTH, i2);
                        flag = true;

                    }
                }, birthday.getYear(), birthday.getMonth(), birthday.getDay());
                dialog.show();

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                birthday.setName(editText.getText().toString());
                if (flag) {
                    birthday.setYear(c.get(Calendar.YEAR));
                    birthday.setMonth(c.get(Calendar.MONTH));
                    birthday.setDay(c.get(Calendar.DAY_OF_MONTH));
                }
                try {
                    int count = BirthdayDaoFactory.getEmptyBirthdayDaoInstance(InfoActivity.this).updateBirthday(birthday);
                    if (count > 0) {
                        goBack("更新成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int count = BirthdayDaoFactory.getEmptyBirthdayDaoInstance(InfoActivity.this).deleteBirthday(_id);
                    if (count > 0) {
                        goBack("删除成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goBack(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void init() {
        editText = (EditText) findViewById(R.id.info_editText);
        textView = (TextView) findViewById(R.id.info_textView);
        button1 = (Button) findViewById(R.id.info_button1);
        button2 = (Button) findViewById(R.id.info_button2);
        button3 = (Button) findViewById(R.id.info_button3);
        editText.setText(birthday.getName());
        textView.setText(DateUtil.formatDate(birthday.getYear(), birthday.getMonth(), birthday.getDay()));

    }
}
