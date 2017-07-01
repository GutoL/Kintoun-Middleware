/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middleware.util;

import java.io.Serializable;

/**
 * value of return of a reply message
 * operationResult: result of reply
 * @author Demis
 */
public class ReplyBody implements Serializable{
    private Object operationResult;

    public Object getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(Object operationResult) {
        this.operationResult = operationResult;
    }

    public ReplyBody(Object operationResult) {
        this.operationResult = operationResult;
    }
}
