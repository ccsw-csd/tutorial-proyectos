package com.ccsw.tutorialgame.game;

import com.ccsw.tutorialgame.common.criteria.SearchCriteria;
import com.ccsw.tutorialgame.game.model.Game;
import com.ccsw.tutorialgame.game.model.GameDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Game> find(String title, Long idCategory) {

        GameSpecification titleSpec = new GameSpecification(new SearchCriteria("title", ":", title));
        GameSpecification categorySpec = new GameSpecification(new SearchCriteria("idCategory", ":", idCategory));

        Specification<Game> spec = Specification.where(titleSpec).and(categorySpec);

        return this.gameRepository.findAll(spec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, GameDto dto) {

        Game game;

        if (id == null) {
            game = new Game();
        } else {
            game = this.gameRepository.findById(id).orElse(null);
        }

        BeanUtils.copyProperties(dto, game, "id");

        game.setIdAuthor(dto.getAuthor().getId());
        game.setIdCategory(dto.getCategory().getId());

        this.gameRepository.save(game);
    }

}