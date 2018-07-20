package com.vending.machine.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.vending.machine.data.Coin;

import lombok.Getter;
import lombok.Setter;

/**
 * @author a.zherdetski
 *
 */
@Component
@PropertySource("classpath:coin-denominations.properties")
@ConfigurationProperties
@Setter
@Getter
public class CoinDenominationsProperties {
    private List<Coin> coins = new ArrayList<>();
}