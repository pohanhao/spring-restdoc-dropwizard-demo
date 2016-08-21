package idv.hank.spring.restdoc.dropwizard.demo.resources;

import static io.github.restdocsext.jersey.JerseyRestDocumentation.document;
import static io.github.restdocsext.jersey.JerseyRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.jersey.test.grizzly.GrizzlyTestContainerFactory;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.payload.FieldDescriptor;

import io.dropwizard.testing.junit.ResourceTestRule;

public class HelloResourceDocumentation {

    @ClassRule
    public static final ResourceTestRule resource = ResourceTestRule.builder()
            .setTestContainerFactory(new GrizzlyTestContainerFactory())
            .addResource(new HelloResource()).build();

    private static final URI BASE_URI = URI.create("http://localhost:9998");

    private final OperationPreprocessor removeUserAgent = removeHeaders("User-Agent");

    private final Client client = resource.client();

    @Rule
    public JUnitRestDocumentation restDocumentation
            = new JUnitRestDocumentation("target/generated-snippets");

    @Test
    public void hello() throws Exception {
        final FieldDescriptor[] entityFieldDescriptors = {
                fieldWithPath("name").description("user name"),
                fieldWithPath("message").description("response message")
        };
        final Response response = client.target(BASE_URI).path("hello")
                .register(documentationConfiguration(this.restDocumentation))
                .register(document("hello", preprocessRequest(this.removeUserAgent), responseFields(entityFieldDescriptors))).request().get();
        Assert.assertEquals(HttpStatus.OK_200.getStatusCode(), response.getStatus());
    }

}
