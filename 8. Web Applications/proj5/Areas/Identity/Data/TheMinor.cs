using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheMinor
{
    public string Name { get; set; } = null!;

    public virtual ICollection<TheMinorReq> TheMinorReqs { get; } = new List<TheMinorReq>();

    public virtual ICollection<ThePlan> ThePlanDminorNavigations { get; } = new List<ThePlan>();

    public virtual ICollection<ThePlan> ThePlanMinorNavigations { get; } = new List<ThePlan>();
}
