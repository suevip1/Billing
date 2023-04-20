package com.pingpongx.smb.fee.domain.enums;

public enum FeePayer {
    OrderPayer(CurrencyType.Source),OrderPayee(CurrencyType.Target);

    CurrencyType currencyType;
    FeePayer(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }
}
