import { Router } from 'express';
import { check } from 'express-validator';
import validateFields from '../middlewares/validate-Fields.js';
import { createGame, getGames, updateGame } from '../controllers/game.controller.js';
const gameRouter = Router();

gameRouter.put('/:id', [
    check('title').not().isEmpty(),
    check('age').not().isEmpty(),
    check('category.id').not().isEmpty(),
    check('author.id').not().isEmpty(),
    validateFields
], updateGame);

gameRouter.put('/', [
    check('title').not().isEmpty(),
    check('age').not().isEmpty(),
    check('age').isNumeric(),
    check('category.id').not().isEmpty(),
    check('author.id').not().isEmpty(),
    validateFields
], createGame);

gameRouter.get('/', getGames);
gameRouter.get('/:query', getGames);


export default gameRouter;