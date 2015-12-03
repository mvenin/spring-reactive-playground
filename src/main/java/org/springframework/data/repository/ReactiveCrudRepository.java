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

package org.springframework.data.repository;

import java.io.Serializable;

import org.reactivestreams.Publisher;

import org.springframework.reactor.rx.ComposablePublisher;
import org.springframework.reactor.rx.PromisePublisher;

/**
 * Interface for generic CRUD operations on a repository for a specific type exposed
 * thanks to Reactive Streams {@code Publisher}.
 *
 * @author Sebastien Deleuze
 * @see CrudRepository
 */
@NoRepositoryBean
public interface ReactiveCrudRepository <T, ID extends Serializable> extends Repository<T, ID> {

	<S extends T> PromisePublisher<S> save(S entity);

	<S extends T> ComposablePublisher<S> save(Publisher<S> entities);

	PromisePublisher<T> findOne(ID id);

	PromisePublisher<Boolean> exists(ID id);

	// TODO We should see how to expose MongoDB {@code FindPublisher} specific features
	ComposablePublisher<T> findAll();

	// TODO We should see how to expose MongoDB {@code FindPublisher} specific features
	ComposablePublisher<T> findAll(Publisher<ID> ids);

	PromisePublisher<Long> count();

	// TODO See https://github.com/spring-projects/spring-reactive/issues/48 about a dedicated type for Void
	ComposablePublisher<Void> delete(ID id);

	// TODO See https://github.com/spring-projects/spring-reactive/issues/48 about a dedicated type for Void
	ComposablePublisher<Void> delete(T entity);

	// TODO See https://github.com/spring-projects/spring-reactive/issues/48 about a dedicated type for Void
	ComposablePublisher<Void> delete(Publisher<? extends T> entities);

	// TODO See https://github.com/spring-projects/spring-reactive/issues/48 about a dedicated type for Void
	ComposablePublisher<Void> deleteAll();

}
