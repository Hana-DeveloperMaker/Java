package com.developermaker;

import com.developermaker.entity.User;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        // 전반부
        Start start = new Start();
        Study study = new Study();
        Alarm alarm = new Alarm();
        WakeUp wakeUp = new WakeUp();
        Dress dress = new Dress();
        Carousel carousel = new Carousel();
        //  후반부
        // TODO : 이스터에그 구현
        Transport transport = new Transport();
        Grandma grandma = new Grandma();
        // TODO : 복장에 따른 면접 질문 생성
        Interview interview = new Interview();
        InterviewResult interviewResult = new InterviewResult();
        // 실행 메서드
        User user = start.play(sc);
        study.play(sc, user);
        alarm.play(sc, user);
        wakeUp.play(sc, user);
        dress.play(sc, user);
        SwingUtilities.invokeLater(() -> {
            DressUp dressUp = new DressUp(user);
            dressUp.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    try {
                        transport.play(sc, user);
                        grandma.play(sc, user);
                        interview.play(sc, user);
                        interviewResult.play(user);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
//                    try {
//                        interviewResult.play(user);
//                    } catch (InterruptedException ex) {
//                        throw new RuntimeException(ex);
//                    }
                    try {
                        carousel.play(user);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
//        transport.play(sc, user);
//        grandma.play(sc, user);
//        interview.play(sc, user);
//        interviewResult.play(user);
                }

//        Carousel carousel = new Carousel(user);
//        carousel.printNickname(user);
    }