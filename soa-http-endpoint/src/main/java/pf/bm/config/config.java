package pf.bm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Auth0Config.class, KafkaProducerConfig.class})
public class config {
}
