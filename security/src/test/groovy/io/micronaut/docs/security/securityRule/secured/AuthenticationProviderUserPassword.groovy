package io.micronaut.docs.security.securityRule.secured

import io.micronaut.context.annotation.Requires
import io.micronaut.security.authentication.AuthenticationFailed
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.UserDetails
import io.reactivex.Flowable
import org.reactivestreams.Publisher

import javax.inject.Singleton

@Singleton
@Requires(property = 'spec.name', value = 'docsecured')
class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Override
    Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        if ( authenticationRequest.identity == 'user' && authenticationRequest.secret == 'password' ) {
            return Flowable.just(new UserDetails('user', []))
        }
        if ( authenticationRequest.identity == 'admin' && authenticationRequest.secret == 'password' ) {
            return Flowable.just(new UserDetails((String) authenticationRequest.identity, ['ROLE_ADMIN']))
        }
        return Flowable.just(new AuthenticationFailed())
    }
}
