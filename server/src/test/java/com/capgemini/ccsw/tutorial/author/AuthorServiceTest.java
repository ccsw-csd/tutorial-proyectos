package com.capgemini.ccsw.tutorial.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.tutorial.author.model.Author;

@SpringBootTest
@Transactional
public class AuthorServiceTest {

   @Autowired
   private AuthorService authorService;

   @Test
   public void getExistsAuthorIdShouldReturnAuthor() {
      assertNotNull(authorService);

      Long authorId = 1L;

      Author author = authorService.get(1L);

      assertNotNull(author);

      assertEquals(authorId, author.getId());

   }

   @Test
   public void getNotExistsAuthorIdShouldReturnNull() {
      assertNotNull(authorService);

      Long authorId = 0L;

      Author author = authorService.get(authorId);

      assertNull(author);

   }

}
