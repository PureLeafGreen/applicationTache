import Navbar from "./Navbar";
import React, {useEffect, useState} from "react";
import {useUserContext} from "../UserContext";
import TaskDTO from "../Modeles/TaskDTO";
import {deleteTask, getTasksByUser} from "../api/TaskAPI";
import {toast} from "react-hot-toast";
import {useNavigate} from "react-router-dom";

function MyTask() {

    const { user } = useUserContext();
    const [ userID , setUserID ] = useState(null);
    const [ task , setTask ] = useState({TaskDTO});
    const navigate = useNavigate();

    useEffect(() => {
        setUserID(user.id);
    }, [user.id]);

    useEffect(() => {
        if (userID ) {
        getTasksByUser(userID)
            .then(response => {
                console.log(response.data);
                setTask(response.data);
                toast.success("Tâches récupérées avec succès");
            })
            .catch(error => {
                console.log(error.response);
                toast.error(error?.response.data || "An error occurred");
            })
        }
    }, [userID]);

    function handleDeleteTask(taskid) {
        deleteTask(taskid)
        .then(response => {
            console.log(response.data);
            toast.success("Evenement supprimé avec succès");
            setTask(task.filter(task => task.id !== taskid));
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response?.data || "An error occurred");
        });
    }

    function handleModifyTask(taskid) {
        navigate(`/user/modifyTask/${taskid}`, { state: { taskID : taskid} });
    }

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <h1 className={"text-4xl font-bold mb-4"}>Mes tâches</h1>
            <div className={"flex flex-col w-10/12 items-center"}>
                {task[0] != null ? task.map((task) => (
                        <div key={task.id} className={"flex flex-col w-1/2 bg-white p-4 rounded-lg shadow-lg mt-4"}>
                            <h1 className={"text-2xl font-bold"}>{task.nom}</h1>
                            <p className={"text-lg"}>{task.description}</p>
                            <p className={"text-lg"}>Date de début : {task.dateDebut}</p>
                            <p className={"text-lg"}>Date de fin : {task.dateFin}</p>
                            <p className={"text-lg"}>Type : {task.type}</p>
                            <p className={"text-lg"}>Couleur : {task.couleur}</p>
                            <button type={"button"} onClick={function () {
                                handleModifyTask(task.id)
                            }} className="bg-yellow-500 text-white rounded px-4 py-2 mt-4">Modifier
                            </button>
                            <button type={"button"} onClick={function () {
                                handleDeleteTask(task.id)
                            }} className="bg-red-500 text-white rounded px-4 py-2 mt-4">Supprimer
                            </button>
                        </div>
                    )) :
                    <div className="flex flex-col bg-white shadow-lg rounded-lg p-4">
                        <p className="text-2xl font-bold mb-2">Aucune taches</p>
                    </div>
                }
                <button className={"p-4 m-4 bg-red-500 text-white rounded-lg"} onClick={function () {
                    window.history.back()
                }}>Retour
                </button>
            </div>
        </div>
    )
}

export default MyTask;