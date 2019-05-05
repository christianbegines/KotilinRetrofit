const express = require('express');
const bodyParser = require('body-parser');
const config = require('./config');
const type = require('./routes/type.route');
const app = express();

app.use('/type',type);

app.listen(config.PORT, () => {
    console.log(`Server started on port ${config.PORT}`);
});
