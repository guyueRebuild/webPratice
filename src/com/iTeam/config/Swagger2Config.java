package com.iTeam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.Operation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
//	public Docket createRestApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				//.apiInfo(apiI           nfo()).select()
//				//.apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
//	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("基础平台 RESTFUL APIS")
				 .description("基础平台 RESTful 风格的接口文档，内容详细，极大的减少了前后端的沟通成本，同时确保代码与文档保持高度一致，极大的减少维护文档的时间。")
	                .termsOfServiceUrl("http://xiachengwei5.coding.me")
	                .contact("Xia")
	                .version("1.0.0")
	                .build();
	}
}