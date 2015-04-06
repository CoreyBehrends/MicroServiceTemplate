package com.proathlete;

import com.google.common.io.Resources;
import com.proathlete.api.HelloWorldController;
import com.proathlete.config.App;
import com.proathlete.config.Config;
import com.proathlete.service.HelloWorldService;
import io.dropwizard.testing.junit.DropwizardAppRule;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.AfterClass;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationTest {


    private static HelloWorldService helloWorldService = mock(HelloWorldService.class);

    @ClassRule
    public static final DropwizardAppRule<Config> RULE =
            new DropwizardAppRule<Config>(App.class, resourceFilePath("template.yml"));

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new HelloWorldController(helloWorldService))
            .build();

    @AfterClass
    public static void teardownClass() throws IOException {
        Files.deleteIfExists(Paths.get("test.h2.db"));
    }


    @Test
    public void default_route_returns_200() {

        when(helloWorldService.sayHello()).thenReturn("Hello!");

        Response response = resources.client().target("/hello/").request().head();
        assertEquals(200,response.getStatus());

    }

    public static String resourceFilePath(final String resourceClassPathLocation) {

        try {
            return new File(Resources.getResource(resourceClassPathLocation).toURI()).getAbsolutePath();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
