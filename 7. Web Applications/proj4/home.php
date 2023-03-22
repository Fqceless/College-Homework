<?php
session_start();
    $plan_id = $_SESSION['plan_id'];
    $name = $_SESSION['name'];
if ($plan_id != 0){

}else{
    header('Location: login.php');
}

?><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Web Apps Project 4</title>
    
    

    <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="//cdn.datatables.net/1.11.4/js/jquery.dataTables.min.js"></script>
    <script src="//code.jquery.com/ui/1.13.0/jquery-ui.min.js"></script>
    <script src="js/objects.js"></script>
    <script src="js/thebutton.js"></script>
    

    <link rel="stylesheet" href="./CSS/inline2.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/mint-choc/jquery-ui.css">
    <link rel="stylesheet" href="//cdn.datatables.net/1.11.4/css/jquery.dataTables.min.css">
    
 
    
    
    
    
</head>
<body>
    <div class="grid-container">
        <div class="header">
            <div>
                <h1>SPAPE</h1>
                <p><strong>S</strong>aint <strong>P</strong>atrick's <strong>A</strong>cademic <strong>P</strong>lanning <strong>E</strong>nvironment</p>
            </div>

            <div class="studentInfo">                  

                    <div id="infoStudent">
                        <p class="infoP"><strong>Student:</strong> Joe Mamma</p>
                        
                    </div>

                    <div id="infoMajor">
                        <p class="infoP"><strong>Major:</strong> Computer Science</p>
                    </div>

                    <div id="infoCatalog">
                        <p class="infoP"><strong>Catalog:</strong> 1994</p>
                    </div>

                    <div id="infoMinor">
                        <p class="infoP"><strong>Minor:</strong> Bible</p>
                    </div>
                
            </div>
            <script src="js/whoami.js"></script>
            
            <div class="headerButtons">
                
                
                    <button id="log">
                        <img src="Pictures/powerOff.png" alt="log out"><br>
                        <strong>Log Out</strong>
                    </button>
                
                <div class="options">
                    <button>
                    <img src="Pictures/settingsCog.png" alt="options"><br>
                    <strong>Choose Plan:</strong><br>
                    
                    <select id = "plans">  
                    <option>Select A Plan</option>
                    <!--inserts-->
                    </select>  
                    </button>
                    
                    
                </div> 
                
                    <button>
                        <img src="Pictures/saveAs.png" alt="save"><br>
                        <strong>Save</strong>
                    </button>
                
            </div>
        </div>

        <div class="courseReqs" ondrop="drop(event)" ondragover="allowDrop(event)">
            <div id="accordion"></div>
            <script src="./js/acc.js"></script>
            
        </div>

        <div class="schedule">        
            <div class="grid-Schedule">
                
                <script src="js/monthboxInsert.js"></script>
                
            </div>
        </div>

        <div class="validation">
            
            <h1>Links:</h1>
            <ul>
                <li><a href="http://judah.cedarville.edu/~laFave/cs3220.html">Chris LaFave<br></a></li>
                <li><a href="http://judah.cedarville.edu/~curry/cs3220.html">Josh Curry<br></a></li>
                <li><a href="http://judah.cedarville.edu/">Home Page<br></a></li>
            </ul>
        </div>

        <div class="courseFinder">
            <div id="catalog">              
                <table id="catalogTable">
                  <thead>
                    <tr>
                      <th>Course ID</th>
                      <th>Course Name</th>
                      <th>Description</th>
                      <th>Credits</th>
                    </tr>
                  </thead>
                  <tbody>
                    <!-- catalog data will be dynamically added here -->
                  </tbody>
                </table>
              </div>
            
        </div>
    </div>
    
    <script src="./js/table.js"></script>
    
</body>

</html>
