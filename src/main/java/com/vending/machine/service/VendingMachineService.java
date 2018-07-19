package com.vending.machine.service;

import java.util.Collection;
import java.util.Collections;

import com.vending.machine.data.Coin;

/**
 * @author a.zherdetski
 *
 */
public interface VendingMachineService {
	default Collection<Coin> getOptimalChangeFor(int cents) {
		return Collections.emptyList();
	}
}
