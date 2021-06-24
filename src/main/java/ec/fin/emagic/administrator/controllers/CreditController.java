package ec.fin.emagic.administrator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.DTO.ClientCreditResponse;
import ec.fin.emagic.administrator.services.imp.CredictCheckService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/v1/credit")
@Api(tags = "Creditos")
public class CreditController {

	@Autowired
	private CredictCheckService creditCheck;
	
	@PostMapping("/client/check")
	public Boolean checkClient(@RequestBody ClientCreditRequest request) {
		return creditCheck.check(request).getSuccess();
	}
	
	@PostMapping("/client/check/details")
	public ClientCreditResponse checkClientDetails(@RequestBody ClientCreditRequest request) {
		return creditCheck.check(request);
	}
	
	@PostMapping("/client/check/validated")
	public ClientCreditResponse checkClientValidated(@RequestBody ClientCreditRequest request) {
		return creditCheck.validate(request);
	}
	
	@GetMapping("/client/check/requirements/{idClient}/by-id")
	public ClientCreditResponse checkClientValidated(@PathVariable Long idClient) {
		return creditCheck.getInfo(idClient);
	}
}
