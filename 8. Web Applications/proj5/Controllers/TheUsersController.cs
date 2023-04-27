using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using Project53.Areas.Identity.Data;
using Project53.Data;

namespace Project53.Controllers
{
    public class TheUsersController : Controller
    {
        
        private readonly Project53Context _context;

        public TheUsersController(Project53Context context)
        {
            _context = context;
        }

        // GET: TheUsers
        
        public async Task<IActionResult> Index()
        {
              return _context.TheUsers != null ? 
                          View(await _context.TheUsers.ToListAsync()) :
                          Problem("Entity set 'Project53Context.TheUsers'  is null.");
        }

        // GET: TheUsers/Details/5
        public async Task<IActionResult> Details(string id)
        {
            if (id == null || _context.TheUsers == null)
            {
                return NotFound();
            }

            var theUser = await _context.TheUsers
                .FirstOrDefaultAsync(m => m.Name == id);
            if (theUser == null)
            {
                return NotFound();
            }

            return View(theUser);
        }

        // GET: TheUsers/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: TheUsers/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Name,Password,Type")] TheUser theUser)
        {
            if (ModelState.IsValid)
            {
                _context.Add(theUser);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(theUser);
        }

        // GET: TheUsers/Edit/5
        public async Task<IActionResult> Edit(string id)
        {
            if (id == null || _context.TheUsers == null)
            {
                return NotFound();
            }

            var theUser = await _context.TheUsers.FindAsync(id);
            if (theUser == null)
            {
                return NotFound();
            }
            return View(theUser);
        }

        // POST: TheUsers/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(string id, [Bind("Name,Password,Type")] TheUser theUser)
        {
            if (id != theUser.Name)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(theUser);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!TheUserExists(theUser.Name))
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
            return View(theUser);
        }

        // GET: TheUsers/Delete/5
        public async Task<IActionResult> Delete(string id)
        {
            if (id == null || _context.TheUsers == null)
            {
                return NotFound();
            }

            var theUser = await _context.TheUsers
                .FirstOrDefaultAsync(m => m.Name == id);
            if (theUser == null)
            {
                return NotFound();
            }

            return View(theUser);
        }

        // POST: TheUsers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(string id)
        {
            if (_context.TheUsers == null)
            {
                return Problem("Entity set 'Project53Context.TheUsers'  is null.");
            }
            var theUser = await _context.TheUsers.FindAsync(id);
            if (theUser != null)
            {
                _context.TheUsers.Remove(theUser);
            }
            
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool TheUserExists(string id)
        {
          return (_context.TheUsers?.Any(e => e.Name == id)).GetValueOrDefault();
        }
    }
}
