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
        //  후반부
        Transport transport = new Transport();
        Grandma grandma = new Grandma();
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
                        boolean isEasterEgg = user.getDressCode() == 0;
                        transport.play(sc, user, isEasterEgg);
                        if (!isEasterEgg) {
                            grandma.play(sc, user);
                            interview.play(sc, user);
                        }
                        interviewResult.play(user, isEasterEgg);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
    }
}