package com.qishenghe.mark.entity;

/**
 * @author 2020/10/26
 */
public class DecisionReport {

    private long score;
    private CheckerBoard checkerBoard;
    private int camp;
    private int pointX;
    private int pointY;

    public DecisionReport(Long score, CheckerBoard checkerBoard, int camp, Integer pointX, Integer pointY) {
        this.score = score;
        this.checkerBoard = checkerBoard;
        this.camp = camp;
        this.pointX = pointX;
        this.pointY = pointY;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public CheckerBoard getCheckerBoard() {
        return checkerBoard;
    }

    public void setCheckerBoard(CheckerBoard checkerBoard) {
        this.checkerBoard = checkerBoard;
    }

    public int getCamp() {
        return camp;
    }

    public void setCamp(int camp) {
        this.camp = camp;
    }

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    @Override
    public String toString() {
        return "DecisionReport{" +
                "score=" + score +
                ", checkerBoard=" + checkerBoard +
                ", camp=" + camp +
                ", pointX=" + pointX +
                ", pointY=" + pointY +
                '}';
    }
}
