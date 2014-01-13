package com.lachongmedia.sol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
public class reg extends Activity {

    private ImageButton btnReg;
    private EditText etSdt;
    TextView tvSdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reg);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Typeface tf = Typeface.createFromAsset(getAssets(), "Font/VAVON.TTF");
        tvSdt = (TextView) findViewById(R.id.tvSdt);
        tvSdt.setTypeface(tf, Typeface.NORMAL);

        btnReg = (ImageButton) findViewById(R.id.btReg);
        etSdt = (EditText) findViewById(R.id.etSdt);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etSdt.getText().length() == 0){
                    Toast.makeText(getBaseContext(), "Vui lòng nhập số điện thoại của bạn", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(reg.this,code.class);
                    reg.this.startActivity(intent);
                }
            }
        });
    }

}
