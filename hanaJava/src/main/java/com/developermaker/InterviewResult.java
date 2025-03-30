package com.developermaker;

import com.developermaker.entity.ScoreType;
import com.developermaker.entity.User;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InterviewResult extends JFrame {

    public void play(User user) {
        print();

        setTitle("하나금융TI 면접 결과 안내");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel outerPanel = new JPanel();
        outerPanel.setBackground(Color.GRAY);
        outerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        outerPanel.setLayout(new BorderLayout());

        JPanel titleBar = new JPanel();
        titleBar.setBackground(new Color(33, 103, 243));
        titleBar.setPreferredSize(new Dimension(600, 40));
        titleBar.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton minBtn = new JButton("–");
        JButton maxBtn = new JButton("⬜");
        JButton closeBtn = new JButton("✕");
        closeBtn.setPreferredSize(new Dimension(20, 20));
        closeBtn.setMargin(new Insets(0, 0, 0, 0));
        closeBtn.setFocusable(false);
        closeBtn.addActionListener(e -> dispose());

        JButton[] buttons = {minBtn, maxBtn, closeBtn};
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(20, 20));
            btn.setMargin(new Insets(0, 0, 0, 0));
            btn.setFocusable(false);
            titleBar.add(btn);
        }

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new BorderLayout());

        // 조건 분기로 메시지 선택
        String message;
        if (isPassed(user)) {
            message = "<html><body style='padding: 30px; font-family: Malgun Gothic;'>"
                    + "<h2 style='color:#008000;'>하나금융TI</h2>"
                    + "<h3 style='color:#199FDB; font-weight:normal;'>2025년 하나금융TI 신입사원 모집</h3>"
                    + "<h3 style='color:#199FDB;'>2차 면접 결과 안내</h3>"
                    + "<hr style='border:1px solid #199FDB;'>"
                    + "<p>안녕하세요, <b>" + user.getNickname() + "</b>님.</p>"
                    + "<p>하나금융TI 인사담당자입니다.</p>"
                    + "<p>'2025년 하나금융TI 신입사원 모집' 2차 면접 합격을 진심으로 축하드립니다.</p>"
                    + "<p style='margin-top:40px;'>- 아    래 -</p>"
                    + "</body></html>";
        } else {
            message = "<html><body style='padding: 30px; font-family: Malgun Gothic;'>"
                    + "<h2 style='color:#008000;'>하나금융TI</h2>"
                    + "<h3 style='color:#199FDB; font-weight:normal;'>2025년 하나금융TI 신입사원 모집</h3>"
                    + "<h3 style='color:#199FDB;'>2차 면접 결과 안내</h3>"
                    + "<hr style='border:1px solid #199FDB;'>"
                    + "<p>안녕하세요, <b>" + user.getNickname() + "</b>님.</p>"
                    + "<p>하나금융TI 인사담당자입니다.</p>"
                    + "<p>먼저 하나금융TI에 관심을 갖고 지원해주셔서 진심으로 감사드립니다.</p>"
                    + "<p>개발자님의 출중한 역량에도 불구하고 제한된 인원으로 인해</p>"
                    + "<p>함께하지 못하게 된 점 깊이 양해 부탁드립니다.</p>"
                    + "<p style='margin-top:40px;'>감사합니다.</p>"
                    + "</body></html>";
        }

        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        contentPanel.add(label, BorderLayout.CENTER);

        outerPanel.add(titleBar, BorderLayout.NORTH);
        outerPanel.add(contentPanel, BorderLayout.CENTER);
        add(outerPanel);

        setVisible(true);
    }

    private void print() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("🔔 띠리링 -");
        System.out.println("═".repeat(60));
        System.out.println("헉 결과 메일이잖아? 얼른 확인해야지");
    }

    private boolean isPassed(User user) {
        System.out.println(user);
        System.out.println("📊 [점수 상세 출력]");

        for (Map.Entry<ScoreType, Integer> entry : user.getScores().entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }

        int total = user.getScores()
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("총점은 " + total);

        return total >= 50;
    }

}
