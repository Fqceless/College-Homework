import styles from './lr.module.scss';
import classNames from 'classnames';
import { Console } from 'console';
import { useEffect, useState } from 'react';
import myFunction from './myFunction';

export interface LRProps {
    className?: string;
}

/**
 * This component was created using Codux's Default new component template.
 * To create custom component templates, see https://help.codux.com/kb/en/article/configuration-for-lrs-and-templates
 */


export const LR = ({ className }: LRProps) => {
    const [Data, setData] = useState({object: [{id: "", name: "", desc: "", credits: ""}]});
    function updateData(){
    fetch('http://localhost:4000/courses/year=' + document.getElementById('yearNum')?.getAttribute('value'))
    .then(res => res.json())
    .then(data => setData(data))  
    .catch(err => console.log(err));
    };
    
    return (
        <div>
        <input type="text" id="myInput" className='myInput' onKeyUp={myFunction} placeholder="Search by ID or Name..."></input>
        <div className={classNames(styles.root, className)} onMouseEnter={updateData}>
            <table  className={styles.CatalogTable} id="myTable">
                <tr className="header">
                    <th>Course ID</th>
                    <th>Course Name</th>
                    <th>Description</th>
                    <th>Credits</th>
                </tr>
                {Data.object.map((val, key) => {
                    return (
                        <tr key={key} draggable>
                            <td>{val.id}</td>
                            <td>{val.name}</td>
                            <td>{val.desc}</td>
                            <td>{val.credits}</td>
                        </tr>
                    );
                })}
            </table>
        </div>
        </div>
    );
};
