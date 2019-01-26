package com.frontright.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView inputTextView;
    TextView equationTextView;
    String equation = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputTextView = findViewById(R.id.inputTextView);
        equationTextView = findViewById(R.id.equationTextField);
    }

    /**
     * Handle keypresses
     * @param view
     */
    public void press(View view) {
        String input = inputTextView.getText().toString();

        // cast to button to get the text so we know which button was pressed
        Button btn = (Button) view;
        String key = btn.getText().toString();

        // perform actions based on which button
        switch (key) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".": {
                inputTextView.setText(String.format("%s%s", input, key));
                break;
            }
            case "+":
            case "-":
            case "÷":
            case "×": {
                addToEquation(key);
                break;
            }
            case "=": {
                addToEquation(key);
                inputTextView.setText(calculate());
            }

        }
    }

    /**
     * Calculate the results from the equation
     * @return
     */
    public String calculate() {
        // split equation history by each = sign
        String[] equations = equation.split(" = ");

        // get the current equation (the last in the array)
        String currentEquation = equations[equations.length - 1];

        // apply operations to only the current equation
        String[] inputs = currentEquation.split(" ");

        // perform equation
        double total = 0;
        String operator = "+";
        for (int i = 0; i < inputs.length; i++) {
            String value = inputs[i];
            if (value.matches("(\\+|\\-|\\×|\\÷)")) {
                operator = value;
            } else if (!value.equals("=")) {
                switch (operator) {
                    case "+":
                        total += Double.parseDouble(value);
                        break;
                    case "-":
                        total -= Double.parseDouble(value);
                        break;
                    case "×":
                        total *= Double.parseDouble(value);
                        break;
                    case "÷":
                        total /= Double.parseDouble(value);
                        break;
                }
            }
        }

        // return nicely formatted string (ie: no .0 if unnecessary)
        // source: https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0
        if(total == (long) total)
            return String.format("%d", (long) total);
        else
            return String.format("%s", total);
    }

    /**
     * Add the input to the full equation
     * @param key
     */
    public void addToEquation(String key) {
        String input = inputTextView.getText().toString();
        String operation = String.format(" %s ", key);

        // don't add multiple operators
        // remove it and add the new one
        if (equation.length() > 3 // must be greater than 3 to have an operator
                && equation.substring(equation.length() - 1).equals(" ") // if the last char is a space it's an operator
                && input.equals("")
        ) {
            // remove the last 3 chars to switch the operator
            equation = equation.substring(0, equation.length() - 3);
        }

        // update the equation
        equation = String.format("%s%s%s", equation, input, operation);
        equationTextView.setText(equation);

        // reset the input
        inputTextView.setText("");
    }

    /**
     * clear the fields
     * @param view
     */
    public void clear(View view) {
        equationTextView.setText("");
        inputTextView.setText("");
        equation = "";
    }
}
