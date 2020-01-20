package com.accenture.flowershop.front.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class Producer {

    @Autowired
    private ConnectionFactory connectionFactory;

    private Connection connection;
    private Session session;

    @Autowired
    @Qualifier("inQueue")
    private Destination inQueue;

    public void sendMessage(String stringXML) throws JMSException {
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(inQueue);
            Message message = session.createTextMessage(stringXML);
            producer.send(message);
        } catch (JMSException e) {

        }finally {
            connection.close();
        }
    }
}
