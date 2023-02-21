import * as GameService from '../services/game.service.js';

export const getGames = async (req, res) => {
    try {
        const titleToFind = req.query?.title || '';
        const categoryToFind = req.query?.idCategory || null;
        const games = await GameService.getGames(titleToFind, categoryToFind);
        res.status(200).json(games);
    } catch(err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const createGame = async (req, res) => {
    try {
        const game = await GameService.createGame(req.body);
        res.status(200).json({
            game
        });
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const updateGame = async (req, res) => {
    const gameId = req.params.id;
    try {
        await GameModel.updateGame(gameId, req.body);
        res.status(200).json(1);
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}