/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
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
import app.processors.impl.FlightEventProcessor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import static org.assertj.core.api.Assertions.assertThat;

public class MapTests {

    @Mock
    private EventProcessor eventProcessor = new FlightEventProcessor();

    @InjectMocks
    private Application application = new Application(new Properties());

    private EventRequest request = new EventRequest("123", "test");

    @Before
    public void prepareTest() {
        EventResponse response = new EventResponse();
        response.setEventId("123");
        response.setMessage("message");
        response.setRequestSize(7);
        Whitebox.setInternalState(application, "eventProcessor", eventProcessor);
    }

    @Test
    public void test() {
        EventResponse result = application.function().apply(request);
        assertThat(result.getEventId()).isEqualTo("123");
    }

    @Test
    public void test_() {
        EventResponse result = application.function().apply(request);
        assertThat(result.getMessage()).isEqualTo("EventRequest processed by: flight_profile");
    }
/*
    @Test
    public void start() throws Exception {
        SpringBootRequestHandler<Object, Object> handler = new SpringBootRequestHandler<>(Application.class);
        handler.handleRequest(new EventRequest("123", "init"), null);
        handler.close();
    }
*/
}
