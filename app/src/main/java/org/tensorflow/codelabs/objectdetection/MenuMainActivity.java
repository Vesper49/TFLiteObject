package org.tensorflow.codelabs.objectdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
public class MenuMainActivity extends AppCompatActivity {
    private Button back;
    private Button changeCloudButton;
    TextView cloudText;
    public static boolean ifItDrive = false; // if false it means Drive, if not Firebase
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_main);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBack();
            }
        });
        changeCloudButton = findViewById(R.id.Change);
        changeCloudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeCloud();
            }
        });
        cloudText = findViewById(R.id.Cloud);
        cloudText.setText("YandexDisk");

    }

    public void doBack(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void changeCloud(){
        if(cloudText.getText() == "YandexDisk"){
            cloudText.setText("Firebase");
            ifItDrive = true;
        }
        else {
            cloudText.setText("YandexDisk");
            ifItDrive = false;
        }
    }

    public static boolean getIfItDrive() {
        return ifItDrive;
    }

}