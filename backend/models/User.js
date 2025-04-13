// models/User.js
import { Schema, model } from 'mongoose';

const userSchema = new Schema({
  name: { 
    type: String, 
    required: true 
  },
  email: { 
    type: String, 
    required: true,
    unique: true
  },
  password: { 
    type: String, // Store a hashed password
    required: true 
  },
  // Saved properties can be directly referenced
  savedProperties: [{
    type: Schema.Types.ObjectId,
    ref: 'Property'
  }],
  // Records of properties the user has viewed
  viewedProperties: [{
    property: { 
      type: Schema.Types.ObjectId,
      ref: 'Property'
    },
    viewedAt: { 
      type: Date, 
      default: Date.now 
    }
  }],
  createdAt: { 
    type: Date, 
    default: Date.now 
  }
});

export default model('User', userSchema);
