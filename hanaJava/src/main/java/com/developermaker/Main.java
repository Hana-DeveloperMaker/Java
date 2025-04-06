package com.developermaker;

import com.developermaker.entity.User;
import com.developermaker.utils.JsonUtil;

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
        alarm.play(sc, JsonUtil.loadUserByNickname(user.getNickname()));
        wakeUp.play(sc, JsonUtil.loadUserByNickname(user.getNickname()));
        dress.play(sc, JsonUtil.loadUserByNickname(user.getNickname()));
        SwingUtilities.invokeLater(() -> {
            DressUp dressUp = null;
            try {
                dressUp = new DressUp(JsonUtil.loadUserByNickname(user.getNickname()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dressUp.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    try {
                        boolean isEasterEgg = JsonUtil.loadUserByNickname(user.getNickname()).getDressCode() == 0;
                        transport.play(sc, JsonUtil.loadUserByNickname(user.getNickname()), isEasterEgg);
                        if (!isEasterEgg) {
                            grandma.play(sc, JsonUtil.loadUserByNickname(user.getNickname()));
                            interview.play(sc, JsonUtil.loadUserByNickname(user.getNickname()));
                        }
                        interviewResult.play(JsonUtil.loadUserByNickname(user.getNickname()), isEasterEgg);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
    }
}