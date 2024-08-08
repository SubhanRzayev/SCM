package com.scm.config;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepositories;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepositories userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("OAuthAuthenicationSuccessHandler");

        // identify the provider

        var authenticationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = authenticationToken.getAuthorizedClientRegistrationId();

        logger.info(authorizedClientRegistrationId);

        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + ": " + value);
        });

        User user = new User();
//        user.setUserId(Long.valueOf(UUID.randomUUID().toString()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("password");



        if (authorizedClientRegistrationId.equalsIgnoreCase("google")){
            //google
            //google attributes

            user.setEmail(Objects.requireNonNull(oauthUser.getAttribute("email")).toString());
            user.setProfilePicture(Objects.requireNonNull(oauthUser.getAttribute("picture")).toString());
            user.setName(Objects.requireNonNull(oauthUser.getAttribute("name")).toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using google.");


        }
        else if (authorizedClientRegistrationId.equalsIgnoreCase("github")){

            //github
            //github attributes

            String email = oauthUser.getAttribute("email") != null ?
                    Objects.requireNonNull(oauthUser.getAttribute("email")).toString() : Objects.requireNonNull(oauthUser.getAttribute("login")).toString()+"@gmail.com";
            String picture = Objects.requireNonNull(oauthUser.getAttribute("avatar_url")).toString();
            String name = Objects.requireNonNull(oauthUser.getAttribute("login")).toString();
            String providerUserId = oauthUser.getName();

            user.setEmail(email);
            user.setProfilePicture(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);
            user.setAbout("This account is created using github.");
        }
        else if (authorizedClientRegistrationId.equalsIgnoreCase("linkedin")){

        }
        else {
            logger.info("OAuthAuthenicationSuccessHandler: Unknown provider");
        }


        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null){
            userRepo.save(user);
            System.out.println("user saved: " + user.getEmail());
        }


        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }


}
