/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.graphql.client;

import java.lang.reflect.Type;

import com.jayway.jsonpath.TypeRef;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;

/**
 * Adapt a JSONPath {@link TypeRef} to {@link ParameterizedTypeReference} and
 * {@link ResolvableType} for classes with generics.
 *
 * @author Rossen Stoyanchev
 * @since 1.0.0
 */
final class TypeRefAdapter<T> extends TypeRef<T> {

	private final Type type;

	TypeRefAdapter(Class<T> clazz) {
		this.type = clazz;
	}

	TypeRefAdapter(ParameterizedTypeReference<T> typeReference) {
		this.type = typeReference.getType();
	}

	TypeRefAdapter(Class<?> clazz, Class<?> generic) {
		this.type = ResolvableType.forClassWithGenerics(clazz, generic).getType();
	}

	TypeRefAdapter(Class<?> clazz, ParameterizedTypeReference<?> generic) {
		this.type = ResolvableType.forClassWithGenerics(clazz, ResolvableType.forType(generic)).getType();
	}


	@Override
	public Type getType() {
		return this.type;
	}

}