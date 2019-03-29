package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class AddPortal extends AppCompatActivity {

    private EditText mNewPortalURL;
    private EditText mNewPortalTitle;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mButton = findViewById(R.id.button);
        mNewPortalURL = findViewById(R.id.editText);
        mNewPortalTitle = findViewById(R.id.editText2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mNewPortalURL.getText().toString();
                String title = mNewPortalTitle.getText().toString();
                Portal newPortal = new Portal(title, url);
                if (!(TextUtils.isEmpty(url)) &&
                        !(TextUtils.isEmpty(title))) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(MainActivity.EXTRA_ADDPORTAL, newPortal);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    Snackbar.make(v, R.string.warningaddportal, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
