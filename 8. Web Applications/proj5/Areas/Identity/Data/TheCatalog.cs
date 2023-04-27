using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheCatalog
{
    public short Year { get; set; }

    public virtual ICollection<TheCatalogCourse> TheCatalogCourses { get; } = new List<TheCatalogCourse>();

    public virtual ICollection<TheGenedReq> TheGenedReqs { get; } = new List<TheGenedReq>();

    public virtual ICollection<TheMajorReq> TheMajorReqs { get; } = new List<TheMajorReq>();

    public virtual ICollection<TheMinorReq> TheMinorReqs { get; } = new List<TheMinorReq>();

    public virtual ICollection<ThePlan> ThePlans { get; } = new List<ThePlan>();
}
