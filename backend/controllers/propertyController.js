// controllers/propertyController.js
import Property from '../models/Property.js';

// Create a new property
export async function createProperty(req, res) {
  try {
    const property = await Property.create({
        description: req.body.description,
        address: {
          houseno: req.body.address.street,
          street: req.body.address.street,
          city: req.body.address.city,
          state: req.body.address.state,
          zip: req.body.address.zip,
        },
        price: req.body.price,
        bedrooms: req.body.bedrooms,
        bathrooms: req.body.bathrooms,
        area: req.body.area,
        imageUrl: req.body.imageUrl
      });
    res.status(201).json(property);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}

// Get all properties
export async function getAllProperties(req, res) {
  try {
    const properties = await Property.find({price: req.body.price});
    res.json(properties);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
}

// Get a single property by ID
export async function getPropertyById(req, res) {
  try {
    const property = await findById(req.params.id);
    if (!property) {
      return res.status(404).json({ message: 'Property not found' });
    }
    res.json(property);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
}

// Update a property by ID
export async function updateProperty(req, res) {
  try {
    const property = await findByIdAndUpdate(req.params.id, req.body, { new: true, runValidators: true });
    if (!property) {
      return res.status(404).json({ message: 'Property not found' });
    }
    res.json(property);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
}

// Delete a property by ID
export async function deleteProperty(req, res) {
  try {
    const property = await findByIdAndDelete(req.params.id);
    if (!property) {
      return res.status(404).json({ message: 'Property not found' });
    }
    res.json({ message: 'Property deleted successfully' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
}
