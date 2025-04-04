package com.developermaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MiniGame extends JFrame implements KeyListener {
    private JLabel imageLabel;
    private int x = 100, y = 100;
    private Image backgroundImage;// 이미지 위치

    public MiniGame() {
        setTitle("방향키로 이미지 이동하기");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 절대 위치 설정

        // 사이즈 재조정할 이미지
        ImageIcon icon = new ImageIcon("src/main/resources/walking.gif");
        Image resizedImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        // 백그라운드 배경
        backgroundImage = new ImageIcon("src/main/resources/background.jpg").getImage();
        imageLabel = new JLabel(resizedIcon);
        imageLabel.setBounds(x, y, 100, 100);
//        add(imageLabel);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.add(imageLabel);

        add(backgroundPanel);
        addKeyListener(this); // 키 입력 감지
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int moveAmount = 10;

        switch (key) {
            case KeyEvent.VK_LEFT:
                x -= moveAmount;
                break;
            case KeyEvent.VK_RIGHT:
                x += moveAmount;
                break;
            case KeyEvent.VK_UP:
                y -= moveAmount;
                break;
            case KeyEvent.VK_DOWN:
                y += moveAmount;
                break;
        }

        imageLabel.setLocation(x, y);
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        MiniGame miniGame = new MiniGame();
    }
}
