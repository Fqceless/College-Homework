var express = require('express');
var db = require('../db/database.js');
var router = express.Router();

/*Get courses items from catalog year*/
router.get('/year=:year', function(req, res, next){
  var plansQuery = "SELECT cc.course_id as 'id', c.name as 'name', c.description as 'desc', c.credits as 'credits' FROM `the_catalog_courses` as cc inner join the_courses as c where cc.course_id = c.Id and cc.cat_year = " + req.params.year;
  db.query(plansQuery, (err, rows) => {

    if(err){
      console.log("catCourses get failed");
      console.log(err);
      return;
    }

    res.json({
        object: rows
    });
  });
});

/*Get courses items from plan */
router.get('/pid=:pid', function(req, res, next){
  var plansQuery = "SELECT pc.course_id, c.name, pc.term, pc.year, c.credits FROM `the_planned_courses` pc inner join `the_courses` as c WHERE pc.course_id = c.Id and plan_id = " + req.params.pid;
  db.query(plansQuery, (err, rows) => {

    if(err){
      console.log("plannedCourses get failed");
      console.log(err);
      return;
    }

    res.json({
        object: rows
    });
  });
});

module.exports = router;