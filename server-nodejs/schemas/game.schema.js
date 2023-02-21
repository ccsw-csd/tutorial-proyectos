import mongoose from "mongoose";
const { Schema, model } = mongoose;
import normalize from 'normalize-mongoose';

const gameSchema = new Schema({
    title: {
        type: String,
        require: true
    },
    age: {
        type: Number,
        require: true,
        max: 99,
        min: 0
    },
    category: {
        type: Schema.Types.ObjectId,
        ref: 'Category',
        required: true
    },
    author: {
        type: Schema.Types.ObjectId,
        ref: 'Author',
        required: true
    }
});

gameSchema.plugin(normalize);
const GameModel = model('Game', gameSchema);

export default GameModel;