import Navbar from "./Navbar";
import React, {useEffect, useState} from "react";
import {useUserContext} from "../UserContext";
import TaskDTO from "../Modeles/TaskDTO";
import {getTasksByUser} from "../api/TaskAPI";
import {toast} from "react-hot-toast";

function MyTask() {

    const { user } = useUserContext();
    const [ task , setTask ] = useState({TaskDTO});

    useEffect(() => {
        getTasksByUser(user.id)
        .then(response => {
            console.log(response.data);
            setTask(response.data);
            toast.success("Tâches récupérées avec succès");
        })
        .catch(error => {
            console.log(error.response);
            toast.error(error?.response.data || "An error occurred");
        })
    }, []);

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className={"text-2xl font-bold"}>Mes tâches</h1>
            <div className={"flex flex-col w-10/12 items-center"}>
                {task[0] != null ? task.map((task) => (
                    <div key={task.id} className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg mt-4"}>
                        <h1 className={"text-2xl font-bold"}>{task.nom}</h1>
                        <p className={"text-lg"}>{task.description}</p>
                        <p className={"text-lg"}>Date de début : {task.dateDebut}</p>
                        <p className={"text-lg"}>Date de fin : {task.dateFin}</p>
                        <p className={"text-lg"}>Type : {task.type}</p>
                        <p className={"text-lg"}>Couleur : {task.couleur}</p>
                    </div>
                )) : <p>Aucune tâche</p>}
            </div>
        </div>
    )
}

export default MyTask;