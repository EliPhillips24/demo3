package com.example.demo3;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Currency2 {
    @JsonProperty("name")
    String name;
    @JsonProperty("Symbol")
    String Symbol;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }
}
