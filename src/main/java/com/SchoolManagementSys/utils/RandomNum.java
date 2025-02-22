package com.SchoolManagementSys.utils;

import java.util.Random;

public class RandomNum
{
    public static Integer getNumber() {
        return new Random().nextInt(100);
    }
}
