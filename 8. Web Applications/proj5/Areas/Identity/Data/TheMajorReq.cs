using System;
using System.Collections.Generic;

namespace Project53.Areas.Identity.Data;

public partial class TheMajorReq
{
    public string MajorName { get; set; } = null!;

    public short CatYear { get; set; }

    public string CourseId { get; set; } = null!;

    public string CourseType { get; set; } = null!;

    public virtual TheCatalog CatYearNavigation { get; set; } = null!;

    public virtual TheMajor MajorNameNavigation { get; set; } = null!;
}
