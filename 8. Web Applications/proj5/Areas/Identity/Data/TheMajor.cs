using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheMajor
{
    public string Name { get; set; } = null!;

    public virtual ICollection<TheMajorReq> TheMajorReqs { get; } = new List<TheMajorReq>();

    public virtual ICollection<ThePlan> ThePlanDmajorNavigations { get; } = new List<ThePlan>();

    public virtual ICollection<ThePlan> ThePlanMajorNavigations { get; } = new List<ThePlan>();
}
