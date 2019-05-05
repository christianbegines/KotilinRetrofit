const mongoose = require('mongoose');
const Schema = mongoose.Schema;

let TypeSchema = new Schema({
    id:{type:String,required:true,max:100},
    name:{type:String,required:true,max:100}
});

module.exports = mongoose.model('Type',TypeSchema);