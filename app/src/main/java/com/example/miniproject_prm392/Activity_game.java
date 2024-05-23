package com.example.miniproject_prm392;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

import android.app.AlertDialog;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.VideoView;

public class Activity_game extends AppCompatActivity {

    private CheckBox cbRace1, cbRace2, cbRace3;
    private SeekBar sbRace1, sbRace2, sbRace3;
    private EditText etRace1Money, etRace2Money, etRace3Money;
    private Button btnStart, btnReset, btnRule;
    private TextView tvYourMoney;
    private double totalMoney = 10000;
    private boolean raceOver = false;


    // Some initial for racing
    private final int racingStep = 2;
    private final double ratio = 1.9;

    private int firstFinished = -1;

    private boolean isWinner = false;


    private boolean isTie = false;
    MediaPlayer player;

    private VideoView bgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        // Khởi động service âm thanh và liên kết với activity
        Intent turnOnSound = new Intent(Activity_game.this, Bg_sound.class);
        startService(turnOnSound);
        // Initialize views
        cbRace1 = findViewById(R.id.cbRace1);
        cbRace2 = findViewById(R.id.cbRace2);
        cbRace3 = findViewById(R.id.cbRace3);
        sbRace1 = findViewById(R.id.sbRace1);
        sbRace2 = findViewById(R.id.sbRace2);
        sbRace3 = findViewById(R.id.sbRace3);
        etRace1Money = findViewById(R.id.etRace1Money);
        etRace2Money = findViewById(R.id.etRace2Money);
        etRace3Money = findViewById(R.id.etRace3Money);
        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        tvYourMoney = findViewById(R.id.tvYourMoney);

        btnRule = findViewById(R.id.btnRule);

        // Set money 10000,00 by default
        tvYourMoney.setText(String.format("%.2f", totalMoney));

