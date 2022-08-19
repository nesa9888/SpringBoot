package com.example.demo.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository) {

      return args -> {
          Book orloviRanoLete = new Book(
                  "Orlovi Rano Lete",
                  "Branko Copic",
                  "1234567890"
          );

          Book manInTheHighCastle = new Book(
                  "Man in the high Castle",
                  "Philip K. Dick",
                  "1234567891"
          );

          repository.saveAll(
                  List.of(orloviRanoLete, manInTheHighCastle)
          );
      };
    }
}
