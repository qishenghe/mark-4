package com.qishenghe.mark.judge;

import com.qishenghe.mark.entity.CheckerBoard;
import com.qishenghe.mark.entity.DecisionReport;
import com.qishenghe.mark.sys.SysInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 2020/10/26
 */
public class Decision {

    /**
     * Mark - 4     Decision
     */
    public static DecisionReport doDecision (CheckerBoard checkerBoard, int selfCamp) {
        return decisionV2(checkerBoard, selfCamp);
    }

    /**
     * Version 1 : prototype
     */
    public static DecisionReport decisionV1 (CheckerBoard checkerBoard, int selfCamp) {

        int[][] array = checkerBoard.getCheckerBoardArray();

        long maxScore = 0;
        int decisionX = 0;
        int decisionY = 0;

        if (CheckerBoard.getTargetCampMemberNum(checkerBoard, selfCamp) == 0 && CheckerBoard.getTargetCampMemberNum(checkerBoard, SysInfo.campNegation(selfCamp)) == 0) {
            decisionX = 7;
            decisionY = 7;
        } else {
            for (int i = 0; i <array.length; i ++) {
                for (int j = 0; j < array[i].length; j ++) {

                    if (array[i][j] != SysInfo.spaceCode) {
                        continue;
                    }

                    CheckerBoard singleProbably = CheckerBoard.dropOff(checkerBoard, selfCamp, i, j);
                    long singleScore = Analysis.analysis(singleProbably, selfCamp);
                    if (singleScore > maxScore) {
                        maxScore = singleScore;
                        decisionX = i;
                        decisionY = j;
                    }
                }
            }
        }
        return new DecisionReport(maxScore, checkerBoard, selfCamp, decisionX, decisionY);
    }

    /**
     * Version 2 : analytic comparison
     */
    public static DecisionReport decisionV2 (CheckerBoard checkerBoard, int selfCamp) {

        int[][] array = checkerBoard.getCheckerBoardArray();

        /*Long maxScore = null;
        Integer decisionX = null;
        Integer decisionY = null;*/

        if (CheckerBoard.getTargetCampMemberNum(checkerBoard, selfCamp) == 0 && CheckerBoard.getTargetCampMemberNum(checkerBoard, SysInfo.campNegation(selfCamp)) == 0) {
            return new DecisionReport(0L, checkerBoard, selfCamp, 7, 7);
            /*maxScore = 0L;
            decisionX = 7;
            decisionY = 7;*/
        } else {
            List<DecisionReport> reports = new ArrayList<>();
            for (int i = 0; i <array.length; i ++) {
                for (int j = 0; j < array[i].length; j ++) {
                    if (array[i][j] != SysInfo.spaceCode) {
                        continue;
                    }

                    CheckerBoard singleProbably = CheckerBoard.dropOff(checkerBoard, selfCamp, i, j);

                    DecisionReport enemyReport = decisionV1(singleProbably, SysInfo.campNegation(selfCamp));
                    CheckerBoard afterEnemyDrop = CheckerBoard.dropOff(singleProbably, SysInfo.campNegation(selfCamp), enemyReport.getPointX(), enemyReport.getPointY());


                    long singleSelfScore = Analysis.analysis(afterEnemyDrop, selfCamp);
                    long singleEnemyScore = Analysis.analysis(afterEnemyDrop, SysInfo.campNegation(selfCamp));
                    long singleScore = singleSelfScore - singleEnemyScore;

                    DecisionReport singleReport = new DecisionReport(singleScore, singleProbably, selfCamp, i, j);
                    reports.add(singleReport);

                    /*if (maxScore == null) {
                        maxScore = singleScore;
                        decisionX = i;
                        decisionY = j;
                    } else {
                        if (singleScore > maxScore) {
                            maxScore = singleScore;
                            decisionX = i;
                            decisionY = j;
                        }
                    }*/
                }
            }

            // Find the best from list
            return Decision.getBestSolution(reports);

        }
//        return new DecisionReport(maxScore, checkerBoard, selfCamp, decisionX, decisionY);
    }

    /**
     * Find best solution from list
     */
    public static DecisionReport getBestSolution (List<DecisionReport> reports) {

        // 找到最大值
        Long maxScore = null;

        for (DecisionReport singleReport : reports) {
            if (maxScore == null) {
                maxScore = singleReport.getScore();
            } else if (singleReport.getScore() > maxScore) {
                maxScore = singleReport.getScore();
            }
        }

        Integer minDistance = null;
        DecisionReport decisionReport = null;
        for (DecisionReport singleReport : reports) {
            if (singleReport.getScore() == maxScore) {
                int singleDis = Decision.getDistance(singleReport);
                if (minDistance == null) {
                    minDistance = singleDis;
                    decisionReport = singleReport;
                } else if (singleDis < minDistance) {
                    minDistance = singleDis;
                    decisionReport = singleReport;
                }
            }
        }
        return decisionReport;
    }

    /**
     * Get distance
     */
    public static Integer getDistance (DecisionReport report) {

        CheckerBoard checkerBoard = report.getCheckerBoard();
        int camp = report.getCamp();
        int pointX = report.getPointX();
        int pointY = report.getPointY();

        Integer minDistance = null;
        int[][] array = checkerBoard.getCheckerBoardArray();
        for (int i = 0; i < array.length; i ++) {
            for (int j = 0; j < array[i].length; j ++) {
                if (array[i][j] == SysInfo.campNegation(camp)) {
                    int singleDis = new Double(Math.pow(pointX - i, 2) + Math.pow(pointY - j, 2)).intValue();
                    if (minDistance == null) {
                        minDistance = singleDis;
                    } else if (singleDis < minDistance) {
                        minDistance = singleDis;
                    }
                }
            }
        }
        return minDistance;
    }

}
