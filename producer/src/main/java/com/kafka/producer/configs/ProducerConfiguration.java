package com.kafka.producer.configs;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.Document;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.mongodb.client.MongoCollection;

public class ProducerConfiguration {

	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
    // initialize socket and input output streams
    private Socket socket            = null;
    private BufferedReader  input   = null;
    private BufferedWriter  out     = null;
    private MongoCollection<Document> coll = null;
    // constructor to put ip address and port
    public void Client1(String address, int port) throws JSONException
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            // takes input from terminal
            input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // sends output to the socket
            out    = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        System.out.println("Reading bytes now...");
        // string to read message from input
        String line = "";

    
        while (!line.equals("Over"))
        {
            System.out.println("Inside...");

            try
            {
            	 System.out.println("Inside here..."+line);
                line = input.readLine();
                System.out.println("Inside line..."+line);
                JSONArray array = new JSONArray(line);  

                for(int i=0; i < array.length(); i++)   
                {  
               	System.out.println("Inside line for..."+line);
                JSONObject object = array.getJSONObject(i);  
                System.out.println(object);  
                

              
                KafkaTemplate<String, String> kafkaTemplate = kafkaTemplate();
                kafkaTemplate.send("XYZ", object.toString());
            	System.out.println("sent data  ..."+object.toString());
   
                } 
              

                System.out.println(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
	/*
	 * public static void main(String args[]) throws JSONException { // Client
	 * client = new Client("52.15.210.112", 12345);
	 * 
	 * producer producer = new producer(); producer.Client1("sockets", 12345); }
	 */ 
  
	public KafkaTemplate<String,String> kafkaTemplate(){
		return new KafkaTemplate<>(producerConfig());
	}
	
	
	public ProducerFactory<String,String> producerConfig(){
		
		Map<String,Object> config = new HashMap<>();
		
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(config);
		
	}
    
}