package com.proathlete.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proathlete.model.Greeting;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

public class GreetingClientTest {

    private HttpClient httpClient = mock(HttpClient.class);
    private GreetingClientImpl greetingClient;


    @Test
    public void save_updates_and_serializes() throws JsonProcessingException {


        greetingClient = new GreetingClientImpl(httpClient);
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");

        ObjectMapper mapper = new ObjectMapper();

        try {
            when(httpClient.execute(any(HttpPost.class), any(BasicResponseHandler.class))).thenReturn(mapper.writeValueAsString(greeting));
            Greeting greeting1 = greetingClient.save(greeting);
            assertNotNull(greeting1);
            //Compare values
            assertEquals(greeting.getGreeting(), greeting1.getGreeting());
            verify(httpClient).execute(any(HttpPost.class), any(BasicResponseHandler.class));

        } catch (Exception ex) {
            //Ignore
        }

    }


    @Test
    public void fetches_and_deserializes() {
        greetingClient = new GreetingClientImpl(httpClient);
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");

        ObjectMapper mapper = new ObjectMapper();


        try {
            when(httpClient.execute(any(HttpGet.class), any(BasicResponseHandler.class))).thenReturn(mapper.writeValueAsString(greeting));
            Greeting greeting1 = greetingClient.getGreeting();
            assertNotNull(greeting1);
            verify(httpClient).execute(any(HttpGet.class), any(BasicResponseHandler.class));

        } catch (Exception ex) {
            //Ignore
        }
    }

    @Test
    public void deletes_entities() throws JsonProcessingException {


        greetingClient = new GreetingClientImpl(httpClient);
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");

        ObjectMapper mapper = new ObjectMapper();

        try {
            HttpResponse response = mock(HttpResponse.class);
            StatusLine line = mock(StatusLine.class);
            when(line.getStatusCode()).thenReturn(200);
            when(response.getStatusLine()).thenReturn(line);

            when(httpClient.execute(any(HttpDelete.class))).thenReturn(response);
            Boolean retVal = greetingClient.delete(greeting);
            assertTrue(retVal);
            verify(httpClient).execute(any(HttpDelete.class));

            //Deals with non 200 code
            when(line.getStatusCode()).thenReturn(404);
            when(httpClient.execute(any(HttpDelete.class))).thenReturn(response);
            retVal = greetingClient.delete(greeting);
            assertFalse(retVal);
            verify(httpClient,times(2)).execute(any(HttpDelete.class));



        } catch (Exception ex) {
            //Ignore
        }
    }

    @Test
    public void deletes_entities_handles_exception() throws JsonProcessingException {


        greetingClient = new GreetingClientImpl(httpClient);
        Greeting greeting = new Greeting();
        greeting.setGreeting("Hello!");

        try {
            when(httpClient.execute(any(HttpDelete.class))).thenThrow(new IOException("Bla"));
            Boolean retVal = greetingClient.delete(greeting);
            assertFalse(retVal);
            verify(httpClient).execute(any(HttpDelete.class));

        } catch (Exception ex) {
            //Ignore
        }
    }


    @Test
    public void getResponseAsString_makes_http_calls_handles_IOException() {
        HttpGet getRequest = mock(HttpGet.class);
        try {
            when(httpClient.execute(any(HttpGet.class), any(BasicResponseHandler.class))).thenThrow(new IOException("error"));
            greetingClient = new GreetingClientImpl(httpClient);
            String val = greetingClient.getResponseAsString(getRequest);

            assertEquals("", val);

        } catch (Exception ex) {
            //Ignore
        }

    }

    @Test
    public void getResponseAsString_makes_http_calls_handles_HTTPResponseException() {
        HttpGet getRequest = mock(HttpGet.class);
        try {
            when(httpClient.execute(any(HttpGet.class), any(BasicResponseHandler.class))).thenThrow(new HttpResponseException(500, "Error"));
            greetingClient = new GreetingClientImpl(httpClient);

            String val = greetingClient.getResponseAsString(getRequest);

            assertEquals("", val);
        } catch (Exception ex) {
            //Ignore
        }

    }
}
