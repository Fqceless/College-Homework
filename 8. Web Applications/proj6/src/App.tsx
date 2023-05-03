import styles from './App.module.scss';
import { Planner } from './components/planner/planner';
import { FacultyPage } from './components/faculty-page/faculty-page';

function App() {
    function onFormSubmit(e: React.SyntheticEvent<HTMLFormElement>) {
        e.preventDefault();

        const formData: Record<string, string> = {};

        function isInputNamedElement(e: Element): e is HTMLInputElement & { name: string } {
            return 'value' in e && 'name' in e;
        }
        Array.from(e.currentTarget.elements).filter(isInputNamedElement).forEach((field) => {
            if (!field.name) return;
            formData[field.name] = field.value;
        });

        //ran out of time to grab from database
        //this would be easy to implement:
        //grab the users matching the text in the username field
        //if password matches, send them to a page based on their type
        //else, send alert
        if ((formData.name === "Josh" || formData.name === "josh") && formData.password === "1234") {
            Show("FacultyPage");
        }
        else if ((formData.name === "Joe" || formData.name === "joe") && formData.password === "mama") {
            Show("PlannerPage");
        }
        else if ((formData.name === "Chris" || formData.name === "chris") && formData.password === "lafafafa") {
            Show("PlannerPage");
        }
        else{
            alert("Incorrect username or password");
        }

        function Show(page:string){            
            var n = document.getElementById(formData.name)?.getAttribute("value");    
            document.getElementById("planId")?.setAttribute("value", n as string);
               
            if (page === "FacultyPage"){
                window.document.getElementById("Faculty")?.setAttribute("style", "display: block;");
                window.document.getElementById("Login")?.setAttribute("style", "display: none;");
                window.document.getElementById("Planner")?.setAttribute("style", "display: none;");
            }
            else if (page === "PlannerPage"){
                window.document.getElementById("Faculty")?.setAttribute("style", "display: none;");
                window.document.getElementById("Login")?.setAttribute("style", "display: none;");
                window.document.getElementById("Planner")?.setAttribute("style", "display: block;");   
            }
            else if (page === "LoginPage"){
                window.document.getElementById("Faculty")?.setAttribute("style", "display: none;");
                window.document.getElementById("Login")?.setAttribute("style", "display: block;");
                window.document.getElementById("Planner")?.setAttribute("style", "display: none;");
            }
        }
    }
    return (
        <div className={styles.App}>
            <input id="planId" style={{display: "none"}} value=""/>
            <div id="Login" >
                <div className={styles.LoginOrginizer}>
                <h1>Login:</h1>
                <form className={styles.LoginForm} onSubmit={onFormSubmit}>
                    <label>Name:</label>
                    <br />
                    <input name="name" id="name" type="text" />
                    <br />
                    <label>Password:</label>
                    <br />
                    <input name="password" id="password" type="text" />
                    <br />
                    <br />
                    <button>Submit</button> 
                </form>
                </div>
            </div>
            <div id="Faculty" className={styles.FacultyPage}>
                <FacultyPage />
            </div>
            <div id="Planner" className={styles.Planner}>
                <Planner />
            </div>
        </div>       
    );
}

export default App;
