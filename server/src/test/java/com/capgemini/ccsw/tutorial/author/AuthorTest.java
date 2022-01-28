package com.capgemini.ccsw.tutorial.author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.tutorial.author.model.AuthorDto;
import com.capgemini.ccsw.tutorial.author.model.AuthorSearchDto;

@SpringBootTest
@Transactional
public class AuthorTest {

   private static final int TOTAL_AUTORS = 6;

   @Autowired
   private AuthorController authorController;

   @Test
   public void findAllShouldReturnAllAuthorInDB() {
      assertNotNull(authorController);

      List<AuthorDto> authors = authorController.findAll();

      assertNotNull(authors);

      assertEquals(TOTAL_AUTORS, authors.size());

   }

   @Test
   public void findFirstPageWithFiveSizeShouldReturnFirstFiveResults() {

      int pageSize = 5;

      assertNotNull(authorController);

      AuthorSearchDto dto = new AuthorSearchDto();
      dto.setPageable(PageRequest.of(0, pageSize));

      Page<AuthorDto> resultPage = authorController.findPage(dto);

      assertNotNull(resultPage);

      assertEquals(TOTAL_AUTORS, resultPage.getTotalElements());
      assertEquals(pageSize, resultPage.getContent().size());

   }

   @Test
   public void findSecondPageWithFiveSizeShouldReturnLastTwoResults() {

      int pageSize = 5;
      int elementsCount = TOTAL_AUTORS - pageSize;

      assertNotNull(authorController);

      AuthorSearchDto searchDto = new AuthorSearchDto();
      searchDto.setPageable(PageRequest.of(1, pageSize));

      Page<AuthorDto> resultPage = authorController.findPage(searchDto);

      assertNotNull(resultPage);

      assertEquals(TOTAL_AUTORS, resultPage.getTotalElements());
      assertEquals(elementsCount, resultPage.getContent().size());

   }

   @Test
   public void saveWithoutIdShouldCreateNewAuthor() {

      assertNotNull(authorController);

      String newAuthorName = "Nuevo Autor";
      String newNationality = "Nueva Nacionalidad";

      long newAuthorId = TOTAL_AUTORS + 1;
      long newAuthorSize = TOTAL_AUTORS + 1;

      AuthorDto dto = new AuthorDto();
      dto.setName(newAuthorName);
      dto.setNationality(newNationality);

      authorController.save(null, dto);

      AuthorSearchDto searchDto = new AuthorSearchDto();
      searchDto.setPageable(PageRequest.of(0, (int) newAuthorSize));

      Page<AuthorDto> resultPage = authorController.findPage(searchDto);

      assertNotNull(resultPage);
      assertEquals(newAuthorSize, resultPage.getTotalElements());

      AuthorDto author = resultPage.getContent().stream().filter(item -> item.getId().equals(newAuthorId)).findFirst().orElse(null);
      assertNotNull(author);
      assertEquals(newAuthorName, author.getName());

   }

   @Test
   public void modifyWithExistIdShouldModifyCategory() {

      assertNotNull(authorController);

      String newAuthorName = "Nuevo Autor";
      String newNationality = "Nueva Nacionalidad";
      long authorId = 3;

      AuthorDto dto = new AuthorDto();
      dto.setName(newAuthorName);
      dto.setNationality(newNationality);

      authorController.save(authorId, dto);

      AuthorSearchDto searchDto = new AuthorSearchDto();
      searchDto.setPageable(PageRequest.of(0, (int) authorId));

      Page<AuthorDto> resultPage = authorController.findPage(searchDto);

      assertNotNull(resultPage);
      assertEquals(TOTAL_AUTORS, resultPage.getTotalElements());

      AuthorDto author = resultPage.getContent().stream().filter(item -> item.getId().equals(authorId)).findFirst().orElse(null);
      assertNotNull(author);
      assertEquals(newAuthorName, author.getName());
      assertEquals(newNationality, author.getNationality());

   }

   @Test
   public void modifyWithNotExistIdShouldThrowException() {

      assertNotNull(authorController);

      String newAuthorName = "Nuevo Autor";
      long authorId = TOTAL_AUTORS + 1;

      AuthorDto dto = new AuthorDto();
      dto.setName(newAuthorName);

      assertThrows(Exception.class, () -> authorController.save(authorId, dto));
   }

   @Test
   public void deleteWithExistsIdShouldDeleteCategory() {

      assertNotNull(authorController);

      long newAuthorsSize = TOTAL_AUTORS - 1;
      long deleteAuthorId = 6;

      authorController.delete(deleteAuthorId);

      AuthorSearchDto searchDto = new AuthorSearchDto();
      searchDto.setPageable(PageRequest.of(0, TOTAL_AUTORS));

      Page<AuthorDto> resultPage = authorController.findPage(searchDto);

      assertNotNull(resultPage);
      assertEquals(newAuthorsSize, resultPage.getTotalElements());

   }

   @Test
   public void deleteWithNotExistsIdShouldThrowException() {

      assertNotNull(authorController);

      long deleteAuthorId = TOTAL_AUTORS + 1;

      assertThrows(Exception.class, () -> authorController.delete(deleteAuthorId));

   }
}
