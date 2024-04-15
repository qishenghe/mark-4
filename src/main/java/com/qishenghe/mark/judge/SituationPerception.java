package com.qishenghe.mark.judge;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.qishenghe.mark.entity.CheckerBoard;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 2020/10/15
 */
public class SituationPerception {

    /**
     * All Dir Perception
     */
    public static int perception(CheckerBoard checkerBoard, int num, int mode, int camp) {
        int total = 0;

        for (int i = 1; i <= 4; i++) {
            total += perception(checkerBoard, num, i, mode, camp);
        }
        return total;
    }

    /**
     * checkerBoard     checkerBoard
     * camp             1 , black   2 , white
     * mode             1 , live    2 , death       3 , both
     */
    public static int perception(CheckerBoard checkerBoard, int num, int dir, int mode, int camp) {

        int total = 0;
        int[][] checkerBoardArray = checkerBoard.getCheckerBoardArray();
        for (int i = 0; i < checkerBoardArray.length; i++) {
            for (int j = 0; j < checkerBoardArray[i].length; j++) {
                if (checkerBoardArray[i][j] == camp) {
                    boolean flag;
                    // 连续性校验
                    flag = judgmentContinuity(checkerBoardArray, i, j, dir, num, camp);
                    if (mode == 3) {
                        if (flag) {
                            total++;
                        }
                        continue;
                    }
                    // 双端性质校验
                    if (flag) {
                        // 如果通过连续性校验，执行双端校验
                        int terminalProp = judgmentTerminalProperty(checkerBoardArray, i, j, dir, num, camp);
                        if (mode == 1) {
                            if (terminalProp == 1) {
                                total++;
                            }
                        } else if (mode == 2) {
                            if (terminalProp == 2) {
                                total++;
                            }
                        }
                    }

                }


            }
        }
        return total;
    }

