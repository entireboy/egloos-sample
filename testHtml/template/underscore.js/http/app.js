var express = require('express');
var app = express();
app.use(express.static('public'));

app.get('/drinks', function(req, res) {
  res.json([
    {name: 'Coffee', desc: 'Black hot drink'}
    , {name: 'Milk', desc: 'White cold drink'}
//    , {name: 'Lemon tea', desc: 'Yellow oval fruit with juicy acidic flesh'}
  ]);
});

app.listen(3000);
console.log('Listening on port 3000');
