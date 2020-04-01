package com.sw.controller;

import javafx.stage.Stage;

/**
 *
 * @author SonBear
 */
public interface Controller<E>
{

    public void setDefaultData(E data);

    public void initStage(Stage s);
}
