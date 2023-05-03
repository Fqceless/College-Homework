import styles from './ul.module.scss';
import classNames from 'classnames';
import Accordion from '../Accordion';
import { useEffect, useState } from 'react';

export interface ULProps {
    className?: string;
}

/**
 * This component was created using Codux's Default new component template.
 * To create custom component templates, see https://help.codux.com/kb/en/article/configuration-for-uls-and-templates
 */
export const UL = ({ className }: ULProps) => {
  const [MajorData, setMajorData] = useState({object: [{course_id: "", name: "", course_type: ""}]});
  const [MinorData, setMinorData] = useState({object: [{course_id: "", name: ""}]});
  const [GenEdData, setGenEdData] = useState({object: [{course_id: "", name: ""}]});
  function updateData(){
    var Mj = document.getElementById('MajorName')?.getAttribute('value');
    var Mn = document.getElementById('MinorName')?.getAttribute('value');
    fetch('http://localhost:4000/reqs/major=' + Mj).then(res => res.json()).then(data => setMajorData(data)).catch(err => console.log(err));
    fetch('http://localhost:4000/reqs/minor=' + Mn).then(res => res.json()).then(data => setMinorData(data)).catch(err => console.log(err));
    fetch('http://localhost:4000/reqs/geneds').then(res => res.json()).then(data => setGenEdData(data)).catch(err => console.log(err));
  };
  const accordionItems = [
      {
        title: 'Major Core Requirements',
        content: (
          <ul>
           {MajorData.object.map((item) => {
              if (item.course_type == "Core"){
                return(
                <li draggable>
                  {item.course_id} - {item.name}
                </li>)
                }
            })}
           </ul>
        ),
      },
      {
        title: 'Major Cognate Requirements',
        content: (
          <ul>
           {MajorData.object.map((item) => {
              if (item.course_type == "Cognate"){
                return(
                <li draggable>
                  {item.course_id} - {item.name}
                </li>)
                }
            })}
           </ul>
        ),
      },
      {
        title: 'Major Elective Requirements',
        content: (
          <ul>
           {MajorData.object.map((item) => {
              if (item.course_type == "Elective"){
                return(
                <li draggable>
                  {item.course_id} - {item.name}
                </li>)
                }
            })}
           </ul>
        ),
      },
      {
        title: 'Minor Requirements',
        content: (
          <ul>
            {MinorData.object.map((item) => {
              return(
                <li draggable>
                  {item.course_id} - {item.name}
                </li>
              );
            })}
          </ul>
        ),
      },
      {
        title: 'GenEd Requirements',
        content: (
          <ul>
            {GenEdData.object.map((item) => {
              return(
                <li draggable>
                  {item.course_id} - {item.name}
                </li>
              );
            })}
          </ul>
        ),
      }
  ];
    return (
        <div id="ACC" className={classNames(styles.root, className)} onMouseEnter={updateData}>
            <Accordion items={accordionItems} />            
        </div>
    );
};
