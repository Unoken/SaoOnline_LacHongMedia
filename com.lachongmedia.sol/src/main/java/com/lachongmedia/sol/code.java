package com.lachongmedia.sol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Unoken on 18/12/13.
 */
public class code extends Activity {

    private TextView tvCode;
    private TextView tvSupport;
    private ImageButton ib_Next;
    private ImageButton ib_Back;
    private EditText et_Code;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_code);

        tvCode = (TextView) findViewById(R.id.tvCode);
        tvSupport = (TextView) findViewById(R.id.tvSupport);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/VAVON.TTF");
        tvCode.setTypeface(tf, Typeface.NORMAL);
        tvSupport.setTypeface(tf, Typeface.NORMAL);

        ib_Next = (ImageButton) findViewById(R.id.ibNext);
        et_Code = (EditText) findViewById(R.id.etCode);

        ib_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_Code.getText().length() == 0){
                    Toast.makeText(getBaseContext(), "Vui lòng nhập mã xác nhận", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(code.this, main.class);
                    code.this.startActivity(intent);
                }
            }
        });

        ib_Back = (ImageButton) findViewById(R.id.ibBackCode);

        ib_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
