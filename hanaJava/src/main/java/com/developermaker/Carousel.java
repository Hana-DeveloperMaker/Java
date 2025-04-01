package com.developermaker;

import com.developermaker.entity.Result;
import com.developermaker.entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Carousel extends JFrame {
    private JLabel imageLabel;
    private int currentIndex = 0;
    private List<String> imagePaths;

    public Carousel(User user) {
        imagePaths = new ArrayList<>();
        List<Result> scoreList = user.getScoreList();

        for (Result result : scoreList) {
            String imgName = result.getImgName();
            imagePaths.add("src/main/java/com/developermaker/images/" + imgName + ".png");
        }

        setTitle("나의 오늘 하루 되돌아보기🔍");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 이미지 라벨 생성
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 400)); // 기본 크기 설정
        add(imageLabel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton leftButton = new JButton("◀");
        JButton rightButton = new JButton("▶");

        // 버튼 이벤트 설정
        leftButton.addActionListener((ActionEvent e) -> {
            if (!imagePaths.isEmpty()) {
                currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
                updateImage();
            }
        });

        rightButton.addActionListener((ActionEvent e) -> {
            if (!imagePaths.isEmpty()) {
                currentIndex = (currentIndex + 1) % imagePaths.size();
                updateImage();
            }
        });

        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setVisible(true);

        // 이미지 로드는 UI가 그려진 이후 실행
        SwingUtilities.invokeLater(this::updateImage);
    }

    private void updateImage() {
        if (imagePaths.isEmpty()) {
            imageLabel.setText("보여줄 이미지가 없습니다.");
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
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    width, height, Image.SCALE_SMOOTH
            );

            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel.setText(""); // 텍스트 초기화
            imageLabel.setIcon(scaledIcon);
        } else {
            imageLabel.setText("이미지를 찾을 수 없습니다.");
            imageLabel.setIcon(null);
        }
    }

    public void printNickname(User user) {
        System.out.println("현재 사용자의 닉네임: " + user.getNickname());
    }
}
