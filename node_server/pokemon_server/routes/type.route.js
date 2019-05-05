const express = require('express');
const router = express.Router();

const typeControler = require('../controller/type-controller');

router.get('/test', typeControler.test);

module.exports = router;