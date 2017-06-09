package errorSimulator;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class AppController {

	@Autowired
	TopErrorSimulator toperror;


	@RequestMapping("/")
	public String index() {

		return "Error simulator spring boot server";
	}

	@RequestMapping("/random_error")
	public String getRandomError() {
		
		return toperror.getRandomError();

	}

	@PostMapping("/ask")
	public String apiResponse(@RequestBody Query query) {
		
		return toperror.getSolution(query.getQuestion());
	}

}
