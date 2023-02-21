import { Router } from 'express';
import { check } from 'express-validator';
import validateFields from '../middlewares/validate-Fields.js';
import { createAuthor, deleteAuthor, getAuthors, updateAuthor, getAuthorsPageable } from '../controllers/author.controller.js';
const authorRouter = Router();

authorRouter.put('/:id', [
    check('name').not().isEmpty(),
    check('nationality').not().isEmpty(),
    validateFields
], updateAuthor);

authorRouter.put('/', [
    check('name').not().isEmpty(),
    check('nationality').not().isEmpty(),
    validateFields
], createAuthor);

authorRouter.get('/', getAuthors);
authorRouter.delete('/:id', deleteAuthor);

authorRouter.post('/', [
    check('pageable').not().isEmpty(),
    check('pageable.pageSize').not().isEmpty(),
    check('pageable.pageNumber').not().isEmpty(),
    validateFields
], getAuthorsPageable)

export default authorRouter;