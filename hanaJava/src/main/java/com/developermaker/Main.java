package com.developermaker;

import com.developermaker.entity.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Start start = new Start();
        Study study = new Study();
        Alarm alarm = new Alarm();
        WakeUp wakeUp = new WakeUp();
        Dress dress = new Dress();

        // 실행 메서드
        User user = start.play(sc);
        study.play(sc, user);
        alarm.play(sc, user);
        wakeUp.play(sc, user);
        dress.play(sc, user);

        Carousel carousel = new Carousel(user);
//        carousel.printNickname(user);
    }
}