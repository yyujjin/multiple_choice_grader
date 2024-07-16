package com.sideproject.grading.domain;

import java.util.HashMap;
import java.util.Map;

public class CorrectAnswers {
    private static Map<Integer, Integer> answers = new HashMap<Integer, Integer>() {{
        put(1, 2);
        put(2, 3);
        put(3, 1);
        put(4, 4);
        put(5, 2);
        put(6, 1);
        put(7, 3);
        put(8, 4);
        put(9, 2);
        put(10, 1);
        put(11, 3);
        put(12, 4);
        put(13, 2);
        put(14, 1);
        put(15, 3);
        put(16, 4);
        put(17, 2);
        put(18, 1);
        put(19, 3);
        put(20, 4);
        put(21, 2);
        put(22, 1);
        put(23, 3);
        put(24, 4);
        put(25, 2);
        put(26, 1);
        put(27, 3);
        put(28, 4);
        put(29, 2);
        put(30, 1);
    }};

    public static Map<Integer, Integer> getAnswers() {
        return answers;
    }
}
