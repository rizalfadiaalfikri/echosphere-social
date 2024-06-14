package com.rizalfadiaalfikri.echosphere.utils;

import org.springframework.beans.factory.annotation.Value;

public class Utils {
    @Value("${application.version}")
    public static String version;

    public static String getVersion() {
        return version;
    }

}
