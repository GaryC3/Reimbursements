import axios from 'axios';
import { useState, useRef } from 'react';
export const SoftWareDeveloperCreate = () =>{
    const[devs, setDevs] = useState([]);
    const[id, setName] = useState("")
    const idRef = useRef();
    const nameRef = useRef(); 
    const reasonRef = useRef();
    const notesRef = useRef();
 
    const handleSubmit = async (event) =>{
        try{
            event.preventDefault(); // prevent default HTML form submission (AKA reload page)
        //Post created Expense
        const {data} = await axios.post('http://localhost:8080/reimbursementV3/Servlet',
            {
                id : idRef.current.value,
                name: nameRef.current.value,
                reason: reasonRef.current.value,
                notes: notesRef.current.value
            }
        );
        setDevs([...devs, data]);
        setName('');

        idRef.current.value = null;
        nameRef.current.value = null;
        reasonRef.current.value = null;
        notesRef.current.value = null;
        } catch(err){
            console.error(err);
        }
    }

    return(
        <>
            <form onSubmit={handleSubmit}>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Reason</th>
                            <th>Notes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            {/* When input updates ref also updates */}
                            <td><input id="id" ref={idRef} placeholder="Employee ID"/></td>
                            <td><input id="name" ref={nameRef} placeholder="Expense Name"/></td>
                            <td><input  id="reason" ref={reasonRef} placeholder="Reason"/></td>
                            <td><input id="notes" ref={notesRef} placeholder="Extra Notes"/></td>
                            <button>Create New Expense</button>
                        </tr>
                    </tbody>
                </table>
            </form>

            
        </>
    )
}