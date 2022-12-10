package br.com.fadesp.apipagamentodebito.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Generated;

@Configuration
@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")
public class OpenApiConfiguration {

    @Bean
    OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Pagamento de Debitos")
                        .description(
                                "API para possibilitar o recebimento de pagamentos de débitos de pessoas físicas e jurídicas.")
                        .termsOfService("")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email("apiteam@swagger.io")));
    }
}
