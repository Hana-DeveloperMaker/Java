package com.developermaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MiniGame extends JFrame implements KeyListener {
    private JLabel imageLabel;
    private int x = 100, y = 100;
    private Image backgroundImage;// 이미지 위치

    private void addGameObjects(JPanel backgroundPanel) {
        addObject(backgroundPanel, "/bed.png", 240, 410, 60, 60);
        addObject(backgroundPanel, "/building.png", 980, 110, 60, 60);
        addObject(backgroundPanel, "/bus.png", 820, 160, 60, 30); // 상단 버스
        addObject(backgroundPanel, "/bus.png", 820, 200, 60, 30); // 하단 버스
        addObject(backgroundPanel, "/closet.png", 140, 550, 60, 60);
        addObject(backgroundPanel, "/grandma.png", 510, 450, 70, 70);
        addObject(backgroundPanel, "/house.png", 20, 40, 60, 60);
        addObject(backgroundPanel, "/person.png", 990, 60, 60, 60);
    }

    private void addObject(JPanel panel, String path, int x, int y, int w, int h) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(x, y, w, h);
        panel.add(label);
    }


    public MiniGame() {
        setTitle("방향키로 이미지 이동하기");
        setSize(1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 절대 위치 설정

        // 사이즈 재조정할 이미지
        ImageIcon icon = new ImageIcon("src/main/resources/walking.gif");
        // 백그라운드 배경
        backgroundImage = new ImageIcon("src/main/resources/background.jpg").getImage();
        imageLabel = new JLabel(icon);
        imageLabel.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.add(imageLabel);
        addGameObjects(backgroundPanel);

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
