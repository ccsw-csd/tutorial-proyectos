import * as CategoryService from '../services/category.service.js';

export const createCategory = async (req, res) => {
    const { name } = req.body;
    try {
        const category = await CategoryService.createCategory(name);
        res.status(200).json({
            category
        });
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const getCategories = async (req, res) => {
    try {
        const categories = await CategoryService.getCategories();
        res.status(200).json(
            categories
        );
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}



export const updateCategory = async (req, res) => {
    const categoryId = req.params.id;
    const { name } = req.body;
    try {
        await CategoryService.updateCategory(categoryId, name);
        res.status(200).json(1);
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}

export const deleteCategory = async (req, res) => {
    const categoryId = req.params.id;
    try {
        const deletedCategory = await CategoryService.deleteCategory(categoryId);
        res.status(200).json({
            category: deletedCategory
        });
    } catch (err) {
        res.status(400).json({
            msg: err.toString()
        });
    }
}


