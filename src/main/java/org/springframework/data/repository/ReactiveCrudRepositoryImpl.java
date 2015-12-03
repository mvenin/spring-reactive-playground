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
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.reactor.rx.PromisePublisher;

/**
 * @author Sebastien Deleuze
 */
public class ReactiveCrudRepositoryImpl<T, ID extends Serializable> implements ReactiveCrudRepository<T, ID> {

	private final ReactiveMongoTemplate template;

	private final Class<T> entityClass;


	public ReactiveCrudRepositoryImpl(ReactiveMongoTemplate template, Class<T> entityClass) {
		this.template = template;
		this.entityClass = entityClass;
	}

	@Override
	public <S extends T> ComposablePublisher<S> save(Publisher<S> entities) {
		return ComposablePublisher.from(entities).flatMap(entity -> ComposablePublisher.from(this.template.insert(entity)).map(v -> entity));
	}

	@Override
	public <S extends T> PromisePublisher<S> save(S entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComposablePublisher<T> findAll() {
		return this.template.findAll(this.entityClass);
	}

	@Override
	public PromisePublisher<T> findOne(ID id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public PromisePublisher<Boolean> exists(ID id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComposablePublisher<T> findAll(Publisher<ID> ids) {
		return ComposablePublisher.error(new UnsupportedOperationException());
	}

	@Override
	public PromisePublisher<Long> count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComposablePublisher<Void> delete(ID id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComposablePublisher<Void> delete(T entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComposablePublisher<Void> delete(Publisher<? extends T> entities) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ComposablePublisher<Void> deleteAll() {
		throw new UnsupportedOperationException();
	}

}
