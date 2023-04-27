using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class ThePlan
{
    public string Id { get; set; } = null!;

    public string User { get; set; } = null!;

    public string Name { get; set; } = null!;

    public bool IsDefault { get; set; }

    public string Major { get; set; } = null!;

    public string? Dmajor { get; set; }

    public string Minor { get; set; } = null!;

    public string? Dminor { get; set; }

    public short CatYear { get; set; }

    public virtual TheCatalog CatYearNavigation { get; set; } = null!;

    public virtual TheMajor? DmajorNavigation { get; set; }

    public virtual TheMinor? DminorNavigation { get; set; }

    public virtual TheMajor MajorNavigation { get; set; } = null!;

    public virtual TheMinor MinorNavigation { get; set; } = null!;

    public virtual ICollection<ThePlannedCourse> ThePlannedCourses { get; } = new List<ThePlannedCourse>();

    public virtual TheUser UserNavigation { get; set; } = null!;
}
