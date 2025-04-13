import { v2 as cloudinary } from 'cloudinary';
import fs from "fs"


// Configuration
cloudinary.config({
    cloud_name: process.env.CLOUD_NAME,
    api_key: process.env.CLOUDINARY_API_KEY,
    api_secret: process.env.CLOUDINARY_API_SECRET
});





export const uploadImageController = async (req, res) => {
    try {
        if (!req.file) {
            return res.status(400).json({ message: "No file uploaded" });
        }

        const localFilePath = req.file.path;

        let response
        let optimizeUrl
        try {
            if (!localFilePath) return null;

            response = await cloudinary.uploader.upload(localFilePath, {
                resource_type: "image"
            })

           optimizeUrl = cloudinary.url(response.public_id, {
                fetch_format: "auto",
                quality: "auto"
            })
            console.log("File is Uploaded Successfully", optimizeUrl)
            fs.unlink(localFilePath)

        } catch (error) {
            fs.unlinkSync(localFilePath)
        }

        if (!response) {
            return res.status(500).json({
                message: "Failed to upload image to Cloudinary"
            });
        }

        return res.status(200).json({ url: optimizeUrl })


    } catch (error) {
        return res.status(500).json({ message: "Upload Server error", error: error.message });
    }
};


export const uploadMultImageController = async (req, res) => {
    try {
        if (!req.files || req.files.length === 0) {
            return res.status(400).json({ message: "No files uploaded" });
        }

        const uploadedUrls = [];

        for (const file of req.files) {
            const localFilePath = file.path;

            try {
                const response = await cloudinary.uploader.upload(localFilePath, {
                    resource_type: "image"
                });

                const optimizeUrl = cloudinary.url(response.public_id, {
                    fetch_format: "auto",
                    quality: "auto"
                });

                uploadedUrls.push(optimizeUrl);
                console.log("File uploaded successfully:", optimizeUrl);

                fs.unlinkSync(localFilePath); // Clean up temp file
            } catch (uploadError) {
                console.error("Error uploading file:", file.originalname, uploadError.message);
                fs.unlinkSync(localFilePath); // Clean up even on error
            }
        }

        if (uploadedUrls.length === 0) {
            return res.status(500).json({
                message: "All uploads failed"
            });
        }

        return res.status(200).json({ urls: uploadedUrls });

    } catch (error) {
        return res.status(500).json({ message: "Upload server error", error: error.message });
    }
};



