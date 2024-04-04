import React, {useEffect, useState} from "react";
import {getEvents} from "../api/EventAPI";
import {toast} from "react-hot-toast";
import Navbar from "./Navbar";
import Evenement from "../Modeles/Evenement";

function EventsPage() {

    const [evenements, setEvents] = useState([{Evenement}]);

    useEffect(() => {
        getEvents()
        .then(response => {
            setEvents(response.data);
            console.log(response.data);
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response?.data || "An error occurred");
        });
    }, []);

    return (
        <div
            className={"flex flex-grow flex-col h-screen justify-around items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className="text-4xl font-bold mb-4">Tous vos Evements</h1>
            <div className="flex flex-grow flex-col w-1/2 overflow-auto p-4 space-y-4">
                {evenements && evenements.map((evenement) => (
                    <div key={evenement.id} className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                        <h2 className="text-2xl font-bold mb-2">{evenement.nom}</h2>
                        <p className="mb-2">{evenement.description}</p>
                        <p className="mb-2">{evenement.dateDebut}</p>
                        <p className="mb-2">{evenement.dateFin}</p>
                        <button className="bg-red-500 text-white rounded px-4 py-2 mt-4">Supprimer</button>
                    </div>
                ))}
            </div>
        </div>
    );

}

export default EventsPage;