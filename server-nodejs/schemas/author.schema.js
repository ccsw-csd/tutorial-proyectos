import mongoose from "mongoose";
import normalize from 'normalize-mongoose';
import mongoosePaginate from 'mongoose-paginate-v2';
const { Schema, model } = mongoose;

const authorSchema = new Schema({
    name: {
        type: String,
        require: true
    },
    nationality: {
        type: String,
        require: true
    }
});
authorSchema.plugin(normalize);
authorSchema.plugin(mongoosePaginate);

const AuthorModel = model('Author', authorSchema);

export default AuthorModel;