import GameModel from '../schemas/game.schema.js';
import { getCategory } from './category.service.js';
import { getAuthor } from './author.service.js';

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
        const category = await getCategory(data.category.id);
        if (!category) {
            throw Error('There is no category with that Id');
        }

        const author = await getAuthor(data.author.id);
        if (!author) {
            throw Error('There is no author with that Id');
        }

        const game = new GameModel({
            ...data,
            category: data.category.id,
            author: data.author.id,
        });
        return await game.save();
    } catch (e) {
        throw Error(e);
    }
}

export const updateGame = async (id, data) => {
    try {
        const game = await GameModel.findById(id);
        if (!game) {
            throw Error('There is no game with that Id');
        }
        
        const category = await getCategory(data.category.id);
        if (!category) {
            throw Error('There is no category with that Id');
        }

        const author = await getAuthor(data.author.id);
        if (!author) {
            throw Error('There is no author with that Id');
        }
        
        const gameToUpdate = {
            ...data,
            category: data.category.id,
            author: data.author.id,
        };
        return await GameModel.findByIdAndUpdate(id, gameToUpdate, { new: false });
    } catch (e) {
        throw Error(e);
    }
}

export const getGame = async (field) => {
    try {
        return await GameModel.find(field);
    } catch (e) {
        throw Error('Error fetching games');
    }
}