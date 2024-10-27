package com.example.calculator;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private TextView textViewTop;
    private TextView textViewBottom;
    private String currentExpression = "";
    private boolean isResultDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // مقداردهی اولیه ویوها
        textViewTop = findViewById(R.id.textViewTop);
        textViewBottom = findViewById(R.id.textViewBottom);

        // تعریف دکمه‌ها
        Button btnEqual = findViewById(R.id.equal_btn);
        Button btnClear = findViewById(R.id.Ac_btn);
        Button btnDelete = findViewById(R.id.back_btn);
        Button btnAdd = findViewById(R.id.plus_btn);
        Button btnSubtract = findViewById(R.id.sub_btn);
        Button btnMultiply = findViewById(R.id.star_btn);
        Button btnDivide = findViewById(R.id.slash_btn);
        Button btnDot = findViewById(R.id.dot_btn);
        Button btnPi = findViewById(R.id.pi_btn);
        Button btnE = findViewById(R.id.e_btn);
        Button btnSin = findViewById(R.id.sin_btn);
        Button btnDeg = findViewById(R.id.deg_btn);

        // تعریف دکمه‌های اعداد 0 تا 9
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            String buttonID = "btn" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            numberButtons[i] = findViewById(resID);

            // اطمینان از اینکه دکمه‌ها مقداردهی شده‌اند
            if (numberButtons[i] != null) {
                int finalI = i;
                numberButtons[i].setOnClickListener(v -> appendToExpression(String.valueOf(finalI)));
            }

        }


        // تنظیم رویداد کلیک برای دکمه‌های عملیات
        if (btnAdd != null) btnAdd.setOnClickListener(v -> appendToExpression("+"));
        if (btnSubtract != null) btnSubtract.setOnClickListener(v -> appendToExpression("-"));
        if (btnMultiply != null) btnMultiply.setOnClickListener(v -> appendToExpression("*"));
        if (btnDivide != null) btnDivide.setOnClickListener(v -> appendToExpression("/"));
        if (btnDot != null) btnDot.setOnClickListener(v -> appendToExpression("."));
        if (btnPi != null) btnPi.setOnClickListener(v -> appendToExpression("π"));
        if (btnE != null) btnE.setOnClickListener(v -> appendToExpression("e"));
        if (btnSin != null) btnSin.setOnClickListener(v -> appendToExpression("sin("));
        if (btnDeg != null) btnDeg.setOnClickListener(v -> appendToExpression("deg("));


        // دکمه مساوی برای محاسبه نتیجه
        if ( btnEqual != null) {
            btnEqual.setOnClickListener(v -> calculateResult());
        }

        // دکمه پاک کردن همه چیز
        if (btnClear != null) {
            btnClear.setOnClickListener(v -> clearExpression());
        }

        // دکمه حذف آخرین کاراکتر
        if (btnDelete != null) {
            btnDelete.setOnClickListener(v -> deleteLastCharacter());
        }
    }

    private void appendToExpression(String value) {
        if (isResultDisplayed) {
            currentExpression = "";
            isResultDisplayed = false;
        }
        currentExpression += value;
        textViewBottom.setText(currentExpression);
    }

    private void calculateResult() {
        try {
            // جایگزینی π و e با مقادیرشان
            currentExpression = currentExpression.replace("π", String.valueOf(Math.PI));
            currentExpression = currentExpression.replace("e", String.valueOf(Math.E));
            Expression expression = new ExpressionBuilder(currentExpression).build();
            double result = expression.evaluate();

            textViewTop.setText(currentExpression);
            textViewBottom.setText(String.valueOf(result));
            isResultDisplayed = true;
        } catch (Exception e) {
            textViewBottom.setText("خطا");
        }
    }

    private void clearExpression() {
        currentExpression = "";
        textViewTop.setText("");
        textViewBottom.setText("");
    }

    private void deleteLastCharacter() {
        if (!currentExpression.isEmpty()) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            textViewBottom.setText(currentExpression);
        }
    }
}


//Button btnEqual = findViewById(R.id.equal_btn);
//Button btnClear = findViewById(R.id.Ac_btn);
//Button btnDelete = findViewById(R.id.back_btn);
//Button btnAdd = findViewById(R.id.plus_btn);
//Button btnSubtract = findViewById(R.id.sub_btn);
//Button btnMultiply = findViewById(R.id.star_btn);
//Button btnDivide = findViewById(R.id.slash_btn);
//Button btnDot = findViewById(R.id.dot_btn);
//Button btnPi = findViewById(R.id.pi_btn);
//Button btnE = findViewById(R.id.e_btn);
//Button btnSin = findViewById(R.id.sin_btn);
//Button btnDeg = findViewById(R.id.deg_btn);












