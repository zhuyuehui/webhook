package com.github.webhook.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author tal_zhuyuehui 2019-04-04
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//        converter.setObjectMapper(JsonUtil.MAPPER);
//
//        converters.add(converter);
//    }
}
