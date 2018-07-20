package com.vending.machine.service;

import java.util.Collection;

import com.vending.machine.data.Coin;

/**
 * @author a.zherdetski
 *
 */
public interface VendingMachineService {

	Collection<Coin> getOptimalChangeFor(int cents);
	
	Collection<Coin> getChangeFor(int cents);
}
