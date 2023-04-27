using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheCourse
{
    public string Id { get; set; } = null!;

    public string Name { get; set; } = null!;

    public string Description { get; set; } = null!;

    public decimal Credits { get; set; }
}
