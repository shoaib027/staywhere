import { Router } from "express";
import { upload } from "../middlewares/multer.middleware.js";
import { uploadImageController, uploadMultImageController } from "../controllers/uploadImageController.js";

const uploadRouter = Router();

// Upload image
uploadRouter.post("/uploadimg", upload.single("image"), uploadImageController);
uploadRouter.post("/uploadimgmultiple", upload.array("image", 5), uploadMultImageController);



export default uploadRouter
