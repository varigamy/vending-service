package com.vending.machine.data;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author a.zherdetski
 *
 */
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Coin {

	@Min(value = 1)
	private int denomination;

	private String name;
}