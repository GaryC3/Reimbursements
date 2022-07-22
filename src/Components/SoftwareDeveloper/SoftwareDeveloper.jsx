//Props is object containing all properties passed doen from parent

import axios from "axios";
import { useState, useRef } from 'react';



export const SoftwareDeveloper = ({dev,setDevs, developers}) =>{
    const [isActive1, setIsActive1] = useState(dev.status === "Pending"? false : true);
    const [isActive2, setIsActive2] = useState(dev.status === "Pending"? false : true);
    const notesRef = useRef();
    
    const handleDelete = async (e) =>{
        try{
            e.preventDefault();
            await axios.delete(`http://localhost:8080/reimbursementV3/Servlet`, {data:{id:dev.id}});
            setDevs(developers.filter(developer => dev.id != developer.id))
        }catch(err){
            console.log(err);
        }
    }
    const handleAccept = async (e) =>{
        try{
            if(!isActive1 && !isActive2){
                setIsActive1(current => !current);
                dev.status = "Approved";
                e.preventDefault();
                await axios.put(`http://localhost:8080/reimbursementV3/Servlet`, {
                    id:dev.id, 
                    status:"2", 
                    notes: notesRef.current.value
                });
                //setDevs(developers.filter(developer => dev.id != developer.id))
            }
            notesRef.current.value = null;
        }catch(err){
            console.log(err);
        }
    }
    const handleDeny = async (e) =>{
        try{
            if(!isActive1 && !isActive2){
                setIsActive2(current => !current);
                dev.status = "Denied";
                e.preventDefault();
                await axios.put(`http://localhost:8080/reimbursementV3/Servlet`, {
                    id:dev.id, 
                    status:"3",
                    notes: notesRef.current.value
                });
                //setDevs(developers.filter(developer => dev.id != developer.id))
            }
            notesRef.current.value = null;
        }catch(err){
            console.log(err);
        }
    }

    return(
        
        <tr>
            <td align='center'>{dev.id}</td>
            <td align='center'>{dev.name}</td>
            <td >{dev.reason}</td>
            <td><input id="notes" ref={notesRef} placeholder={dev.notes}/></td>
            <button style ={{ backgroundColor: isActive1 && dev.status === "Approved"? 'green': ''}} onClick={handleAccept}>✔</button>
            <button style ={{ backgroundColor: isActive2 && dev.status === "Denied"? 'red': ''}} onClick={handleDeny}>✘</button>{/* ☒☑ */}
            <button onClick={handleDelete}>Delete</button>
        </tr>
    );
}