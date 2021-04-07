package com.example.artikolevyinf.TextViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.example.artikolevyinf.R;

public class TextView_blue_bold_8 extends androidx.appcompat.widget.AppCompatTextView{

    // attr create in attrs
    String strTitle;

    public TextView_blue_bold_8(Context context) {
        super(context);
        // Call methods to create TextView
        setStrTitle(strTitle);
        setStyle();
    }

    public TextView_blue_bold_8(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViews);
        int count = typedArray.getIndexCount();

        // Read attributes
        try {
            for (int i = 0; i < count; ++i){
                int attr = typedArray.getIndex(i);
                if(attr == R.styleable.TextViews_title){
                    strTitle = typedArray.getString(attr);
                    setStrTitle(strTitle);
                }
            }
            // Apply style
            setStyle();
        }finally {
            typedArray.recycle();
        }

    }

    public TextView_blue_bold_8(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStrTitle(String strTitle){
        if(strTitle != null){
            setText(strTitle);
        }
    }

    // Style TextView
    public void setStyle(){
        int blueColor = getResources().getColor(R.color.colorBlue);
        setTextColor(blueColor);
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        // Text Bold
        setTypeface(null, Typeface.BOLD);
    }

}
