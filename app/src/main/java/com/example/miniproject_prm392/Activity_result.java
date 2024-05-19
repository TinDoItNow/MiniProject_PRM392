package com.example.miniproject_prm392;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvResultMessage = findViewById(R.id.tvResultMessage);
        TextView tvWinningAmount = findViewById(R.id.tvWinningAmount);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        boolean isWinner = intent.getBooleanExtra("isWinner", false);
        int winningAmount = intent.getIntExtra("winningAmount", 0);

        // Thiết lập kết quả và số tiền trúng thưởng
        if (isWinner) {
            tvResultMessage.setText("You Win!");
            tvResultMessage.setTextColor(getResources().getColor(android.R.color.holo_green_light));
        } else {
            tvResultMessage.setText("You Lose!");
            tvResultMessage.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        }
        tvWinningAmount.setText(String.valueOf(winningAmount));
        }
    public void goBack(View view) {
        finish();
    }
}