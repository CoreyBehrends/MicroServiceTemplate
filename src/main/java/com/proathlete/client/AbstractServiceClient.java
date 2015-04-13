package com.proathlete.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.io.IOException;

public abstract class AbstractServiceClient {
    HttpClient httpClient;
    protected final Logger logger = LoggerFactory.getLogger(getClass());      //NOSONAR Not static due to being part of abstract class

    public static <T> T fromJSON(final TypeReference<T> type, final String jsonPacket) throws IOException {
        return new ObjectMapper().readValue(jsonPacket, type);
    }

    @NotNull
    HttpGet buildHttpGet(String input) {
        return new HttpGet(input);
    }

    @NotNull
    HttpPost buildHttpPost(String input) {
        return new HttpPost(input);
    }

    @NotNull
    HttpDelete buildHttpDelete(String input) {
        return new HttpDelete(input);
    }


    public String getResponseAsString(HttpRequestBase httpRequest) {
        String result = "";
        try {
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            result = httpClient.execute(httpRequest, responseHandler);
        } catch (HttpResponseException httpResponseException) {
            logger.error("getResponseAsString(): caught 'HttpResponseException' while processing request <" + httpRequest.toString() + "> :=> <" + httpResponseException.getMessage() + ">", httpResponseException);
        } catch (IOException ioe) {
            logger.error("getResponseAsString(): caught 'IOException' while processing request <" + httpRequest.toString() + "> :=> <" + ioe.getMessage() + ">", ioe);
        } finally {
            httpRequest.releaseConnection();
        }
        return result;
    }


}