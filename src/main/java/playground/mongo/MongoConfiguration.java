/*
 * Copyright 2002-2015 the original author or authors.
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

package playground.mongo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoDatabase;
import reactor.io.net.ReactiveNet;
import reactor.io.net.nexus.Nexus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Sebastien Deleuze
 */
@Configuration
public class MongoConfiguration {

	@Bean
	ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder.json().build();
	}

	@Bean
	MongoDatabase mongoDatabase() {
		return MongoClients.create().getDatabase("reactive-playground");
	}

	@Bean
	Nexus nexus() throws InterruptedException {
		Nexus nexus = ReactiveNet.nexus().withSystemStats();
		nexus.startAndAwait();
		return nexus;
	}

}
