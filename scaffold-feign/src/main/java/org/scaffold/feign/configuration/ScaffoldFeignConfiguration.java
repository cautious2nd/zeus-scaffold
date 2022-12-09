/**
 * Author : chiziyue
 * Date : 2021年8月21日 上午9:45:16
 * Title : org.scaffold.feign.configuration.ScaffoldFeignConfiguration.java
 *
**/
package org.scaffold.feign.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import org.scaffold.feign.DefaultScaffoldErrorDecoder;
import org.scaffold.feign.ScaffoldErrorDecoder;
import org.scaffold.feign.ScaffoldRequestInterceptor;
import org.scaffold.feign.databind.ScaffoldDataFormat;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(ScaffoldFeignProPerties.class)
public class ScaffoldFeignConfiguration {
	@Autowired
	ScaffoldFeignProPerties scaffoldFeignProPerties;

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
			Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
		ObjectMapper objectMapper = jackson2ObjectMapperBuilder.build();
		objectMapper.setDateFormat(new ScaffoldDataFormat(objectMapper.getDateFormat()));
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(
				objectMapper);
		return mappingJackson2HttpMessageConverter;
	}

	@Bean
	@ConditionalOnMissingBean
	public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converter) {
		return new HttpMessageConverters(converter.orderedStream().collect(Collectors.toList()));
	}

	@Bean
	@ConditionalOnMissingBean(value = ScaffoldErrorDecoder.class)
	public ScaffoldErrorDecoder errorDecoder() {
		return new DefaultScaffoldErrorDecoder();
	}

	@Bean
	@ConditionalOnProperty(value = "scaffold.rest.enableTokenApply", havingValue = "true", matchIfMissing = true)
	public RequestInterceptor requestInterceptor() {
		return new ScaffoldRequestInterceptor(scaffoldFeignProPerties.getToken());
	}
}
