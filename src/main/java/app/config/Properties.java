package app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

@ConfigurationProperties("app.properties")
public class Properties {

    @Value("${message:Hello cloud app with default message}")
    private String message;

    @Value("${environment:default}")
    private String environment;

    @PostConstruct
    public void init() {
        System.out.println("Application Properties");
        System.out.println("[message]:     [" + getMessage() + "]");
        System.out.println("[environment]: [" + getEnvironment() + "]");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
