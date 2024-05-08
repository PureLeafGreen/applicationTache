import Navbar from "./Navbar";
import React, {useEffect, useState} from "react";
import {createGroupe, joinGroupe} from "../api/GroupeAPI";
import {toast} from "react-hot-toast";
import {useUserContext} from "../UserContext";

function GroupePage() {
    const [groupe, setGroupe] = useState({});
    const [toJoinGroupe, setJoinGroupe] = useState({});
    const [create , setCreate] = useState(false);
    const [errors, setError] = useState({});
    const {user, setUser} = useUserContext();

    function handleCreate() {
        setCreate(!create);
        setError({});
        setGroupe({utilisateurs : [user.id]});
    }

    useEffect(() => {
        let list  = [];
        list.push(user.id);
        setGroupe({...groupe,utilisateurs : list});
    }, []);

    function handleCreation() {
        const errors = {};
        if (!groupe.nom) {
            toast.error("Nom du groupe est requis");
            errors.nom = "Nom du groupe est requis";
        }
        if (!groupe.description) {
            toast.error("Description du groupe est requise");
            errors.description = "Description du groupe est requise";
        }
        if (!groupe.code) {
            toast.error("Code du groupe est requis");
            errors.code = "Code du groupe est requis";
        }
        if (Object.keys(errors).length > 0) {
            setError(errors);
            return;
        }
        console.log("groupe");
        console.log(groupe);
        createGroupe(groupe)
        .then(response => {
            console.log(response.data);
            toast.success("Groupe créé avec succès");
            setUser({...user, groupe : user.groupe.length > 0 ? [...user.groupe, response.data.id] : [response.data.id]});
        })
        .catch(error => {
            console.log(error.response);
            toast.error(error.response?.data || "An error occurred");
        });
    }

    function handleJoin() {
        var errors = {};
        if (!toJoinGroupe.code) {
            toast.error("Code du groupe est requis");
            errors.code = "Code du groupe est requis";
        }
        if (Object.keys(errors).length > 0) {
            setError(errors);
            return;
        }
        joinGroupe(toJoinGroupe.code, user.id)
        .then(response => {
            console.log(response.data);
            toast.success("Groupe rejoint avec succès");
            setUser({...user, groupe : user.groupe.length > 0 ? [...user.groupe, response.data.id] : [response.data.id]});
        })
        .catch(error => {
            console.log(error.response);
            toast.error(error.response?.data || "An error occurred");
        });
    }

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className="text-4xl font-bold mb-4">Rejoindre ou creer un groupe</h1>
            {create ?
                <div className={"flex flex-col w-1/2"}>
                    <form className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                        <label htmlFor="nom">Nom du groupe</label>
                        <input type="text" id="nom" className="mb-2 border-black border-2 rounded" value={groupe.nom || ''} onChange={event => setGroupe({...groupe, nom : event.target.value})}/>
                        {errors.nom && <span className="text-red-500">{errors.nom}</span>}
                        <label htmlFor="description">Description</label>
                        <textarea id="description" className="mb-2 border-black border-2 rounded" value={groupe.description || ''} onChange={event => setGroupe({...groupe, description : event.target.value})}/>
                        {errors.description && <span className="text-red-500">{errors.description}</span>}
                        <label htmlFor={"code"}>Code du groupe</label>
                        <input type="text" id="code" className="mb-2 border-black border-2 rounded" value={groupe.code || ''} onChange={event => setGroupe({...groupe, code : event.target.value})}/>
                        {errors.code && <span className="text-red-500">{errors.code}</span>}
                        <button type={"button"} onClick={function () {handleCreation()}} className="bg-green-500 text-white rounded px-4 py-2 mt-4">Creer</button>
                    </form>
                </div>
                :
                <div className={"flex flex-col w-1/2"}>
                    <form className={"flex flex-col bg-white shadow-lg rounded-lg p-4 "}>
                        <label htmlFor="code">Code du groupe</label>
                        <input type="text" id="code" className="mb-2 border-black border-2 rounded" value={toJoinGroupe.code || ''} onChange={event => setJoinGroupe({...toJoinGroupe, code : event.target.value})}/>
                        <button type={"button"} onClick={function () {handleJoin()}} className="bg-green-500 text-white rounded px-4 py-2 mt-4">Rejoindre</button>
                    </form>
                </div>
            }
            <button type={"button"} onClick={function (){handleCreate()}} className="bg-blue-500 text-white rounded px-4 py-2 mt-4">{create ? "Rejoindre" : "Creer"}</button>
        </div>
    )
}

export default GroupePage;