package mx.erick.library.service;

import org.springframework.stereotype.Service;

import mx.erick.library.exeption.InvalidParamsException;
import mx.erick.library.service.interfaces.HelloServiceI;

@Service
public class HelloService implements HelloServiceI {

	@Override
	public String grettings(String name, int age) throws InvalidParamsException {
		if (name != null) {
			throw new InvalidParamsException("helloService.grettings.paramsInvalid");	
		}
		
		return  String.format("%s with age: %d;", name, age) ;
	}

}
