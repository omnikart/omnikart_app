package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yash Gupta on 12-12-2015.
 */
public class Activity_Error extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);
        Button button_retry  = (Button) findViewById(R.id.button_retry);
        button_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Error.this,Main_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
