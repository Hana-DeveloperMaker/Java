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

    public void play(User user) throws InterruptedException {
        printIntro();

        setupFrame();
        loadImages(user);
        if (imagePaths.isEmpty()) {
            showNoImagesMessage();
            return;
        }

        JPanel imagePanel = createImagePanel();
        JPanel buttonPanel = createButtonPanel();

        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
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
    private void loadImages(User user) {
        imagePaths = new ArrayList<>();
        List<Result> scoreList = user.getScoreList();
        for (Result result : user.getScoreList()) {
            String imgName = result.getImgName();
            System.out.println(imgName);
            System.out.println("왜안되는데;;;");
            imagePaths.add("src/main/java/com/developermaker/images/" + imgName + ".png");
        }
    }

    // 🔁 이미지 라벨과 첫 이미지 세팅
    private JPanel createImagePanel() {
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
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
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setText("");
        } else {
            imageLabel.setText("이미지를 찾을 수 없습니다.");
            imageLabel.setIcon(null);
        }
    }

    // 이미지 없을 때 처리
    private void showNoImagesMessage() {
        JOptionPane.showMessageDialog(this, "표시할 이미지가 없습니다.", "오류", JOptionPane.WARNING_MESSAGE);
    }
}
