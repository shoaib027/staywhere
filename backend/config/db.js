import mongoose from "mongoose";
import { config } from "dotenv";

config();

const mongoURI = process.env.MONGODB_LOCAL_URI;

// MongoDB Connection
const connectDB = async () => { 
        mongoose.connect(mongoURI)
 };

 

export default connectDB;
