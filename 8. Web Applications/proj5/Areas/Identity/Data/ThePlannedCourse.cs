using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class ThePlannedCourse
{
    public string PlanId { get; set; } = null!;

    public string CourseId { get; set; } = null!;

    public string Term { get; set; } = null!;

    public short Year { get; set; }

    public virtual ThePlan Plan { get; set; } = null!;
}
