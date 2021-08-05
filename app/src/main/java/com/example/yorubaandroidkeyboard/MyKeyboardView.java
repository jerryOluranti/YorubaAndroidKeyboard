package com.example.yorubaandroidkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class MyKeyboardView extends KeyboardView {


    public MyKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // That popup keyboard with the close button is annoying when we have only one popup character.
    // Simpler way is to override the longpress method of keyboardView class like this:
    @Override
    protected boolean onLongPress(Keyboard.Key popupKey) {
        switch(popupKey.codes[0]){
            case 113:
                // send 49("1") to the KeyboardListenerObject when 113 ("q") is pressed down
                getOnKeyboardActionListener().onKey(49, null);
                return true;
        }
        return super.onLongPress(popupKey);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    public void showPopup (View anchorView){

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
//        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(18);
        paint.setColor(Color.WHITE);

        int keyXAxis = 15;
        int keyYAxis = 30;

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key: keys){
            if (key.label != null){
                switch (key.codes[0]){
//                    case 97:
//                        canvas.drawText(("à á"), key.x+keyXAxis, key.y+keyYAxis, paint);
//                        break;
//
//                    case 101:
//                        canvas.drawText(("è é"), key.x+keyXAxis, key.y+keyYAxis, paint);
//                        break;
//
//                    case 105:
//                        canvas.drawText(("ì í"), key.x+keyXAxis, key.y+keyYAxis, paint);
//                        break;

//                    case 111:
//                        canvas.drawText(("ò ó"), key.x+keyXAxis, key.y+keyYAxis, paint);
//                        break;

                        case 491:
                        canvas.drawText(("ọ̀ ọ́"), key.x+keyXAxis, key.y+keyYAxis, paint);
                        break;

//                    case 117:
//                        canvas.drawText(("ù ú"), key.x+keyXAxis, key.y+keyYAxis, paint);
//                        break;

                    case 281:
                        canvas.drawText(("ẹ̀ é̩"), key.x+keyXAxis, key.y+keyYAxis, paint);
                        break;


                }
            }
        }

    }
}
