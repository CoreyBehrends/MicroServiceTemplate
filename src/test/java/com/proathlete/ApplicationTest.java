package com.proathlete;

import com.google.common.io.Resources;
import com.proathlete.productcatalog.api.ProductController;
import com.proathlete.productcatalog.catalog.domain.ProductImpl;
import com.proathlete.productcatalog.catalog.service.CatalogService;
import com.proathlete.productcatalog.config.ProductCatalogConfig;
import com.proathlete.productcatalog.config.ProductCatalogServiceRunner;
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


    private static CatalogService offerService = mock(CatalogService.class);

    @ClassRule
    public static final DropwizardAppRule<ProductCatalogConfig> RULE =
            new DropwizardAppRule<>(ProductCatalogServiceRunner.class, resourceFilePath("product_catalog_test.yml"));

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ProductController(offerService))
            .build();

    @AfterClass
    public static void teardownClass() throws IOException {
        Files.deleteIfExists(Paths.get("test.h2.db"));
    }


    @Test
    public void default_route_returns_200() {

        when(offerService.findProductById(1L)).thenReturn(new ProductImpl());

        Response response = resources.client().target("/product/1/").request().head();
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
