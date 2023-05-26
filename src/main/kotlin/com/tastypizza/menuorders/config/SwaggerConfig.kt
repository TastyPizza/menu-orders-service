//package com.tastypizza.menuorders.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import springfox.documentation.builders.ApiInfoBuilder
//import springfox.documentation.builders.PathSelectors
//import springfox.documentation.builders.RequestHandlerSelectors
//import springfox.documentation.service.ApiInfo
//import springfox.documentation.service.Contact
//import springfox.documentation.spi.DocumentationType
//import springfox.documentation.spring.web.plugins.Docket
//import springfox.documentation.swagger2.annotations.EnableSwagger2
//
//
//@Configuration
//class SwaggerConfig {
//
//    @Bean
//    fun api(): Docket {
//        return Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.tastypizza.menuorders.controllers"))
//            .paths(PathSelectors.any())
//            .build()
//
//    }
//
//
//    private fun apiInfo(): ApiInfo {
//        return ApiInfoBuilder()
//            .title("Ваш заголовок API")
//            .description("Описание вашего API")
//            .version("1.0.0")
//            .contact(Contact("Ваше имя", "URL вашего сайта", "ваш_email@example.com"))
//            .build()
//    }
//}