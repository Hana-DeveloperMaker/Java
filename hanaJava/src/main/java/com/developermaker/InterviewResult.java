package com.developermaker;

import com.developermaker.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InterviewResult extends JFrame {

    public void play(User user) throws InterruptedException {
        printIntro();

        setupFrame();
        JPanel outerPanel = createOuterPanel();

        // 타이틀 바 생성 및 추가
        outerPanel.add(createTitleBar(), BorderLayout.NORTH);

        // 합격 여부에 따른 메시지 처리
        boolean passed = isPassed(user);
        outerPanel.add(createContentPanel(user, passed), BorderLayout.CENTER);

        add(outerPanel);
        addWindowListener(createWindowCloseHandler(passed));

        setVisible(true);
    }

    // 초기 안내 메시지 출력
    private void printIntro() throws InterruptedException {
        print("🔔 띠리링 -");
        Thread.sleep(1000);
        System.out.println("헉 결과 메일이잖아? 얼른 확인해야지");
        Thread.sleep(2000);
    }

    // 프레임 설정
    private void setupFrame() {
        setTitle("하나금융TI 면접 결과 안내");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    // 외부 패널 생성
    private JPanel createOuterPanel() {
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.setBackground(Color.GRAY);
        outerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        return outerPanel;
    }

    // 타이틀 바 생성
    private JPanel createTitleBar() {
        JPanel titleBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        titleBar.setBackground(new Color(33, 103, 243));
        titleBar.setPreferredSize(new Dimension(600, 40));

        String[] symbols = {"–", "⬜", "✕"};
        for (String symbol : symbols) {
            JButton button = createWindowButton(symbol);
            if (symbol.equals("✕")) {
                button.addActionListener(e -> dispose());
            }
            titleBar.add(button);
        }
        return titleBar;
    }

    // 윈도우 버튼 생성 (공통 설정)
    private JButton createWindowButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(20, 20));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFocusable(false);
        return button;
    }

    // 내용 패널 생성
    private JPanel createContentPanel(User user, boolean passed) {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        String message = passed ? createPassMessage(user) : createFailMessage(user);
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        contentPanel.add(label, BorderLayout.CENTER);
        return contentPanel;
    }

    // 합격 메시지 HTML 생성
    private String createPassMessage(User user) {
        return "<html><body style='padding: 30px; font-family: Malgun Gothic;'>" + "<h2 style='color:#008000;'>하나금융TI</h2>" + "<h3 style='color:#199FDB; font-weight:normal;'>2025년 하나금융TI 신입사원 모집</h3>" + "<h3 style='color:#199FDB;'>2차 면접 결과 안내</h3>" + "<hr style='border:1px solid #199FDB;'>" + "<p>안녕하세요, <b>" + user.getNickname() + "</b>님.</p>" + "<p>하나금융TI 인사담당자입니다.</p>" + "<p>'2025년 하나금융TI 신입사원 모집' 2차 면접 <b>합격</b>을 진심으로 축하드립니다.</p>" + "<p style='margin-top:40px;'>- 아    래 -</p>" + "</body></html>";
    }

    // 불합격 메시지 HTML 생성
    private String createFailMessage(User user) {
        return "<html><body style='padding: 30px; font-family: Malgun Gothic;'>" + "<h2 style='color:#008000;'>하나금융TI</h2>" + "<h3 style='color:#199FDB; font-weight:normal;'>2025년 하나금융TI 신입사원 모집</h3>" + "<h3 style='color:#199FDB;'>2차 면접 결과 안내</h3>" + "<hr style='border:1px solid #199FDB;'>" + "<p>안녕하세요, <b>" + user.getNickname() + "</b>님.</p>" + "<p>하나금융TI 인사담당자입니다.</p>" + "<p>먼저 하나금융TI에 관심을 갖고 지원해주셔서 진심으로 감사드립니다.</p>" + "<p>개발자님의 출중한 역량에도 불구하고 제한된 인원으로 인해</p>" + "<p>함께하지 못하게 된 점 깊이 양해 부탁드립니다.</p>" + "<p style='margin-top:40px;'>감사합니다.</p>" + "</body></html>";
    }

    // 윈도우 종료 이벤트 핸들러 생성
    private WindowAdapter createWindowCloseHandler(boolean passed) {
        return new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                if (passed) {
                    print("😀 야호 합격이다!");
                } else {
                    print("😥 으으 또 떨어졌네...");
                }
            }
        };
    }

    // 합격 여부 결정 (점수 합계가 50 이상이면 합격)
    private boolean isPassed(User user) {
        return user.getScores().values().stream().mapToInt(Integer::intValue).sum() >= 50;
    }

    // 콘솔 출력 (테두리 포함)
    private void print(String message) {
        System.out.println("═".repeat(60));
        System.out.println(message);
        System.out.println("═".repeat(60));
    }
}
