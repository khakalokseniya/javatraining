package exceptions;

import java.util.Properties;

public class KeyNotFoundException extends Exception{
	public KeyNotFoundException(){
	}
	public KeyNotFoundException(String message, Throwable exception){
		super(message, exception);
	}
	public KeyNotFoundException(String message){
		super(message);
	}
	public KeyNotFoundException(Throwable exception){
		super(exception);
	}
	

}
