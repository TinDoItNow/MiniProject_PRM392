package com.example.miniproject_prm392;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_result extends AppCompatActivity {
    MediaPlayer mediaPlayer;
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

        //
        if(mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(Activity_result.this, R.raw.result_sound);
        }
        mediaPlayer.start();

        setResult(RESULT_OK);
        TextView tvResultMessage = findViewById(R.id.tvResultMessage);
        TextView tvWinningAmount = findViewById(R.id.tvWinningAmount);
        TextView tvWinningAmountLabel = findViewById(R.id.tvWinningAmountLabel);

        // Get data from Intent
        Intent intent = getIntent();
        boolean isWinner = intent.getBooleanExtra("isWinner", false);
        double totalGetAmount = intent.getDoubleExtra("totalGetAmount", 0);

        // Set result and winning amount
        if (isWinner) {
            tvResultMessage.setText("You Win!");

            tvResultMessage.setTextColor(getResources().getColor(android.R.color.holo_green_light));
            tvWinningAmountLabel.setText("Winning Amount:");
        } else {
            tvResultMessage.setText("You Lose!");
            tvResultMessage.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            tvWinningAmountLabel.setText("Losing Amount:");
        }
//        tvWinningAmount.setText(String.format("%.2f", winningAmount));
        tvWinningAmount.setText(String.format("%.2f", totalGetAmount));
    }

    public void goBack(View view) {
        Intent turnOnSound = new Intent(Activity_result.this, Bg_sound.class);
        startService(turnOnSound);
        finish();
    }
}
