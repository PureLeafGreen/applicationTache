import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { toast } from "react-hot-toast";
import { getEventById, updateEvent } from "../api/EventAPI";
import Navbar from "./Navbar";

function EventModifyPage() {
    const [event, setEvent] = useState({
        nom: "",
        description: "",
        dateDebut: "",
        dateFin: ""
    });
    const [errors, setErrors] = useState({});
    const location = useLocation();
    const [selectGroup, setSelectGroup] = useState(null);

    useEffect(() => {
        if (location.state) {
            const { eventid } = location.state;
            getEventById(eventid)
                .then(response => {
                    if (typeof response.data === 'string') {
                        console.log("No event found");
                    } else {
                        setEvent(response.data);
                        toast.success("Événement trouvé");
                    }
                })
                .catch(error => {
                    console.log(error.response?.data);
                    toast.error(error.response?.data || "An error occurred");
                });
        }
    }, [location.state]);

    function saveEvent() {
        setErrors({});
        const validationErrors = {};
        if (!event.nom) {
            validationErrors.title = "Titre de l'événement requis";
        }
        if (!event.description) {
            validationErrors.description = "Description de l'événement requise";
        }
        if (!event.dateDebut) {
            validationErrors.start = "Date de début requise";
        }
        if (!event.dateFin) {
            validationErrors.end = "Date de fin requise";
        }
        if (event.dateDebut > event.dateFin) {
            validationErrors.end = "La date de fin doit être après la date de début";
        }
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }
        console.log("selectGroup");
        console.log(selectGroup);
        const updatedEvent = {
            ...event,
            groupe: null,
            taches: []
        };
        console.log(updatedEvent);
        updateEvent(updatedEvent)
            .then(r => {
                console.log(r);
                toast.success("Événement mis à jour");
                console.log("Event updated");
            })
            .catch(e => {
                console.log(e.response.data);
                toast.error("Erreur lors de la mise à jour de l'événement");
            });
    }

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <h1 className="text-4xl font-bold mb-4">Modifier l'événement</h1>
            <div className={"flex flex-grow flex-col justify-start items-center w-full"}>
                <form className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg mt-4"}>
                    <label htmlFor="nom">Titre de l'événement</label>
                    <input
                        className={"border-2 p-2"}
                        type="text"
                        id="nom"
                        name="nom"
                        value={event.nom}
                        onChange={(e) => setEvent({ ...event, nom: e.target.value })}
                        required
                    />
                    {errors.title && <span className="text-red-500">{errors.title}</span>}
                    <label htmlFor="description">Description de l'événement</label>
                    <textarea
                        className={"border-2 p-2"}
                        id="description"
                        name="description"
                        value={event.description}
                        onChange={(e) => setEvent({ ...event, description: e.target.value })}
                        required
                    />
                    {errors.description && <span className="text-red-500">{errors.description}</span>}
                    <label htmlFor="start">Date de début</label>
                    <input
                        className={"border-2 p-2"}
                        type="datetime-local"
                        id="start"
                        name="start"
                        value={event.dateDebut}
                        onChange={(e) => setEvent({ ...event, dateDebut: e.target.value })}
                        required
                    />
                    {errors.start && <span className="text-red-500">{errors.start}</span>}
                    <label htmlFor="end">Date de fin</label>
                    <input
                        className={"border-2 p-2"}
                        type="datetime-local"
                        id="end"
                        name="end"
                        value={event.dateFin}
                        onChange={(e) => setEvent({ ...event, dateFin: e.target.value })}
                        required
                    />
                    {errors.end && <span className="text-red-500">{errors.end}</span>}
                    <button
                        type="button"
                        onClick={saveEvent}
                        className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}
                    >
                        Mettre à jour l'événement
                    </button>
                </form>
                <button
                    className={"p-4 m-4 bg-red-500 text-white rounded-lg"}
                    onClick={() => window.history.back()}
                >
                    Retour
                </button>
            </div>
        </div>
    );
}

export default EventModifyPage;
