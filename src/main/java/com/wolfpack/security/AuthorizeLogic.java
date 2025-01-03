package com.wolfpack.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizeLogic {

    public boolean hasAccess(String path) {
        boolean result = false;

        String methodRole = switch (path) {
            case "example" -> "ADMIN,USER";
            case "book" -> "ADMIN";
            default -> "ROOT";
        };

        String[] methodRoles = methodRole.split(",");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority gra : auth.getAuthorities()) {
            String rolUser = gra.getAuthority();

            for (String rolMet : methodRoles) {
                if (rolUser.equalsIgnoreCase(rolMet)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
