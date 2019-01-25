package com.frontright.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
     * @param key
     */
    public void press(String key) {
        String input = inputTextView.getText().toString();

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
            case "/":
            case "*": {
                addToEquation(key);
                break;
            }
            case "=": {
                addToEquation(key);
                //inputTextView.setText(calculate());
            }

        }
    }

    /**
     * Calculate the results from the equation
     * @return
     */
    public String calculate() {
        String[] inputs = equation.split(" ");

        for (int i = 0; i < inputs.length; i++) {

        }

        return "0";
    }

    /**
     * Add the input to the full equation
     * @param key
     */
    public void addToEquation(String key) {
        String input = inputTextView.getText().toString();
        String operation = String.format(" %s ", key);

        // don't add multiple operators - if 2nd last char is an operator
        // remove it and add the new one
        // if length is not > 3 then no operator exists
        String last = equation.length() > 3 ? equation.substring(equation.length() - 3) : "";

        // match on " + ", " - ", " \ ", " = ", or " * "
        if (!last.isEmpty() && last.matches("\\ (\\+|\\-|\\*|\\/|\\=)\\ ") && !input.matches("(1|2|3|4|5|6|7|8|9|0|\\.)")) {
            // remove the last 3 chars as it's the current operation
            equation = equation.substring(0, equation.length() - 3);
        }

        // update the equation
        equation = String.format("%s%s%s", equation, input, operation);
        equationTextView.setText(equation);

        // reset the input
        inputTextView.setText("");
    }

    public void press1(View view) {
        press("1");
    }

    public void press2(View view) {
        press("2");
    }

    public void press3(View view) {
        press("3");
    }

    public void press4(View view) {
        press("4");
    }

    public void press5(View view) {
        press("5");
    }

    public void press6(View view) {
        press("6");
    }

    public void press7(View view) {
        press("7");
    }

    public void press8(View view) {
        press("8");
    }

    public void press9(View view) {
        press("9");
    }

    public void press0(View view) {
        press("0");
    }

    public void pressDecimal(View view) {
        press(".");
    }

    public void pressEquals(View view) {
        press("=");
    }

    public void pressPlus(View view) {
        press("+");
    }

    public void pressMinus(View view) {
        press("-");
    }

    public void pressMultiply(View view) {
        press("*");
    }

    public void pressDivide(View view) {
        press("/");
    }
}
