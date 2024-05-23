package com.example.miniproject_prm392;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class Bg_sound extends Service {
    MediaPlayer mediaPlayer;
    public Bg_sound() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    //Hàm tạo các đối tượng mà service quản lí

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(Bg_sound.this, R.raw.bg_sound );
        mediaPlayer.setLooping(true);
    }

    // Hàm khởi động đối tượng service service quản lí
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mediaPlayer.isPlaying())
            mediaPlayer.pause();
        else
            mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    // Hàm dừng đối tuognjw service quản lí
    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }
}