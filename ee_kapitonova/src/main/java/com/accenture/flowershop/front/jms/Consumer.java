package com.accenture.flowershop.front.jms;

import com.accenture.flowershop.back.business.service.UserMarshallingService;
import com.accenture.flowershop.back.business.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.IOException;

@EnableScheduling
@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private UserMarshallingService userMarshallingService;

    @Autowired
    private UserService userService;

    private Connection connection;
    private Session session;

    @Autowired
    @Qualifier("outQueue")
    private  Destination outQueue;

    public Consumer() {
    }

    @Scheduled(fixedRate = 10000)
    public void receive() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(outQueue);
        consumer.setMessageListener(
                new MessageListener() {
                    public void onMessage(Message message) {
                        String textFromMessage = null;
                        try {
                            TextMessage textmessage = (TextMessage) message;
                            textFromMessage = textmessage.getText();
                            DiscountObject discountObject = (DiscountObject) userMarshallingService.convertStringXmlToObject(textFromMessage);
                            userService.updateDiscountForUser(discountObject.getId(),discountObject.getDiscount());
                        } catch (JMSException  e) {
                            logger.error("Consumer.recieve throw a JMSException");
                            e.printStackTrace();
                        } catch (IOException e) {
                            logger.error("Consumer.recieve throw a IOException");
                            e.printStackTrace();
                        }  finally {
                            try {
                                connection.close();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
        );
    }

}
