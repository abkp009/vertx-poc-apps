package com.example.nelsdemo.designpatterns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        Single instance = Single.getInstance();
        System.out.println(instance);

        //try jackson
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String value = objectMapper.writeValueAsString(instance);
            System.out.println(value);
            Single instance2 = objectMapper.readValue(value, Single.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }

        //try clone
        try {
            instance.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }
        //try serialization
        try (OutputStream os = new FileOutputStream("single.ser");
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            //oos.defaultWriteObject();
            oos.writeObject(instance);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //try deserialization
        try (InputStream is = new FileInputStream("single.ser");
             ObjectInputStream ois = new ObjectInputStream(is)) {
            //ois.defaultReadObject();
            Object readObject = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