    /**
     * 判断数组是否越界
     */
    private static boolean judgmentIndexOut(int[][] array, int row, int col, int offsetRow, int offsetCol) {
        boolean flag = true;
        try {
            if (array[row + offsetRow][col + offsetCol] == 0) {
                flag = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            flag = true;
        }
        return flag;
    }

    /**
     * 连续性校验
     */
    private static boolean judgmentContinuity(int[][] checkerBoardArray, int startX, int startY, int dir, int move, int camp) {
        boolean flag = true;
        for (int n = 0; n < move; n++) {
            if (dir == 1) {
                try {
                    if (checkerBoardArray[startX - n][startY + n] != camp) {
                        flag = false;
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    flag = false;
                    break;
                }
            } else if (dir == 2) {
                try {
                    if (checkerBoardArray[startX][startY + n] != camp) {
                        flag = false;
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    flag = false;
                    break;
                }
            } else if (dir == 3) {
                try {
                    if (checkerBoardArray[startX + n][startY + n] != camp) {
                        flag = false;
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    flag = false;
                    break;
                }
            } else if (dir == 4) {
                try {
                    if (checkerBoardArray[startX + n][startY] != camp) {
                        flag = false;
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 双端性质校验
     */
    private static int judgmentTerminalProperty(int[][] checkerBoardArray, int startX, int startY, int dir, int move, int camp) {
        // 双端情况记录
        // 1. 越界
        // 2. 同
        // 3. 异
        // 4. 空
        int leftProp;
        int rightProp;

        // 对各个方向情况进行双端检验
        if (dir == 1) {
            // 判断左端是否越界
            boolean leftOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, +1, -1);
            // 判断右端是否越界
            boolean rightOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, -move, +move);
            // 左端
            if (leftOutOfFlag) {
                // 越界
                leftProp = 1;
            } else {
                int point = checkerBoardArray[startX + 1][startY - 1];
                if (point == 0) {
                    // 空
                    leftProp = 4;
                } else if (point == camp) {
                    // 同
                    leftProp = 2;
                } else {
                    // 异
                    leftProp = 3;
                }
            }
            // 右端
            if (rightOutOfFlag) {
                // 越界
                rightProp = 1;
            } else {
                int point = checkerBoardArray[startX - move][startY + move];
                if (point == 0) {
                    // 空
                    rightProp = 4;
                } else if (point == camp) {
                    // 同
                    rightProp = 2;
                } else {
                    // 异
                    rightProp = 3;
                }
            }

        } else if (dir == 2) {
            // 判断左端是否越界
            boolean leftOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, 0, -1);
            // 判断右端是否越界
            boolean rightOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, 0, move);
            // 左端
            if (leftOutOfFlag) {
                // 越界
                leftProp = 1;
            } else {
                int point = checkerBoardArray[startX][startY - 1];
                if (point == 0) {
                    // 空
                    leftProp = 4;
                } else if (point == camp) {
                    // 同
                    leftProp = 2;
                } else {
                    // 异
                    leftProp = 3;
                }
            }
            // 右端
            if (rightOutOfFlag) {
                // 越界
                rightProp = 1;
            } else {
                int point = checkerBoardArray[startX][startY + move];
                if (point == 0) {
                    // 空
                    rightProp = 4;
                } else if (point == camp) {
                    // 同
                    rightProp = 2;
                } else {
                    // 异
                    rightProp = 3;
                }
            }

        } else if (dir == 3) {
            // 判断左端是否越界
            boolean leftOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, -1, -1);
            // 判断右端是否越界
            boolean rightOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, move, move);
            // 左端
            if (leftOutOfFlag) {
                // 越界
                leftProp = 1;
            } else {
                int point = checkerBoardArray[startX - 1][startY - 1];
                if (point == 0) {
                    // 空
                    leftProp = 4;
                } else if (point == camp) {
                    // 同
                    leftProp = 2;
                } else {
                    // 异
                    leftProp = 3;
                }
            }
            // 右端
            if (rightOutOfFlag) {
                // 越界
                rightProp = 1;
            } else {
                int point = checkerBoardArray[startX + move][startY + move];
                if (point == 0) {
                    // 空
                    rightProp = 4;
                } else if (point == camp) {
                    // 同
                    rightProp = 2;
                } else {
                    // 异
                    rightProp = 3;
                }
            }

        } else {
            // 判断左端是否越界
            boolean leftOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, -1, 0);
            // 判断右端是否越界
            boolean rightOutOfFlag = judgmentIndexOut(checkerBoardArray, startX, startY, move, 0);
            // 左端
            if (leftOutOfFlag) {
                // 越界
                leftProp = 1;
            } else {
                int point = checkerBoardArray[startX - 1][startY];
                if (point == 0) {
                    // 空
                    leftProp = 4;
                } else if (point == camp) {
                    // 同
                    leftProp = 2;
                } else {
                    // 异
                    leftProp = 3;
                }
            }
            // 右端
            if (rightOutOfFlag) {
                // 越界
                rightProp = 1;
            } else {
                int point = checkerBoardArray[startX + move][startY];
                if (point == 0) {
                    // 空
                    rightProp = 4;
                } else if (point == camp) {
                    // 同
                    rightProp = 2;
                } else {
                    // 异
                    rightProp = 3;
                }
            }

        }

        // 总计
        // 1. live
        // 2. death
        // 3. null
        if (leftProp == 1) {
            if (rightProp == 1) {
                return 3;
            } else if (rightProp == 2) {
                return 3;
            } else if (rightProp == 3) {
                return 3;
            } else {
                return 2;
            }
        } else if (leftProp == 2) {
            return 3;
        } else if (leftProp == 3) {
            if (rightProp == 1) {
                return 3;
            } else if (rightProp == 2) {
                return 3;
            } else if (rightProp == 3) {
                return 3;
            } else {
                return 2;
            }
        } else {
            if (rightProp == 1) {
                return 2;
            } else if (rightProp == 2) {
                return 3;
            } else if (rightProp == 3) {
                return 2;
            } else {
                return 1;
            }
        }
    }

}
