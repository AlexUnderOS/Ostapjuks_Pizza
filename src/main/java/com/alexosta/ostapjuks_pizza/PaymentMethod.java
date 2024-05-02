package com.alexosta.ostapjuks_pizza;

public class PaymentMethod {

    private boolean moneyPaymentTypeIs;

    private PaymentMethod(boolean moneyPaymentTypeIs) {
        this.moneyPaymentTypeIs = moneyPaymentTypeIs;
    }

    public void switchMoneyPaymentType(boolean moneyPaymentTypeIs) {
        System.out.println(moneyPaymentTypeIs);
    }
}
