import mongoose from "mongoose";
const { Schema, model } = mongoose;
import normalize from 'normalize-mongoose';

const categorySchema = new Schema({
    name: {
        type: String,
        require: true
    }
});
categorySchema.plugin(normalize);
const CategoryModel = model('Category', categorySchema);

export default CategoryModel;