using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Project53.Areas.Identity.Data;
using System.Reflection.Emit;

namespace Project53.Data;

public class Project53Context : IdentityDbContext<Project53User>
{
    public Project53Context(DbContextOptions<Project53Context> options)
        : base(options)
    {
    }

    public virtual DbSet<TheCatalog> TheCatalogs { get; set; }

    public virtual DbSet<TheCatalogCourse> TheCatalogCourses { get; set; }

    public virtual DbSet<TheCourse> TheCourses { get; set; }

    public virtual DbSet<TheGenedReq> TheGenedReqs { get; set; }

    public virtual DbSet<TheMajor> TheMajors { get; set; }

    public virtual DbSet<TheMajorReq> TheMajorReqs { get; set; }

    public virtual DbSet<TheMinor> TheMinors { get; set; }

    public virtual DbSet<TheMinorReq> TheMinorReqs { get; set; }

    public virtual DbSet<ThePlan> ThePlans { get; set; }

    public virtual DbSet<ThePlannedCourse> ThePlannedCourses { get; set; }

    public virtual DbSet<TheUser> TheUsers { get; set; }

    protected override void OnModelCreating(ModelBuilder builder)
    {
        base.OnModelCreating(builder);

        builder
            .UseCollation("utf8mb4_general_ci")
            .HasCharSet("utf8mb4");

        builder.Entity<TheCatalog>(entity =>
        {
            entity.HasKey(e => e.Year).HasName("PRIMARY");

            entity
                .ToTable("the_catalog")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.Property(e => e.Year)
                .ValueGeneratedNever()
                .HasColumnType("year(4)")
            .HasColumnName("year");
        });

        builder.Entity<TheCatalogCourse>(entity =>
        {
            entity.HasKey(e => new { e.CatYear, e.CourseId })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

            entity
                .ToTable("the_catalog_courses")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.HasIndex(e => e.CatYear, "cat_year");

            entity.HasIndex(e => e.CourseId, "course_id");

            entity.Property(e => e.CatYear)
                .HasColumnType("year(4)")
                .HasColumnName("cat_year");
            entity.Property(e => e.CourseId)
                .HasMaxLength(69)
                .HasColumnName("course_id");

            entity.HasOne(d => d.CatYearNavigation).WithMany(p => p.TheCatalogCourses)
                .HasForeignKey(d => d.CatYear)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_catalog_courses_ibfk_1");
        });

        builder.Entity<TheCourse>(entity =>
        {
            entity.HasKey(e => new { e.Id, e.Name })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

            entity.ToTable("the_courses");

            entity.Property(e => e.Id)
                .HasMaxLength(69)
                .UseCollation("latin1_swedish_ci")
                .HasCharSet("latin1");
            entity.Property(e => e.Name)
                .HasMaxLength(69)
                .HasColumnName("name");
            entity.Property(e => e.Credits)
                .HasPrecision(2, 1)
                .HasDefaultValueSql("'3.0'")
                .HasColumnName("credits");
            entity.Property(e => e.Description)
                .HasMaxLength(420)
            .HasColumnName("description");
        });

        builder.Entity<TheGenedReq>(entity =>
        {
            entity.HasKey(e => new { e.CatYear, e.CourseId })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

            entity
                .ToTable("the_gened_reqs")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.HasIndex(e => e.CourseId, "course_id");

            entity.Property(e => e.CatYear)
                .HasColumnType("year(4)")
                .HasColumnName("cat_year");
            entity.Property(e => e.CourseId)
                .HasMaxLength(69)
                .HasColumnName("course_id");

            entity.HasOne(d => d.CatYearNavigation).WithMany(p => p.TheGenedReqs)
                .HasForeignKey(d => d.CatYear)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_gened_reqs_ibfk_1");
        });

        builder.Entity<TheMajor>(entity =>
        {
            entity.HasKey(e => e.Name).HasName("PRIMARY");

            entity
                .ToTable("the_majors")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.Property(e => e.Name)
                .HasMaxLength(69)
            .HasColumnName("name");
        });

        builder.Entity<TheMajorReq>(entity =>
        {
            entity.HasKey(e => new { e.MajorName, e.CatYear, e.CourseId })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0 });

            entity
                .ToTable("the_major_reqs")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.HasIndex(e => e.CatYear, "cat_year");

            entity.HasIndex(e => e.CourseId, "course_id");

            entity.HasIndex(e => e.MajorName, "major_name");

            entity.Property(e => e.MajorName)
                .HasMaxLength(69)
                .HasColumnName("major_name");
            entity.Property(e => e.CatYear)
                .HasColumnType("year(4)")
                .HasColumnName("cat_year");
            entity.Property(e => e.CourseId)
                .HasMaxLength(69)
                .HasColumnName("course_id");
            entity.Property(e => e.CourseType)
                .HasMaxLength(69)
                .HasColumnName("course_type");

            entity.HasOne(d => d.CatYearNavigation).WithMany(p => p.TheMajorReqs)
                .HasForeignKey(d => d.CatYear)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_major_reqs_ibfk_1");

            entity.HasOne(d => d.MajorNameNavigation).WithMany(p => p.TheMajorReqs)
                .HasForeignKey(d => d.MajorName)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_major_reqs_ibfk_3");
        });

        builder.Entity<TheMinor>(entity =>
        {
            entity.HasKey(e => e.Name).HasName("PRIMARY");

            entity
                .ToTable("the_minors")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.Property(e => e.Name)
                .HasMaxLength(69)
            .HasColumnName("name");
        });

        builder.Entity<TheMinorReq>(entity =>
        {
            entity.HasKey(e => new { e.MinorName, e.CatYear, e.CourseId })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0 });

            entity
                .ToTable("the_minor_reqs")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.HasIndex(e => e.CatYear, "cat_year");

            entity.HasIndex(e => e.CourseId, "course_id");

            entity.HasIndex(e => e.MinorName, "minor_name");

            entity.Property(e => e.MinorName)
                .HasMaxLength(69)
                .HasColumnName("minor_name");
            entity.Property(e => e.CatYear)
                .HasColumnType("year(4)")
                .HasColumnName("cat_year");
            entity.Property(e => e.CourseId)
                .HasMaxLength(69)
                .HasColumnName("course_id");

            entity.HasOne(d => d.CatYearNavigation).WithMany(p => p.TheMinorReqs)
                .HasForeignKey(d => d.CatYear)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_minor_reqs_ibfk_1");

            entity.HasOne(d => d.MinorNameNavigation).WithMany(p => p.TheMinorReqs)
                .HasForeignKey(d => d.MinorName)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_minor_reqs_ibfk_3");
        });

        builder.Entity<ThePlan>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("PRIMARY");

            entity
                .ToTable("the_plan")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.HasIndex(e => e.Major, "!!!_plan_ibfk_2");

            entity.HasIndex(e => e.Minor, "!!!_plan_ibfk_3");

            entity.HasIndex(e => e.Dmajor, "!!!_plan_ibfk_4");

            entity.HasIndex(e => e.Dminor, "!!!_plan_ibfk_5");

            entity.HasIndex(e => e.CatYear, "cat_year");

            entity.HasIndex(e => e.User, "user");

            entity.Property(e => e.Id)
                .HasMaxLength(69)
                .HasColumnName("id");
            entity.Property(e => e.CatYear)
                .HasColumnType("year(4)")
                .HasColumnName("cat_year");
            entity.Property(e => e.Dmajor)
                .HasMaxLength(69)
                .HasColumnName("dmajor");
            entity.Property(e => e.Dminor)
                .HasMaxLength(69)
                .HasColumnName("dminor");
            entity.Property(e => e.IsDefault).HasColumnName("is_default");
            entity.Property(e => e.Major)
                .HasMaxLength(69)
                .HasColumnName("major");
            entity.Property(e => e.Minor)
                .HasMaxLength(69)
                .HasColumnName("minor");
            entity.Property(e => e.Name)
                .HasMaxLength(69)
                .HasColumnName("name");
            entity.Property(e => e.User)
                .HasMaxLength(69)
                .HasColumnName("user");

            entity.HasOne(d => d.CatYearNavigation).WithMany(p => p.ThePlans)
                .HasForeignKey(d => d.CatYear)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_plan_ibfk_6");

            entity.HasOne(d => d.DmajorNavigation).WithMany(p => p.ThePlanDmajorNavigations)
                .HasForeignKey(d => d.Dmajor)
                .HasConstraintName("the_plan_ibfk_4");

            entity.HasOne(d => d.DminorNavigation).WithMany(p => p.ThePlanDminorNavigations)
                .HasForeignKey(d => d.Dminor)
                .HasConstraintName("the_plan_ibfk_5");

            entity.HasOne(d => d.MajorNavigation).WithMany(p => p.ThePlanMajorNavigations)
                .HasForeignKey(d => d.Major)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_plan_ibfk_2");

            entity.HasOne(d => d.MinorNavigation).WithMany(p => p.ThePlanMinorNavigations)
                .HasForeignKey(d => d.Minor)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_plan_ibfk_3");

            entity.HasOne(d => d.UserNavigation).WithMany(p => p.ThePlans)
                .HasForeignKey(d => d.User)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_plan_ibfk_1");
        });

        builder.Entity<ThePlannedCourse>(entity =>
        {
            entity.HasKey(e => new { e.PlanId, e.CourseId, e.Term, e.Year })
                .HasName("PRIMARY")
                .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0, 0 });

            entity
                .ToTable("the_planned_courses")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.HasIndex(e => e.CourseId, "course_id");

            entity.Property(e => e.PlanId)
                .HasMaxLength(69)
                .HasColumnName("plan_id");
            entity.Property(e => e.CourseId)
                .HasMaxLength(69)
                .HasColumnName("course_id");
            entity.Property(e => e.Term)
                .HasMaxLength(69)
                .HasColumnName("term");
            entity.Property(e => e.Year)
                .HasColumnType("year(4)")
                .HasColumnName("year");

            entity.HasOne(d => d.Plan).WithMany(p => p.ThePlannedCourses)
                .HasForeignKey(d => d.PlanId)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("the_planned_courses_ibfk_1");
        });

        builder.Entity<TheUser>(entity =>
        {
            entity.HasKey(e => e.Name).HasName("PRIMARY");

            entity
                .ToTable("the_user")
                .HasCharSet("latin1")
                .UseCollation("latin1_swedish_ci");

            entity.Property(e => e.Name)
                .HasMaxLength(69)
                .HasColumnName("name");
            entity.Property(e => e.Password)
                .HasMaxLength(69)
                .HasColumnName("password");
            entity.Property(e => e.Type)
                .HasMaxLength(7)
                .HasDefaultValueSql("'Student'")
                .HasColumnName("type");
            entity.Property(e => e.AspID)
                .HasMaxLength(255)
                .HasColumnName("asp_id");
        });
    }
}
