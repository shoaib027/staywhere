import { Router } from "express";
import { signUpController } from "../controllers/userController.js";

const authRouter = Router()

authRouter.post("/signup",signUpController);

export default authRouter