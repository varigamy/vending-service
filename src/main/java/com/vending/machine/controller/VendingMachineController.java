package com.vending.machine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vending.machine.service.VendingMachineService;

/**
 * Rest controller for handling vending-machine service API calls
 * 
 * @author a.zherdetski
 *
 */
@RestController
public class VendingMachineController {
	@Autowired
	private VendingMachineService vendingMachineService;

	@GetMapping("/api/change/{amountInCents}")
	@ResponseBody
	ResponseEntity<?> getChange(@PathVariable Integer amountInCents) {
		return ResponseEntity.status(HttpStatus.OK).body(vendingMachineService.getOptimalChangeFor(amountInCents));
	}
}
