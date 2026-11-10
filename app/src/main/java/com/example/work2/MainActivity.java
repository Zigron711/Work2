package com.example.work2;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Num1, Num2;
    TextView Operation, Result;
    Button btnPlus, btnMinus, btnMultiply, btnDivide, btnClear, btnEquals, btnDone;
    String currentOperation = "+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Num1 = findViewById(R.id.Num1);
        Num2 = findViewById(R.id.Num2);
        Operation = findViewById(R.id.Operation);
        Result = findViewById(R.id.Result);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnClear = findViewById(R.id.btnClear);
        btnEquals = findViewById(R.id.btnEquals);
        btnDone = findViewById(R.id.btnDone);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnEquals.setOnClickListener(this);
        btnDone.setOnClickListener(this);

        int[] numberButtons = {
                R.id.btn1,
                R.id.btn2,
                R.id.btn3,
                R.id.btn4,
                R.id.btn5,
                R.id.btn6,
                R.id.btn7,
                R.id.btn8,
                R.id.btn9,
                R.id.btn0,
                R.id.btnComma,
                R.id.btnDot,
                R.id.btnBackspace,
                R.id.btn10
        };
        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnPlus) {
            currentOperation = "+";
            Operation.setText("+");
            return;
        } else if (id == R.id.btnMinus) {
            currentOperation = "-";
            Operation.setText("-");
            return;
        } else if (id == R.id.btnMultiply) {
            currentOperation = "*";
            Operation.setText("*");
            return;
        } else if (id == R.id.btnDivide) {
            currentOperation = "/";
            Operation.setText("/");
            return;
        }
        if (id == R.id.btnClear) {
            Num1.setText("");
            Num2.setText("");
            Result.setText("= 0.0");
            Operation.setText("+");
            currentOperation = "+";
            return;
        }
        if (id == R.id.btnEquals || id == R.id.btnDone) {
            calculateResult();
            return;
        }
        EditText targetEditText = Num1;
        if (Num2.hasFocus()) {
            targetEditText = Num2;
        }
        Button b = (Button) v;
        String buttonText = b.getText().toString();
        if (id == R.id.btnBackspace) {
            String currentText = targetEditText.getText().toString();
            if (currentText.length() > 0) {
                targetEditText.setText(currentText.substring(0, currentText.length() - 1));
            }
            return;
        }
        if (buttonText.equals(",")) {
            buttonText = ".";
        }
        if (id == R.id.btn10) {
            return;
        }
        targetEditText.append(buttonText);
    }

    private void calculateResult() {
        String str1 = Num1.getText().toString();
        String str2 = Num2.getText().toString();

        if (str1.isEmpty() || str2.isEmpty()) {
            Result.setText("= Ошибка");
            return;
        }
        try {
            double num1 = Double.parseDouble(str1);
            double num2 = Double.parseDouble(str2);
            double result = 0;
            switch (currentOperation) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 == 0) {
                        Result.setText("= на ноль делить нельзя!");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            if (result == (long) result) {
                Result.setText("= " + (long) result);
            } else {
                Result.setText("= " + result);
            }
        } catch (NumberFormatException e) {
            Result.setText("= Ошибка ввода");
        }
    }
}