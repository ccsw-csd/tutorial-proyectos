import AuthorModel from '../schemas/author.schema.js';

export const getAuthors = async () => {
    try {
        return await AuthorModel.find().sort('id');
    } catch (e) {
        throw Error('Error fetching authors');
    }
}

export const createAuthor = async (data) => {
    const { name, nationality } = data;
    try {
        const author = new AuthorModel({ name, nationality });
        return await author.save();
    } catch (e) {
        throw Error('Error creating author');
    }
}

export const updateAuthor = async (id, data) => {
    try {
        const author = await AuthorModel.findById(id);
        if (!author) {
            throw Error('There is no author with that Id');
        }
        return await AuthorModel.findByIdAndUpdate(id, data);
    } catch (e) {
        throw Error('Error updating author');
    }
}

export const deleteAuthor = async (id) => {
    try {
        const author = await AuthorModel.findById(id);
        if (!author) {
            throw 'There is no author with that Id';
        }
        const games = await getGame({ author });
        if (games.length > 0) {
            throw 'There are games related to this author';
        }
        return await AuthorModel.findByIdAndDelete(id);
    } catch (err) {
        throw Error(err);
    }
}

export const getAuthorsPageable = async (page, limit, sort) => {
    const sortObj = {
        [sort?.property || 'name']: sort?.direction === 'DESC' ? 'DESC' : 'ASC'
    };
    try {
        const options = {
            page: parseInt(page) + 1,
            limit,
            sort: sortObj
        };

        return await AuthorModel.paginate({}, options);
    } catch (err) {
        throw Error('Error deleting author');
    }
}

