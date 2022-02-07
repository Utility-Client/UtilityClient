package org.utilityclient.utils;

public enum Color {
    TEXT(-1),                       // a:255 r:255 g:255 b:255
    BACKGROUND(1426063360),         // a:85 r:0 g:0 b:0
    ;

    public final int color;
    Color(int color) {
        this.color = color;
    }
}
