package com.ccsw.tutorialgame.game;

import com.ccsw.tutorialgame.author.AuthorClient;
import com.ccsw.tutorialgame.author.model.AuthorDto;
import com.ccsw.tutorialgame.category.CategoryClient;
import com.ccsw.tutorialgame.category.model.CategoryDto;
import com.ccsw.tutorialgame.game.model.Game;
import com.ccsw.tutorialgame.game.model.GameDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ccsw
 *
 */
@Tag(name = "Game", description = "API of Game")
@RequestMapping(value = "/game")
@RestController
@CrossOrigin(origins = "*")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    CategoryClient categoryClient;

    @Autowired
    AuthorClient authorClient;

    /**
     * Método para recuperar una lista de {@link Game}
     *
     * @param title título del juego
     * @param idCategory PK de la categoría
     * @return {@link List} de {@link GameDto}
     */
    @Operation(summary = "Find", description = "Method that return a filtered list of Games")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GameDto> find(@RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "idCategory", required = false) Long idCategory) {

        List<CategoryDto> categories = categoryClient.findAll();
        List<AuthorDto> authors = authorClient.findAll();

        return gameService.find(title, idCategory).stream().map(game -> {
            GameDto gameDto = new GameDto();

            gameDto.setId(game.getId());
            gameDto.setTitle(game.getTitle());
            gameDto.setAge(game.getAge());
            gameDto.setCategory(categories.stream().filter(category -> category.getId().equals(game.getIdCategory())).findFirst().orElse(null));
            gameDto.setAuthor(authors.stream().filter(author -> author.getId().equals(game.getIdAuthor())).findFirst().orElse(null));

            return gameDto;
        }).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar un {@link Game}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Game")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody GameDto dto) {

        gameService.save(id, dto);
    }

}