﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Migrations;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Project53.Data;

#nullable disable

namespace Project53.Migrations
{
    [DbContext(typeof(Project53Context))]
    [Migration("20230414005401_Inital")]
    partial class Inital
    {
        /// <inheritdoc />
        protected override void BuildTargetModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .UseCollation("utf8mb4_general_ci")
                .HasAnnotation("ProductVersion", "7.0.5")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            MySqlModelBuilderExtensions.HasCharSet(modelBuilder, "utf8mb4");

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRole", b =>
                {
                    b.Property<string>("Id")
                        .HasColumnType("varchar(255)");

                    b.Property<string>("ConcurrencyStamp")
                        .IsConcurrencyToken()
                        .HasColumnType("longtext");

                    b.Property<string>("Name")
                        .HasMaxLength(256)
                        .HasColumnType("varchar(256)");

                    b.Property<string>("NormalizedName")
                        .HasMaxLength(256)
                        .HasColumnType("varchar(256)");

                    b.HasKey("Id");

                    b.HasIndex("NormalizedName")
                        .IsUnique()
                        .HasDatabaseName("RoleNameIndex");

                    b.ToTable("AspNetRoles", (string)null);
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRoleClaim<string>", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("ClaimType")
                        .HasColumnType("longtext");

                    b.Property<string>("ClaimValue")
                        .HasColumnType("longtext");

                    b.Property<string>("RoleId")
                        .IsRequired()
                        .HasColumnType("varchar(255)");

                    b.HasKey("Id");

                    b.HasIndex("RoleId");

                    b.ToTable("AspNetRoleClaims", (string)null);
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserClaim<string>", b =>
                {
                    b.Property<int>("Id")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("ClaimType")
                        .HasColumnType("longtext");

                    b.Property<string>("ClaimValue")
                        .HasColumnType("longtext");

                    b.Property<string>("UserId")
                        .IsRequired()
                        .HasColumnType("varchar(255)");

                    b.HasKey("Id");

                    b.HasIndex("UserId");

                    b.ToTable("AspNetUserClaims", (string)null);
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserLogin<string>", b =>
                {
                    b.Property<string>("LoginProvider")
                        .HasMaxLength(128)
                        .HasColumnType("varchar(128)");

                    b.Property<string>("ProviderKey")
                        .HasMaxLength(128)
                        .HasColumnType("varchar(128)");

                    b.Property<string>("ProviderDisplayName")
                        .HasColumnType("longtext");

                    b.Property<string>("UserId")
                        .IsRequired()
                        .HasColumnType("varchar(255)");

                    b.HasKey("LoginProvider", "ProviderKey");

                    b.HasIndex("UserId");

                    b.ToTable("AspNetUserLogins", (string)null);
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserRole<string>", b =>
                {
                    b.Property<string>("UserId")
                        .HasColumnType("varchar(255)");

                    b.Property<string>("RoleId")
                        .HasColumnType("varchar(255)");

                    b.HasKey("UserId", "RoleId");

                    b.HasIndex("RoleId");

                    b.ToTable("AspNetUserRoles", (string)null);
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserToken<string>", b =>
                {
                    b.Property<string>("UserId")
                        .HasColumnType("varchar(255)");

                    b.Property<string>("LoginProvider")
                        .HasMaxLength(128)
                        .HasColumnType("varchar(128)");

                    b.Property<string>("Name")
                        .HasMaxLength(128)
                        .HasColumnType("varchar(128)");

                    b.Property<string>("Value")
                        .HasColumnType("longtext");

                    b.HasKey("UserId", "LoginProvider", "Name");

                    b.ToTable("AspNetUserTokens", (string)null);
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.Project53User", b =>
                {
                    b.Property<string>("Id")
                        .HasColumnType("varchar(255)");

                    b.Property<int>("AccessFailedCount")
                        .HasColumnType("int");

                    b.Property<string>("ConcurrencyStamp")
                        .IsConcurrencyToken()
                        .HasColumnType("longtext");

                    b.Property<string>("Email")
                        .HasMaxLength(256)
                        .HasColumnType("varchar(256)");

                    b.Property<bool>("EmailConfirmed")
                        .HasColumnType("tinyint(1)");

                    b.Property<bool>("LockoutEnabled")
                        .HasColumnType("tinyint(1)");

                    b.Property<DateTimeOffset?>("LockoutEnd")
                        .HasColumnType("datetime(6)");

                    b.Property<string>("NormalizedEmail")
                        .HasMaxLength(256)
                        .HasColumnType("varchar(256)");

                    b.Property<string>("NormalizedUserName")
                        .HasMaxLength(256)
                        .HasColumnType("varchar(256)");

                    b.Property<string>("PasswordHash")
                        .HasColumnType("longtext");

                    b.Property<string>("PhoneNumber")
                        .HasColumnType("longtext");

                    b.Property<bool>("PhoneNumberConfirmed")
                        .HasColumnType("tinyint(1)");

                    b.Property<string>("SecurityStamp")
                        .HasColumnType("longtext");

                    b.Property<bool>("TwoFactorEnabled")
                        .HasColumnType("tinyint(1)");

                    b.Property<string>("UserName")
                        .HasMaxLength(256)
                        .HasColumnType("varchar(256)");

                    b.HasKey("Id");

                    b.HasIndex("NormalizedEmail")
                        .HasDatabaseName("EmailIndex");

                    b.HasIndex("NormalizedUserName")
                        .IsUnique()
                        .HasDatabaseName("UserNameIndex");

                    b.ToTable("AspNetUsers", (string)null);
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheCatalog", b =>
                {
                    b.Property<short>("Year")
                        .HasColumnType("year(4)")
                        .HasColumnName("year");

                    b.HasKey("Year")
                        .HasName("PRIMARY");

                    b.ToTable("the_catalog", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheCatalogCourse", b =>
                {
                    b.Property<short>("CatYear")
                        .HasColumnType("year(4)")
                        .HasColumnName("cat_year");

                    b.Property<string>("CourseId")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("course_id");

                    b.HasKey("CatYear", "CourseId")
                        .HasName("PRIMARY")
                        .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

                    b.HasIndex(new[] { "CatYear" }, "cat_year");

                    b.HasIndex(new[] { "CourseId" }, "course_id");

                    b.ToTable("the_catalog_courses", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheCourse", b =>
                {
                    b.Property<string>("Id")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .UseCollation("latin1_swedish_ci");

                    MySqlPropertyBuilderExtensions.HasCharSet(b.Property<string>("Id"), "latin1");

                    b.Property<string>("Name")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("name");

                    b.Property<decimal>("Credits")
                        .ValueGeneratedOnAdd()
                        .HasPrecision(2, 1)
                        .HasColumnType("decimal(2,1)")
                        .HasColumnName("credits")
                        .HasDefaultValueSql("'3.0'");

                    b.Property<string>("Description")
                        .IsRequired()
                        .HasMaxLength(420)
                        .HasColumnType("varchar(420)")
                        .HasColumnName("description");

                    b.HasKey("Id", "Name")
                        .HasName("PRIMARY")
                        .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

                    b.ToTable("the_courses", (string)null);
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheGenedReq", b =>
                {
                    b.Property<short>("CatYear")
                        .HasColumnType("year(4)")
                        .HasColumnName("cat_year");

                    b.Property<string>("CourseId")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("course_id");

                    b.HasKey("CatYear", "CourseId")
                        .HasName("PRIMARY")
                        .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0 });

                    b.HasIndex(new[] { "CourseId" }, "course_id")
                        .HasDatabaseName("course_id1");

                    b.ToTable("the_gened_reqs", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMajor", b =>
                {
                    b.Property<string>("Name")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("name");

                    b.HasKey("Name")
                        .HasName("PRIMARY");

                    b.ToTable("the_majors", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMajorReq", b =>
                {
                    b.Property<string>("MajorName")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("major_name");

                    b.Property<short>("CatYear")
                        .HasColumnType("year(4)")
                        .HasColumnName("cat_year");

                    b.Property<string>("CourseId")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("course_id");

                    b.Property<string>("CourseType")
                        .IsRequired()
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("course_type");

                    b.HasKey("MajorName", "CatYear", "CourseId")
                        .HasName("PRIMARY")
                        .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0 });

                    b.HasIndex(new[] { "CatYear" }, "cat_year")
                        .HasDatabaseName("cat_year1");

                    b.HasIndex(new[] { "CourseId" }, "course_id")
                        .HasDatabaseName("course_id2");

                    b.HasIndex(new[] { "MajorName" }, "major_name");

                    b.ToTable("the_major_reqs", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMinor", b =>
                {
                    b.Property<string>("Name")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("name");

                    b.HasKey("Name")
                        .HasName("PRIMARY");

                    b.ToTable("the_minors", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMinorReq", b =>
                {
                    b.Property<string>("MinorName")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("minor_name");

                    b.Property<short>("CatYear")
                        .HasColumnType("year(4)")
                        .HasColumnName("cat_year");

                    b.Property<string>("CourseId")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("course_id");

                    b.HasKey("MinorName", "CatYear", "CourseId")
                        .HasName("PRIMARY")
                        .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0 });

                    b.HasIndex(new[] { "CatYear" }, "cat_year")
                        .HasDatabaseName("cat_year2");

                    b.HasIndex(new[] { "CourseId" }, "course_id")
                        .HasDatabaseName("course_id3");

                    b.HasIndex(new[] { "MinorName" }, "minor_name");

                    b.ToTable("the_minor_reqs", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.ThePlan", b =>
                {
                    b.Property<string>("Id")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("id");

                    b.Property<short>("CatYear")
                        .HasColumnType("year(4)")
                        .HasColumnName("cat_year");

                    b.Property<string>("Dmajor")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("dmajor");

                    b.Property<string>("Dminor")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("dminor");

                    b.Property<bool>("IsDefault")
                        .HasColumnType("tinyint(1)")
                        .HasColumnName("is_default");

                    b.Property<string>("Major")
                        .IsRequired()
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("major");

                    b.Property<string>("Minor")
                        .IsRequired()
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("minor");

                    b.Property<string>("Name")
                        .IsRequired()
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("name");

                    b.Property<string>("User")
                        .IsRequired()
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("user");

                    b.HasKey("Id")
                        .HasName("PRIMARY");

                    b.HasIndex(new[] { "Major" }, "!!!_plan_ibfk_2");

                    b.HasIndex(new[] { "Minor" }, "!!!_plan_ibfk_3");

                    b.HasIndex(new[] { "Dmajor" }, "!!!_plan_ibfk_4");

                    b.HasIndex(new[] { "Dminor" }, "!!!_plan_ibfk_5");

                    b.HasIndex(new[] { "CatYear" }, "cat_year")
                        .HasDatabaseName("cat_year3");

                    b.HasIndex(new[] { "User" }, "user");

                    b.ToTable("the_plan", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.ThePlannedCourse", b =>
                {
                    b.Property<string>("PlanId")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("plan_id");

                    b.Property<string>("CourseId")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("course_id");

                    b.Property<string>("Term")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("term");

                    b.Property<short>("Year")
                        .HasColumnType("year(4)")
                        .HasColumnName("year");

                    b.HasKey("PlanId", "CourseId", "Term", "Year")
                        .HasName("PRIMARY")
                        .HasAnnotation("MySql:IndexPrefixLength", new[] { 0, 0, 0, 0 });

                    b.HasIndex(new[] { "CourseId" }, "course_id")
                        .HasDatabaseName("course_id4");

                    b.ToTable("the_planned_courses", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheUser", b =>
                {
                    b.Property<string>("Name")
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("name");

                    b.Property<string>("Password")
                        .IsRequired()
                        .HasMaxLength(69)
                        .HasColumnType("varchar(69)")
                        .HasColumnName("password");

                    b.Property<string>("Type")
                        .IsRequired()
                        .ValueGeneratedOnAdd()
                        .HasMaxLength(7)
                        .HasColumnType("varchar(7)")
                        .HasColumnName("type")
                        .HasDefaultValueSql("'Student'");

                    b.HasKey("Name")
                        .HasName("PRIMARY");

                    b.ToTable("the_user", (string)null);

                    MySqlEntityTypeBuilderExtensions.HasCharSet(b, "latin1");
                    MySqlEntityTypeBuilderExtensions.UseCollation(b, "latin1_swedish_ci");
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityRoleClaim<string>", b =>
                {
                    b.HasOne("Microsoft.AspNetCore.Identity.IdentityRole", null)
                        .WithMany()
                        .HasForeignKey("RoleId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserClaim<string>", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.Project53User", null)
                        .WithMany()
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserLogin<string>", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.Project53User", null)
                        .WithMany()
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserRole<string>", b =>
                {
                    b.HasOne("Microsoft.AspNetCore.Identity.IdentityRole", null)
                        .WithMany()
                        .HasForeignKey("RoleId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("Project53.Areas.Identity.Data.Project53User", null)
                        .WithMany()
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Microsoft.AspNetCore.Identity.IdentityUserToken<string>", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.Project53User", null)
                        .WithMany()
                        .HasForeignKey("UserId")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheCatalogCourse", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.TheCatalog", "CatYearNavigation")
                        .WithMany("TheCatalogCourses")
                        .HasForeignKey("CatYear")
                        .IsRequired()
                        .HasConstraintName("the_catalog_courses_ibfk_1");

                    b.Navigation("CatYearNavigation");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheGenedReq", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.TheCatalog", "CatYearNavigation")
                        .WithMany("TheGenedReqs")
                        .HasForeignKey("CatYear")
                        .IsRequired()
                        .HasConstraintName("the_gened_reqs_ibfk_1");

                    b.Navigation("CatYearNavigation");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMajorReq", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.TheCatalog", "CatYearNavigation")
                        .WithMany("TheMajorReqs")
                        .HasForeignKey("CatYear")
                        .IsRequired()
                        .HasConstraintName("the_major_reqs_ibfk_1");

                    b.HasOne("Project53.Areas.Identity.Data.TheMajor", "MajorNameNavigation")
                        .WithMany("TheMajorReqs")
                        .HasForeignKey("MajorName")
                        .IsRequired()
                        .HasConstraintName("the_major_reqs_ibfk_3");

                    b.Navigation("CatYearNavigation");

                    b.Navigation("MajorNameNavigation");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMinorReq", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.TheCatalog", "CatYearNavigation")
                        .WithMany("TheMinorReqs")
                        .HasForeignKey("CatYear")
                        .IsRequired()
                        .HasConstraintName("the_minor_reqs_ibfk_1");

                    b.HasOne("Project53.Areas.Identity.Data.TheMinor", "MinorNameNavigation")
                        .WithMany("TheMinorReqs")
                        .HasForeignKey("MinorName")
                        .IsRequired()
                        .HasConstraintName("the_minor_reqs_ibfk_3");

                    b.Navigation("CatYearNavigation");

                    b.Navigation("MinorNameNavigation");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.ThePlan", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.TheCatalog", "CatYearNavigation")
                        .WithMany("ThePlans")
                        .HasForeignKey("CatYear")
                        .IsRequired()
                        .HasConstraintName("the_plan_ibfk_6");

                    b.HasOne("Project53.Areas.Identity.Data.TheMajor", "DmajorNavigation")
                        .WithMany("ThePlanDmajorNavigations")
                        .HasForeignKey("Dmajor")
                        .HasConstraintName("the_plan_ibfk_4");

                    b.HasOne("Project53.Areas.Identity.Data.TheMinor", "DminorNavigation")
                        .WithMany("ThePlanDminorNavigations")
                        .HasForeignKey("Dminor")
                        .HasConstraintName("the_plan_ibfk_5");

                    b.HasOne("Project53.Areas.Identity.Data.TheMajor", "MajorNavigation")
                        .WithMany("ThePlanMajorNavigations")
                        .HasForeignKey("Major")
                        .IsRequired()
                        .HasConstraintName("the_plan_ibfk_2");

                    b.HasOne("Project53.Areas.Identity.Data.TheMinor", "MinorNavigation")
                        .WithMany("ThePlanMinorNavigations")
                        .HasForeignKey("Minor")
                        .IsRequired()
                        .HasConstraintName("the_plan_ibfk_3");

                    b.HasOne("Project53.Areas.Identity.Data.TheUser", "UserNavigation")
                        .WithMany("ThePlans")
                        .HasForeignKey("User")
                        .IsRequired()
                        .HasConstraintName("the_plan_ibfk_1");

                    b.Navigation("CatYearNavigation");

                    b.Navigation("DmajorNavigation");

                    b.Navigation("DminorNavigation");

                    b.Navigation("MajorNavigation");

                    b.Navigation("MinorNavigation");

                    b.Navigation("UserNavigation");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.ThePlannedCourse", b =>
                {
                    b.HasOne("Project53.Areas.Identity.Data.ThePlan", "Plan")
                        .WithMany("ThePlannedCourses")
                        .HasForeignKey("PlanId")
                        .IsRequired()
                        .HasConstraintName("the_planned_courses_ibfk_1");

                    b.Navigation("Plan");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheCatalog", b =>
                {
                    b.Navigation("TheCatalogCourses");

                    b.Navigation("TheGenedReqs");

                    b.Navigation("TheMajorReqs");

                    b.Navigation("TheMinorReqs");

                    b.Navigation("ThePlans");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMajor", b =>
                {
                    b.Navigation("TheMajorReqs");

                    b.Navigation("ThePlanDmajorNavigations");

                    b.Navigation("ThePlanMajorNavigations");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheMinor", b =>
                {
                    b.Navigation("TheMinorReqs");

                    b.Navigation("ThePlanDminorNavigations");

                    b.Navigation("ThePlanMinorNavigations");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.ThePlan", b =>
                {
                    b.Navigation("ThePlannedCourses");
                });

            modelBuilder.Entity("Project53.Areas.Identity.Data.TheUser", b =>
                {
                    b.Navigation("ThePlans");
                });
#pragma warning restore 612, 618
        }
    }
}