//package org.project2.tz1_cinema.config;
//
//
//import org.modelmapper.Conditions;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ModelMapperConfig {
//
//
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//
//        // Установка стратегии сопоставления: STRICT для строгого соответствия
//        modelMapper.getConfiguration()
//                .setMatchingStrategy(MatchingStrategies.STRICT)
//                .setSkipNullEnabled(true) // Игнорировать null поля при маппинге
//                .setFieldMatchingEnabled(true) // Сопоставление по полям (без геттеров/сеттеров)
//                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE); // Доступ к private полям
//
//        // Пример: специфическая настройка маппинга для конкретных классов
//        modelMapper.typeMap(MovieDto.class, Movie.class)
//                .addMappings(mapper -> {
//                    mapper.map(MovieDto::getTitle, Movie::setTitle);
//                    mapper.map(MovieDto::getReleaseYear, Movie::setReleaseYear);
//                });
//
//        // Условие: игнорировать нулевые значения при маппинге
//        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
//
//        return modelMapper;
//    }
//}
