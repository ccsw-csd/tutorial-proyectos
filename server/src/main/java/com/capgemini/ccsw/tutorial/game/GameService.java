package com.capgemini.ccsw.tutorial.game;

import java.util.List;

import com.capgemini.ccsw.tutorial.game.model.Game;
import com.capgemini.ccsw.tutorial.game.model.GameDto;

/**
* @author ccsw
*/
public interface GameService {

   /**
   * Recupera los juegos filtrando opcionalmente por título y/o categoría
   * @param title
   * @param idCategory
   * @return
   */
   List<Game> find(String title, Long idCategory);

   /**
   * Guarda o modifica un juego, dependiendo de si el id está o no informado
   * @param id
   * @param dto
   */
   void save(Long id, GameDto dto);

}