var express = require('express');
var db = require('../db/database.js');
var router = express.Router();

/*Get plan item from id*/
router.get('/id=:planId', function(req, res, next){
  var plansQuery = "SELECT * FROM `the_plan` where id = " + req.params.planId;
  db.query(plansQuery, (err, rows) => {

    if(err){
      console.log("plan by id get failed");
      console.log(err);
      return;
    }

    res.json({
        object: rows
    });
  });
});

/*Get plan item from user*/
router.get('/name=:name', function(req, res, next){
    var plansQuery = "SELECT * FROM `the_plan` where user = '" + req.params.name +"'";
    db.query(plansQuery, (err, rows) => {
  
      if(err){
        console.log("plan by name get failed");
        console.log(err);
        return;
      }
  
      res.json({
          object: rows
      });
    });
  });

router.post('/notes', function(req, res){
    var text = req.text;
    var pid = req.pid;
    var query = "UPDATE `the_plan` as p SET p.notes='" + text + "' where p.id = '" + pid + "'"
    db.query(query, (err)=>{
      if(err){
        console.log("uh oh");
        return;
      }
    });
});

module.exports = router;