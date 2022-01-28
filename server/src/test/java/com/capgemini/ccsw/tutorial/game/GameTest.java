package com.capgemini.ccsw.tutorial.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.ccsw.tutorial.author.model.AuthorDto;
import com.capgemini.ccsw.tutorial.category.model.CategoryDto;
import com.capgemini.ccsw.tutorial.game.model.GameDto;

@SpringBootTest
@Transactional
public class GameTest {

   @Autowired
   private GameController gameController;

   private final String notExistsTitle = "NotExists";
   private final String existsTitle = "Aventureros";
   private final Long notExistsCategory = 0L;
   private final Long existsCategory = 3L;

   @Test
   public void findWithoutFiltersShouldReturnAllGamesInDB() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 6;

      List<GameDto> games = gameController.find(null, null);

      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());
   }

   @Test
   public void findExistsTitleShouldReturnGames() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 1;

      List<GameDto> games = gameController.find(existsTitle, null);

      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());
   }

   @Test
   public void findExistsCategoryShouldReturnGames() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 2;

      List<GameDto> games = gameController.find(null, existsCategory);

      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());
   }

   @Test
   public void findExistsTitleAndCategoryShouldReturnGames() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 1;

      List<GameDto> games = gameController.find(existsTitle, existsCategory);

      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());
   }

   @Test
   public void findNotExistsTitleShouldReturnEmpty() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 0;

      List<GameDto> games = gameController.find(notExistsTitle, null);

      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());
   }

   @Test
   public void findNotExistsCategoryShouldReturnEmpty() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 0;

      List<GameDto> games = gameController.find(null, notExistsCategory);

      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());
   }

   @Test
   public void findNotExistsTitleOrCategoryShouldReturnEmpty() {

      assertNotNull(gameController);

      int GAMES_WITH_FILTER = 0;

      List<GameDto> games = gameController.find(notExistsTitle, notExistsCategory);
      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());

      games = gameController.find(notExistsTitle, existsCategory);
      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());

      games = gameController.find(existsTitle, notExistsCategory);
      assertNotNull(games);
      assertEquals(GAMES_WITH_FILTER, games.size());

   }

   @Test
   public void saveWithoutIdShouldCreateNewGame() {

      String newTitle = "Nuevo juego";

      GameDto dto = new GameDto();
      AuthorDto authorDto = new AuthorDto();
      authorDto.setId(1L);

      CategoryDto categoryDto = new CategoryDto();
      categoryDto.setId(1L);

      dto.setTitle(newTitle);
      dto.setAge("18");
      dto.setAuthor(authorDto);
      dto.setCategory(categoryDto);

      List<GameDto> games = gameController.find(newTitle, null);
      assertNotNull(games);
      assertEquals(0, games.size());

      gameController.save(null, dto);

      games = gameController.find(newTitle, null);
      assertNotNull(games);
      assertEquals(1, games.size());
   }

   @Test
   public void modifyWithExistIdShouldModifyGame() {

      Long gameId = 1L;
      String newTitle = "Nuevo juego";

      GameDto dto = new GameDto();
      AuthorDto authorDto = new AuthorDto();
      authorDto.setId(1L);

      CategoryDto categoryDto = new CategoryDto();
      categoryDto.setId(1L);

      dto.setTitle(newTitle);
      dto.setAge("18");
      dto.setAuthor(authorDto);
      dto.setCategory(categoryDto);

      List<GameDto> games = gameController.find(newTitle, null);
      assertNotNull(games);
      assertEquals(0, games.size());

      gameController.save(gameId, dto);

      games = gameController.find(newTitle, null);
      assertNotNull(games);
      assertEquals(1, games.size());

      GameDto game = games.get(0);
      assertEquals(gameId, game.getId());

   }

   @Test
   public void modifyWithNotExistIdShouldThrowException() {
      assertNotNull(gameController);

      String newTitle = "Nuevo juego";
      long gameId = 0;

      GameDto dto = new GameDto();
      dto.setTitle(newTitle);

      assertThrows(Exception.class, () -> gameController.save(gameId, dto));
   }

}