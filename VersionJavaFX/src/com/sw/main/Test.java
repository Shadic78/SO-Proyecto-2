/*
 * The MIT License
 *
 * Copyright 2020 SonBear.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sw.main;

import com.sw.controller.StageFactory;
import static com.sw.controller.StageFactory.createStage;
import com.sw.model.Proceso;
import java.io.IOException;
import javafx.application.Application;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author SonBear
 */
public class Test extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        ObservableList<Proceso> colaProcesos = observableArrayList();

        Proceso p1 = new Proceso("P1", 3, 2, 9);
        Proceso p2 = new Proceso("P2", 18, 2, 12);
        Proceso p3 = new Proceso("P3", 20, 4, 15);
        Proceso p4 = new Proceso("P4", 18, 5, 8);
        Proceso p5 = new Proceso("P5", 15, 7, 12);
        Proceso p6 = new Proceso("P6", 13, 8, 3);
        Proceso p7 = new Proceso("P7", 15, 9, 8);
        Proceso p8 = new Proceso("P8", 19, 9, 9);
        Proceso p9 = new Proceso("P9", 10, 12, 4);

        colaProcesos.add(p1);
        colaProcesos.add(p2);
        colaProcesos.add(p3);
        colaProcesos.add(p4);
        colaProcesos.add(p5);
        colaProcesos.add(p6);
        colaProcesos.add(p7);
        colaProcesos.add(p8);
        colaProcesos.add(p9);

        createStage("/com/sw/view/Vista.fxml", "MVT", StageFactory.RUTA_ESTILOS, Modality.NONE, colaProcesos, null);
    }

}
