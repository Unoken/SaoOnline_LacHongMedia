package com.lachongmedia.sol;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.TextView;

/**
 * Created by Unoken on 10/01/14.
 */
public class ListenerEditText extends TextView{

    public ListenerEditText(Context context) {
        super(context);
    }

    public ListenerEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListenerEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private KeyImeChange keyImeChangeListener;

    public void setKeyImeChangeListener(KeyImeChange listener){
        keyImeChangeListener = listener;
    }

    public interface KeyImeChange {
        public void onKeyIme(int keyCode, KeyEvent event);
    }

    @Override
    public boolean onKeyPreIme (int keyCode, KeyEvent event){
        if(keyImeChangeListener != null){
            keyImeChangeListener.onKeyIme(keyCode, event);
        }
        return false;
    }

}
