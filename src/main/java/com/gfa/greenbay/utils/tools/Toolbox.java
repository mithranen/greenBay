package com.gfa.greenbay.utils.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Toolbox {

  public static LocalDateTime convertRequestTimeToLocalDAteTime(String requestTime) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return LocalDateTime.parse(requestTime, formatter);
  }

  @Bean
  public static ModelMapper modelmapper() {
    return new ModelMapper();
  }

  @Bean
  public static ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  //TODO if using jackson-datatype-jsr310
  /*@Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    return new Jackson2ObjectMapperBuilder() {

      @Override
      public void configure(ObjectMapper objectMapper) {
        super.configure(objectMapper);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
      }

    };
  }*/

  /*  @Bean
  public ObjectMapper defaultMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }*/

  /*  private static final String dateFormat = "yyyy-MM-dd";
  private static final String dateTimeFormat = "yyyy-MM-dd HH:mm";

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder -> {
      builder.simpleDateFormat(dateTimeFormat);
      builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)));
      builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat)));
    };
  }*/
}
