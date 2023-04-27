using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using MySqlConnector;
using Newtonsoft.Json;
using Project53.Areas.Identity.Data;
using Project53.Data;

namespace Project53.Controllers
{
    public class APE : Controller
    {
        private readonly Project53Context _context;

        public APE(Project53Context context)
        {
            _context = context;
        }

        // GET: APE
        public IActionResult Planner()
        { 
            
            return View();
        }

        public IActionResult GetCourses(bool DEF = true)
        {
            //var plan_id = HttpContext.Session.GetString("plan_id"); //uncomment this line if you have session enabled
            var sql = "";
            var connectionString = "server=localhost;database=Project53;uid=root;pwd=;charset=utf8";
            if (DEF == false)
            {
                sql = "select C.id, C.name, C.credits, PC.term, PC.year from(the_plan as P inner join the_planned_courses as PC on P.id = PC.plan_id) inner join the_courses as C on C.id = PC.course_id where P.id =69";
            }
            else
            {
                sql = "select C.id, C.name, C.credits, PC.term, PC.year from(the_plan as P inner join the_planned_courses as PC on P.id = PC.plan_id) inner join the_courses as C on C.id = PC.course_id where P.id =420";
            }
            
            
            using (var conn = new MySqlConnection(connectionString))
            {
                conn.Open();
                using (var cmd = new MySqlCommand(sql, conn))
                {
                    var reader = cmd.ExecuteReader();
                    var courses = new List<Dictionary<string, object>>();
                    while (reader.Read())
                    {
                        var course = new Dictionary<string, object>();
                        for (var i = 0; i < reader.FieldCount; i++)
                        {
                            course[reader.GetName(i)] = reader.GetValue(i);
                        }
                        courses.Add(course);
                    }
                    return Ok(new { courses = courses });
                }
            }
        }
        public async Task<IActionResult> Index()
        {
            var project53Context = _context.ThePlannedCourses.Include(t => t.Plan);
            return View(await project53Context.ToListAsync());
        }

        // GET: APE/Details/5
        public async Task<IActionResult> Details(string id)
        {
            if (id == null || _context.ThePlannedCourses == null)
            {
                return NotFound();
            }

            var thePlannedCourse = await _context.ThePlannedCourses
                .Include(t => t.Plan)
                .FirstOrDefaultAsync(m => m.PlanId == id);
            if (thePlannedCourse == null)
            {
                return NotFound();
            }

            return View(thePlannedCourse);
        }

        // GET: APE/Create
        public IActionResult Create()
        {
            ViewData["PlanId"] = new SelectList(_context.ThePlans, "Id", "Id");
            return View();
        }

        // POST: APE/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("PlanId,CourseId,Term,Year")] ThePlannedCourse thePlannedCourse)
        {
            if (ModelState.IsValid)
            {
                _context.Add(thePlannedCourse);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["PlanId"] = new SelectList(_context.ThePlans, "Id", "Id", thePlannedCourse.PlanId);
            return View(thePlannedCourse);
        }

        // GET: APE/Edit/5
        public async Task<IActionResult> Edit(string id)
        {
            if (id == null || _context.ThePlannedCourses == null)
            {
                return NotFound();
            }

            var thePlannedCourse = await _context.ThePlannedCourses.FindAsync(id);
            if (thePlannedCourse == null)
            {
                return NotFound();
            }
            ViewData["PlanId"] = new SelectList(_context.ThePlans, "Id", "Id", thePlannedCourse.PlanId);
            return View(thePlannedCourse);
        }

        // POST: APE/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(string id, [Bind("PlanId,CourseId,Term,Year")] ThePlannedCourse thePlannedCourse)
        {
            if (id != thePlannedCourse.PlanId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(thePlannedCourse);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ThePlannedCourseExists(thePlannedCourse.PlanId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["PlanId"] = new SelectList(_context.ThePlans, "Id", "Id", thePlannedCourse.PlanId);
            return View(thePlannedCourse);
        }

        // GET: APE/Delete/5
        public async Task<IActionResult> Delete(string id)
        {
            if (id == null || _context.ThePlannedCourses == null)
            {
                return NotFound();
            }

            var thePlannedCourse = await _context.ThePlannedCourses
                .Include(t => t.Plan)
                .FirstOrDefaultAsync(m => m.PlanId == id);
            if (thePlannedCourse == null)
            {
                return NotFound();
            }

            return View(thePlannedCourse);
        }

        // POST: APE/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(string id)
        {
            if (_context.ThePlannedCourses == null)
            {
                return Problem("Entity set 'Project53Context.ThePlannedCourses'  is null.");
            }
            var thePlannedCourse = await _context.ThePlannedCourses.FindAsync(id);
            if (thePlannedCourse != null)
            {
                _context.ThePlannedCourses.Remove(thePlannedCourse);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool ThePlannedCourseExists(string id)
        {
          return (_context.ThePlannedCourses?.Any(e => e.PlanId == id)).GetValueOrDefault();
        }
    }
}
