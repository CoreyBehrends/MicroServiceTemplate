package com.proathlete.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Config extends Configuration {


    @Valid
    @NotNull
    @JsonProperty
    private HttpClientConfiguration httpClient;

    public HttpClientConfiguration getHttpClientConfiguration() {
        return httpClient;
    }
}
