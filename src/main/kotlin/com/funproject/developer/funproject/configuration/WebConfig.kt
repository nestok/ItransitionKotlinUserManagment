package com.funproject.developer.funproject.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurerAdapter() {
//    override fun addCorsMappings(registry: CorsRegistry?) {
//        registry!!.addMapping("/**")
//                .allowedOrigins("http://localhost:4200")
//                .allowedHeaders("*")
//                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
//                .allowCredentials(false).maxAge(MAX_AGE.toLong())
//    }

    companion object {

        private val MAX_AGE = 3600
    }
}