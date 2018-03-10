package com.nicholasholley.dev.hueboat.util.log;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.jetbrains.annotations.NotNull;

public class StaticHelper {
    public static final String[] TRUE_STRINGS = new String[]{
            "true", "t", "yes", "1"
    };

    public static void hideSoftKeyboard(View view) {
        try {
            ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (NullPointerException e){
            Log.Companion.d("Cannot hide soft keyboard");
        }
    }

    public static boolean isTrueString(String str) {
        if (str == null ) return false;
        else {
            for(int i =0; i < TRUE_STRINGS.length; i++) {
                if(str.toLowerCase().contains(TRUE_STRINGS[i])) { return true; }
            }
            return false;
        }
    }
}
