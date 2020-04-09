package com.sw.controller;

import com.sun.javafx.tk.Toolkit;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import static java.lang.String.valueOf;
import javafx.scene.text.Font;

/**
 *
 * @author SonBear
 */
public class Utilidades
{

    public static double getFontWidth(String text, Font font)
    {
        return getToolkit().getFontLoader().computeStringWidth(valueOf(text), font);
    }

    public static double getFontHeight(Font font)
    {
        return getToolkit().getFontLoader().getFontMetrics(font).getLineHeight();
    }

}
