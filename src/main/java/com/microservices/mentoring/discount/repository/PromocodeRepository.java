package com.microservices.mentoring.discount.repository;

import com.microservices.mentoring.discount.domain.Promocode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

@Repository
public class PromocodeRepository {

    @Autowired
    private DynamoDBRepository dynamoDBRepository;

    @Retryable
    public Promocode getPromocode(String code) {
        return dynamoDBRepository.findPromocode(Promocode.class, code);
    }
}
