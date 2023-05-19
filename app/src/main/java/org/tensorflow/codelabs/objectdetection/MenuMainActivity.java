package org.tensorflow.codelabs.objectdetection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MenuMainActivity extends AppCompatActivity {
    private Button back;
    private Button changeCloudButton;
    RecyclerView videoList;
    TextView cloudText;
    ListOfVid adapter;
    public static boolean ifItDrive = false; // if false it means Drive, if not Firebase
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference listRef;
    ArrayList<String> vis = new ArrayList<String>();
    ArrayList<String> preVis = new ArrayList<String>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_main);
        getName();
        //setInitial();
        back = findViewById(R.id.back);
        videoList = findViewById(R.id.obj);
        adapter = new ListOfVid(this, vis);
        videoList.setAdapter(adapter);

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

    private void getName(){
        listRef = storage.getReference().child("Videos/");
        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        for(StorageReference item: listResult.getItems()){
                            String name = item.getName();
                            vis.add(name);
                            adapter.notifyDataSetChanged();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MenuMainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void setInitial(){

    }

    public static boolean getIfItDrive() {
        return ifItDrive;
    }

}