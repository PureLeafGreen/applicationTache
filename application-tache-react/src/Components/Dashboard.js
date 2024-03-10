import React, { useEffect } from "react";
import { useUserContext } from "../UserContext";

function Dashboard() {
    const { user } = useUserContext();

    useEffect(() => {
        // Log the user information when the component mounts or when user changes
        console.log("User:", user);
    }, [user]); // Dependency array ensures it runs whenever the user state changes

    return (
        <div>
            <h1>Dashboard</h1>
            {user ? <h1>{user.id}</h1> : <p>Loading user...</p>}
            {user ? <h1>{user.prenom}</h1> : <p>Loading user...</p>}
            {user ? <h1>{user.nom}</h1> : <p>Loading user...</p>}
            {user ? <h1>{user.phone}</h1> : <p>Loading user...</p>}
            {user ? <h1>{user.email}</h1> : <p>Loading user...</p>}
        </div>
    );
}

export default Dashboard;
