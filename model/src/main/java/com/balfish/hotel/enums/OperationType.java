package com.balfish.hotel.enums;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */

public enum OperationType {
    PLUS {
        double eval(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        double eval(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        double eval(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        double eval(double x, double y) {
            return x / y;
        }
    };

    // Do arithmetic op represented by this constant
    abstract double eval(double x, double y);

    public static void main(String[] args) {
        System.out.println(OperationType.PLUS.eval(1.0, 2.1));

    }
}



