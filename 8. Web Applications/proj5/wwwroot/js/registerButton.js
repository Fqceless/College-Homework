function scrape() {
    name = document.getElementById("name");
    pass = document.getElementById("password");
    $.post("http://localhost/Project5php/addRegister.php",
        {
            username: name,
            password: pass
        }
    );
    console.log(name, pass);
}