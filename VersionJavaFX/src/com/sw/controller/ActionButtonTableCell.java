package com.sw.controller;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author HikingCarrot7
 */
public class ActionButtonTableCell<S> extends TableCell<S, Button>
{

    private final Button actionButton;

    public ActionButtonTableCell(String label, Consumer<S> consumer)
    {
        actionButton = new Button(label);
        actionButton.setStyle("-fx-background-color: tomato;");
        initButtonEvents(consumer);
        actionButton.setMaxWidth(Double.MAX_VALUE);
    }

    private void initButtonEvents(Consumer<S> consumer)
    {
        actionButton.setOnMouseEntered(e ->
        {
            actionButton.setStyle("-fx-background-color: red;");
        });

        actionButton.setOnMouseExited(e ->
        {
            actionButton.setStyle("-fx-background-color: tomato;");
        });

        actionButton.setOnAction(e ->
        {
            consumer.accept(getCurrentItem());
        });
    }

    public S getCurrentItem()
    {
        return getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String texto, Consumer<S> function)
    {
        return param -> new ActionButtonTableCell<>(texto, function);
    }

    @Override
    public void updateItem(Button item, boolean empty)
    {
        super.updateItem(item, empty);
        setGraphic(empty ? null : actionButton);
    }

}
