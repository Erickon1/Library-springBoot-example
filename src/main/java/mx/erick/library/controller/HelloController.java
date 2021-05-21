package mx.erick.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.erick.library.service.RestClient;
import mx.erick.library.service.interfaces.HelloServiceI;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.Response;


@RestController
public class HelloController {
	
	@Autowired
	HelloServiceI helloService;
	
	@Autowired
	RestClient restClient;
	
	
	@GetMapping(value = "/{age}")
	public ResponseEntity<String> grettings(
			@PathVariable("age") int age,
			@RequestParam(value = "name", required = true) String name ) 
					throws Exception {
		return new ResponseEntity<>( helloService.grettings(name,age), HttpStatus.ACCEPTED );
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<String> client(
			@RequestParam(value = "uri", required = false) String uri) 
					throws Exception {
		
		uri = uri != null ? uri : "/posts/1";
		System.out.println(uri);
		String response = restClient.get( uri );
		System.out.println(response);
		
		if (response == null || response.isEmpty()) {
			return new ResponseEntity<>( "Error" , HttpStatus.BAD_REQUEST );
		}else {
			return new ResponseEntity<>( "Hello, World" , HttpStatus.OK );
		}
	}
	
	@GetMapping(value = "/clientHttp")
	public ResponseEntity<String> clientHttp() throws Exception{
		String response = clientCall();
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	private String clientCall() throws IOException  {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url("https://jsonplaceholder.typicode.com/comments?postId=1")
		  .get()
		  .build();
		Response response = client.newCall(request).execute();
		ResponseBody rb = response.body();
		return rb.string();
	}

}
