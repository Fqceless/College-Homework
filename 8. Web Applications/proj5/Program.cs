using Microsoft.AspNetCore.Identity;
using Microsoft.EntityFrameworkCore;
using Project53.Data;
using Project53.Areas.Identity.Data;
var builder = WebApplication.CreateBuilder(args);
var connectionString = builder.Configuration.GetConnectionString("Project53ContextConnection") ?? throw new InvalidOperationException("Connection string 'Project53ContextConnection' not found.");

builder.Services.AddDbContext<Project53Context>(options => options.UseMySql(connectionString, new MySqlServerVersion(new Version(10,4,27))));

builder.Services.AddDefaultIdentity<Project53User>(options => options.SignIn.RequireConfirmedAccount = true).AddEntityFrameworkStores<Project53Context>();

// Add services to the container.
builder.Services.AddControllersWithViews();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.MapRazorPages();
app.Run();
