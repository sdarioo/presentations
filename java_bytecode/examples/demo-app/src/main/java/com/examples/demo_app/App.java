package com.examples.demo_app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Current time: " + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) );
    }
}
