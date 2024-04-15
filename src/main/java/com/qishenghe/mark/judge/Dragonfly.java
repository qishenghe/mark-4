package com.qishenghe.mark.judge;

import com.qishenghe.mark.entity.CheckerBoard;
import com.qishenghe.mark.sys.SysInfo;

/**
 * 2020/10/26
 */
public class Dragonfly {

    public static int dragonfly (CheckerBoard checkerBoard) {

        if (SituationPerception.perception(checkerBoard, 5, 3, SysInfo.blackCode) > 0) {
            return SysInfo.blackCode;
        } else if (SituationPerception.perception(checkerBoard, 5, 3, SysInfo.whiteCode) > 0) {
            return SysInfo.whiteCode;
        } else {
            return 0;
        }
    }

}
