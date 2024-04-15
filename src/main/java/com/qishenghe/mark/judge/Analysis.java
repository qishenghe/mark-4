package com.qishenghe.mark.judge;

import com.qishenghe.mark.entity.CheckerBoard;
import com.qishenghe.mark.sys.SituationWeight;

/**
 * 2020/10/15
 */
public class Analysis {


    public static long analysis (CheckerBoard checkerBoard, int camp) {

        long score = 0;
        // 1
        score += SituationPerception.perception(checkerBoard, 1, 2, camp) * SituationWeight.death_single;

        score += SituationPerception.perception(checkerBoard, 1, 1, camp) * SituationWeight.live_single;

        // 2 Death
        score += SituationPerception.perception(checkerBoard, 2, 2, camp) * SituationWeight.death_double;
        // 2 Live
        score += SituationPerception.perception(checkerBoard, 2, 1, camp) * SituationWeight.live_double;

        // 3 Death
        score += SituationPerception.perception(checkerBoard, 3, 2, camp) * SituationWeight.death_three;
        // 3 Live
        score += SituationPerception.perception(checkerBoard, 3, 1, camp) * SituationWeight.live_three;

        // 4 Death
        score += SituationPerception.perception(checkerBoard, 4, 2, camp) * SituationWeight.death_four;
        // 4 Live
        score += SituationPerception.perception(checkerBoard, 4, 1, camp) * SituationWeight.live_four;

        // 5
        score += SituationPerception.perception(checkerBoard, 5, 3, camp) * SituationWeight.five;

        return score;
    }


}
