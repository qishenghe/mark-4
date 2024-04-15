package com.qishenghe.mark.sys;

/**
 * 2020/10/23
 */
public class SysInfo {

    /**
     * 制表符
     */
    public static final String interval = "\t";

    /**
     * Black
     */
    public static final String black = "●";         // ●



    /**
     * White
     */
    public static final String white = "○";         // ○

    /**
     * Space
     */
    public static final String space = " . ";         // ➕

    /**
     * Space code
     */
    public static final int spaceCode = 0;

    /**
     * Black code
     */
    public static final int blackCode = 1;

    /**
     * White code
     */
    public static final int whiteCode = 2;

    /**
     * Camp Negation
     */
    public static int campNegation (int camp) {
        if (camp == SysInfo.blackCode) {
            return SysInfo.whiteCode;
        } else {
            return SysInfo.blackCode;
        }
    }

}
