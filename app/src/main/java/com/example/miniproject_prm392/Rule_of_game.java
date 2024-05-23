package com.example.miniproject_prm392;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Rule_of_game extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rule_of_game);

        TextView gameRuleTitle = findViewById(R.id.gameRuleTitle);
        gameRuleTitle.setText(
                "1. Người chơi sẽ được miễn phí 100 tiền khởi đầu\n" +
                        "2. Tích vào các ô checkbox rồi mới được ghi số tiền đặt cược\n" +
                        "3. Tỉ lệ ăn sẽ là 1.8 (tức là đặt 10 ăn 18)\n" +
                        "4. Click chọn checkbox xong và ghi số tiền thì mới nhấn nút bắt đầu\n" +
                        "5. Khi xe về đích đầu tiên, dựa vào lựa chọn trước đó của người chơi mà thông báo số tiền đã thắng hoặc thua\n" +
                        "6. Bấm quay lại để trở về màn hình chơi game và tiếp tục\n"
        );
    }
}
