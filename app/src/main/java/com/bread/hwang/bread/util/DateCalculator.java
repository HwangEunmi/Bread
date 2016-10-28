package com.bread.hwang.bread.util;

import org.w3c.dom.ProcessingInstruction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Hwang on 2016-10-28.
 */

public class DateCalculator {
    private static final String dateFormat = "yyyy.MM.dd";
    private SimpleDateFormat form;

    public DateCalculator() {
        form = new SimpleDateFormat(dateFormat);
    }

    public String CalToStr(Calendar cal) {
        return form.format(cal.getTime());
    }

    public Calendar StrToCal(String str) {
        Calendar result = Calendar.getInstance();

        try {
            result.setTime(form.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

}
