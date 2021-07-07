package org.whistle.easywechat.exception;

/**
 * @author Gentvel
 * @version 1.0.0
 */
public class MessageFormatException extends RuntimeException{

    public MessageFormatException(){
        super();
    }

    public MessageFormatException(String message){
        super(message);
    }

}
