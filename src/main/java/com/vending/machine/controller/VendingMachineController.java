package com.vending.machine.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@Validated
@RestController
public class VendingMachineController {
	@Autowired
	private VendingMachineService vendingMachineService;

	@GetMapping("/api/change/{amountInCents}")
	@ResponseBody
	ResponseEntity<?> getChange(@PathVariable @Min(1) @Max(100000) Integer amountInCents) {
		return ResponseEntity.status(HttpStatus.OK).body(vendingMachineService.getOptimalChangeFor(amountInCents));
	}

	@GetMapping("/api/inventory-change/{amountInCents}")
	@ResponseBody
	ResponseEntity<?> getChangeInventory(@PathVariable @Min(1) @Max(100000) Integer amountInCents) {
		return ResponseEntity.status(HttpStatus.OK).body(vendingMachineService.getChangeFor(amountInCents));
	}
}
