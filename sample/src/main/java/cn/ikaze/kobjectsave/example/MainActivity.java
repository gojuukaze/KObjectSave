package cn.ikaze.kobjectsave.example;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ikaze.kobjectsave.KObjectSave;

public class MainActivity extends AppCompatActivity {

    Date tempBirthday;
    KObjectSave kSave;
    Student st;

    @BindView(R.id.no)
    EditText no;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.birthday)
    EditText birthday;
    @BindView(R.id.math)
    CheckBox math;
    @BindView(R.id.physics)
    CheckBox physics;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.load)
    Button load;
    @BindView(R.id.result)
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        result.setMovementMethod(ScrollingMovementMethod.getInstance());

        birthday.setInputType(InputType.TYPE_NULL);

        birthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showDatePickerDialog();
                }
            }
        });

        kSave = new KObjectSave(this, Student.class);
        st = (Student) kSave.getObj();

        result.append("\nFirst open :\n");
        result.append(st.getSpannableString());

    }


    @OnClick({R.id.save, R.id.load, R.id.birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save:
                save();
                break;
            case R.id.load:
                load();
                break;
            case R.id.birthday:
                showDatePickerDialog();
                break;
        }
    }

    private void save() {

        st.no = Integer.valueOf(no.getText().toString());
        st.name = name.getText().toString();
        st.birthday = tempBirthday;
        List<String> tempSubjects = new ArrayList<>();
        if (math.isChecked())
            tempSubjects.add("math");
        if (physics.isChecked())
            tempSubjects.add("physics");
        st.subjects = tempSubjects;

        try {
            kSave.save(st);
        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
            result.append("\nsave fail !!!!");

        }

        result.append("\nsave done !");

    }

    private void load() {

        Student tempSt=(Student) new KObjectSave(this,Student.class).getObj();
        result.append("\nload from file:\n");
        result.append(tempSt.getSpannableString());



    }

    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String strDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                birthday.setText(strDate);
                Format format = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    tempBirthday = (Date) format.parseObject(strDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();

    }
}
