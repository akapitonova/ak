package com.accenture.flowershop.back.business.service;

import com.accenture.flowershop.back.entity.Users;
import com.accenture.flowershop.front.jms.DiscountObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Properties;


@Service
public class UserMarshallingService {

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    @Value("classpath:conf.properties")
    private Resource propertiesfile;

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public UserMarshallingService() {
    }

    public void convertFromObjectToXML(Object object, String fileName)
            throws IOException {
        FileOutputStream os = null;
        Properties properties = new Properties();
        try{
            properties.load(propertiesfile.getInputStream());
        }catch(IOException e){
            e.printStackTrace();
        }
        String filepath1 = properties.getProperty("filepath") + fileName + ".xml";
        File file = new File(filepath1);
        os = new FileOutputStream(file, false);
        try {
            setMarshaller(JAXBContext.newInstance(Users.class).createMarshaller());
            getMarshaller().marshal(object, new StreamResult(os));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        os.close();
    }

    public Object convertFromXMLToObject(String xmlFile) throws IOException {
        FileInputStream is = null;
        is = new FileInputStream(xmlFile);
        try {
            setUnmarshaller(JAXBContext.newInstance(DiscountObject.class).createUnmarshaller());
            return getUnmarshaller().unmarshal(new StreamSource(is));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String convertFromObjectToString(Object object) throws IOException {
        StringWriter os = new StringWriter();
        try {
            setMarshaller(JAXBContext.newInstance(DiscountObject.class).createMarshaller());
            getMarshaller().marshal(object, new StreamResult(os));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return os.toString();
    }

    public Object convertStringXmlToObject(String stringXml) throws IOException {
        StringReader stringReader = new StringReader(stringXml);
        try {
            setUnmarshaller(JAXBContext.newInstance(DiscountObject.class).createUnmarshaller());
            return getUnmarshaller().unmarshal(new StreamSource(stringReader));
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
