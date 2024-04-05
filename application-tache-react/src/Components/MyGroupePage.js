import React, {useEffect, useState} from "react";
import {getGroupe} from "../api/GroupeAPI";
import {useUserContext} from "../UserContext";
import {toast} from "react-hot-toast";
import Navbar from "./Navbar";

function MyGroupePage() {
    const {user, setUser} = useUserContext();
    const [groupe, setGroupe] = useState({});

    useEffect(() => {
        getGroupe(user.groupe)
        .then(response => {
            console.log(response.data);
            setGroupe(response.data);
            toast.success("Groupe récupéré avec succès");
        })
        .catch(error => {
            console.log(error.response);
            toast.error("An error occurred");
        })
    }, []);

    return (

        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className={"text-2xl font-bold"}>Page de votre groupe</h1>
            <div className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg"}>
                <h1 className={"text-2xl font-bold"}>{groupe.nom}</h1>
                <p className={"text-lg"}>{groupe.description}</p>
                <p className={"text-lg"}>Code : {groupe.code}</p>
                <p className={"text-lg"}>Membres : {groupe.utilisateurs ? groupe.utilisateurs.length : 0}</p>
                {groupe.utilisateurs && groupe.utilisateurs.map((utilisateur, index) => {
                    return (
                        <div key={index} className={"flex flex-row"}>
                            <p className={"text-lg"}>{utilisateur}</p>
                        </div>
                    )
                })}
            </div>
        </div>
    )
}

export default MyGroupePage;