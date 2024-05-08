import React, {useEffect, useState} from "react";
import {deleteEvent, getEvents, getEventsByUser} from "../api/EventAPI";
import {toast} from "react-hot-toast";
import Navbar from "./Navbar";
import Evenement from "../Modeles/Evenement";
import {useUserContext} from "../UserContext";

function EventsPage() {

    const [evenements, setEvents] = useState([{Evenement}]);
    const [userid, setUserID] = useState(null);
    const {user} = useUserContext();

    useEffect(() => {
        setUserID(user.id);
    }, [user.id]);

    useEffect(() => {
        if (userid) {
            getEventsByUser(userid)
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
        }
    }, [userid]);

    const handleDeleteEvent = (eventId) => {
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
        <div
            className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className="text-4xl font-bold mb-4">Tous vos Evements</h1>
            <div className="flex flex-grow flex-col w-1/2 overflow-auto p-4 space-y-4">
                {evenements[0].id != null ?
                    evenements.map((evenement) => (

                    <div key={evenement.id} className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                        <h2 className="text-2xl font-bold mb-2">{evenement.nom}</h2>
                        <p className="mb-2">{evenement.description}</p>
                        <p className="mb-2">{evenement.dateDebut}</p>
                        <p className="mb-2">{evenement.dateFin}</p>
                        <button type={"button"} onClick={function () {handleDeleteEvent(evenement.id)}} className="bg-red-500 text-white rounded px-4 py-2 mt-4">Supprimer</button>
                    </div>
                )) :
                <div className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                    <p className="text-2xl font-bold mb-2">Aucun événement</p>
                </div>
                }
            </div>
        </div>
    );

}

export default EventsPage;