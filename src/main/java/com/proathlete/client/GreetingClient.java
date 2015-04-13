package com.proathlete.client;


import com.proathlete.model.Greeting;

import java.io.IOException;

public interface GreetingClient {

     Greeting getGreeting() throws IOException;

     Greeting save(Greeting entity) throws IOException;

     boolean delete(Greeting entity);

}
