package com.accenture.flowershop.front.ws.client;

public class WSClient {
    public static void main(String[] arg) {

        for (int i = 0; i < 1; i++) {
            try {
                int count = 10 + (int) (Math.random() * 30);
                increase(count);
                Thread.sleep( 1000L * 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void increase(int count) {
        StockWebServiceImplService service = new StockWebServiceImplService();
        StockWebService sei = service.getStockWebServiceImplPort();
        sei.increaseStockSize(count);
    }
}
