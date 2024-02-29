package com.example.pratika20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    Camera camera;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        SurfaceHolder holder = surfaceView.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try{
                    camera.setPreviewDisplay(holder);
                    camera.startPreview();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        camera = Camera.open();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(camera != null)
            camera.release();
        camera = null;
    }
}