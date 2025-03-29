package com.developermaker;
import javax.swing.*;
import java.awt.*;

public class Carousel extends JFrame {
    public Carousel(){
        setTitle("나의 오늘 하루 되돌아보기🔍");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 프레임 종료버튼 사용 시 응용 프로그램도 종료시키는 방법

        // 컨텐트팬 알아내기
        Container contentPane = getContentPane();
        // 컨텐트팬 배경색 설정
        contentPane.setBackground(Color.YELLOW);
        // 컨턴트팬 레이아웃 설정
        contentPane.setLayout(new FlowLayout());

        // 프레임에 JButton 컴포넌트 추가하기
        contentPane.add(new JButton("Java"));
        contentPane.add(new JButton("Python"));
        contentPane.add(new JButton("C"));
        ImageIcon icon = new ImageIcon("src/main/java/com/developermaker/images/dress.png");
        JLabel lb1 = new JLabel(icon);
        add(lb1);

        setSize(500, 500); // 프레임의 크기를 설정
        setVisible(true);
    }

    public static void main(String[] args) {
        Carousel frame = new Carousel();
    }
}
