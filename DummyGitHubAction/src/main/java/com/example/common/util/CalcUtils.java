package com.example.common.util;

import static java.lang.Math.*;

public class CalcUtils {

    public static double Round(double nr, int decimals) {
        return round(nr * pow(10, decimals)) / pow(10, decimals);
    }

}
