import Navbar from "./Navbar";
import React, {useState} from "react";
import {createGroupe} from "../api/GroupeAPI";
import {toast} from "react-hot-toast";

function GroupePage() {
    const [groupe, setGroupe] = useState({});
    const [joinGroupe, setJoinGroupe] = useState("");
    const [create , setCreate] = useState(false);

    const handleCreate = () => {
        setCreate(create => !create);
    }

    const handleCreation = () => {
        createGroupe(groupe)
        .then(response => {
            console.log(response.data);
            toast.success("Groupe créé avec succès");
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response?.data || "An error occurred");
        });
    }

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className="text-4xl font-bold mb-4">Rejoindre ou creer un groupe</h1>
            {create ?
                <div className={"flex flex-col"}>
                    <form className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                        <label htmlFor="nom">Nom du groupe</label>
                        <input type="text" id="nom" className="mb-2 border-black border-2 rounded" onChange={event => setCreate({...groupe, nom : event.target.value})}/>
                        <label htmlFor="description">Description</label>
                        <textarea id="description" className="mb-2 border-black border-2 rounded" onChange={event => setCreate({...groupe, description : event.target.value})}/>
                        <label htmlFor={"code"}>Code du groupe</label>
                        <input type="text" id="code" className="mb-2 border-black border-2 rounded" onChange={event => setCreate({...groupe, code : event.target.value})}/>
                        <button onClick={function () {handleCreation()}} className="bg-green-500 text-white rounded px-4 py-2 mt-4">Creer</button>
                    </form>
                </div>
                :
                <div className={"flex flex-col"}>
                    <form className={"flex flex-col bg-white shadow-lg rounded-lg p-4"}>
                        <label htmlFor="code">Code du groupe</label>
                        <input type="text" id="code" className="mb-2 border-black border-2 rounded" onChange={event => setJoinGroupe({...joinGroupe, code : event.target.value})}/>
                        <button className="bg-green-500 text-white rounded px-4 py-2 mt-4">Rejoindre</button>
                    </form>
                </div>
            }
            <button onClick={function (){handleCreate()}} className="bg-blue-500 text-white rounded px-4 py-2 mt-4">{create ? "Rejoindre" : "Creer"}</button>
        </div>
    )
}

export default GroupePage;