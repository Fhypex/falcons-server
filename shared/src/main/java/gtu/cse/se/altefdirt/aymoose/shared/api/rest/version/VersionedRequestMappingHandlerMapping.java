package gtu.cse.se.altefdirt.aymoose.shared.api.rest.version;

import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class VersionedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected void registerHandlerMethod(Object handler, Method method, org.springframework.web.servlet.mvc.method.RequestMappingInfo mapping) {
        Class<?> beanType = method.getDeclaringClass();
        
        // Check if the class has @ApiVersion annotation
        ApiVersionV1 apiVersion = beanType.getAnnotation(ApiVersionV1.class);
        if (apiVersion != null) {
            // Add the API version prefix to the method's path
            String versionPrefix = "/api/v" + apiVersion.value();
            org.springframework.web.servlet.mvc.method.RequestMappingInfo versionedMapping =
                    org.springframework.web.servlet.mvc.method.RequestMappingInfo.paths(versionPrefix)
                            .build()
                            .combine(mapping);

            // Register the handler method with the versioned path
            super.registerHandlerMethod(handler, method, versionedMapping);
        } else {
            // Register the handler method as usual
            super.registerHandlerMethod(handler, method, mapping);
        }
    }
}