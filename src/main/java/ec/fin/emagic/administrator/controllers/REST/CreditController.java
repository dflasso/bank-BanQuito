package ec.fin.emagic.administrator.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.fin.emagic.administrator.model.DTO.ClientCreditRequest;
import ec.fin.emagic.administrator.model.DTO.CreditReportResponse;
import ec.fin.emagic.administrator.services.ISaveCredit;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/credit")
@Api(tags = "Creditos")
public class CreditController {

	@Autowired
	private ISaveCredit saveCredit;
	
	@PostMapping
	public CreditReportResponse save(@RequestBody ClientCreditRequest request) {
		return saveCredit.trySaveAndReportered(request);
	}
	
}
