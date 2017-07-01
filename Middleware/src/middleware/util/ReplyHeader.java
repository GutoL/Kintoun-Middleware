/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.Serializable;

/**
 * header of reply message
 * 
 * serviceContext: tag of message (ex: if encrypted)
 * requestId: id of request
 * replyStatus: status of reply, associated if reply is OK or not
 * @author Demis
 */
public class ReplyHeader implements Serializable{

    public ReplyHeader() {
    }

    public ReplyHeader(String serviceContext, int requestId, int replyStatus) {
        this.serviceContext = serviceContext;
        this.requestId = requestId;
        this.replyStatus = replyStatus;
    }

    public String getServiceContext() {
        return serviceContext;
    }

    public void setServiceContext(String serviceContext) {
        this.serviceContext = serviceContext;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(int replyStatus) {
        this.replyStatus = replyStatus;
    }
    private String serviceContext;
    private int requestId;
    private int replyStatus;
}
