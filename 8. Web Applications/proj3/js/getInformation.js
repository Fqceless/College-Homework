//THIS FILE IS NO LONGER USED IN THE MAIN PROJECT
let myform = document.getElementById("form");

myform.addEventListener("submit", function (event) {
  event.preventDefault();
 
  if (ValidateForm() == 1) {
    alert("Please fill out all fields");
    return;
  }
  
  else if (ValidateForm() == 2) {    
    alert("Please enter a valid email");
    return;
  }

  else if (ValidateForm() == 3) {    
    alert("Please enter a valid year");
    return;
  }

  else if (ValidateForm() == 4) {    
    alert("Please enter a valid first name");
    return;
  }

  else if (ValidateForm() == 5) {    
    alert("Please enter a valid last name");
    return;
  }
  
  post('/all/',{fname,lname,email,startYear})
});

myform.addEventListener("keyup", function (event) {ValidateForm();});

function ValidateForm(){
    fname = document.getElementById("fname").value;
    email = document.getElementById("email").value;
    startYear = document.getElementById("startYear").value;
    lname = document.getElementById("lname").value;
  let Validated = 0;
 
    //Validate form filling
    if (!fname || !email || !startYear || !lname) {
      Validated = 1;
      
    }

    let emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    if (!email.match(emailRegex)) {        
      document.getElementById("email").style.color = "red";
      Validated = 2;     
    }else{document.getElementById("email").style.color = "green";}
    let startYearRegex = /^(20)+([0-9]{2})+$/;
    if (!startYear.match(startYearRegex)) {  
      document.getElementById("startYear").style.color = "red";
      Validated = 3;
 
    }else{document.getElementById("startYear").style.color = "green";}

    let fnameRegex = /^[a-z]+$/i;
    if (!fname.match(fnameRegex)) {  
      document.getElementById("fname").style.color = "red";
      Validated = 4;
 
    }else{document.getElementById("fname").style.color = "green";}
    
    let lnameRegex = /^[a-z]+$/i;
    if (!lname.match(lnameRegex)) {  
      document.getElementById("lname").style.color = "red";
  
    }else{document.getElementById("lname").style.color = "green";}

  //good
  return Validated;
}

