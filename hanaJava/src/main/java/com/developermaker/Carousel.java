package com.developermaker;
import com.developermaker.entity.Result;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import com.developermaker.entity.User;
import com.developermaker.entity.Result;
import com.developermaker.utils.JsonUtil;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

public class Carousel extends JFrame {
    private JLabel imageLabel;
    private int currentIndex = 0;
    private List<String> imagePaths;
//    private String[] imagePaths = {
//            "src/main/java/com/developermaker/images/dress.png",
//            "src/main/java/com/developermaker/images/wakeUpResult1.png",
//            "src/main/java/com/developermaker/images/img_happy.png"
//    };
    public Carousel(User user){
        imagePaths = new ArrayList<>();
        List<Result> scoreList = user.getScoreList();

        for (Result result: scoreList) {
            String imgName = result.getImgName();
//            System.out.println(result.getImgName());
            imagePaths.add("src/main/java/com/developermaker/images/" + imgName + ".png");
        }

        setTitle("나의 오늘 하루 되돌아보기🔍");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 이미지 라벨 생성
        imageLabel = new JLabel();
        updateImage();  // 첫 번째 이미지 로드
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton leftButton = new JButton("◀");
        JButton rightButton = new JButton("▶");

        // 버튼 이벤트 설정
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex - 1 + imagePaths.size()) % imagePaths.size();
                updateImage();
            }
        });

        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % imagePaths.size();
                updateImage();
            }
        });

        buttonPanel.add(leftButton);
        buttonPanel.add(rightButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(500, 500);
        setVisible(true);
    }
    private void updateImage() {
        String path = imagePaths.get(currentIndex);
        File file = new File(path);
        if (file.exists()) {
            ImageIcon originalIcon = new ImageIcon(path);

            // 이미지 리사이징
            Image scaledImage = originalIcon.getImage().getScaledInstance(
                    imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH
            );

            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            imageLabel.setText(""); // 텍스트 초기화
            imageLabel.setIcon(scaledIcon);
        } else {
            imageLabel.setText("이미지를 찾을 수 없습니다.");
            imageLabel.setIcon(null);
        }
    }

//    public void printNickname(User user) {
//        System.out.println("현재 사용자의 닉네임: " + user.getNickname());
//    }

//    public static void main(String[] args) {
//
//        Carousel frame = new Carousel();
//
//
//
//
//    }
}
