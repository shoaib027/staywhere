// models/Property.js
import { Schema, model } from 'mongoose';

const propertySchema = new Schema({
  description: { 
    type: String 
  },
  address: {
    houseno: { 
      type: String, 
      required: true 
    },
    street: { 
      type: String, 
      required: true 
    },
    city: { 
      type: String, 
      required: true 
    },
    state: { 
      type: String, 
      required: true 
    },
    zip: { 
      type: String 
    }
  },
  price: { 
    type: Number, 
    required: true 
  },
  bedrooms: { 
    type: Number, 
    required: true 
  },
  bathrooms: { 
    type: Number, 
    required: true 
  },
  area: { 
    type: Number // e.g. square feet or square meters
  },
  imageUrl: {
    type: String
  }
});

export default model('Property', propertySchema);
