package ec.fin.emagic.administrator.controllers.REST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.fin.emagic.administrator.model.DAO.ClientDAO;
import ec.fin.emagic.administrator.model.entities.Client;
import ec.fin.emagic.administrator.services.ISaveClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Clientes")
public class ClientController {

	@Autowired
	private ISaveClient saveClient;
	
	@Autowired
	private ClientDAO clientDAO;
	
	@PostMapping("/client")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void save(@RequestBody Client client) {
		saveClient.save(client);
	}
	
	@GetMapping("/clients")
	@ApiOperation(value = "Obtener los clientes", notes = "<p>Busqueda sin filtro: http://localhost:8080/v1/clients </p> "
			+ "<p>Busqueda con filtro: http://localhost:8080/v1/clients?searchParameter=t </p>"
			+ "<br/>Filtros: <ul><li>Nombres</li> <li>Apellidos</li> <li>Num. Identificación</li> <li> Correo </li> <li> Estado Civil </li> </ul>")
	public List<Client> find(@Or({
		@Spec(path = "name", params  = "searchParameter", spec = LikeIgnoreCase.class ),
		@Spec(path = "lastname", params = "searchParameter", spec = LikeIgnoreCase.class),
		@Spec(path = "numIdentification", params = "searchParameter", spec = LikeIgnoreCase.class),
		@Spec(path = "email", params = "searchParameter", spec = LikeIgnoreCase.class),
		@Spec(path = "stateCivil", params = "searchParameter", spec = LikeIgnoreCase.class)
	}) Specification<Client> specification){
		return clientDAO.findAll(specification);
	}
}
