package com.microservices.mentoring.discount.controller;

import com.microservices.mentoring.discount.service.PromocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromocodeController {

    @Autowired
    private PromocodeService promocodeService;

    @GetMapping("/promocode/{code}")
    public String getDiscountByPromocode(@PathVariable String code) {
        return promocodeService.getDiscount(code).toPlainString();
    }
}
