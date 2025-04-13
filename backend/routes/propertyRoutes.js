import { Router } from "express";
import { createProperty,getAllProperties } from "../controllers/propertyController.js";

const propertyRouter = Router();

// Upload image
propertyRouter.post("/createproperty", createProperty);
propertyRouter.post("/getallproperties", getAllProperties);
// propertyRouter.post("/uploadimgmultiple", upload.array("image", 5), uploadImageController);



export default propertyRouter
