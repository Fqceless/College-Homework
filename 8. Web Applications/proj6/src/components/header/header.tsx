import styles from './header.module.scss';
import classNames from 'classnames';
import { useState } from 'react';
import changePid from './changePid';

export interface HeaderProps {
    className?: string;
}

/**
 * This component was created using Codux's Default new component template.
 * To create custom component templates, see https://help.codux.com/kb/en/article/configuration-for-headers-and-templates
 */
export const Header = ({ className }: HeaderProps) => {
    const [Data, setData] = useState({object: [{id: "", user: "", name: "", is_default: "",  major: "",  dmajor: "",  minor: "" , dminor: "" ,cat_year: "" ,notes:""}]});
    const [Plans, setPlans] = useState({object: [{id: "", user: "", name: ""}]});
    const [Credits, setCredits] = useState({object: [{credits: "" }]});
    var sum:Number = 0;
    Credits.object.forEach(function(course){
        sum = sum + course.credits as unknown as Number;
    });
    
    
    function updateData(){
    fetch('http://localhost:4000/plans/id=' + document.getElementById('planId')?.getAttribute('value'))
        .then(res => res.json())
        .then(data => setData(data))  
        .catch(err => console.log(err));

    fetch('http://localhost:4000/courses/pid=' + document.getElementById('planId')?.getAttribute('value'))
        .then(res => res.json())
        .then(data => setCredits(data))  
        .catch(err => console.log(err));
    }
    function updatePlans(){
    fetch('http://localhost:4000/plans/name=' + Data.object[0].user)
        .then(res => res.json())
        .then(plans => setPlans(plans))  
        .catch(err => console.log(err));
    }

    function LogOut(){
        window.location.reload()
    }

    
    
    return (
        <div className={classNames(styles.root, className)} >
            <div className={styles.Header} onMouseEnter={updateData}>
                <div className={classNames(styles.HeaderSelect, styles.headerInfo)}>
                    <h1>R&amp;E APE</h1>
                    <p>
                        <strong>R</strong>eact <strong>&amp; E</strong>xpress <strong>A</strong>
                        cademic <strong>P</strong>lanning <strong>E</strong>
                        nvironment
                    </p>
                </div>
                <div className={classNames('studentInfo', styles.studentInfo)}>
                    <div id="infoStudent">
                        <p className="infoP">
                            <strong>Student:</strong> {Data.object[0].user}
                        </p>
                    </div>
                    {Data.object.map((user, key) =>{
                        if(user.dmajor !== null && user.dmajor !== ""){
                            return(
                                <div id="infoMajor">
                                    <input type="hidden" id="MajorName" value={Data.object[0].major} />
                                    <p className="infoP">
                                        <strong>Major:</strong> {Data.object[0].major} + {Data.object[0].dmajor}
                                    </p>
                                </div>
                            )
                        }
                        else{
                            return(
                                <div id="infoMajor">
                                    <input type="hidden" id="MajorName" value={Data.object[0].major} />
                                    <p className="infoP">
                                        <strong>Major:</strong> {Data.object[0].major}
                                    </p>
                                </div>
                            )
                        }
                    })}

                    <div id="infoCatalog">
                        <input type="hidden" id="yearNum" value={Data.object[0].cat_year} />
                        <p className="infoP">
                            <strong>Catalog:</strong> {Data.object[0].cat_year}
                        </p>
                    </div>


                    {Data.object.map((user, key) =>{
                        if(user.dminor !== null && user.dminor !== ""){
                            return(
                                <div id="infoMinor">
                                    <input type="hidden" id="MinorName" value={Data.object[0].minor} />
                                    <p className="infoP">
                                        <strong>Minor:</strong> {Data.object[0].minor} + {Data.object[0].dminor}
                                    </p>
                                </div>
                            )
                        }
                        else{
                            return(
                                <div id="infoMinor">
                                    <input type="hidden" id="MinorName" value={Data.object[0].minor} />
                                    <p className="infoP">
                                        <strong>Minor:</strong> {Data.object[0].minor}
                                    </p>
                                </div>  
                            )
                        }
                    })}
                    
                    <div id="infoCredits">
                        <input type="hidden" id="totalCredits"/>
                        <p className="infoP">
                            <strong>Credits:</strong> {sum as unknown as string}
                        </p>
                    </div>
                </div>
                <div className={styles.headerButtons}>
                    <div>
                        <select id="plans" className={styles.ChoosePlan} onClick={updatePlans} onChange={changePid}>
                            <option>Choose Plan:</option>
                            {Plans.object.map((plan, key) => {
                                return(<option value={plan.id}>{plan.name}</option>)
                            })}
                        </select>
                    </div>
                    <div>
                        <button onClick={LogOut}>
                            Log Out
                        </button>
                    </div>
                </div>
            </div>
        </div>
    ); 
};
