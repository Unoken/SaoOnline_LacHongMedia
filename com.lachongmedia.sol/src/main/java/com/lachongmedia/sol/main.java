package com.lachongmedia.sol;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Html;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.FocusFinder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Unoken on 19/12/13.
 */
public class main extends Activity{

    private RelativeLayout ivHeader;
    private EditText et_Chatbox;
    private ImageButton ib_Send;
    private ViewGroup vg_Chat;
    private ScrollView sv_Chat;
    private ImageButton ib_Gioithieu;
    private ImageButton ib_Chat;
    private ImageButton ib_Hatcungsao;
    private ImageButton ib_Backkhungchat;
    private RelativeLayout layout_Khungchat;
    private RelativeLayout layout_Main;
    private RelativeLayout layout_Emo;
    private RelativeLayout layout_Gioithieu;
    private RelativeLayout layout_Id;
    private RelativeLayout layout_root;
    private LinearLayout layout_button;
    private GridView gv_Emo;
    private TCPClient tcp;
    private emo emo = new emo(this);
    int src = -1;
    int maEmo;
    int ret;
    String text;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        et_Chatbox = (EditText) findViewById(R.id.etChatbox);
        ib_Send = (ImageButton) findViewById(R.id.ibSend);
        sv_Chat = (ScrollView) findViewById(R.id.svChat);
        vg_Chat = (ViewGroup) findViewById(R.id.layoutChat);
        ib_Gioithieu = (ImageButton) findViewById(R.id.ibGioithieu);
        ib_Chat = (ImageButton) findViewById(R.id.ibChat);
        ib_Gioithieu = (ImageButton) findViewById(R.id.ibGioithieu);
        ib_Hatcungsao = (ImageButton) findViewById(R.id.ibHatcungsao);
        layout_Khungchat =  (RelativeLayout) findViewById(R.id.layoutKhungChat);
        layout_Main =  (RelativeLayout) findViewById(R.id.layoutMain);
        layout_Emo = (RelativeLayout) findViewById(R.id.layoutEmo);
        layout_Gioithieu = (RelativeLayout) findViewById(R.id.rlGioiThieu);
        gv_Emo = (GridView) findViewById(R.id.gvEmo);
        ivHeader = (RelativeLayout) findViewById(R.id.ivThanhtrencungmain);
        layout_Id = (RelativeLayout) findViewById(R.id.rlBackgroundID);
        layout_button = (LinearLayout) findViewById(R.id.llButton);
        layout_root = (RelativeLayout) findViewById(R.id.rootLayout);
        ib_Backkhungchat = (ImageButton) findViewById(R.id.btBackkhungchat);

        new AsynConnect().execute();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        gv_Emo.setAdapter(emo);

        ivHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = -1;
            }
        });

        layout_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = -1;
            }
        });

        sv_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = -1;
            }
        });

        vg_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = -1;
            }
        });

        layout_Khungchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = -1;
            }
        });

        gv_Emo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                src = emo.images[i];
                maEmo = i;
                gv_Emo.clearFocus();
                gv_Emo.setSelection(i);
                gv_Emo.requestFocusFromTouch();
            }
        });

        sv_Chat.post(new Runnable() {
            @Override
            public void run() {
                sv_Chat.fullScroll(View.FOCUS_DOWN);
            }
        });

        ib_Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_Gioithieu.setVisibility(View.GONE);
                layout_Main.setVisibility(View.VISIBLE);
                layout_Id.setVisibility(View.VISIBLE);
                layout_Emo.setVisibility(View.VISIBLE);
                sv_Chat.fullScroll(View.FOCUS_DOWN);
                src = -1;
            }
        });

        ib_Gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_Gioithieu.setVisibility(View.VISIBLE);
                layout_Main.setVisibility(View.GONE);
                layout_Id.setVisibility(View.GONE);
                layout_Emo.setVisibility(View.GONE);
                src = -1;
            }
        });

        ib_Hatcungsao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                src = -1;
            }
        });

        ib_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                new sendTask().execute();
                sv_Chat.fullScroll(View.FOCUS_DOWN);

                et_Chatbox.clearFocus();
                if ((layout_Emo.getVisibility() == View.GONE) && (layout_button.getVisibility() == View.GONE)) {
                    layout_Emo.setVisibility(View.VISIBLE);
                    layout_button.setVisibility(View.VISIBLE);
                }
            }
        });

        et_Chatbox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    new sendTask().execute();

                    et_Chatbox.clearFocus();
                    if ((layout_Emo.getVisibility() == View.GONE) && (layout_button.getVisibility() == View.GONE)) {
                        layout_Emo.setVisibility(View.VISIBLE);
                        layout_button.setVisibility(View.VISIBLE);
                    }
                    return true;
                }
                return false;
            }
        });

        et_Chatbox.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                src = -1;
                sv_Chat.post(new Runnable() {
                    @Override
                    public void run() {
                        sv_Chat.fullScroll(View.FOCUS_DOWN);
                    }
                });
                return false;
            }
        });

        et_Chatbox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b && (layout_button.getVisibility() == View.GONE) && (layout_Emo.getVisibility() == View.GONE)) {
                    layout_button.setVisibility(View.VISIBLE);
                    layout_Emo.setVisibility(View.VISIBLE);
                } else {
                    layout_button.setVisibility(View.GONE);
                    layout_Emo.setVisibility(View.GONE);
                }
                sv_Chat.post(new Runnable() {
                    @Override
                    public void run() {
                        sv_Chat.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        ib_Backkhungchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                sv_Chat.fullScroll(View.FOCUS_DOWN);

                et_Chatbox.clearFocus();
                if ((layout_Emo.getVisibility() == View.GONE) && (layout_button.getVisibility() == View.GONE)) {
                    layout_Emo.setVisibility(View.VISIBLE);
                    layout_button.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void showMs (String ms){

        SpannableStringBuilder ssb = new SpannableStringBuilder("       " + ms);
        Bitmap sao = BitmapFactory.decodeResource(getResources(), R.drawable.saochat2);
        ssb.setSpan(new ImageSpan(sao), 2, 3, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        final TextView textView = new TextView(main.this);
        textView.setTextColor(getResources().getColor(R.color.xanh));
        textView.setTextSize(20);
        textView.setBackgroundResource(R.drawable.backgroundline);
        textView.setText(ssb, TextView.BufferType.SPANNABLE);
        textView.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vg_Chat.addView(textView);
            }
        });
    }

    private void showEmo (int rsc){

        SpannableStringBuilder ssb = new SpannableStringBuilder("         ");
        Bitmap sao = BitmapFactory.decodeResource(getResources(), R.drawable.saochat2);
        ssb.setSpan(new ImageSpan(sao), 3, 4, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        Bitmap emobit = BitmapFactory.decodeResource(getResources(), src);
        ssb.setSpan(new ImageSpan(emobit), 8, 9, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        final TextView textView = new TextView(main.this);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setBackgroundResource(R.drawable.backgroundline);
        textView.setText(ssb, TextView.BufferType.SPANNABLE);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT );
        textView.setLayoutParams(params);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vg_Chat.addView(textView);
            }
        });
    }

    public class AsynConnect extends AsyncTask<Void, Void, Void> {
        ProgressDialog taxiDialog;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                tcp = new TCPClient();
                tcp.run();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return null;
        }
    }

    public class sendTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog taxiDialog;

        @Override
        protected Void doInBackground(Void... params) {
            if ((et_Chatbox.getText().length() != 0) && src == -1) {
                text = "#SOLmes 0975737836 test " + et_Chatbox.getText().toString();
            } else if (src != -1) {
                text = "#SOLemo 0975737836 test " + maEmo;
            }
            ret = tcp.sendMessage(text);
            return null;
        }

        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            if(ret < 0){
                Toast.makeText(getBaseContext(), "Không có kết nối mạng", Toast.LENGTH_SHORT).show();
                new AsynConnect().execute();
            }
            else{
                if ((et_Chatbox.getText().length() != 0) && src == -1) {
                    showMs(et_Chatbox.getText().toString());
                    et_Chatbox.setText("");
                } else if (src != -1) {
                    et_Chatbox.setText("");
                    showEmo(src);
                    src = -1;
                }
            }
            sv_Chat.post(new Runnable() {
                @Override
                public void run() {
                    sv_Chat.fullScroll(View.FOCUS_DOWN);
                }
            });
        }
    }
}