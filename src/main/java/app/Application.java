/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app;

import app.config.Properties;
import app.model.EventRequest;
import app.model.EventResponse;
import app.processors.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@EnableConfigurationProperties(Properties.class)
public class Application {

    private Properties props;

    @Autowired
    public Application(Properties props) {
        this.props = props;
    }

    @Autowired
    private EventProcessor eventProcessor;

    @Bean
    public Function<EventRequest, EventResponse> function() {
        return eventRequest -> eventProcessor.process(eventRequest);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}

