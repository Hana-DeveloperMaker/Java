package com.developermaker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Start start = new Start();
        Study study = new Study();
        Alarm alarm = new Alarm();
        WakeUp wakeUp = new WakeUp();

        // 실행 메서드
        start.play(sc);
        study.play(sc);
        alarm.play(sc);
        wakeUp.play(sc);
    }
}