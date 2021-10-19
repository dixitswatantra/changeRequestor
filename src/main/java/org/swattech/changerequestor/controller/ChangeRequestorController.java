package org.swattech.changerequestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swattech.changerequestor.exception.ChangeRequestorException;
import org.swattech.changerequestor.model.CoinsTray;
import org.swattech.changerequestor.service.ChangeCalculatorService;

@RestController
@RequestMapping(path = "/api")
public class ChangeRequestorController {

	@Autowired
	private ChangeCalculatorService service;

	@GetMapping("/bill/{bill}/{minimumcoin}")
	public ResponseEntity<CoinsTray> getChange(@PathVariable int bill,@PathVariable String minimumcoin) {
		try {
			return new ResponseEntity<CoinsTray>(service.calculateChangeForABill(bill,Boolean.valueOf(minimumcoin)), HttpStatus.OK);
		} catch (ChangeRequestorException e) {
			throw new ChangeRequestorException(e.getMessage());
		}

	}

}
