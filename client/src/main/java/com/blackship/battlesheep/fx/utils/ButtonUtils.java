package com.blackship.battlesheep.fx.utils;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Mateusz Słaboński
 * @since 31.07.2017
 */
public class ButtonUtils {

    private final static int MIN_BUTTON_HEIGHT = 30;
    private final static int MIN_BUTTON_WIDTH = 46;

    private ButtonUtils() {}

    public static String pinkButtonColorStyle() {
        return "-fx-background-color: pink;";
    }

    public static String defaultButtonColorStyle() {
        return "";
    }

    public static Button createStyledButton(int value) {
        Button button = new Button();
        button.setId(String.valueOf(value));
        button.setText(String.valueOf(value));
        button.setMinWidth(MIN_BUTTON_WIDTH);
        button.setMinHeight(MIN_BUTTON_HEIGHT);
        return button;
    }

    public static List<Button> generateButtons(int boardSize) {
        List<Button> buttons = new ArrayList<>();
        IntStream
                .rangeClosed(1, boardSize * boardSize)
                .forEach(data -> buttons.add(ButtonUtils.createStyledButton(data)));
        return buttons;
    }
}
