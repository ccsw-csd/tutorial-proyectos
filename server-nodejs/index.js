
import express from 'express';
import connectDB from './config/db.js';
import { config } from 'dotenv';
import categoryRouter from './routes/category.routes.js';
import authorRouter from './routes/author.routes.js';
import gameRouter from './routes/game.routes.js';

config();
connectDB(process.env.MONGODB_URL);
const app = express();

app.use(express.json());
app.use('/category', categoryRouter);
app.use('/author', authorRouter);
app.use('/game', gameRouter);

app.listen(process.env.PORT, () => {
    console.log(`Server running on port ${process.env.PORT}`);
});