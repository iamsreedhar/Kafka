package com.simple.kafka;


import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConsumerDemo {

    public static void main(String[] args) {

        System.out.println("with out logger : ");

        Logger logger = LogManager.getLogger(ConsumerDemo.class.getName());
        BasicConfigurator.configure();

        System.out.println("with logger : "+logger);
        logger.info("This is my info level log");

    }
}
