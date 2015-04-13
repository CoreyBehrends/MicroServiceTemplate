package com.proathlete.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proathlete.model.Greeting;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class GreetingClientImpl extends AbstractServiceClient implements GreetingClient {

    private String helloUrl;
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingClientImpl.class);


    public GreetingClientImpl(HttpClient httpClient){
        this.httpClient = httpClient;
        this.helloUrl = "hello/";
    }

    @Override
    public Greeting getGreeting() throws IOException {
        return fromJSON(new TypeReference<Greeting>() {}, getResponseAsString(buildHttpGet(helloUrl)));
    }

    @Override
    public Greeting save(Greeting entity) throws IOException {

        HttpPost httpPost = buildHttpPost(helloUrl);
        ObjectMapper mapper = new ObjectMapper();
        httpPost.setEntity(new StringEntity(mapper.writeValueAsString(entity)));
        return mapper.readValue(getResponseAsString(httpPost), Greeting.class);
    }

    @Override
    public boolean delete(Greeting entity) {

        HttpDelete httpDelete = buildHttpDelete(helloUrl + "1");
        try {
            HttpResponse response = httpClient.execute(httpDelete);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                return true;
            }
        } catch (IOException e) {
            LOGGER.error("An error occured while deleting greeting",e);
        }

        return false;
    }
}
