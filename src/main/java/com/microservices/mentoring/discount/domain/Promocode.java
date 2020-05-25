package com.microservices.mentoring.discount.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.math.BigDecimal;
import java.util.StringJoiner;

@DynamoDBTable(tableName = "${db_table}")
public class Promocode {

    @DynamoDBHashKey(attributeName = "code")
    private String code;
    @DynamoDBAttribute(attributeName = "discount")
    private BigDecimal discount;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Promocode.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("discount=" + discount)
                .toString();
    }
}
