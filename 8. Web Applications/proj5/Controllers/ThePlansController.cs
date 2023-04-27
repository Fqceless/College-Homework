using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Project53.Areas.Identity.Data;
using Project53.Data;

namespace Project53.Controllers
{
    public class ThePlansController : Controller
    {
        private readonly Project53Context _context;

        public ThePlansController(Project53Context context)
        {
            _context = context;
        }
        
        // GET: ThePlans
        public async Task<IActionResult> Index()
        {
            var project53Context = _context.ThePlans.Include(t => t.CatYearNavigation).Include(t => t.DmajorNavigation).Include(t => t.DminorNavigation).Include(t => t.MajorNavigation).Include(t => t.MinorNavigation).Include(t => t.UserNavigation);
            return View(await project53Context.ToListAsync());
        }

        // GET: ThePlans/Details/5
        public async Task<IActionResult> Details(string id)
        {
            if (id == null || _context.ThePlans == null)
            {
                return NotFound();
            }

            var thePlan = await _context.ThePlans
                .Include(t => t.CatYearNavigation)
                .Include(t => t.DmajorNavigation)
                .Include(t => t.DminorNavigation)
                .Include(t => t.MajorNavigation)
                .Include(t => t.MinorNavigation)
                .Include(t => t.UserNavigation)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (thePlan == null)
            {
                return NotFound();
            }

            return View(thePlan);
        }

        // GET: ThePlans/Create
        public IActionResult Create()
        {
            ViewData["CatYear"] = new SelectList(_context.TheCatalogs, "Year", "Year");
            ViewData["Dmajor"] = new SelectList(_context.TheMajors, "Name", "Name");
            ViewData["Dminor"] = new SelectList(_context.TheMinors, "Name", "Name");
            ViewData["Major"] = new SelectList(_context.TheMajors, "Name", "Name");
            ViewData["Minor"] = new SelectList(_context.TheMinors, "Name", "Name");
            ViewData["User"] = new SelectList(_context.TheUsers, "Name", "Name");
            return View();
        }

        // POST: ThePlans/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Id,User,Name,IsDefault,Major,Dmajor,Minor,Dminor,CatYear")] ThePlan thePlan)
        {
            if (ModelState.IsValid)
            {
                _context.Add(thePlan);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["CatYear"] = new SelectList(_context.TheCatalogs, "Year", "Year", thePlan.CatYear);
            ViewData["Dmajor"] = new SelectList(_context.TheMajors, "Name", "Name", thePlan.Dmajor);
            ViewData["Dminor"] = new SelectList(_context.TheMinors, "Name", "Name", thePlan.Dminor);
            ViewData["Major"] = new SelectList(_context.TheMajors, "Name", "Name", thePlan.Major);
            ViewData["Minor"] = new SelectList(_context.TheMinors, "Name", "Name", thePlan.Minor);
            ViewData["User"] = new SelectList(_context.TheUsers, "Name", "Name", thePlan.User);
            return View(thePlan);
        }

        // GET: ThePlans/Edit/5
        public async Task<IActionResult> Edit(string id)
        {
            if (id == null || _context.ThePlans == null)
            {
                return NotFound();
            }

            var thePlan = await _context.ThePlans.FindAsync(id);
            if (thePlan == null)
            {
                return NotFound();
            }
            ViewData["CatYear"] = new SelectList(_context.TheCatalogs, "Year", "Year", thePlan.CatYear);
            ViewData["Dmajor"] = new SelectList(_context.TheMajors, "Name", "Name", thePlan.Dmajor);
            ViewData["Dminor"] = new SelectList(_context.TheMinors, "Name", "Name", thePlan.Dminor);
            ViewData["Major"] = new SelectList(_context.TheMajors, "Name", "Name", thePlan.Major);
            ViewData["Minor"] = new SelectList(_context.TheMinors, "Name", "Name", thePlan.Minor);
            ViewData["User"] = new SelectList(_context.TheUsers, "Name", "Name", thePlan.User);
            return View(thePlan);
        }

        // POST: ThePlans/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(string id, [Bind("Id,User,Name,IsDefault,Major,Dmajor,Minor,Dminor,CatYear")] ThePlan thePlan)
        {
            if (id != thePlan.Id)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(thePlan);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!ThePlanExists(thePlan.Id))
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
            ViewData["CatYear"] = new SelectList(_context.TheCatalogs, "Year", "Year", thePlan.CatYear);
            ViewData["Dmajor"] = new SelectList(_context.TheMajors, "Name", "Name", thePlan.Dmajor);
            ViewData["Dminor"] = new SelectList(_context.TheMinors, "Name", "Name", thePlan.Dminor);
            ViewData["Major"] = new SelectList(_context.TheMajors, "Name", "Name", thePlan.Major);
            ViewData["Minor"] = new SelectList(_context.TheMinors, "Name", "Name", thePlan.Minor);
            ViewData["User"] = new SelectList(_context.TheUsers, "Name", "Name", thePlan.User);
            return View(thePlan);
        }

        // GET: ThePlans/Delete/5
        public async Task<IActionResult> Delete(string id)
        {
            if (id == null || _context.ThePlans == null)
            {
                return NotFound();
            }

            var thePlan = await _context.ThePlans
                .Include(t => t.CatYearNavigation)
                .Include(t => t.DmajorNavigation)
                .Include(t => t.DminorNavigation)
                .Include(t => t.MajorNavigation)
                .Include(t => t.MinorNavigation)
                .Include(t => t.UserNavigation)
                .FirstOrDefaultAsync(m => m.Id == id);
            if (thePlan == null)
            {
                return NotFound();
            }

            return View(thePlan);
        }

        // POST: ThePlans/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(string id)
        {
            if (_context.ThePlans == null)
            {
                return Problem("Entity set 'Project53Context.ThePlans'  is null.");
            }
            var thePlan = await _context.ThePlans.FindAsync(id);
            if (thePlan != null)
            {
                _context.ThePlans.Remove(thePlan);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool ThePlanExists(string id)
        {
          return (_context.ThePlans?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
