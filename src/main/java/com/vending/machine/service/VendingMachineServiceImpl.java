package com.vending.machine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.vending.machine.config.CoinDenominationsProperties;
import com.vending.machine.data.Coin;
import com.vending.machine.exception.InsufficientCoinageException;
import com.vending.machine.exception.OperationProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a.zherdetski
 *
 */
@Service
@Slf4j
public class VendingMachineServiceImpl implements VendingMachineService {
	@Autowired
	private CoinDenominationsProperties coinDenominations;

	@Autowired
	private ApplicationContext applicationContext;

	private static Map<Integer, Coin> coinsMap;
	private static List<Integer> sortedDenominations;

	@PostConstruct
	public void initialize() {
		coinsMap = coinDenominations.getCoins().stream()
				.collect(Collectors.toMap(Coin::getDenomination, Function.identity()));

		sortedDenominations = coinsMap.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

		if (sortedDenominations.isEmpty()) {
			log.error("Denominations list is empty");
		}
	}

	@Override
	public Collection<Coin> getOptimalChangeFor(int cents) {
		if (sortedDenominations.isEmpty()) {
			throw new OperationProcessingException("Denominations are not initialized");
		}

		final Collection<Coin> coinsList = new ArrayList<>();
		int centsResidual = cents;

		int denominationIndex = 0;
		while (centsResidual > 0) {
			int denomination = sortedDenominations.get(denominationIndex);
			if (denomination <= centsResidual) {
				centsResidual = addCoinsForDenomination(coinsList, centsResidual, denomination);
			}
			denominationIndex++;
		}

		return coinsList;
	}

	private static int addCoinsForDenomination(final Collection<Coin> createdCoinsCollection, final int centsResidual,
			final int denomination) {
		int availableAmount = centsResidual;

		while (availableAmount >= denomination) {
			availableAmount -= denomination;
			createdCoinsCollection.add(coinsMap.get(denomination));
		}

		return availableAmount;
	}

	@Override //TODO
	public synchronized Collection<Coin> getChangeFor(int cents) {
		final Map<Integer, Integer> inventoryCoinsMap = new LinkedHashMap<>();
		boolean notEnoughCoins = false;
		try {
			Resource resource = applicationContext.getResource("classpath:coin-inventory.properties");
			try {
				InputStream is = resource.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));

				String line;
				int totalSum = 0;

				while ((line = br.readLine()) != null) {
					final String[] inventory = line.split("=");
					if(inventory.length == 2) {
						final Integer denomination = Integer.valueOf(inventory[0]);
						final Integer count = Integer.valueOf(inventory[1]);
						inventoryCoinsMap.put(denomination, count);
						totalSum += denomination * count;
					}
				}

				br.close();

				if (cents < totalSum) {
					notEnoughCoins = true;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			throw new OperationProcessingException("Error operating inventory");
		}
		
		if (notEnoughCoins) {
			throw new InsufficientCoinageException("Not enough coins");
		}
		return null;
	}
}
