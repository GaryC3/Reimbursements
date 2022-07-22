import axios from 'axios';
import { useState, useEffect } from 'react';
import { SoftwareDeveloper } from './SoftwareDeveloper';
export const SoftWareDeveloperList = () =>{
    const[devs, setDevs] = useState([]);

    useEffect(() => {   //When you open list it populates with list of reimbursement requests

        // axios.get('https://my-json-server.typicode.com/skillstorm-walsh/employees-v1/employees')
    //         .then(res => setDevs(res.data)); // sets my devs state to be that array of devs
    // }, []); // Be sure this is an empty array
        axios.get('http://localhost:8080/reimbursementV3/Servlet')
            .then(res => {setDevs(res.data)}) // sets my devs state to be that array of devs

    
    
        }, []);

    return(
        <>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Expense</th>
                        <th>Reason</th>
                        <th>Notes</th>
                    </tr>
                </thead>
                <tbody>
                    {devs.map((dev) =>{
                        return(
                            <SoftwareDeveloper key={dev.id} dev={dev} developers={devs} setDevs={setDevs} />
                        );
                    })}
                </tbody>
            </table>
        </>
    )

}