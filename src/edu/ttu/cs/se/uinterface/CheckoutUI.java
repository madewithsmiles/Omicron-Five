package edu.ttu.cs.se.uinterface;

public class CheckoutUI {
    enum DisplayCode {
        WELCOME,
        NORMAL,
        PAYMENT,
        CONFIRMATION,
        CANCEL;
    }

    public static void display(DisplayCode code) {
        switch (code) {
            case NORMAL:break;
            case WELCOME:break;
            case PAYMENT:break;
            case CANCEL:break;
            case CONFIRMATION:break;
        }
    }
}
