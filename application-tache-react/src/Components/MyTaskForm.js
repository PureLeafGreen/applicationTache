import Navbar from "./Navbar";
import React, {useState} from "react";
import {useUserContext} from "../UserContext";
import Evenement from "../Modeles/Evenement";
import TaskDTO from "../Modeles/TaskDTO";
import {createTask} from "../api/TaskAPI";
import {toast} from "react-hot-toast";

function MyTaskForm() {
    const { user } = useUserContext();
    const [ task , setTask ] = useState({TaskDTO});
    const [errors, setErrors] = useState({});

    function saveTask() {
        setErrors({});
        const validationErrors = {};
        if (!task.nom) {
            validationErrors.title = "Titre de la tâche requis";
        }
        if (!task.description) {
            validationErrors.description = "Description de la tâche requise";
        }
        if (!task.dateDebut) {
            validationErrors.start = "Date de début requise";
        }
        if (!task.dateFin) {
            validationErrors.end = "Date de fin requise";
        }
        if (task.dateDebut > task.dateFin) {
            validationErrors.end = "La date de fin doit être après la date de début";
        }
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        const updatedTask = {
            ...task,
            utilisateur: user.id,
            groupe: null,
            evenement: null
        };

        setTask(updatedTask);

        console.log(updatedTask);

        createTask(updatedTask)
        .then(r => {
            console.log(r);
            toast.success("Tâche créée");
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response?.data || "An error occurred");
        });
    }

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className={"text-2xl font-bold"}>Ajouter une taches</h1>
            <form className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg mt-4"}>
                <label className={"text-lg"}>Nom</label>
                <input type={"text"} className={"border-2 p-2 rounded-lg"} value={task.nom}
                       onChange={(e) => setTask({...task, nom: e.target.value})}/>
                {errors.title && <p className={"text-red-500"}>{errors.title}</p>}
                <label className={"text-lg"}>Description</label>
                <input type={"text"} className={"border-2 p-2 rounded-lg"} value={task.description}
                       onChange={(e) => setTask({...task, description: e.target.value})}/>
                {errors.description && <p className={"text-red-500"}>{errors.description}</p>}
                <label className={"text-lg"}>Date de début</label>
                <input type={"datetime-local"} className={"border-2 p-2 rounded-lg"} value={task.dateDebut}
                       onChange={(e) => setTask({...task, dateDebut: e.target.value})}/>
                {errors.start && <p className={"text-red-500"}>{errors.start}</p>}
                <label className={"text-lg"}>Date de fin</label>
                <input type={"datetime-local"} className={"border-2 p-2 rounded-lg"} value={task.dateFin}
                       onChange={(e) => setTask({...task, dateFin: e.target.value})}/>
                {errors.end && <p className={"text-red-500"}>{errors.end}</p>}
                <label className={"text-lg"}>Couleur</label>
                <input type={"color"} className={"border-2 p-2 rounded-lg"} value={task.couleur}
                       onChange={(e) => setTask({...task, couleur: e.target.value})}/>
                <button type={"button"} className={"bg-blue-500 text-white p-2 rounded-lg"} onClick={saveTask}>Ajouter
                </button>
            </form>
            <button className={"p-4 m-4 bg-red-500 text-white rounded-lg"} onClick={function () {
                window.history.back()
            }}>Retour
            </button>
        </div>
    )
}

export default MyTaskForm;