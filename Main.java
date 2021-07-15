

/*

Furkan Gurcay 08.07.2021

Java REST GET Simple
In the Java file, write a program to perform a GET request on the route: 
https://coderbyte.com/api/challenges/json/rest-get-simple
and then print to the console the hobbies property in the following format:
ITEM1, ITEM2, ...
Example output:
running, painting
 
*/



package com.company;
import java.util.*;
import java.io.*;
import java.net.*;

public class Main {
    // Parsing json file without any library
    public static String simpleParseArrayProperty(String json, final String propertyName) {
        if(json == null)
            return null;
        int lastIndex = json.lastIndexOf(String.format("\"%s\"", propertyName));
        json = json.substring(lastIndex);
        lastIndex = json.lastIndexOf("[");
        json = json.substring(lastIndex+1);
        return json = json
                .replaceAll("[\\]}\"]", "")
                .replaceAll("\\,", ", ")
                .trim();
    }

    public static void main(String[] args) {
        System.setProperty("http.agent", "Chrome");
        try {
            URL url = new URL("https://coderbyte.com/api/challenges/json/rest-get-simple");
            try {
                URLConnection connection = url.openConnection();
                String line="";
                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStream);
                StringBuilder apiInfo = new StringBuilder();
                while((line=bufferedReader.readLine())!= null){
                    apiInfo.append(line);
                }
                bufferedReader.close();
                // api body
                System.out.println(apiInfo.toString());
                // chosen info
                System.out.println(simpleParseArrayProperty(apiInfo.toString(),"hobbies"));
            } catch (IOException ioEx) {
                System.out.println(ioEx);
            }
        } catch (MalformedURLException malEx) {
            System.out.println(malEx);
        }
    }
}


