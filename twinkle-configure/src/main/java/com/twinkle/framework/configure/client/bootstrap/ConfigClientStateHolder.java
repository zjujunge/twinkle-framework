/*
 * Copyright 2018-2019 the original author or authors.
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

package com.twinkle.framework.configure.client.bootstrap;

/**
 * @author Spencer Gibb
 */
public final class ConfigClientStateHolder {

	private ConfigClientStateHolder() {
		throw new IllegalStateException("Can't instantiate a utility class");
	}

	private static ThreadLocal<String> state = new ThreadLocal<>();

	public static void resetState() {
		state.remove();
	}

	public static String getState() {
		return state.get();
	}

	public static void setState(String newState) {
		if (newState == null) {
			resetState();
			return;
		}
		state.set(newState);
	}

}
