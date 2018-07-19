package com.vending.machine.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.vending.machine.data.Coin;
import com.vending.machine.exception.OperationProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author a.zherdetski
 *
 */
@Service
@Slf4j
public class VendingMachineServiceImpl implements VendingMachineService {
	private static final Map<Integer, Coin> coinsMap = new HashMap<>();
	private static List<Integer> sortedDenominations = null;

	@PostConstruct
	public void initialize() {
		//TODO Out it in a properties or yaml file
		coinsMap.put(100, new Coin(100, "One Euro"));
		coinsMap.put(50, new Coin(50, "Fifty cents"));
		coinsMap.put(20, new Coin(20, "Twenty cents"));
		coinsMap.put(10, new Coin(10, "Ten cents"));
		coinsMap.put(5, new Coin(5, "Five cents"));
		coinsMap.put(2, new Coin(2, "Two cents"));
		coinsMap.put(1, new Coin(1, "One cent"));

		sortedDenominations = coinsMap.keySet()
				.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());

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
			centsResidual = addCoinsForDenomination(coinsList, centsResidual, denomination);
			denominationIndex++;
		}
		
		return coinsList;
	}

	private static int addCoinsForDenomination(final Collection<Coin> createdCoinsCollection, final int centsResidual, final int denomination) {
		int availableAmount = centsResidual;

		while (availableAmount >= denomination) {
			availableAmount -= denomination;
			createdCoinsCollection.add(coinsMap.get(denomination));
		}

		return availableAmount;
	}
}
