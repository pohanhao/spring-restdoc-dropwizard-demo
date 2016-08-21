package idv.hank.spring.restdoc.dropwizard.demo;

import idv.hank.spring.restdoc.dropwizard.demo.resources.HelloResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DemoApplication extends Application<DemoConfiguration> {

    @Override
    public void run(DemoConfiguration demoConfiguration, Environment environment) throws Exception {
        environment.jersey().register(HelloResource.class);
    }


    public static void main(String[] args) throws Exception {
        new DemoApplication().run(args);
    }

}
