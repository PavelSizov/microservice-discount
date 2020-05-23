package com.microservices.mentoring.discount.service;

import com.microservices.mentoring.discount.domain.Promocode;
import com.microservices.mentoring.discount.repository.PromocodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromocodeService {

    @Autowired
    private PromocodeRepository promocodeRepository;

    public BigDecimal getDiscount(String code) {
        Promocode promocode = promocodeRepository.getPromocode(code);
        if (promocode == null) {
            System.out.println("Promocode [" + code + "] not found");
            return BigDecimal.ZERO;
        }
        return promocode.getDiscount();
    }
}
