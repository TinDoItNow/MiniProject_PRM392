package com.example.miniproject_prm392;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_game extends AppCompatActivity {
//    private TextView tvYourMoney;
//    private RadioButton rbRace1, rbRace2, rbRace3;
//    private EditText etRace1Money, etRace2Money, etRace3Money;
//    private Button btnStart, btnReset;
//    private ImageView ivRace1, ivRace2, ivRace3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//
//        tvYourMoney = findViewById(R.id.tvYourMoney);
//        rbRace1 = findViewById(R.id.rbRace1);
//        rbRace2 = findViewById(R.id.rbRace2);
//        rbRace3 = findViewById(R.id.rbRace3);
//        etRace1Money = findViewById(R.id.etRace1Money);
//        etRace2Money = findViewById(R.id.etRace2Money);
//        etRace3Money = findViewById(R.id.etRace3Money);
//        btnStart = findViewById(R.id.btnStart);
//        btnReset = findViewById(R.id.btnReset);
//        ivRace1 = findViewById(R.id.ivRace1);
//        ivRace2 = findViewById(R.id.ivRace2);
//        ivRace3 = findViewById(R.id.ivRace3);

//        // Load animations
//        Animation race1Anim = AnimationUtils.loadAnimation(this, R.anim.race_animation);
//        Animation race2Anim = AnimationUtils.loadAnimation(this, R.anim.race_animation);
//        Animation race3Anim = AnimationUtils.loadAnimation(this, R.anim.race_animation);
//
//        // Apply animations when RadioButton is checked
//        rbRace1.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                ivRace1.startAnimation(race1Anim);
//                ivRace2.clearAnimation();
//                ivRace3.clearAnimation();
//            }
//        });
//
//        rbRace2.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                ivRace2.startAnimation(race2Anim);
//                ivRace1.clearAnimation();
//                ivRace3.clearAnimation();
//            }
//        });
//
//        rbRace3.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                ivRace3.startAnimation(race3Anim);
//                ivRace1.clearAnimation();
//                ivRace2.clearAnimation();
//            }
//        });
//
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startGame();
//            }
//        });
//
//        btnReset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetGame();
//            }
//        });
//    }
//
//    private void startGame() {
//        String selectedRace = "";
//        int betAmount = 0;
//
//        if (rbRace1.isChecked()) {
//            selectedRace = "First racetrack";
//            betAmount = Integer.parseInt(etRace1Money.getText().toString());
//        } else if (rbRace2.isChecked()) {
//            selectedRace = "Second racetrack";
//            betAmount = Integer.parseInt(etRace2Money.getText().toString());
//        } else if (rbRace3.isChecked()) {
//            selectedRace = "Third racetrack";
//            betAmount = Integer.parseInt(etRace3Money.getText().toString());
//        }
//
//        if (!selectedRace.isEmpty() && betAmount > 0) {
//            boolean isWinner = /* logic để xác định kết quả */;
//            int winningAmount = /* logic để tính toán số tiền trúng thưởng nếu thắng */;
//
//            Intent intent = new Intent(Activity_game.this, Activity_result.class);
//            intent.putExtra("isWinner", isWinner);
//            intent.putExtra("winningAmount", winningAmount);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Please select a track and enter a valid price!!!", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void resetGame() {
//        rbRace1.setChecked(false);
//        rbRace2.setChecked(false);
//        rbRace3.setChecked(false);
//        etRace1Money.setText("");
//        etRace2Money.setText("");
//        etRace3Money.setText("");
//        tvYourMoney.setText("0");
//
//        ivRace1.clearAnimation();
//        ivRace2.clearAnimation();
//        ivRace3.clearAnimation();
//    }
    }
}
