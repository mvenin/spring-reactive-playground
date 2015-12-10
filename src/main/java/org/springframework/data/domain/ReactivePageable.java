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

package org.springframework.data.domain;

import org.springframework.reactor.rx.PromisePublisher;

/**
 * Abstract interface for Reactive Streams based pagination information.
 *
 * @author Sebastien Deleuze
 * @see Pageable
 */
public interface ReactivePageable {

	int getPageNumber();

	int getPageSize();

	int getOffset();

	Sort getSort();

	PromisePublisher<ReactivePageable> next();

	PromisePublisher<ReactivePageable> previousOrFirst();

	PromisePublisher<ReactivePageable> first();

	boolean hasPrevious();

}
