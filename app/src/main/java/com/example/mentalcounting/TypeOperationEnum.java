package com.example.mentalcounting;

public enum TypeOperationEnum {
    ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("×"),
    DIVIDE("÷");

    private String symbol;

    TypeOperationEnum(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}