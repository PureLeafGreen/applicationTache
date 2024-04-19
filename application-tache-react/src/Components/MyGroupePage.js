import React, {useEffect, useState} from "react";
import {deleteGroupe, getGroupe, getGroupes, getGroupesWithUser} from "../api/GroupeAPI";
import {useUserContext} from "../UserContext";
import {toast} from "react-hot-toast";
import Navbar from "./Navbar";
import {getUser} from "../api/UserAPI";
import {useNavigate} from "react-router-dom";

function MyGroupePage() {
    const {user, setUser} = useUserContext();
    const [groupes, setGroupes] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        console.log(user.groupe);
        getGroupesWithUser(user.groupe)
        .then(response => {
            console.log(response.data);
            setGroupes(response.data);
            toast.success("Groupe récupéré avec succès");
        })
        .catch(error => {
            console.log(error.response);
            toast.error(error?.response.data || "An error occurred");
        })
    }, []);

    function handleGroupEvent(groupeId) {
        navigate(`/user/groupeEvent/${groupeId}`, { state: { groupe : groupeId } });
        console.log("Group Event clicked" +  groupeId);
    }

    function handleAddGroupEvent(groupeId) {
        navigate(`/user/groupeEventForm/${groupeId}`,  { state: { groupe : groupeId } });
    }

    function handleDeleteGroupe(groupeId) {
        deleteGroupe(groupeId)
        .then(response => {
            console.log(response.data);
            toast.success("Groupe supprimé avec succès");
            setGroupes(groupes.filter(groupe => groupe.id !== groupeId));
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response?.data || "An error occurred");
        });
    }

    return (

        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className={"text-2xl font-bold"}>Page de votre groupe</h1>
            <div className={"flex flex-col w-10/12 items-center"}>
                {groupes[0] != null ? groupes.map((groupe) => (
                    <div key={groupe.id} className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg mt-4"}>
                        <h1 className={"text-2xl font-bold"}>{groupe.nom}</h1>
                        <p className={"text-lg"}>{groupe.description}</p>
                        <p className={"text-lg"}>Code : {groupe.code}</p>
                        <p className={"text-lg"}>Administrateur : {groupe.admin.nom}</p>
                        <p className={"text-lg"}>Membres : {groupe.utilisateurs ? groupe.utilisateurs.length : 0}</p>
                        <div className={"flex flex-row"}>
                            {groupe.utilisateurs && groupe.utilisateurs.map((utilisateur, index) => {
                                return (
                                    <div key={index} className={"flex flex-row mr-5"}>
                                        <p className={"text-lg"}>{utilisateur.nom},</p>
                                    </div>
                                )
                            })}
                        </div>
                        <button className={"bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"} onClick={function () {handleGroupEvent(groupe.id)}}>Voir les evenements</button>
                        <button className={"bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"} onClick={function () {handleAddGroupEvent(groupe.id)}}>Ajouter des evenements</button>
                        {/*<button className={"bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"}>Voir les taches</button>*/}
                        {groupe.admin.id === user.id ? <button className={"bg-red-500 text-white p-2 rounded-lg border border-2 border-black"} onClick={function () {handleDeleteGroupe(groupe.id)}}>Supprimer le groupe</button> : null}
                    </div>
                )) :
                    <div className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg"}>
                        <h1 className={"text-2xl font-bold"}>Aucun groupe</h1>
                    </div>
                }
            </div>
        </div>
    )
}

export default MyGroupePage;