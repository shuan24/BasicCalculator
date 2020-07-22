package com.example.basiccalculatorforandroid;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView outputTextView;
    Button AC, power, back, divide, seven, eight, nine, multiply, four, five, six, minus, one, two, three, add, zero, dot, ANS, equal;
    private String inputValue, outputValue;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        centerTitle();

        outputTextView = findViewById(R.id.outputTextView);
        AC = findViewById(R.id.buttonAC);
        power = findViewById(R.id.buttonPower);
        back = findViewById(R.id.buttonBack);
        divide = findViewById(R.id.buttonDivide);
        seven = findViewById(R.id.button7);
        eight = findViewById(R.id.button8);
        nine = findViewById(R.id.button9);
        multiply = findViewById(R.id.buttonMultiply);
        four = findViewById(R.id.button4);
        five = findViewById(R.id.button5);
        six = findViewById(R.id.button6);
        minus = findViewById(R.id.buttonSubtract);
        one = findViewById(R.id.button1);
        two = findViewById(R.id.button2);
        three = findViewById(R.id.button3);
        add = findViewById(R.id.buttonAdd);
        zero = findViewById(R.id.button0);
        dot = findViewById(R.id.buttonDot);
        ANS = findViewById(R.id.buttonANS);
        equal = findViewById(R.id.buttonEqual);
    }

    public void buttonClick(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data) {
            case "AC":
                inputValue = "";
                break;
            case "ANS":
                inputValue += outputValue;
                break;
            case "X":
                Solve();
                inputValue += "*";
                break;
            case "ˆ":
                Solve();
                inputValue += "ˆ";
                break;
            case "=":
                Solve();
                outputValue = inputValue;
                break;
            case "⌫":
                if(inputValue.length()>0) {
                    String newText = inputValue.substring(0, inputValue.length() - 1);
                    inputValue = newText;
                }
                break;
            default:
                if (inputValue == null) {
                    inputValue = "";
                }
                if (data.equals("+") || data.equals("-") || data.equals("÷")) {
                    Solve();
                }
                inputValue += data;
        }
        outputTextView.setText(inputValue);
    }

    public void Solve() {
        if (inputValue.split("\\*").length == 2) {
            String[] numbers = inputValue.split("\\*");
            try {
                double multiplication = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                inputValue = multiplication + "";
            } catch (Exception e) {
                //Display error
            }
        } else if (inputValue.split("÷").length == 2) {
            String[] numbers = inputValue.split("÷");
            try {
                double division = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                inputValue = division + "";
            } catch (Exception e) {
                //Display error
            }
        } else if (inputValue.split("\\ˆ").length == 2) {
            String[] numbers = inputValue.split("\\ˆ");
            try {
                double power = Math.pow(Double.parseDouble(numbers[0]),Double.parseDouble(numbers[1]));
                inputValue = power + "";
            } catch (Exception e) {
                //Display error
            }
        } else if (inputValue.split("\\+").length == 2) {
            String[] numbers = inputValue.split("\\+");
            try {
                double addition = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                inputValue = addition + "";
            } catch (Exception e) {
                //Display error
            }
        } else if (inputValue.split("\\-").length > 1) {
            String[] numbers = inputValue.split("\\-");
            if (numbers[0] == "" && numbers.length == 2) {
                numbers[0] = 0 + "";
            }
            try {
                double subtract = 0;
                if (numbers.length == 2) {
                    subtract = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                } else if (numbers.length == 3) {
                    subtract = -Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[2]);
                }
                inputValue = subtract + "";
            } catch (Exception e) {
                //Display error
            }
        }
        String[] n = inputValue.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                inputValue = n[0];
            }
        }
        outputTextView.setText(inputValue);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void centerTitle() {
        ArrayList<View> textViews = new ArrayList<>();

        getWindow().getDecorView().findViewsWithText(textViews, getTitle(), View.FIND_VIEWS_WITH_TEXT);

        if(textViews.size() > 0) {
            AppCompatTextView appCompatTextView = null;
            if(textViews.size() == 1) {
                appCompatTextView = (AppCompatTextView) textViews.get(0);
            } else {
                for(View v : textViews) {
                    if(v.getParent() instanceof Toolbar) {
                        appCompatTextView = (AppCompatTextView) v;
                        break;
                    }
                }
            }

            if(appCompatTextView != null) {
                ViewGroup.LayoutParams params = appCompatTextView.getLayoutParams();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                appCompatTextView.setLayoutParams(params);
                appCompatTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }
}