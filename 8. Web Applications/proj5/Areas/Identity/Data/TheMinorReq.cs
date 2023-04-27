using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheMinorReq
{
    public string MinorName { get; set; } = null!;

    public short CatYear { get; set; }

    public string CourseId { get; set; } = null!;

    public virtual TheCatalog CatYearNavigation { get; set; } = null!;

    public virtual TheMinor MinorNameNavigation { get; set; } = null!;
}
