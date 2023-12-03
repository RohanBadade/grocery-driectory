package com.demo.grcy.service.util;

import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.event.Observes;

@Startup
public class ExceptionUtils {

    private static String platformErrorCode;
    private static String serviceErrorCode;

    void onStart(@Observes StartupEvent ev) {
        updateProperties();
    }

    public static String getErrorCode(String errorCode) {
        return platformErrorCode + serviceErrorCode + errorCode;
    }

    private static void updateProperties() {
        platformErrorCode = ConfigProvider.getConfig().getValue("platform.error.code", String.class);
        serviceErrorCode = ConfigProvider.getConfig().getValue("service.error.code", String.class);
    }
}
