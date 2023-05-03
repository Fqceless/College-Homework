var express = require('express');
var db = require('../db/database.js');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
var catalogList = [];

  var catalogQuery = "select * from the_catalog";
  db.query(catalogQuery, (err, rows) => {

    if(err){
      console.log("select from the_catalog failed");
      console.log(err);
      return;
    }
    //render index.pug page using array
    res.render('index', {catalogs: rows});
  });

  var userQuery = "select * from the_user";
  db.query(userQuery, (err, rows) => {
    if(err){
      console.log("select from the_user failed");
      console.log(err);
      return;
    }
    //res.render('users', {users: rows});
  });

});



module.exports = router;
