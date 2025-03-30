package com.developermaker;
import com.developermaker.entity.User;

import javax.swing.*;
import java.awt.*;

public class InterviewResult extends JFrame {
    public void play(User user) {
        setTitle("하나금융TI 면접 결과 안내");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null); // 화면 중앙
        setUndecorated(true); // 기본 타이틀바 제거

        // 외곽 테두리 패널 (회색)
        JPanel outerPanel = new JPanel();
        outerPanel.setBackground(Color.GRAY);
        outerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        outerPanel.setLayout(new BorderLayout());

        // 상단 파란색 타이틀 바
        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(33, 103, 243));
        titleBar.setPreferredSize(new Dimension(600, 40));
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        // 모양만 흉내낸 버튼들 (기능 없음)
        JButton minBtn = new JButton("–");
        JButton maxBtn = new JButton("⬜");
        JButton closeBtn = new JButton("✕");
        closeBtn.setPreferredSize(new Dimension(20, 20));
        closeBtn.setMargin(new Insets(0, 0, 0, 0));
        closeBtn.setFocusable(false);
        closeBtn.addActionListener(e -> dispose()); // <<<< 여기가 핵심!

        JButton[] buttons = {minBtn, maxBtn, closeBtn};
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(20, 20));
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setFocusable(false);
            titleBar.add(btn);
        }

        // 흰 배경 본문
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        // 텍스트 HTML 형식
        String message = "<html><body style='padding: 30px; font-family: Malgun Gothic;'>" +
                "<h2 style='color:#008000;'>하나금융TI</h2>" +
                "<h3 style='color:#199FDB; font-weight:normal;'>2025년 하나금융TI 신입사원 모집</h3>" +
                "<h3 style='color:#199FDB;'>2차 면접 결과 안내</h3>" +
                "<hr style='border:1px solid #199FDB;'>" +
                "<p>안녕하세요, <b>" + user.getNickname() + "</b>님.</p>" +
                "<p>하나금융TI 인사담당자입니다.</p>" +
                "<p>'2025년 하나금융TI 신입사원 모집' 2차 면접 합격을 진심으로 축하드립니다.</p>" +
                "<p style='margin-top:40px;'>- 아    래 -</p>" +
                "</body></html>";

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.LEFT);

        contentPanel.add(label, BorderLayout.CENTER);

        // 조립
        outerPanel.add(titleBar, BorderLayout.NORTH);
        outerPanel.add(contentPanel, BorderLayout.CENTER);
        add(outerPanel);

        setVisible(true);
    }

}
