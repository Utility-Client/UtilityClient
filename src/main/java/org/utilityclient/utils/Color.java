package org.utilityclient.utils;

import org.utilityclient.api.abstraction.StandaloneCompatible;

/**
 * Color codes used to draw UI in Minecraft.<br/>
 * This enum was created to prevent the use of "magic numbers" and make it easier to render specific colors.<br/><br/>
 * Since 3.0 this includes the full color palette of the UtilityClient 3 branding.
 * @implNote  The format used is: <br/> 0xAARRGGBB
 * @author Sam302
 * @author Sangelo
 * @since 2.14
 */
@StandaloneCompatible
public enum Color {
    NightBlack(0xff171314),
    SnowWhite(0xffFFF9FB),
    Irresistible(0xffAA4465),
    NightBlue(0xff243981),
    DeepBlue(0xff0C173E),
    YellowCorolla(0xffEFCB68),
    GreenMunsell(0xff04A777),
    JungleGreen(0xff1BAF83),
    SizzlingRed(0xffFF495C, 0xffE84556),
    DeepSaffron(0xffF19A3E, 0xffE48F34),
    SnowGray(0xffEFEFEF, 0xffD1D1D1),
    Background(0x55000000),
    ;

    public final int color;
    public final int strokeColor;

    Color(int color) {
        this.color = color;
        this.strokeColor = color;
    }

    Color(int color, int stroke) {
        this.color = color;
        this.strokeColor = stroke;
    }
}
