package com.qishenghe.mark.util;

import com.qishenghe.mark.entity.CheckerBoard;

/**
 * 2020/10/15
 */
public class CheckerBoardBuilder {

    public static CheckerBoard createCheckerBoard () {
        int[][] board = new int[15][15];
        return new CheckerBoard(board);
    }

}
