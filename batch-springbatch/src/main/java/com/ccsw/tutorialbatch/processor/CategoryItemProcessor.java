package com.ccsw.tutorialbatch.processor;

import com.ccsw.tutorialbatch.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CategoryItemProcessor implements ItemProcessor<Category, Category> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryItemProcessor.class);

    @Override
    public Category process(final Category category) {
        String name = category.getName().toUpperCase();
        String type = category.getType().toUpperCase();
        String characteristics = category.getCharacteristics().toUpperCase();

        Category transformedCategory = new Category(name, type, characteristics);
        LOGGER.info("Converting ( {} ) into ( {} )", category, transformedCategory);

        return transformedCategory;
    }
}