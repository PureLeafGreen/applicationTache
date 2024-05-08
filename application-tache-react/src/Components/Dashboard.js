import React, { useEffect } from "react";
import { useUserContext } from "../UserContext";
import Navbar from "./Navbar";

function Dashboard() {
    const { user } = useUserContext();

    useEffect(() => {
        // Log the user information when the component mounts or when user changes
        console.log("User:", user);
        if (user === null) {
            // Redirect to login page if user is not logged in
            window.location.href = "/login";
        }
    }, [user]); // Dependency array ensures it runs whenever the user state changes

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <div className={"flex flex-grow flex-col justify-center items-center"}>
                <h1 className={"text-4xl font-bold mb-4"}>Bienvenue sur votre Dashboard</h1>
            </div>
            <div className={"flex flex-grow flex-col justify-start items-center"}>
                {user ?
                <>
                    <div className={"flex flex-row justify-around items-center"}>
                        <a href={"/user/" + user.id + "/events"} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Voir les événements</a>
                        <a href={"/user/" + user.id + "/dayDetails/addEvent"} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Créer un événement</a>
                    </div>
                    <div className={"flex flex-row justify-around items-center"}>
                        <a href={"/user/" + user.id + "/calendar"} className={"p-4 m-4 bg-amber-300 text-black rounded-lg"}>Voir votre calendrier</a>
                    </div>
                    <div className={"flex flex-row justify-around items-center"}>
                        <a href={"/user/" + user.id + "/taches"} className={"p-4 m-4 bg-emerald-200 text-black rounded-lg"}>Voir vos tâches</a>
                        <a href={"/user/" + user.id + "/taches/add"} className={"p-4 m-4 bg-emerald-200 text-black rounded-lg"}>Ajouter une tâche</a>
                    </div>
                </>
                    :
                <>
                </>
                }
            </div>
        </div>
    );
}

export default Dashboard;
