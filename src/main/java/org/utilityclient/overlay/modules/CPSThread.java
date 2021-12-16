package org.utilityclient.overlay.modules;

public class CPSThread extends Thread {

    private static int clicks;
    private static int clicks2;

    @Override
    public void run() {
        while (true) {
            clicks = 0;
            clicks2 = 0;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getClicks(boolean whichOne) {
        if (whichOne) return clicks;
        else return clicks2;
    }

    public void addClick(boolean whichOne) {
        if (whichOne) clicks++;
        else clicks2++;
    }
}
