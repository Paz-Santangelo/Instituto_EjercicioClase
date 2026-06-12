package com.ProyectoSpring.Instituto.dto.response;

import java.util.Arrays;
import java.util.List;

public class RolDTO {

    private static final List<String> VALID_ROLES = Arrays.asList("USER", "OWNER", "MANAGEMENT", "ADMIN");

    public static boolean isValidRole(String role) {
        if (role == null) {
            return false;
        }
        return VALID_ROLES.contains(role.toUpperCase());
    }

    public static String getValidRoles() {
        return String.join(", ", VALID_ROLES);
    }
}
