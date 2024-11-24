package com.spa.demo.frontend.Cassa.Utils;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class JavaFxElementModifier {

    //Paraméterül egy Stringet vár ami a színt határozza meg, illetve egy vagy több TextFieldet aminek a hátterét akarjuk
    //modosítani
    public static void changeTextFieldBackgroundColor(String color, TextField... textFields)
    {
        for (TextField textField : textFields)
        {
            textField.setStyle("-fx-background-color:" +color + ";");
        }
    }

    //Paraméterül egy Stringet vár ami a színt határozza meg, illetve egy vagy több RadioButton-t aminek a hátterét akarjuk
    //modosítani
    public static void changeRadioButtonBackgroundColor(String color, RadioButton... radioButtons)
    {
        for (RadioButton radioButton : radioButtons){
            radioButton.setStyle("-fx-background-color:" +color + ";");
        }
    }

}
