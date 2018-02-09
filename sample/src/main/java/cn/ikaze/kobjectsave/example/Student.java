package cn.ikaze.kobjectsave.example;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by gojuukaze on 2018/2/9.
 * Email: i@ikaze.uu.me
 */

public class Student {
    int no = 1; //default 1
    String name = "gojuukaze";//default gojuukaze
    Date birthday = new Date(); //default now
    List<String> subjects;

    public String toString() {
        Format format = new SimpleDateFormat("yyyy-MM-dd");
        String birthdayString = format.format(birthday);
        return "No." + no + ", name: " + name + ", birthday: " + birthdayString + ", subjects: " + subjects;
    }

    public SpannableString getSpannableString() {
        SpannableString s = new SpannableString(this.toString());
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        s.setSpan(colorSpan, 0, s.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return s;
    }


}
