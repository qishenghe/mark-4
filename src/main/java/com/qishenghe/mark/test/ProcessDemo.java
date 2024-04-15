package com.qishenghe.mark.test;

import com.qishenghe.mark.entity.CheckerBoard;
import com.qishenghe.mark.entity.DecisionReport;
import com.qishenghe.mark.judge.Analysis;
import com.qishenghe.mark.judge.Decision;
import com.qishenghe.mark.judge.Dragonfly;
import com.qishenghe.mark.sys.SysInfo;
import com.qishenghe.mark.util.CheckerBoardBuilder;

import java.util.Scanner;

/**
 * 2020/10/26
 */
public class ProcessDemo {

    @SuppressWarnings("all")
    public void mainProcess () {

        System.out.println("press any key to continue");

        System.out.println("1. black        2. white");
        Scanner input = new Scanner(System.in);
        int selectInput = input.nextInt();

        int dragonfly = 0;
        CheckerBoard checkerBoard = CheckerBoardBuilder.createCheckerBoard();
        CheckerBoard.showCheckerBoard(checkerBoard);
        while (true) {
            if (selectInput == SysInfo.blackCode) {
                checkerBoard = dropTimeProcess(checkerBoard, 1, SysInfo.blackCode);
            } else {
                checkerBoard = dropTimeProcess(checkerBoard, 2, SysInfo.blackCode);
            }
            CheckerBoard.showCheckerBoard(checkerBoard);
            System.out.println("black : " + Analysis.analysis(checkerBoard, SysInfo.blackCode));
            System.out.println("white : " + Analysis.analysis(checkerBoard, SysInfo.whiteCode));
            dragonfly = Dragonfly.dragonfly(checkerBoard);
            if (dragonfly == SysInfo.blackCode) {
                System.out.println("Black!");
                break;
            } else if (dragonfly == SysInfo.whiteCode) {
                System.out.println("White!");
                break;
            }

            if (selectInput == SysInfo.blackCode) {
                checkerBoard = dropTimeProcess(checkerBoard, 2, SysInfo.whiteCode);
            } else {
                checkerBoard = dropTimeProcess(checkerBoard, 1, SysInfo.whiteCode);
            }
            CheckerBoard.showCheckerBoard(checkerBoard);
            System.out.println("black : " + Analysis.analysis(checkerBoard, SysInfo.blackCode));
            System.out.println("white : " + Analysis.analysis(checkerBoard, SysInfo.whiteCode));
            dragonfly = Dragonfly.dragonfly(checkerBoard);
            if (dragonfly == SysInfo.blackCode) {
                System.out.println("Black!");
                break;
            } else if (dragonfly == SysInfo.whiteCode) {
                System.out.println("White!");
                break;
            }
        }
    }

    /**
     * Drop Time Process
     * Identity : 1. P      2. E
     */
    public static CheckerBoard dropTimeProcess (CheckerBoard checkerBoard, int identity, int camp) {

        CheckerBoard newBoard = null;
        if (identity == 1) {
            System.out.println("push a point and split coordinate with '.'");
            Scanner pointInput = new Scanner(System.in);
            String pointStr = pointInput.next();
            int pointX = Integer.parseInt(pointStr.split("\\.", -1)[0]);
            int pointY = Integer.parseInt(pointStr.split("\\.", -1)[1]);

            newBoard = CheckerBoard.dropOff(checkerBoard, camp, pointX, pointY);
        } else if (identity == 2) {
            System.out.println("Mark - 4 turn");
            DecisionReport decisionReport = Decision.doDecision(checkerBoard, camp);
            int pointX = decisionReport.getPointX();
            int pointY = decisionReport.getPointY();
            System.out.println("Mark - 4 : X : " + pointX + "\tY : " + pointY + "");
            newBoard = CheckerBoard.dropOff(checkerBoard, camp, pointX, pointY);
        } else {
            new Exception("Error").printStackTrace();
        }
        return newBoard;
    }

    public static void main(String[] args) {
        new ProcessDemo().mainProcess();
    }

}
