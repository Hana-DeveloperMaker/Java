package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import java.util.List;

public class Carousel extends JFrame {
    private JLabel imageLabel;
    private int currentIndex = 0;
    private List<String> imagePaths;

    public void play(User user, boolean isEnding) throws InterruptedException {
        printIntro();

        setupFrame();
        loadImages(user, isEnding);
        if (imagePaths.isEmpty()) {
            showNoImagesMessage();
            return;
        }

        JPanel imagePanel = createImagePanel();
        JPanel buttonPanel = createButtonPanel();

        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
        SwingUtilities.invokeLater(this::updateImage);
    }

    // 🧾 초기 인트로 메시지 출력
    private void printIntro() throws InterruptedException {
        System.out.println("🌄 당신의 하루를 이미지로 되돌아봅니다...");
        Thread.sleep(1500);
    }

    // 🪟 프레임 설정
    private void setupFrame() {
        setTitle("나의 오늘 하루 되돌아보기🔍");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    // 🖼 이미지 리스트 생성
    private void loadImages(User user, boolean isEnding) {
        imagePaths = new ArrayList<>();
        for (Result result : user.getScoreList()) {
            String imgName = result.getImgName();
            if (!isEnding && imgName.equals("outfit"))
                continue;
            imagePaths.add("src/main/resources/" + result.getImgName() + ".png");
        }
    }

    // 🔁 이미지 라벨과 첫 이미지 세팅
    private JPanel createImagePanel() {
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 400));
        SwingUtilities.invokeLater(() -> updateImage());
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(imageLabel, BorderLayout.CENTER);
        return panel;
    }

    // ◀▶ 버튼 생성
    private JPanel createButtonPanel() {
        JButton leftButton = new JButton("◀");
        JButton rightButton = new JButton("▶");

        ActionListener leftAction = e -> {
            currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
            updateImage();
        };
        ActionListener rightAction = e -> {
            currentIndex = (currentIndex + 1) % imagePaths.size();
            updateImage();
        };

        leftButton.addActionListener(leftAction);
        rightButton.addActionListener(rightAction);

        JPanel panel = new JPanel();
        panel.add(leftButton);
        panel.add(rightButton);
        return panel;
    }

    // 이미지 업데이트
    private void updateImage() {
        if (imagePaths == null || imagePaths.isEmpty()) {
            imageLabel.setText("이미지를 불러올 수 없습니다.");
            imageLabel.setIcon(null);
            return;
        }

        String path = imagePaths.get(currentIndex);
        File file = new File(path);
        if (file.exists()) {
            ImageIcon originalIcon = new ImageIcon(path);

            int width = imageLabel.getWidth();
            int height = imageLabel.getHeight();
            if (width <= 0 || height <= 0) {
                width = 400;
                height = 400;
            }

            // 이미지 리사이징
            Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel.setText(""); // 텍스트 초기화
            imageLabel.setIcon(scaledIcon);
        } else {
            imageLabel.setText("이미지를 찾을 수 없습니다.");
            imageLabel.setIcon(null);
        }
    }

    // 이미지 없을 때 처리
    private void showNoImagesMessage() {
        JOptionPane.showMessageDialog(this, "표시할 이미지가 없습니다.", "오류", JOptionPane.WARNING_MESSAGE);
    }
    public void printNickname(User user) {
        System.out.println("현재 사용자의 닉네임: " + user.getNickname());
    }
}
