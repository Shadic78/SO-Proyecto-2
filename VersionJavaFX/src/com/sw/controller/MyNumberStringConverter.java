package com.sw.controller;

import java.text.ParseException;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Nicolás
 */
public class MyNumberStringConverter extends NumberStringConverter
{

    @Override
    public Number fromString(String value)
    {
        if (value.equals(""))
            return null;

        try
        {
            int number = Integer.parseInt(value);

            if (number <= 0)
                throw new ParseException(value, 0);

            return number;

        } catch (NumberFormatException | ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(Number value)
    {
        return value == null ? "" : String.valueOf(Integer.parseInt(String.valueOf(value.intValue())));
    }

}
