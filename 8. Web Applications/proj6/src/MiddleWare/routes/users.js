var express = require('express');
var db = require('../db/database.js');
var router = express.Router();

/*Get user item*/
router.get('/', function(req, res, next){
  var userQuery = "select U.name, U.password, U.type, P.id as planId from the_user as U inner join the_plan as P where P.is_default = 1 and U.name = P.user";
  db.query(userQuery, (err, rows) => {

    if(err){
      console.log("user get failed");
      console.log(err);
      return;
    }
    
    res.json({
        object: rows
    });
  });
});


module.exports = router;