package com.example.yorubaandroidkeyboard;

import android.annotation.SuppressLint;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

public class YorubaKeyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private MyKeyboardView kv;
    private Keyboard keyboardAlpha;
    private Keyboard keyboardNumber;
    private Keyboard keyboardPunctuation;

    private boolean isCaps = false;
    private boolean is_Number = false;


    @Override
    public View onCreateInputView() {
        kv = (MyKeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);

        keyboardAlpha = new Keyboard(this, R.xml.qwerty);
        keyboardNumber = new Keyboard(this, R.xml.number_pad);
        keyboardPunctuation = new Keyboard(this, R.xml.punctuation_pad);

        keyboardAlpha.setShifted(true);
        isCaps = true;

        kv.setKeyboard(keyboardAlpha);

//        kv.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN){
//                    kv.closing(); // Close popup keyboard if it is showing
//                }
//                return false;
//            }
//        });

        kv.setOnKeyboardActionListener(this);


        return kv;

    }


    @Override
    public void onPress(int primaryCode) {


    }

    @Override
    public void onRelease(int primaryCode) {

    }


    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();

        switch (primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1, 0);
                break;

            case Keyboard.KEYCODE_SHIFT:
                isCaps = !isCaps;
                keyboardAlpha.setShifted(isCaps);
                kv.invalidateAllKeys();
                break;

            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;

            case 3210:
                kv.setKeyboard(keyboardAlpha);
                kv.invalidateAllKeys();
                break;

            case 1023:
                kv.setKeyboard(keyboardNumber);
                kv.invalidateAllKeys();
                break;

            case 1111:
                kv.setKeyboard(keyboardPunctuation);
                kv.invalidateAllKeys();
                break;

            case 173:
                validateGb(ic);

            default:
                char code = (char) primaryCode;
                if (Character.isLetter(code) && isCaps) {
                    code = Character.toUpperCase(code);
                    ic.commitText(String.valueOf(code), 1);
                }
                else{
                    code = Character.toLowerCase(code);
                    ic.commitText(String.valueOf(code), 1);
                }

                keyboardAlpha.setShifted(false);
                kv.invalidateAllKeys();
                isCaps = false;

        }
    }

    public void validateGb(InputConnection inputConnection){
        if (isCaps){
            inputConnection.commitText("GB", 1);
            inputConnection.deleteSurroundingText(0,1);
        } else{
            inputConnection.commitText("gb",1);
            inputConnection.deleteSurroundingText(0,1);

        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}