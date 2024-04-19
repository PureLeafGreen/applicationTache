import Navbar from "./Navbar";
import React, { useEffect, useState } from "react";
import Evenement from "../Modeles/Evenement";
import { useUserContext } from "../UserContext";
import { deleteEvent, getEventsByGroup } from "../api/EventAPI";
import { toast } from "react-hot-toast";
import { useLocation } from "react-router-dom";

function GroupeEventsPage() {
    const [evenements, setEvents] = useState([]);
    const { user } = useUserContext();
    const location = useLocation();
    const [selectGroup, setSelectGroup] = useState(null);

    useEffect(() => {
        if (location.state) {
            const { groupe } = location.state;
            setSelectGroup(groupe);
        }
    }, [location.state]);

    useEffect(() => {
        if (selectGroup == null) {
            return;
        }
        getEventsByGroup(selectGroup)
            .then(response => {
                if (typeof response.data === 'string') {
                    setEvents([]);
                } else {
                    setEvents(response.data);
                }
            })
            .catch(error => {
                console.log(error.response?.data);
                toast.error(error.response?.data || "An error occurred");
            });
    }, [selectGroup]);

    function handleDeleteEvent(eventId) {
        deleteEvent(eventId)
            .then(response => {
                console.log(response.data);
                toast.success("Evenement supprimé avec succès");
                setEvents(evenements.filter(event => event.id !== eventId));
            })
            .catch(error => {
                console.log(error.response.data);
                toast.error(error.response?.data || "An error occurred");
            });
    }

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <h1 className="text-4xl font-bold mb-4">Evenements de votre groupe</h1>
            <div className="flex flex-grow flex-col w-1/2 overflow-auto p-4 space-y-4">
                {evenements.length > 0 ?
                    evenements.map((evenement) => (
                        <div key={evenement.id} className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                            <h2 className="text-2xl font-bold mb-2">{evenement.nom}</h2>
                            <p className="mb-2">{evenement.description}</p>
                            <p className="mb-2">{evenement.dateDebut}</p>
                            <p className="mb-2">{evenement.dateFin}</p>
                            <p className="mb-2">Créé par : {evenement.utilisateur ? evenement.utilisateur.nom : "Utilisateur inconnu"}</p>
                            <button type={"button"} onClick={() => handleDeleteEvent(evenement.id)} className="bg-red-500 text-white rounded px-4 py-2 mt-4">Supprimer</button>
                        </div>
                    )) :
                    <div className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                        <p className="text-2xl font-bold mb-2">Aucun événement</p>
                    </div>
                }
                <button className={"p-4 m-4 bg-red-500 text-white rounded-lg"} onClick={() => window.history.back()}>Retour</button>
            </div>
        </div>
    );
}

export default GroupeEventsPage;
