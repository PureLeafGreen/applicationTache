import React, { useState } from 'react';
import {addUser, getUser} from "../api/AuthAPI";
import User from "../Modeles/User";

function Login() {
    var [user, setUser] = useState({User});
    var [loginInUser, setLoginInUser] = useState({User});
    var [isRegistering, setRegistering] = useState(false);

    function registering() {
        setRegistering(!isRegistering);
    }

    function displayUser() {
        console.log(user);
    }
    return (
        <div className={"flex flex-grow flex-col h-screen justify-center items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            { !isRegistering ?
                <>
                    <h1 className={"text-5xl"}>Se connecter</h1>
                    <form className={"mt-20"}>
                        <div className={"flex flex-col"}>
                            <label htmlFor="email">Email</label>
                            <input type="email" id="email" name="email" onChange={(e) => setLoginInUser({...loginInUser,email:e.target.value})} required/>
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="password">Mot de passe</label>
                            <input type="password" id="password" name="password" onChange={(e) => setLoginInUser({...loginInUser,password:e.target.value})} required/>
                        </div>
                        <button type="button" onClick={function () {getUser(loginInUser)}} className={"mt-5 w-full bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"}>Se connecter</button>
                    </form>
                </>
                :
                <>
                    <h1 className={"text-5xl"}>S'inscrire</h1>
                    <form className={"mt-20"}>
                        <div className={"flex flex-col"}>
                            <label htmlFor="email">Email</label>
                            <input type="email" id="email" name="email" placeholder={"jeanPierre@google.com"} onChange={(e) => setUser({...user,email:e.target.value})}  required/>
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="nom">Nom</label>
                            <input type="text" id="nom" name="nom" placeholder={"Jean"} onChange={(e) => setUser({...user,nom:e.target.value})} required/>
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="prenom">Prenom</label>
                            <input type="text" id="prenom" name="prenom" placeholder={"Pierre"} onChange={(e) => setUser({...user,prenom:e.target.value})} required/>
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="phone">Telephonne</label>
                            <input type="tel" id="phone" name="phone" placeholder={"555-555-5555"} onChange={(e) => setUser({...user,phone:e.target.value})} pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"} required/>
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="password">Mot de passe</label>
                            <input type="password" id="password" name="password" onChange={(e) => setUser({...user,password:e.target.value})} required/>
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="confirmPassword">Confirmer le mot de passe</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" required/>
                        </div>
                        <button type="button" onClick={function () {addUser(user)}}  className={"mt-5 w-full bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"}>S'inscrire</button>
                    </form>
                </>
            }
            <button onClick={registering} className={"mt-10 w-1/2 text-white p-2 rounded-lg bg-amber-300 text-black border border-2 border-black"}>{isRegistering ? "Se connecter?" : "S'inscrire?"}</button>
        </div>
    );
}

export default Login;
