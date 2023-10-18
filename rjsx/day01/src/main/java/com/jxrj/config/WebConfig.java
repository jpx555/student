package com.jxrj.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jxrj.converters.StringToDateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        MappingJackson2HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();

        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(new MediaType("text","html", StandardCharsets.UTF_8));
        mediaTypes.add(new MediaType("application", "json", StandardCharsets.UTF_8));

        jacksonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);


        ObjectMapper om = new ObjectMapper();
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        om.setTimeZone(new SimpleTimeZone(8*60*60*1000,"Asia/Shanghai"));


        //DefaultSerializerProvider.Impl impl = new DefaultSerializerProvider.Impl();
        // impl.setNullValueSerializer(new JsonSerializer(){
        om.getSerializerProvider().setNullValueSerializer(new JsonSerializer(){
            @Override
            public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString("");
            }
        });
        //om.setSerializerProvider(impl);

        jacksonHttpMessageConverter.setObjectMapper(om);


        converters.add(0,jacksonHttpMessageConverter);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        StringToDateConverter converter = new StringToDateConverter();
        List<String> patterns = new ArrayList<String>();
        patterns.add("yyyy");
        patterns.add("yyyy-MM");
        patterns.add("yyyy-MM-dd");
        patterns.add("yyyy-MM-dd HH:mm:ss");
        converter.setPatterns(patterns);
        registry.addConverter(converter);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/**","/js/**","/img/**","/user/loginView","/user/login");
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**","/user/loginView","/user/login");
//    }

    //    @Bean
//    @Primary
//    @ConditionalOnMissingBean(ObjectMapper.class)
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
//        objectMapper.setTimeZone(new SimpleTimeZone(8*60*60*1000,"Asia/Shanghai"));
//
//        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//                jsonGenerator.writeString("");
//            }
//        });
//        return objectMapper;
//    }
}
