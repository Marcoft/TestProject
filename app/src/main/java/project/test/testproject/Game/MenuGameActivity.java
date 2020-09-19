package project.test.testproject.Game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import project.test.testproject.MainActivity;
import project.test.testproject.R;

public class MenuGameActivity extends AppCompatActivity {

    Button newGame , repeatRun;
    private static final String firstStartApp = "first_start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_game);

        newGame = (Button) findViewById(R.id.newGame);
        repeatRun = (Button) findViewById(R.id.repeatRun);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartGame.class);
                startActivity(intent);
            }
        });

        repeatRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences(firstStartApp, MODE_PRIVATE); // For First Download App
                sp.edit().clear().apply();

                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}