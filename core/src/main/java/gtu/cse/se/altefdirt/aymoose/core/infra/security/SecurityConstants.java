package gtu.cse.se.altefdirt.aymoose.core.infra.security;

import java.util.Arrays;

public class SecurityConstants {

    public enum AccessRole {
        ROLE_USER, ROLE_VERIFIED_USER, ROLE_FACILITY_OWNER, ROLE_ADMIN, ROLE_SUPERADMIN;

        @Override
        public String toString() {
            return super.toString().toUpperCase();
        }

        public static AccessRole safeCast(String role) {
            return AccessRole.has(role) ? AccessRole.fromString(role) : null;
        }

        public static boolean has(String role) {
            return Arrays.stream(AccessRole.values()).anyMatch(r -> r.toString().equals(role.toUpperCase()));
        }

        public static AccessRole fromString(String role) {
            return AccessRole.valueOf(role.toUpperCase());
        }
    }

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_VERIFIED_USER = "ROLE_VERIFIED_USER";
    public static final String ROLE_FACILITY_OWNER = "ROLE_FACILITY_OWNER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SUPERADMIN = "superadmin";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "content-type";
    public static final String JWT_ROLE_PREFIX = "ROLE_";
    public static final String JWT_ROLES = "roles";
    public static final String JWT_REALM_ACCESS = "realm_access";

    private SecurityConstants() {
    }
}
