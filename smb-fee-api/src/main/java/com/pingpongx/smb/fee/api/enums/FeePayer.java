package com.pingpongx.smb.fee.api.enums;

import com.pingpongx.smb.fee.api.enums.CurrencyType;

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
