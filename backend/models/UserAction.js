// models/UserAction.js
const mongoose = require('mongoose');

const userActionSchema = new mongoose.Schema({
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'User',
    required: true
  },
  property: {
    type: mongoose.Schema.Types.ObjectId,
    ref: 'Property',
    required: true
  },
  actionType: {
    type: String,
    enum: ['view', 'like', 'save', 'inquire'],
    required: true
  },
  createdAt: {
    type: Date,
    default: Date.now
  }
});

module.exports = mongoose.model('UserAction', userActionSchema);
