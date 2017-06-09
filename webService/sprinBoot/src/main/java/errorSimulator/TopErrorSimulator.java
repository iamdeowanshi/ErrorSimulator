package errorSimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class TopErrorSimulator {
	
	@Autowired
	Gson gson;
	
	private Random randomGenerator = new Random();
	private static List<ErrorModel> errors = new ArrayList<>();

	public TopErrorSimulator() {
		// TODO Auto-generated constructor stub
	}

	public static void loadTopErrors(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Scanner scanner = null;
		int index = 0;
		while ((line = reader.readLine()) != null) {
			ErrorModel error = new ErrorModel();
			List<String> tags = new ArrayList<>();
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String data = scanner.next();
				if (index == 0)
					tags.add(data);
				else if (index == 1 && data != "")
					tags.add(data);
				else if (index == 2 && data != "")
					tags.add(data);
				else if (index == 3 && data != "")
					tags.add(data);
				else if (index == 4 && data != "")
					tags.add(data);
				else if (index == 23)
					error.setId(data);
				else if (index == 26)
					error.setTitle(data);
				else if (index == 24)
					error.setLink(data);

				error.setTags(tags);
				index++;
			}
			index = 0;
			errors.add(error);
		}
		reader.close();

	}

	public String getRandomError() {
		if (!errors.isEmpty()) {
			int index = randomGenerator.nextInt(errors.size());

			if (index == 0) {
				index++;
			}
			
			Map<String, String> m = new HashMap<String, String>();
			m.put("question", errors.get(index).getTitle());

			return gson.toJson(m);
		}

		return null;
	}

	public String getSolution(String title) {
		String req_url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&intitle="+ title + "?&site=stackoverflow";

		return restTemplateImplementation(req_url);
	}

	private String restTemplateImplementation(String url) {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		Answer answer = restTemplate.getForObject(url, Answer.class);

		if (answer == null || answer.getItems().isEmpty()){
			return "ERROR OCCURED WHILE PROCESSING YOUR REQUEST, TRY AGAIN LATER";
		}
		
		Link link = new Link();
		link.setLink(answer.getItems().get(0).getLink());

		return gson.toJson(link);
	}

}
