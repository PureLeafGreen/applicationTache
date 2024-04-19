import React, {useEffect, useState} from 'react';
import Navbar from "./Navbar";
import Evenement from "../Modeles/Evenement";
import {useUserContext} from "../UserContext";
import {useLocation} from "react-router-dom";
import {createEvent} from "../api/EventAPI";
import {toast} from "react-hot-toast";
function EventForm() {
    const {user} = useUserContext();
    const [ event , setEvent ] = useState({Evenement});
    const [errors, setErrors] = useState({});
    const location = useLocation();
    const [selectedDate, setSelectedDate] = useState(null);
    const [month, setMonth] = useState(null);
    const [year, setYear] = useState(null);

    useEffect(() => {
        if (location.state) {
            const { selectedDate, month, year } = location.state;
            console.log("Day:", selectedDate, "Month:", month +1, "Year:", year);
            setSelectedDate(selectedDate);
            setMonth(month+1);
            setYear(year);
            //Set the date input to the selected date
        }

    }, [location.state]);

    useEffect(() => {
        if (year !== null && month !== null && selectedDate !== null) {
            document.getElementById('start').value = `${year}-${month.toString().padStart(2, '0')}-${selectedDate.toString().padStart(2, '0')}T00:00`;
            document.getElementById('end').value = `${year}-${month.toString().padStart(2, '0')}-${(selectedDate + 1).toString().padStart(2, '0')}T00:00`;
            setEvent({...event, dateDebut: `${year}-${month.toString().padStart(2, '0')}-${selectedDate.toString().padStart(2, '0')}T00:00`});
            setEvent({...event, dateFin: `${year}-${month.toString().padStart(2, '0')}-${(selectedDate + 1).toString().padStart(2, '0')}T00:00`});
        }
    }, [year, month, selectedDate, setEvent]);
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

        createEvent(updatedEvent)
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
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <div className={"flex flex-grow flex-col justify-center items-center"}>
                <h1 className={"text-5xl"}>Créer un événement</h1>
            </div>
            <div className={"flex flex-grow flex-col justify-start items-center"}>
                <form className={"flex flex-col w-full bg-white p-4 rounded-lg shadow-lg mt-4"}>
                    <label htmlFor="nom">Titre de l'événement</label>
                    <input className={"border-2 p-2"} type="text" id="nom" name="nom" onChange={(e) => setEvent({...event, nom: e.target.value})}
                           required/>
                    {errors.title && <span className="text-red-500">{errors.title}</span>}
                    <label htmlFor="description">Description de l'événement</label>
                    <textarea className={"border-2 p-2"} id="description" name="description"
                              onChange={(e) => setEvent({...event, description: e.target.value})} required/>
                    {errors.description && <span className="text-red-500">{errors.description}</span>}
                    <label htmlFor="start">Date de début</label>
                    <input className={"border-2 p-2"} type="datetime-local" id="start" name="start"
                           onChange={(e) => setEvent({...event, dateDebut: e.target.value})} required/>
                    {errors.start && <span className="text-red-500">{errors.start}</span>}
                    <label htmlFor="end">Date de fin</label>
                    <input className={"border-2 p-2"} type="datetime-local" id="end" name="end"
                           onChange={(e) => setEvent({...event, dateFin: e.target.value})} required/>
                    {errors.end && <span className="text-red-500">{errors.end}</span>}
                    <button type="button" onClick={function () {
                        saveEvent()
                    }} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Créer l'événement
                    </button>
                </form>
                <button className={"p-4 m-4 bg-red-500 text-white rounded-lg"} onClick={function() {window.history.back()}}>Retour</button>
            </div>
        </div>
    );
}

export default EventForm;