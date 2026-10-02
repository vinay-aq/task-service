package com.clientmanagement.taskservice.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        if(authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            String token = jwtAuthenticationToken.getToken().getTokenValue();

            template.header("Authorization",
                    "Bearer " + token);

        }

    }


}
