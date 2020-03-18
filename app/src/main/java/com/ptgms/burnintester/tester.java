package com.ptgms.burnintester;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class tester extends AppCompatActivity {

    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hidefull(mContentView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int[] current = {1};
        FloatingActionButton fab = findViewById(R.id.fab);
        mContentView = findViewById(R.id.frame);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingActionButton butt = findViewById(R.id.fab);
                ConstraintLayout frame = findViewById(R.id.frame);
                hidefull(view);
                switch (current[0]) {
                    case 0:
                        frame.setBackgroundColor(getResources().getColor(R.color.greenscreen));
                        current[0]++;
                        debugprint("SET GREEN FRAME");
                        break;
                    case 1:
                        frame.setBackgroundColor(getResources().getColor(R.color.redscreen));
                        current[0]++;
                        debugprint("SET RED FRAME");
                        break;
                    case 2:
                        frame.setBackgroundColor(getResources().getColor(R.color.whitescreen));
                        current[0]++;
                        debugprint("SET WHITE FRAME");
                        break;
                    case 3:
                        frame.setBackgroundColor(getResources().getColor(R.color.blackscreen));
                        current[0]++;
                        debugprint("SET BLACK FRAME");
                        break;
                    case 4:
                        frame.setBackgroundColor(getResources().getColor(R.color.bluescreen));
                        current[0]++;
                        debugprint("SET BLUE FRAME");
                        break;
                    case 5:
                        frame.setBackgroundColor(getResources().getColor(R.color.greenscreen));
                        current[0] = 1;
                        butt.setVisibility(View.VISIBLE);
                        debugprint("SET GREEN FRAME AND BUTTON TO VISIBLE");
                        break;
                }
            }
        });
    }

    public void finishTest(View view) {
        Intent intent = new Intent(this, results.class);
        startActivity(intent);
        finish();
    }

    public void hidefull(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    }

    public void debugprint(String string) {
        if (BuildConfig.DEBUG) {
            System.out.println("DEBUG: " + string);
        }
    }
}


