import { Router } from 'express';
import { check } from 'express-validator';
import validateFields from '../middlewares/validate-Fields.js';
import { getCategories, createCategory, deleteCategory, updateCategory } from '../controllers/category.controller.js';
const categoryRouter = Router();

categoryRouter.put('/:id', [
    check('name').not().isEmpty(),
    validateFields
], updateCategory);

categoryRouter.put('/', [
    check('name').not().isEmpty(),
    validateFields
], createCategory);

categoryRouter.get('/', getCategories);
categoryRouter.delete('/:id', deleteCategory);

export default categoryRouter;