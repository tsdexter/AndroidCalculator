package com.frontright.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

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
        // get current input
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
                // if the last entry was = user must enter a new operator before more numbers
                String last = !equation.isEmpty() ? equation.substring(equation.length() - 3) : "";
                if (last.contains("=")) {
                    Toast.makeText(this, "Must enter an operator first", Toast.LENGTH_LONG).show();
                    break;
                }
                // append this key to the existing input
                inputTextView.setText(String.format("%s%s", input, key));
                break;
            }
            case "+":
            case "-":
            case "÷":
            case "×": {
                // if there is an equation or input, add the operator to it
                if (!equation.isEmpty() || !input.isEmpty())
                    addToEquation(key);
                break;
            }
            case "=": {
                // if there is an equation or input, add the equals and stick the
                // calculated result into the input field
                if (!equation.isEmpty() || !input.isEmpty()) {
                    addToEquation(key);
                    inputTextView.setText(calculate());
                }
            }

        }
    }

    /**
     * Add the input to the full equation
     * @param key
     */
    public void addToEquation(String key) {
        // get the current input
        String input = inputTextView.getText().toString();

        // format the operator that was pressed as space operator space
        String operation = String.format(" %s ", key);

        // don't add multiple operators
        // remove it and add the new one
        if (equation.length() > 3 // must be greater than 3 to have an operator
                && equation.substring(equation.length() - 1).equals(" ") // if the last char is a space it's an operator
                && input.equals("") // if input is also empty, last keypress was operator
        ) {
            // remove the last 3 chars to switch the operator
            equation = equation.substring(0, equation.length() - 3);
        }

        // update the equation - append input and operation to existing equation
        equation = String.format("%s%s%s", equation, input, operation);
        equationTextView.setText(equation);

        // reset the input
        inputTextView.setText("");
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
        BigDecimal total = BigDecimal.valueOf(0);
        String operator = "+";
        for (int i = 0; i < inputs.length; i++) {
            String value = inputs[i];
            if (value.matches("(\\+|\\-|\\×|\\÷)")) {
                operator = value;
            } else if (!value.equals("=")) {
                switch (operator) {
                    case "+":
                        total = total.add(BigDecimal.valueOf(Double.parseDouble(value)));
                        break;
                    case "-":
                        total = total.subtract(BigDecimal.valueOf(Double.parseDouble(value)));
                        break;
                    case "×":
                        total = total.multiply(BigDecimal.valueOf(Double.parseDouble(value)));
                        break;
                    case "÷":
                        total = total.divide(BigDecimal.valueOf(Double.parseDouble(value)));
                        break;
                }
            }
        }

        // return nicely formatted string (ie: no .0 if unnecessary)
        // source: https://stackoverflow.com/questions/703396/how-to-nicely-format-floating-numbers-to-string-without-unnecessary-decimal-0
        double totalDbl = total.doubleValue();
        if(totalDbl == (long) totalDbl)
            return String.format("%d", (long) totalDbl);
        else
            return String.format("%s", totalDbl);
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
