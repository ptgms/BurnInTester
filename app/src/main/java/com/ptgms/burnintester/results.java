package com.ptgms.burnintester;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class results extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final int[] toggle = {0}; //toggle button to mark screen as working/not working
        Window window = getWindow(); //get the window to set it to transparent later.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT); //set color to transparent
        Button butt1 = findViewById(R.id.workbutt);
        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle[0] != 0) {
                    Snackbar.make(view, "You marked your screen as working!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    toggle[0] = 0;
                    Button butt1 = findViewById(R.id.workbutt);
                    butt1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.greybutt)));
                    Button butt2 = findViewById(R.id.flawlessbutt);
                    butt2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.whitebutt)));
                    LinearLayout what = findViewById(R.id.whattodo);
                    what.setVisibility(View.GONE);
                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button butt2 = findViewById(R.id.flawlessbutt);
        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (toggle[0] != 1) {
                    Snackbar.make(view, "You marked your screen as having Burn-In marks, yikes!", Snackbar.LENGTH_LONG)
                            .setAction("What now?", null).show();
                    toggle[0] = 1;
                    LinearLayout what = findViewById(R.id.whattodo);
                    what.setVisibility(View.VISIBLE);
                    Button butt1 = findViewById(R.id.flawlessbutt);
                    butt1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.greybutt)));
                    Button butt2 = findViewById(R.id.workbutt);
                    butt2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.whitebutt)));
                }
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Point size = new Point();
        display.getSize(size);
        String id = display.getName();
        int width = size.x;
        int height = size.y;
        float ratio = ((float) metrics.heightPixels / (float) metrics.widthPixels);
        int densityDpi = (int) (metrics.density * 160f);
        TextView displayinfo = findViewById(R.id.displaystats);
        displayinfo.setText("" + getResources().getText(R.string.dis_res) + width + "x" + height + "\n" + getResources().getText(R.string.dis_type) + id + "\n" + getResources().getText(R.string.dis_dpi) + densityDpi + "\n" + getResources().getText(R.string.dis_asp) + ratio);
    }

}
