/*
 * Copyright 2017-2020 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.security.authentication;

import io.micronaut.http.HttpRequest;
import org.reactivestreams.Publisher;

/**
 * Defines an authentication provider.
 *
 * @author Sergio del Amo
 * @author Graeme Rocher
 * @since 1.0
 */
public interface AuthenticationProvider {

    /**
     * Authenticates a user with the given request. If a successful authentication is
     * returned, the object must be an instance of {@link UserDetails}.
     *
     * @param authenticationRequest The request to authenticate
     * @return A publisher that emits 0 or 1 responses
     * @deprecated Use {@link #authenticate(HttpRequest, AuthenticationRequest)} instead.
     */
    @Deprecated
    Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

    /**
     * Authenticates a user with the given request. If a successful authentication is
     * returned, the object must be an instance of {@link UserDetails}.
     *
     * @param request The HTTP request
     * @param authenticationRequest The request to authenticate
     * @return A publisher that emits 0 or 1 responses
     */
    default Publisher<AuthenticationResponse> authenticate(HttpRequest<?> request, AuthenticationRequest<?, ?> authenticationRequest) {
        return authenticate(authenticationRequest);
    }
}
