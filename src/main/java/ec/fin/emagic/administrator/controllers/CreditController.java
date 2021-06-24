package ec.fin.emagic.administrator.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/credit")
@Api(tags = "Creditos")
public class CreditController {

	@PostMapping("/client/check")
	public Boolean checkClient(@RequestBody ClientCreditRequest request) {
		return true;
	}
}
