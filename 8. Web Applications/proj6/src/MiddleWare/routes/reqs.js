var express = require('express');
var db = require('../db/database.js');
var router = express.Router();

/*Get requirements for majors*/
router.get('/major=:major', function(req, res, next){
  var plansQuery = "SELECT m.course_id, c.name, m.course_type FROM `the_major_reqs` as m inner join `the_courses` as c where m.course_id = c.Id and m.major_name = '" + req.params.major + "' order by m.course_type";
  db.query(plansQuery, (err, rows) => {

    if(err){
      console.log("major reqs get failed");
      console.log(err);
      return;
    }

    res.json({
        object: rows
    });
  });
});

/*Get requirements for minors*/
router.get('/minor=:minor', function(req, res, next){
    var plansQuery = "SELECT m.course_id, c.name FROM `the_minor_reqs` as m inner join `the_courses` as c where m.course_id = c.Id and m.minor_name = '" + req.params.minor + "'";
    db.query(plansQuery, (err, rows) => {
  
      if(err){
        console.log("minor reqs get failed");
        console.log(err);
        return;
      }
  
      res.json({
          object: rows
      });
    });
  });

  /*Get requirements for geneds*/
router.get('/geneds', function(req, res, next){
    var plansQuery = "SELECT g.course_id, c.name FROM `the_gened_reqs` as g inner join `the_courses` as c where g.course_id = c.Id";
    db.query(plansQuery, (err, rows) => {
  
      if(err){
        console.log("geneds reqs get failed");
        console.log(err);
        return;
      }
  
      res.json({
          object: rows
      });
    });
  });

module.exports = router;