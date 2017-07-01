/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.Serializable;

/**
 * Message to be send between client and server
 * messageHeader: header of message (message type, order, etc)
 * messageBody: body of message (parameters, method to be invoked, etc)
 * @author Demis
 */
public class Message implements Serializable{
    private MessageHeader messageHeader;
    private MessageBody messageBody;

    public Message(MessageHeader messageHeader, MessageBody messageBody) {
        this.messageHeader = messageHeader;
        this.messageBody = messageBody;
    }

    public Message() {
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public void setMessageHeader(MessageHeader messageHeader) {
        this.messageHeader = messageHeader;
    }

    public MessageBody getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }
    
}
