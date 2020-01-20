package com.accenture.flowershop.front.ws;

import com.accenture.flowershop.back.business.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;

@WebService(endpointInterface = "com.accenture.flowershop.front.ws.StockWebService")
public class StockWebServiceImpl implements StockWebService {
/*    @Override
    public void increaseStockSize(int count) {

    }*/
    @Autowired
    private ProductService productService;
    @Override
    public void increaseStockSize(int count) {
        productService.increaseStockSize(count);
    }
}
