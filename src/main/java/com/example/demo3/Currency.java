package com.example.demo3;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Currency {
    @JsonProperty("success")
    String success;
    @JsonProperty("validationMessage")
    String validationMessage;
    @JsonProperty("result")
    String result;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }



}
        /*{"
       success":true,
        "validationMessage":[],
        "result":{
        "from":"EUR",
        "to":"KWD",
        "amountToConvert":10,
        "convertedAmount":3.3345253963609567}}
         */