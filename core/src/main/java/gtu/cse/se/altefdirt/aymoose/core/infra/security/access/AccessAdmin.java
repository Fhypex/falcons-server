package gtu.cse.se.altefdirt.aymoose.core.infra.security.access;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

import gtu.cse.se.altefdirt.aymoose.core.infra.security.SecurityConstants;

/**
 * Admin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasRole('" + SecurityConstants.ROLE_ADMIN + "')")
public @interface AccessAdmin {
    
}