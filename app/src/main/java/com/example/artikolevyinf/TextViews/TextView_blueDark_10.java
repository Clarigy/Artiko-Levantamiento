package com.example.artikolevyinf.TextViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;

import com.example.artikolevyinf.R;

public class TextView_blueDark_10 extends androidx.appcompat.widget.AppCompatTextView{
    // Se crean las variables para los atributos
    String strTitle;

    public TextView_blueDark_10(Context context) {
        super(context);
        // Llamar los metodos para crear el TextView
        setStrTitle(strTitle);
        setStyle();
    }

    public TextView_blueDark_10(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViews);
        int count = typedArray.getIndexCount();

        // Leer los atributos
        try {
            for (int i = 0; i < count; ++i){
                int attr = typedArray.getIndex(i);
                if(attr == R.styleable.TextViews_title){
                    strTitle = typedArray.getString(attr);
                    setStrTitle(strTitle);
                }
            }
            // Aplicar los estilos
            setStyle();
        }finally {
            typedArray.recycle();
        }

    }

    public TextView_blueDark_10(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStrTitle(String strTitle){
        if(strTitle != null){
            setText(strTitle);
        }
    }

    public void setStyle(){
        int blueColor = getResources().getColor(R.color.colorBlueDark);
        setTextColor(blueColor);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
    }
}
