import GameModel from '../schemas/game.schema.js';

export const getGames = async (title, category) => {
    try {
        const regexTitle = new RegExp(title, 'i');
        const find = category ? { $and: [{ title: regexTitle }, { category: category }] } : { title: regexTitle };
        return await GameModel.find(find).sort('id').populate('category').populate('author');
    } catch(e) {
        throw Error('Error fetching games');
    }
}

export const createGame = async (data) => {
    try {
        const game = new GameModel({
            ...data,
            category: data.category.id,
            author: data.author.id,
        });
        return await game.save();
    } catch (e) {
        throw Error('Error creating game');
    }
}

export const updateGame = async (id, data) => {
    try {
        const game = await GameModel.findById(id);
        if (!game) {
            throw Error('There is no game with that Id');
        }    
        const gameToUpdate = {
            ...data,
            category: data.category.id,
            author: data.author.id,
        };
        return await GameModel.findByIdAndUpdate(gameId, gameToUpdate, { new: false });
    } catch (e) {
        throw Error('Error updating game');
    }
}

export const getGame = async (field) => {
    try {
        return await GameModel.find(field);
    } catch (e) {
        throw Error('Error fetching games');
    }
}