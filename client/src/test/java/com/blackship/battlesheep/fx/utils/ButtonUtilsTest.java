package com.blackship.battlesheep.fx.utils;

import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Button;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

import static org.testng.Assert.assertEquals;

/**
 * @author Mateusz Słaboński
 * @since 02.08.2017
 */
public class ButtonUtilsTest {

    @BeforeMethod
    public void setupJavaFx() {
        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // initializes JavaFX environment
            latch.countDown();
        });
    }


    @DataProvider
    private static Object[][] buttonCreationData() {
            return new Object[][] {
                    {5, 30, 46},
                    {10, 30, 46},
                    {15, 30, 46},
                    {561, 30, 46},
                    {32, 30, 46},
                    {112, 30, 46},
                    {32, 30, 46},
                    {43, 30, 46}
            };
    }

    @Test(dataProvider = "buttonCreationData")
    public void givenIntDataExpectNotNullButtonTest(int expectedValue, int expectedHeight, int expectedWidth) {
        //when
        Button button = ButtonUtils.createStyledButton(expectedValue);
        //then
        SoftAssert softAssert = new SoftAssert();
//        assertEquals(button.getId(), String.valueOf(expectedValue));
        softAssert.assertEquals(button.getId(), String.valueOf(expectedValue));
        softAssert.assertEquals(button.getText(), String.valueOf(expectedValue));
        softAssert.assertEquals(button.getHeight(), expectedHeight);
        softAssert.assertEquals(button.getWidth(), expectedWidth);
    }


}