package com.example.javafx_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {

    private long number1;
    private String operator = "";
    private boolean start = true;

    @FXML
    private Label output;

    @FXML
    private void processNumPad(ActionEvent event) {
        if (start) {
            output.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void processOperator(ActionEvent event) {
        if (output.getText().equals("ERROR")) {
            return;
        }
        String value = ((Button) event.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty())
                return;
            operator = value;
            number1 = Long.parseLong(output.getText());
            output.setText("");
        } else {
            if (operator.isEmpty()) {
                return;
            }
                if (output.getText().isEmpty()) {
                    output.setText("ERROR");
                operator = "";
                start = true;
                return;
            }

            output.setText(String.valueOf(calculate(number1, Long.parseLong(output.getText()), operator)));
            operator = "";
            start = true;

        }
    }

    @FXML
    private void clearOutput(ActionEvent event) {
        output.setText("0");
        start = true;
        operator = "";
    }

    private String calculate(long number1, long number2, String operator) {
        switch(operator) {
            case "+":
                return String.valueOf(number1 + number2);
            case "-":
                return String.valueOf(number1 - number2);
            case "×":
                return String.valueOf(number1 * number2);
            case "÷":
                if (number2 == 0) {
                    return "Error";
                } else {
                    return String.valueOf(number1 / number2);
                }
            default:
                return "Error";
        }
    }
}
