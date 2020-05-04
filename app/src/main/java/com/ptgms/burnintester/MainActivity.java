package com.ptgms.burnintester;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public int dark = 0;
    public int highlight = 0;
    public int noticount = 0;
    public int debug = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<addPopUp> PopUp = new ArrayList<>();
        dark = 0; //set dark int to 0 to say "all fine"
        LinearLayout darkMenu = findViewById(R.id.WarningPopUps);
        darkMenu.setVisibility(View.GONE);
        if (BuildConfig.DEBUG) {
            debug = 11; //enable debug int mode
            noticount++; //increase notification count
            TextView dtext1 = findViewById(R.id.notiText2);
            TextView dtext2 = findViewById(R.id.typeOfNoti2);
            dtext1.setText(R.string.debugnotice);
            dtext2.setText(getResources().getText(R.string.warning));
            Button fix = findViewById(R.id.fixbutton);
            fix.setVisibility(View.GONE);
        } else {
            LinearLayout debugM = findViewById(R.id.WarningPopUp2);
            debugM.setVisibility(View.GONE);
            LinearLayout buttonRow = findViewById(R.id.button_row);
            buttonRow.setVisibility(View.INVISIBLE);
        }
        if (BuildConfig.DEBUG) { //The clear-all button, only visible if you have are running a debug-build and both possible notifications are showing.
            if (noticount >= 2) {
                Button clearall = findViewById(R.id.clearAll);
                clearall.setVisibility(View.VISIBLE); //Check if the Notification count is over or equal to 2 and set clear button visible.
            } else {
                Button clearall = findViewById(R.id.clearAll);
                clearall.setVisibility(View.GONE); //if its not equal/over 2, hide it.
            }
        } else {
            Button clearall = findViewById(R.id.clearAll);
            clearall.setVisibility(View.GONE); //if not running debug-build, hide it.
        }
    }

    public void startTest(final View view) { //The Start test button
        if (dark == 1) { //Check if User has dark mode enforced or didn't dismiss the warning
            debugprint("User cant proceed to test as he didn't acknowledge the PopUp!");
            LinearLayout notiDark = findViewById(R.id.WarningPopUps);
            if (highlight == 0) {//if warning not dismissed/fixed toggle highlighting colors
                notiDark.setBackgroundColor(getResources().getColor(R.color.greybutt));
                highlight++;
            } else {
                notiDark.setBackground(getResources().getDrawable(R.drawable.border));
                highlight = 0;
            }
        } else { //every condition is met, start the test!
            Toast toast = Toast.makeText(this, getResources().getText(R.string.notice_bright), Toast.LENGTH_SHORT);
            toast.show(); //show a toast informing user to set brightness to maximum
            Intent intent = new Intent(this, tester.class);
            startActivity(intent);
        }
    }

    public void debugMode(View view) { //deprecated, will be replaced soon.
        LinearLayout debugbuttons = (LinearLayout) findViewById(R.id.button_row);
        debugbuttons.setVisibility(View.VISIBLE);
    }

    public void jumpForce(View view) { //unused function as of now, will be completely replaced.
        Intent intent = new Intent(this, tester.class);
        startActivity(intent);
    }

    public void jumpResults(View view) { //instantly jump to result instead of having to do the whole test
        Intent intent = new Intent(this, results.class);
        startActivity(intent);
    }

    public void crashApp(View view) { //user can crash the app here.
        throw new RuntimeException("User called crash - nothing serious here");
    }

    public void settingsDisplay(View view) { //start system settings on display settings to fix dark-mode issue
        startActivityForResult(new Intent(android.provider.Settings.ACTION_DISPLAY_SETTINGS), 0);
    }

    public void setignore(View view) { //ignore dark-mode warning
        dark = 0;
        LinearLayout linear = (LinearLayout) findViewById(R.id.WarningPopUps);
        linear.setVisibility(View.GONE);
        Button clearall = findViewById(R.id.clearAll);
        clearall.setVisibility(View.GONE);
    }

    public void setignoredebug(View view) { //ignore debug-build warning
        LinearLayout linear = (LinearLayout) findViewById(R.id.WarningPopUp2);
        linear.setVisibility(View.GONE);
        Button clearall = findViewById(R.id.clearAll);
        clearall.setVisibility(View.GONE);
    }

    public void ignoreall(View view) { //button to set all notifications as dismissed
        LinearLayout linear = (LinearLayout) findViewById(R.id.WarningPopUp2);
        linear.setVisibility(View.GONE);
        dark = 0;//set dark-mode warning as dismissed/fixed
        LinearLayout linear2 = (LinearLayout) findViewById(R.id.WarningPopUps);
        linear2.setVisibility(View.GONE);
        Button clearall = findViewById(R.id.clearAll);
        clearall.setVisibility(View.GONE);
    }

    public void debugtog(View view) { //debug toggle imageview (app icon at the top)
        if (debug == 11) { //if debug-build or imageview clicked 11 times
            LinearLayout debugM = findViewById(R.id.button_row);
            debugM.setVisibility(View.INVISIBLE);
            debug = 1;
        } else if (debug == 10) { //if imageview clicked 10 times enable debug buttons
            LinearLayout debugM = findViewById(R.id.button_row);
            debugM.setVisibility(View.VISIBLE);
            debug = 11;
            Toast toast = Toast.makeText(this, getResources().getText(R.string.enable_debug), Toast.LENGTH_SHORT);
            toast.show();
        } else { //increase debug int
            debug++;
        }
    }

    public void debugprint(String string) { //self explanatory
        if (BuildConfig.DEBUG) {
            System.out.println("DEBUG: " + string);
        }
    }
}