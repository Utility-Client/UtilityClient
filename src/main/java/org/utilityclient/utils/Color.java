package org.utilityclient.utils;

/**
 * Color codes used to draw UI in Minecraft.
 * This enum was created to prevent the use of "magic numbers" and make it easier to render specific colors.
 *
 * @author Sam302
 * @since 2.14
 */
public enum Color {
    TEXT(-1),                       // a:255 r:255 g:255 b:255
    BACKGROUND(1426063360),         // a:85 r:0 g:0 b:0
    ;

    public final int color;
    Color(int color) {
        this.color = color;
    }
}
