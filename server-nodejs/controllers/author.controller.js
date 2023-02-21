import * as AuthorService from '../services/author.service.js';

export const getAuthors = async (req, res) => {
    try {
        const authors = await AuthorService.getAuthors();
        res.status(200).json(
            authors
        );
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const createAuthor = async (req, res) => {
    try {
        const author = await AuthorService.createAuthor(req.body);
        res.status(200).json({
            author
        });
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const updateAuthor = async (req, res) => {
    const authorId = req.params.id;
    try {
        await AuthorService.updateAuthor(authorId, req.body);
        res.status(200).json(1);
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const deleteAuthor = async (req, res) => {
    const authorId = req.params.id;
    try {
        const deletedAuthor = await AuthorService.deleteAuthor(authorId);
        res.status(200).json({
            author: deletedAuthor
        });
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const getAuthorsPageable = async (req, res) => {
    const page = req.body.pageable.pageNumber || 0;
    const limit = req.body.pageable.pageSize || 5;
    const sort = req.body.pageable.sort || null;

    try {
        const response = await AuthorService.getAuthorsPageable(page, limit, sort);
        res.status(200).json({
            content: response.docs,
            pageable: {
                pageNumber: response.page - 1,
                pageSize: response.limit
            },
            totalElements: response.totalDocs
        });
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

