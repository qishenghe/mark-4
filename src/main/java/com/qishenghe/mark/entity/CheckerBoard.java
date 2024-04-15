package com.qishenghe.mark.entity;

import com.qishenghe.mark.sys.SysInfo;
import com.qishenghe.mark.util.DoubleArrayDeepClone;

/**
 * 2020/10/15
 */
public class CheckerBoard {

    private int[][] checkerBoardArray;

    public CheckerBoard (int[][] checkerBoardArray) {
        this.checkerBoardArray = checkerBoardArray;
    }

    public int[][] getCheckerBoardArray() {
        return checkerBoardArray;
    }

    public void setCheckerBoardArray(int[][] checkerBoardArray) {
        this.checkerBoardArray = checkerBoardArray;
    }

    /**
     * Print CheckerBoard
     */
    public synchronized static void showCheckerBoard (CheckerBoard checkerBoard) {
        System.out.print(SysInfo.space + SysInfo.interval);
        for (int i = 0; i < 15; i ++) {
            System.out.print(i + SysInfo.interval);
        }
        // 换行
        System.out.println();

        int[][] array = checkerBoard.getCheckerBoardArray();

        for (int i = 0; i < array.length; i ++) {
            System.out.print(i + SysInfo.interval);
            for (int j = 0; j < array[i].length; j ++) {
                if (array[i][j] == 0) {
                    System.out.print(SysInfo.space + SysInfo.interval);
                } else if (array[i][j] == 1) {
                    System.out.print(SysInfo.black + SysInfo.interval);
                } else if (array[i][j] == 2) {
                    System.out.print(SysInfo.white + SysInfo.interval);
                }
            }
            System.out.println();
        }
    }

    /**
     * fall drop
     */
    public static CheckerBoard dropOff (CheckerBoard checkerBoard, int camp, int pointX, int pointY) {

        int[][] array = checkerBoard.getCheckerBoardArray();

        int[][] newArray = DoubleArrayDeepClone.function(array);
        newArray[pointX][pointY] = camp;

        return new CheckerBoard(newArray);
    }

    /**
     * Test target camp member num
     */
    public static int getTargetCampMemberNum (CheckerBoard checkerBoard, int targetCamp) {

        int num = 0;
        int[][] array = checkerBoard.getCheckerBoardArray();

        for (int i = 0; i < array.length; i ++)
            for (int j = 0; j < array[i].length; j ++)
                if (array[i][j] == targetCamp) num ++;
        return num;
    }


}
