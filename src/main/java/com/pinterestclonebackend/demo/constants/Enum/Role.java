package com.pinterestclonebackend.demo.constants.Enum;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Role {


    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    public String enumName;

    public String getEnumName() {
        return enumName;
    }
}
