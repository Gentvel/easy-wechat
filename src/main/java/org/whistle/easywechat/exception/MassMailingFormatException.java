package org.whistle.easywechat.exception;

/**
 * @author Gentvel
 * @version 1.0.0
 */
public class MassMailingFormatException extends RuntimeException{
    public MassMailingFormatException(){
            super();
    }
    public MassMailingFormatException(String message){
            super(message);
    }
}
