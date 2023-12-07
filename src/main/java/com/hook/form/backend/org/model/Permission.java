package com.hook.form.backend.org.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    DEFAULT_READ("default:read"),
    DEFAULT_CREATE("default:create"),
    DEFAULT_UPDATE("default:update"),
    DEFAULT_DELETE("default:delete")

    ;

    @Getter
    private final String permission;


}