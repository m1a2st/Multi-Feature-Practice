package com.example.annotation;

import java.math.BigDecimal;

@ExcelSheet(sheetName = "model")
public class Model {

    @ExcelField(name = "fundUuid")
    private String fundUuid;
    @ExcelField(name = "tempId")
    private String tempId;
    @ExcelField(name = "fundFileDate")
    private String fundFileDate;
    @ExcelField(name = "fundFileTime")
    private BigDecimal money;

    public String getFundUuid() {
        return fundUuid;
    }

    public void setFundUuid(String fundUuid) {
        this.fundUuid = fundUuid;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId;
    }

    public String getFundFileDate() {
        return fundFileDate;
    }

    public void setFundFileDate(String fundFileDate) {
        this.fundFileDate = fundFileDate;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
