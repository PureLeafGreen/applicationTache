import React, {useEffect, useState} from "react";
import Navbar from "./Navbar";
import {useUserContext} from "../UserContext";
import Evenement from "../Modeles/Evenement";
import {createEvent, createEventWithGroup} from "../api/EventAPI";
import {toast} from "react-hot-toast";
import {useLocation} from "react-router-dom";

function GroupeEventsFormPage() {
    const { user } = useUserContext();
    const [ event , setEvent ] = useState({Evenement});
    const [errors, setErrors] = useState({});
    const location = useLocation();
    const [selectGroup, setSelectGroup] = useState(null);

    useEffect(() => {
        if (location.state) {
            const { groupe } = location.state;
            setSelectGroup(groupe); // Update to setSelectGroup(groupe) instead of setSelectGroup(selectGroup)
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

        const updatedEvent = {
            ...event,
            utilisateur: user.id,
            groupe: null,
            taches: []
        };

        setEvent(updatedEvent);

        console.log(updatedEvent);

        createEventWithGroup(updatedEvent, selectGroup)
            .then(r => {
                console.log(r);
                toast.success("Événement créé");
                console.log("Event created");
            }).catch(e => {
            console.log(e.response.data);
            toast.error("Erreur lors de la création de l'événement");
        });
    }

    return (
        <div
            className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className="text-4xl font-bold mb-4">Créer un événement pour le groupe </h1>
            <div className={"flex flex-grow flex-col justify-start items-center"}>
                <form className={"flex flex-col justify-around items-center"}>
                    <label htmlFor="nom">Titre de l'événement</label>
                    <input type="text" id="nom" name="nom" onChange={(e) => setEvent({...event, nom: e.target.value})}
                           required/>
                    {errors.title && <span className="text-red-500">{errors.title}</span>}
                    <label htmlFor="description">Description de l'événement</label>
                    <textarea id="description" name="description"
                              onChange={(e) => setEvent({...event, description: e.target.value})} required/>
                    {errors.description && <span className="text-red-500">{errors.description}</span>}
                    <label htmlFor="start">Date de début</label>
                    <input type="datetime-local" id="start" name="start"
                           onChange={(e) => setEvent({...event, dateDebut: e.target.value})} required/>
                    {errors.start && <span className="text-red-500">{errors.start}</span>}
                    <label htmlFor="end">Date de fin</label>
                    <input type="datetime-local" id="end" name="end"
                           onChange={(e) => setEvent({...event, dateFin: e.target.value})} required/>
                    {errors.end && <span className="text-red-500">{errors.end}</span>}
                    <button type="button" onClick={function () {
                        saveEvent()
                    }} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Créer l'événement
                    </button>
                </form>
                <button className={"p-4 m-4 bg-red-500 text-white rounded-lg"} onClick={function () {
                    window.history.back()
                }}>Retour
                </button>
            </div>
        </div>
    );
}

export default GroupeEventsFormPage;