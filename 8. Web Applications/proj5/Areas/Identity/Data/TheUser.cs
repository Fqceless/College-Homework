using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheUser
{
    public string Name { get; set; } = default!;

    public string Password { get; set; } = default!;

    public string Type { get; set; } = default!;

    public string AspID { get; set; } = default!;

    public virtual ICollection<ThePlan> ThePlans { get; } = new List<ThePlan>();
}
