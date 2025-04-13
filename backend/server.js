import express, { json } from "express";
import { config } from "dotenv";
import cors from "cors";
import connectDB from "./config/db.js";
import uploadRouter from "./routes/uploadRoutes.js";
import propertyRouter from "./routes/propertyRoutes.js";
import authRouter from "./routes/userRoutes.js";

config();

const app = express();

try {
    await connectDB();
    console.log("Connected to DB")
    app.use(cors({
        origin: ['http://localhost:3000', 'http://192.168.137.1:3000'],
    // origin: '*',
    methods: ['GET', 'POST']
    }));
    app.use(json());

    //API upload route
    app.use("/api",uploadRouter)
    app.use("/api",propertyRouter)


    //Auth Routes
    app.use("/api/auth",authRouter)

    
    const PORT = process.env.PORT
    app.listen(PORT, () => console.log(`Server running on port ${PORT}`))
} catch (error) {
    console.error("Error starting the server:", error)
    process.exit(1);
}
