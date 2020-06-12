package com.madadipouya.quarkus.example.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "user", description = "User operations."),
        },
        info = @Info(
                title = "User API with Quarkus",
                version = "0.0.1",
                contact = @Contact(
                        name = "Kasra Madadipouya",
                        url = "http://geekyhacker.com/contact",
                        email = "kasra@madadipouya.com"),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"))
)
public class SwaggerConfig extends Application {

}