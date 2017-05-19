package com.js.amoeba.exception;

public class AmoebaDaoException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2101536559772638296L;
public AmoebaDaoException(){
		
	}
	public AmoebaDaoException(String message){
		super(message);
	}
	
	public AmoebaDaoException(String message, Throwable cause) {
        super(message, cause);
    }
	 public AmoebaDaoException(Throwable cause) {
	        super(cause);
	    }
}
