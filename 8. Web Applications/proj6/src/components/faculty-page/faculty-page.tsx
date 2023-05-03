import styles from './faculty-page.module.scss';
import classNames from 'classnames';
import { useEffect, useState } from 'react';

export interface FacultyPageProps {
    className?: string;
}

/**
 * This component was created using Codux's Default new component template.
 * To create custom component templates, see https://help.codux.com/kb/en/article/configuration-for-faculty-pages-and-templates
 */
 
    
export const FacultyPage = ({ className }: FacultyPageProps) => {

    const [Data, setData] = useState({object: [{name: "string", password: "string", type: "string", planId: "string" }]});

    useEffect(() => {
    fetch('http://localhost:4000/users/')
    .then(res => res.json())
    .then(data => setData(data))  
    .catch(err => console.log(err));
    }, []);

    function PlanChose(event:any) {
        var r = event.target;
        document.getElementById("planId")?.setAttribute("value", r.value);
        document.getElementById("Faculty")?.setAttribute("style", "display: none;");
        document.getElementById("Login")?.setAttribute("style", "display: none;");
        document.getElementById("Planner")?.setAttribute("style", "display: block;");
    };
    return (
        <div className={classNames(styles.root, className)}>
            <h1>Welcome Faculty!</h1>
            <h2>Your students are:</h2>
            <table className={styles.CatalogTable}>
                <thead>
                    <th>Student Name</th>
                    <th>Password</th>
                    <th>Type</th>
                    <th>Default Plan</th>
                </thead>
                {Data.object.map((val, key) => {
                    return (
                        <tbody key={key}>
                            <td>{val.name}</td>
                            <td>{val.password}</td>
                            <td>{val.type}</td>
                            <td>
                            <button id={val.name} onClick={PlanChose} value={val.planId}>Click Me!</button>
                            </td>
                        </tbody>
                    );
                })}
            </table>
        </div>
    );
};
