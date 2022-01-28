package com.capgemini.ccsw.tutorial.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ccsw.tutorial.game.model.Game;
import com.capgemini.ccsw.tutorial.game.model.GameDto;
import com.devonfw.module.beanmapping.common.api.BeanMapper;

/**
* @author ccsw
*/
@RequestMapping(value = "/game")
@RestController
@CrossOrigin(origins = "*")
public class GameController {

   @Autowired
   GameService gameService;

   @Autowired
   BeanMapper beanMapper;

   @RequestMapping(path = "", method = RequestMethod.GET)
   public List<GameDto> find(@RequestParam(value = "title", required = false) String title,
         @RequestParam(value = "idCategory", required = false) Long idCategory) {

      List<Game> games = gameService.find(title, idCategory);

      return beanMapper.mapList(games, GameDto.class);
   }

   @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
   public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody GameDto dto) {

      gameService.save(id, dto);
   }

}