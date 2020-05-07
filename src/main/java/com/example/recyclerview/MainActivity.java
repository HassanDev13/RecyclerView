package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface, RecyclerViewClickInterface2 {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String text = "ModuleX", text2 = "ModuleX", text3 = "ModuleX";
    String m_Text;
    SharedPreferences pref;
    List<String> moviesList;
    List<Integer> imageView;
    List<Integer> icon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        moviesList = new ArrayList<>();
        imageView = new ArrayList<>();
        icon = new ArrayList<>();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(moviesList, imageView, icon, this, this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);


        moviesList.add(text);
        imageView.add(R.drawable.logo);
        icon.add(R.drawable.ic_settings_);

        moviesList.add(text2);
        imageView.add(R.drawable.logo2);
        icon.add(R.drawable.ic_settings_);

        moviesList.add(text3);
        imageView.add(R.drawable.logo);
        icon.add(R.drawable.ic_refresh);

    }


    //    These are the interface Methods from our custom RecyclerViewClickInterface
    @Override
    public void onItemClick(int position) {

        if(position == 0){
            File path =MainActivity.this.getExternalFilesDir(DIRECTORY_DOWNLOADS) ;
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            assert path != null;
            Uri uri = Uri.parse(path.toString()); // a directory
            intent.setDataAndType(uri, "application/pdf");
            startActivity(Intent.createChooser(intent, "Open folder"));
        }
    }

    @Override
    public void onItemClickIcon(int position) {
        Toast.makeText(getApplicationContext(), "icon" + position, Toast.LENGTH_LONG).show();
        if (position == 0) {
            showEditNameAndSaveData();

        }
    }

    @Override
    public void onLongItemClick(final int position) {

    }


    void showEditNameAndSaveData() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText input = new EditText(MainActivity.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("votre numéro ici :");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                if (m_Text.matches("")) {
                    Toast.makeText(MainActivity.this, "Vous n'avez pas entré ", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                     pref = MainActivity.this.getSharedPreferences("MyPref", 0); // 0 - for private mode

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("key_name", m_Text); // Storing string
                    editor.apply();
                    update();
                }

            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        //  Toast.makeText(getApplicationContext(), downloadsFolder.getPath(), Toast.LENGTH_LONG).show();
        builder.show();

    }

    void update() {
        text = pref.getString("key_name", null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //Image browsing intent processed successfully
            if (requestCode == 1){
                if (data.getData() != null) {
                    Uri uri = data.getData();


                }
            }
        }
    }


}
