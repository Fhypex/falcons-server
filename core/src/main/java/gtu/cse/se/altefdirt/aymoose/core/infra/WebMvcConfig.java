package gtu.cse.se.altefdirt.aymoose.core.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import gtu.cse.se.altefdirt.aymoose.shared.api.rest.version.VersionedRequestMappingHandlerMapping;

/* !!!!!!!!!!!!!!!!! DO NOT DELETE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping(
            ContentNegotiationManager contentNegotiationManager,
		    FormattingConversionService conversionService,
			ResourceUrlProvider resourceUrlProvider) {
                return new VersionedRequestMappingHandlerMapping();
    }
}