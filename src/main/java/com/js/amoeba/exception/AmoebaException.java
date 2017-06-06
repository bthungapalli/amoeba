package com.js.amoeba.exception;

public class AmoebaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3706384858124794415L;
public AmoebaException(){
		
	}
	public AmoebaException(String message){
		super(message);
	}
	
	public AmoebaException(String message, Throwable cause) {
        super(message, cause);
    }
	 public AmoebaException(Throwable cause) {
	        super(cause);
	    }

}
