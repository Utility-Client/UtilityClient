package org.utilityclient.debug;

import org.utilityclient.Instances;
import org.utilityclient.debug.elements.GuiButton;
import org.utilityclient.debug.elements.GuiInput;
import org.utilityclient.debug.elements.GuiLabel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GuiScreenParser extends Instances {
    public static final GuiScreenParser gsp = new GuiScreenParser();

    public ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner fr = new Scanner(new File("uc3/debugScreen.txt"));
        while (fr.hasNextLine()) {
            String line = fr.nextLine();
            if (!line.startsWith("#")) lines.add(line);
        }
        return lines;
    }

    public ArrayList<GuiElement> parse(ArrayList<String> lines) {
        ArrayList<GuiElement> elements = new ArrayList<>();

        lines.forEach(line -> {
            String[] values = line.split(", ");
            // 0 type: <btn,input,label>
            // 1 id: integer
            // 2 text: string
            // 3 pivot_x: <left,center,right>
            // 4 pivot_y: <top,middle,bottom>
            // 5 x: integer
            // 6 y: integer
            // 7 width: integer

            int pivotX = 0, pivotY = 0;

            switch (values[3]) {
                case "center":
                    pivotX = mc().width / 2;
                    break;
                case "right":
                    pivotX = mc().width;
                    break;
            }

            switch (values[4]) {
                case "middle":
                    pivotY = mc().height / 2;
                    break;
                case "bottom":
                    pivotY = mc().height;
                    break;
            }

            switch (values[0]) {
                case "btn":
                    elements.add(new GuiButton(Integer.parseInt(values[1]), values[2], Integer.parseInt(values[5]) + pivotX,
                            Integer.parseInt(values[6]) + pivotY, Integer.parseInt(values[6])));
                    break;
                case "input":
                    elements.add(new GuiInput(Integer.parseInt(values[1]), values[2], Integer.parseInt(values[5]) + pivotX,
                            Integer.parseInt(values[6]) + pivotY, Integer.parseInt(values[6])));
                    break;
                case "label":
                    elements.add(new GuiLabel(Integer.parseInt(values[1]), values[2], Integer.parseInt(values[5]) + pivotX,
                            Integer.parseInt(values[6]) + pivotY, 0));
                    break;
            }

        });

        return elements;
    }

    public static GuiScreenParser gsp() {
        return gsp;
    }
}
