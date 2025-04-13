import User from "../models/User.js"
const jwt_secret = process.env.JWT_SECRET


export const signUpController = async (req, res) => {
    const user = await User.create({
        name: "Shoaib",
        email: "My123",
        password: "pass"
    })
    console.log("I am called")
    return res.status(200).json({success: true, user: user})
}