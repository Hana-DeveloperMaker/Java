package com.developermaker;

import com.developermaker.entity.User;

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
        transport.play(sc, user);
        grandma.play(sc, user);
        interview.play(sc, user);
        interviewResult.play(user);
    }
}