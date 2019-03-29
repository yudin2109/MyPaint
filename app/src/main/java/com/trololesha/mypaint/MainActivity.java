package com.trololesha.mypaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private PaintView paintView;
    private Button whiteButton;
    private Button blueButton;
    private Button redButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // прячем панель навигации
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // прячем строку состояния
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        paintView = findViewById(R.id.paint_view);
        whiteButton = findViewById(R.id.whiteColorButton);
        blueButton = findViewById(R.id.blueColorButton);
        redButton = findViewById(R.id.redColorButton);

        whiteButton.setOnClickListener(this);
        blueButton.setOnClickListener(this);
        redButton.setOnClickListener(this);
    }

    public void onClearButtonClick(View view) {
        paintView.Clear();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.whiteColorButton: {
                paintView.ChangeColor(255, 255, 255);
            } break;

            case R.id.blueColorButton: {
                paintView.ChangeColor(0, 0, 255);
            } break;

            case R.id.redColorButton: {
                paintView.ChangeColor(255, 0, 0);
            } break;
        }
    }
}
