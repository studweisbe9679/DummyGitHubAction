package de.adesso.common;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

/**
 * @author Bernd, Weiss
 * @version 1.0, last edit by
 * @since 21.02.2022
 */
@OpenAPIDefinition(
        info = @Info(
                title = "Sensor API with Quarkus",
                version = "0.0.1",
                contact = @Contact(
                        name = "Bernd Weiß",
                        email = "bernd.weiss@adesso.de")
))
public class SwaggerConfig extends Application {
}
