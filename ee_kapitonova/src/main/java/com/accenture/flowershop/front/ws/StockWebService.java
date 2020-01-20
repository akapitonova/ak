
package com.accenture.flowershop.front.ws;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface StockWebService {
    @WebResult
     public void increaseStockSize(
            @WebParam(name = "request") int count);

}
