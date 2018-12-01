package com.fappy.javamodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	static final String CLIEN_ID = "my-trusted-client";
	static final String CLIENT_SECRET = "$argon2i$v=19$m=65536,t=1,p=1$FSshUUbq6fT8oovBAyq6Cg$lJ4nO8mBz0illvE+UjfInsuPnV+UdlIOY+Gsofn6l/k";
	static final String GRANT_TYPE = "password";
	static final String CLIENT_CREDENTIALS = "client_credentials";	
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String SCOPE_READ = "read";
	static final String SCOPE_WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;
    
    @Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.inMemory()
			.withClient(CLIEN_ID)
			.secret(CLIENT_SECRET)
			.authorizedGrantTypes(CLIENT_CREDENTIALS, GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
			.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
			.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
			.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
			.resourceIds("oauth2-resource");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
        	.tokenStore(tokenStore)
			.authenticationManager(authenticationManager);
	}

}
