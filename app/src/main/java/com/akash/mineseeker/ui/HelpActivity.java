package com.akash.mineseeker.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.akash.mineseeker.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Button backButton = findViewById(R.id.btn_helpBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView mLink = (TextView) findViewById(R.id.txtview_link);
        if (mLink != null) {
            mLink.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
