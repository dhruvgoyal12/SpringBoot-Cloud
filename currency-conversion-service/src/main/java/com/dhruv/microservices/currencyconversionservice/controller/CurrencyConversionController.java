package com.dhruv.microservices.currencyconversionservice.controller;

import com.dhruv.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity){
        return new CurrencyConversion(10001L, from, to, quantity, BigDecimal.ONE, BigDecimal.ONE,"");
    }
}