import React, { useEffect } from "react";
import { useUserContext } from "../UserContext";

function Dashboard() {
    const { user } = useUserContext();

    useEffect(() => {
        // Log the user information when the component mounts or when user changes
        console.log("User:", user);
    }, [user]); // Dependency array ensures it runs whenever the user state changes

    return (
        <div className={"flex flex-grow flex-col h-screen justify-around items-center bg-gradient-to-r from-blue-300 to-gray-500"}>

            <div className={"flex flex-grow flex-col justify-center items-center"}>
                <h1 className={"text-5xl"}>Bienvenue sur votre Dashboard</h1>
            </div>
            <div className={"flex flex-grow flex-col justify-start items-center"}>
                <div className={"flex flex-row justify-around items-center"}>
                    <a href={"/user/" + user.id + "/events/create"} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Créer un événement</a>
                    <a href={"/user/" + user.id + "/events"} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Voir les événements</a>
                    <a href={"/user/" + user.id + "/events/edit"} className={"p-4 m-4 bg-blue-500 text-white rounded-lg"}>Modifier un événement</a>
                </div>
                <div className={"flex flex-row justify-around items-center"}>
                    <a href={"/user/" + user.id + "/calendar"} className={"p-4 m-4 bg-amber-300 text-black rounded-lg"}>Voir votre calendrier</a>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
