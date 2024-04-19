import Navbar from "./Navbar";
import React, {useEffect, useState} from "react";
import {useUserContext} from "../UserContext";
import TaskDTO from "../Modeles/TaskDTO";
import {useLocation} from "react-router-dom";
import {getTasksByGroup} from "../api/TaskAPI";
import {toast} from "react-hot-toast";

function GroupeTaskPage() {
    const { user } = useUserContext();
    const [ task , setTask ] = useState({TaskDTO});
    const location = useLocation();
    const [selectGroup, setSelectGroup] = useState(null);

    useEffect(() => {
        if (location.state) {
            const { groupe } = location.state;
            setSelectGroup(groupe); // Update to setSelectGroup(groupe) instead of setSelectGroup(selectGroup)
        }
    }, [location.state]);

    useEffect(() => {
        console.log(selectGroup);
        if (selectGroup == null) {
            return;
        }
        getTasksByGroup(selectGroup)
        .then(response => {
            console.log(response.data);
            setTask(response.data);
            toast.success("Tâches récupérées avec succès");
        })
        .catch( error => {
            console.log(error.response?.data);
            toast.error(error.response?.data || "An error occurred");
        })
    }, [selectGroup]);

    return (
        <div
            className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className="text-4xl font-bold mb-4">Taches de votre groupe</h1>
            <div className="flex flex-grow flex-col w-1/2 overflow-auto p-4 space-y-4">
                {task[0] != null ? task.map((task) => (
                    <div key={task.id} className="flex flex-col w-full bg-white p-4 rounded-lg shadow-lg">
                        <h2 className="text-2xl font-bold">{task.nom}</h2>
                        <p className="text-lg">{task.description}</p>
                        <p className="text-lg">Date de début : {task.dateDebut}</p>
                        <p className="text-lg">Date de fin : {task.dateFin}</p>
                    </div>
                )) : <p>Aucune tâche</p>}
            </div>
        </div>
    );
}

export default GroupeTaskPage;