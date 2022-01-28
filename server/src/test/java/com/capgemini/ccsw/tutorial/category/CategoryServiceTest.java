package com.capgemini.ccsw.tutorial.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.tutorial.category.model.Category;

@SpringBootTest
@Transactional
public class CategoryServiceTest {

   @Autowired
   private CategoryService categoryService;

   @Test
   public void getExistsCategoryIdShouldReturnCategory() {
      assertNotNull(categoryService);

      Long categoryId = 1L;

      Category category = categoryService.get(1L);

      assertNotNull(category);

      assertEquals(categoryId, category.getId());

   }

   @Test
   public void getNotExistsCategoryIdShouldReturnNull() {
      assertNotNull(categoryService);

      Long categoryId = 0L;

      Category category = categoryService.get(categoryId);

      assertNull(category);

   }

}
