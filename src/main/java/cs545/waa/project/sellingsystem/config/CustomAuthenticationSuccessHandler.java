package cs545.waa.project.sellingsystem.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
    		throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        
        roles.stream().forEach(System.out::println);
        if (roles.contains("ROLE_ADMIN"))
            httpServletResponse.sendRedirect("/admin");
        else if(roles.contains("ROLE_SELLER"))
            httpServletResponse.sendRedirect("/seller");
        else if(roles.contains("ROLE_BUYER"))
        	httpServletResponse.sendRedirect("/buyer");
        else
        	httpServletResponse.sendRedirect("/home");
    }
}
