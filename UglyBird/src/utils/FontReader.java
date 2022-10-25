package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontReader {

    private static final String FONTS_FOLDER_PATHS = "../media/font/"; 

    public static Font readFont(String fontName) {
    try {
        Font myFont = Font.createFont(Font.TRUETYPE_FONT, new File(FONTS_FOLDER_PATHS+fontName+".ttf"));
        return myFont.deriveFont(16f); 
        } catch (IOException  e) {
            e.printStackTrace();
            return null;
    } catch (FontFormatException e){
        e.printStackTrace();
            return null;
        }
    }
}
