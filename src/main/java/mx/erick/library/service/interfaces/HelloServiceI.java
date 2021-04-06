package mx.erick.library.service.interfaces;

import mx.erick.library.exeption.InvalidParamsException;

public interface HelloServiceI {

	public String grettings(String name, int age) throws InvalidParamsException;
	
}
