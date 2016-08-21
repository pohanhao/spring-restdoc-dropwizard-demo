package idv.hank.spring.restdoc.dropwizard.demo.resources;

import idv.hank.spring.restdoc.dropwizard.demo.model.Hello;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
@Produces("application/json")
@Consumes("application/json")
public class HelloResource {

    @GET
    public Hello sayHello() {
        return new Hello("Hank", "Hello world.");
    }

}
