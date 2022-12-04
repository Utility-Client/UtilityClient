package org.utilityclient.debug;

import net.minecraft.client.MinecraftClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DebugFrame extends JFrame {
    public DebugFrame(DebugScreen debugScreen) {
        setTitle("Screen Editor");
        setSize(300, 100);
        setLayout(new GridLayout(2, 1));
        JButton reloadBtn = new JButton("Reload");
        reloadBtn.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {

            }
            @Override public void mousePressed(MouseEvent e) {

            }
            @Override public void mouseReleased(MouseEvent e) { debugScreen.reload(); }
            @Override public void mouseEntered(MouseEvent e) {

            }
            @Override public void mouseExited(MouseEvent e) {

            }
        });
        JButton exitBtn = new JButton("Exit screen");
        exitBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MinecraftClient.getInstance().openScreen(null);
                setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(reloadBtn);
        add(exitBtn);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);
    }
}