        // Set up checkboxess
        cbRace1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRace2.setChecked(false);
                cbRace3.setChecked(false);
                etRace1Money.setEnabled(true);
                etRace2Money.setEnabled(false);
                etRace3Money.setEnabled(false);
                etRace2Money.setText("");
                etRace3Money.setText("");
            }
        });
        cbRace2.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRace1.setChecked(false);
                cbRace3.setChecked(false);
                etRace1Money.setEnabled(false);
                etRace2Money.setEnabled(true);
                etRace3Money.setEnabled(false);
                etRace1Money.setText("");
                etRace3Money.setText("");
            }
        });
        cbRace3.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbRace1.setChecked(false);
                cbRace2.setChecked(false);
                etRace1Money.setEnabled(false);
                etRace2Money.setEnabled(false);
                etRace3Money.setEnabled(true);
                etRace1Money.setText("");
                etRace2Money.setText("");
            }
        });

        // Set up seek bars
        sbRace1.setEnabled(false);
        sbRace2.setEnabled(false);
        sbRace3.setEnabled(false);

        // Set up start button
        btnStart.setOnClickListener(v -> {

            Intent turnOffSound = new Intent(Activity_game.this, Bg_sound.class);
            stopService(turnOffSound);
            //Change racing sound
            if (player != null) {
                player.stop();
                player.release();
            }
            player = MediaPlayer.create(Activity_game.this, R.raw.racing_sound);
            player.start();
            // Get the money the user inputs into the EditText fields
            double betAmount;
            if (cbRace1.isChecked() && !etRace1Money.getText().toString().isEmpty()) {
                betAmount = Double.parseDouble(etRace1Money.getText().toString());
            } else if (cbRace2.isChecked() && !etRace2Money.getText().toString().isEmpty()) {
                betAmount = Double.parseDouble(etRace2Money.getText().toString());
            } else if (cbRace3.isChecked() && !etRace3Money.getText().toString().isEmpty()) {
                betAmount = Double.parseDouble(etRace3Money.getText().toString());
            } else {
                betAmount = 0;
            }

            // Check if a race is selected and money is inputted
            if ((cbRace1.isChecked() || cbRace2.isChecked() || cbRace3.isChecked()) && betAmount > 0) {
                // Check if the bet amount is not more than the total money
                if (betAmount <= totalMoney) {
                    // Rest of your code...
                    // Disable buttons and inputs
                    btnStart.setEnabled(false);
                    btnReset.setEnabled(false);
                    cbRace1.setEnabled(false);
                    cbRace2.setEnabled(false);
                    cbRace3.setEnabled(false);
                    etRace1Money.setEnabled(false);
                    etRace2Money.setEnabled(false);
                    etRace3Money.setEnabled(false);

                    // Reset seek bars
                    sbRace1.setProgress(0);
                    sbRace2.setProgress(0);
                    sbRace3.setProgress(0);

                    // Start race
                    new Thread(() -> {
                        Random random = new Random();
                        while (!raceOver) {
                            runOnUiThread(() -> {
                                if (sbRace1.getProgress() < 100) sbRace1.setProgress(sbRace1.getProgress() + random.nextInt(racingStep));
                                if (sbRace2.getProgress() < 100) sbRace2.setProgress(sbRace2.getProgress() + random.nextInt(racingStep));
                                if (sbRace3.getProgress() < 100) sbRace3.setProgress(sbRace3.getProgress() + random.nextInt(racingStep));

                                if (sbRace1.getProgress() >= 100 || sbRace2.getProgress() >= 100 || sbRace3.getProgress() >= 100) {
                                    raceOver = true;
                                    btnReset.setEnabled(true);
                                    // Check if user won
                                    if ((cbRace1.isChecked() && sbRace1.getProgress() >= 100 && !etRace1Money.getText().toString().isEmpty())
                                            || (cbRace2.isChecked() && sbRace2.getProgress() >= 100 && !etRace2Money.getText().toString().isEmpty())
                                            || (cbRace3.isChecked() && sbRace3.getProgress() >= 100 && !etRace3Money.getText().toString().isEmpty())) {
                                        totalMoney += (!etRace1Money.getText().toString().isEmpty() ? Integer.parseInt(etRace1Money.getText().toString()) * ratio : 0)
                                                + (!etRace2Money.getText().toString().isEmpty() ? Integer.parseInt(etRace2Money.getText().toString()) * ratio : 0)
                                                + (!etRace3Money.getText().toString().isEmpty() ? Integer.parseInt(etRace3Money.getText().toString()) * ratio : 0);
                                    } else {
                                        totalMoney -= (!etRace1Money.getText().toString().isEmpty() ? Integer.parseInt(etRace1Money.getText().toString()) : 0)
                                                + (!etRace2Money.getText().toString().isEmpty() ? Integer.parseInt(etRace2Money.getText().toString()) : 0)
                                                + (!etRace3Money.getText().toString().isEmpty() ? Integer.parseInt(etRace3Money.getText().toString()) : 0);
                                    }

                                }
                                // Update the TextView with the new total
                                runOnUiThread(() -> tvYourMoney.setText(String.format("%.2f", totalMoney)));
                            });
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // Determine winning or not with result page
                        if (sbRace1.getProgress() >= 100) {
                            firstFinished = 1;
                        } else if (sbRace2.getProgress() >= 100) {
                            firstFinished = 2;
                        } else if (sbRace3.getProgress() >= 100) {
                            firstFinished = 3;
                        }

                        // Check if the slider has checkbox reach first or not
                        if ((cbRace1.isChecked() && firstFinished == 1)
                                || (cbRace2.isChecked() && firstFinished == 2)
                                || (cbRace3.isChecked() && firstFinished == 3)) {
                            isWinner = true;
                        } else {
                            isWinner = false;
                        }


                        // Calculate the total amount the user gets = betamount + 0.9 * betamount
                        double totalGetAmount = 0; // For show in result
                        if (isWinner) {
                            // if winner then total get amount = 1.9 bet amount
                            totalGetAmount += betAmount * ratio;
                        } else {
                            totalGetAmount = betAmount;
                        }

                        // Create an Intent to navigate to the result page
                        Intent intent = new Intent(Activity_game.this, Activity_result.class);
                        intent.putExtra("isWinner", isWinner);
                        intent.putExtra("totalGetAmount", totalGetAmount); // Pass the total amount to the result page
                        startActivity(intent);
                    }).start();
                    bgVideo.start();
                } else {
                    Toast.makeText(Activity_game.this, "You cannot bet more money than you have", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Activity_game.this, "Please select a race and input your bet", Toast.LENGTH_SHORT).show();
            }
        });


        // Set up reset button
        btnReset.setOnClickListener(v -> {
            // Reset checkboxes
            cbRace1.setChecked(false);
            cbRace2.setChecked(false);
            cbRace3.setChecked(false);

            // Reset seek bars
            sbRace1.setProgress(0);
            sbRace2.setProgress(0);
            sbRace3.setProgress(0);

            // Reset money inputs
            etRace1Money.setText("");
            etRace2Money.setText("");
            etRace3Money.setText("");

            // Enable all inputs
            etRace1Money.setEnabled(true);
            etRace2Money.setEnabled(true);
            etRace3Money.setEnabled(true);
            cbRace1.setEnabled(true);
            cbRace2.setEnabled(true);
            cbRace3.setEnabled(true);
            btnStart.setEnabled(true);
            raceOver = false;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Initialize logout button
        Button btnLogout = findViewById(R.id.btnLogout);

        // Set up logout button
        btnLogout.setOnClickListener(v -> {
            // Confirm logout
            new AlertDialog.Builder(Activity_game.this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to logout?")
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        // User confirmed logout, go to login page
                        Intent intent = new Intent(Activity_game.this, Login_main.class);
                        startActivity(intent);
                        Intent turnOffSound = new Intent(Activity_game.this, Bg_sound.class);
                        stopService(turnOffSound);
                        finish();
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });

        bgVideo = (VideoView) findViewById(R.id.background);
        String path = "android.resource://com.example.miniproject_prm392/" + R.raw.background;
        Uri u = Uri.parse(path);
        bgVideo.setVideoURI(u);


        bgVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        btnRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRule();
            }
        });
    }
        private void showRule() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.activity_rule_of_game, null);
            builder.setView(dialogLayout);
            builder.setPositiveButton("OK", null);
            builder.show();
        }

    @Override
    protected void onResume() {
        bgVideo.resume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        bgVideo.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        bgVideo.stopPlayback();
        super.onDestroy();
    }


}




