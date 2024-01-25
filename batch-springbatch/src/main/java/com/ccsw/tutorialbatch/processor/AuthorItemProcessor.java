package com.ccsw.tutorialbatch.processor;


import com.ccsw.tutorialbatch.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class AuthorItemProcessor implements ItemProcessor<Author, Author> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorItemProcessor.class);

    @Override
    public Author process(final Author author) {
        String name = author.getName();
        String nationality = author.getNationality().toLowerCase() + "_" + author.getNationality().toUpperCase();

        Author transformedAuthor = new Author(name, nationality);
        LOGGER.info("Converting ( {} ) into ( {} )", author, transformedAuthor);

        return transformedAuthor;
    }
}