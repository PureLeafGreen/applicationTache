import React, { useState } from 'react';
import LoginUserDTO from "../Modeles/LoginUserDTO";
import { useNavigate } from 'react-router-dom';
import {request, setAuthTokens} from "../api/axiosHelper";
import { useUserContext } from "../UserContext";
import {registerUser, loginUser} from "../api/AuthAPI";
import {toast, Toaster} from "react-hot-toast";
import Navbar from "./Navbar";

function Login() {
    const navigate = useNavigate();
    const { setUser } = useUserContext();
    const [registeringUser, setregisteringUser] = useState({
        email: '',
        nom: '',
        prenom: '',
        phone: '',
        password: '',
        confirmPassword: '',
    });
    const [errors, setErrors] = useState({});
    let [loginInUser, setLoginInUser] = useState({LoginUserDTO});
    let [isRegistering, setRegistering] = useState(false);

    function registering() {
        setErrors({});
        setRegistering(!isRegistering);
    }

    function register() {
        // Validation checks
        const validationErrors = {};
        if (!registeringUser.email) {
            validationErrors.email = "Email is required";
        }
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailRegex.test(registeringUser.email)) {
            validationErrors.email = "Email must be in the format : john@email.com";
        }
        if (!registeringUser.nom) {
            validationErrors.nom = "Nom is required";
        }
        if (!registeringUser.prenom) {
            validationErrors.prenom = "Prenom is required";
        }
        const phoneRegex = /^[0-9]{3}-[0-9]{3}-[0-9]{4}$/;
        if (!phoneRegex.test(registeringUser.phone)) {
            validationErrors.phone = "Phone number must be in the format 555-555-5555";
        }
        if (!registeringUser.phone) {
            validationErrors.phone = "Phone is required";
        }
        if (!registeringUser.password) {
            validationErrors.password = "Password is required";
        }
        if (registeringUser.password !== registeringUser.confirmPassword) {
            validationErrors.confirmPassword = "Passwords do not match";
        }

        // If there are validation errors, update the state and return
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        registerUser(registeringUser)
        .then(response => {
            toast.success("Vous êtes inscrit");
            console.log(response.data);
            setAuthTokens(response.data.token);
            setUser(response.data);
            navigate(`/user/${response.data.id}/dashboard`);
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response.data);
        });
    }


    function login() {
        const validationErrors = {};
        if (!loginInUser.email) {
            validationErrors.email = "Email is required";
        }
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailRegex.test(loginInUser.email)) {
            validationErrors.email = "Email must be in the format : john@gmail.com"
        }
        if (!loginInUser.password) {
            validationErrors.password = "Password is required";
        }
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }
        loginUser(loginInUser)
        .then(response => {
            console.log(response.data);
            toast.success("Vous êtes connecté");
            setAuthTokens(response.data.token);
            setUser(response.data);
            navigate(`/user/${response.data.id}/dashboard`);
        })
        .catch(error => {
            console.log(error.response.data);
            toast.error(error.response.data);
        })
    }
    //flex flex-grow flex-col h-screen justify-center items-center bg-gradient-to-r from-blue-300 to-gray-500"
    return (
        <div className={"flex flex-grow top-0 items-center flex-col bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <div className={"flex flex-grow flex-col justify-center items-center w-screen"}>
            { !isRegistering ?
                <>
                    <h1 className={"text-5xl"}>Se connecter</h1>
                    <form className={"mt-20"}>
                        <div className={"flex flex-col"}>
                            <label htmlFor="email">Email</label>
                            <input type="email" id="email" name="email" onChange={(e) => setLoginInUser({...loginInUser,email:e.target.value})} required/>
                            {errors.email && <span className="text-red-500">{errors.email}</span>}
                        </div>
                        <div className={"flex flex-col"}>
                            <label htmlFor="password">Mot de passe</label>
                            <input type="password" id="password" name="password" onChange={(e) => setLoginInUser({...loginInUser,password:e.target.value})} required/>
                            {errors.password && <span className="text-red-500">{errors.password}</span>}
                        </div>
                        <button type="button" onClick={function () {login()}} className={"mt-5 w-full bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"}>Se connecter</button>
                    </form>
                </>
                :
                <>
                    <h1 className={"text-5xl"}>S'inscrire</h1>
                    <form className={"mt-20"}>
                        {/* Email */}
                        <div className={"flex flex-col"}>
                            <label htmlFor="email">Email</label>
                            <input type="email" id="email" name="email" placeholder={"jeanPierre@google.com"} onChange={(e) => setregisteringUser({ ...registeringUser, email: e.target.value })} required />
                            {errors.email && <span className="text-red-500">{errors.email}</span>}
                        </div>

                        {/* Nom */}
                        <div className={"flex flex-col"}>
                            <label htmlFor="nom">Nom</label>
                            <input type="text" id="nom" name="nom" placeholder={"Jean"} onChange={(e) => setregisteringUser({ ...registeringUser, nom: e.target.value })} required />
                            {errors.nom && <span className="text-red-500">{errors.nom}</span>}
                        </div>

                        {/* Prenom */}
                        <div className={"flex flex-col"}>
                            <label htmlFor="prenom">Prenom</label>
                            <input type="text" id="prenom" name="prenom" placeholder={"Pierre"} onChange={(e) => setregisteringUser({ ...registeringUser, prenom: e.target.value })} required />
                            {errors.prenom && <span className="text-red-500">{errors.prenom}</span>}
                        </div>

                        {/* Phone */}
                        <div className={"flex flex-col"}>
                            <label htmlFor="phone">Telephonne</label>
                            <input type="tel" id="phone" name="phone" placeholder={"555-555-5555"} onChange={(e) => setregisteringUser({ ...registeringUser, phone: e.target.value })} pattern={"[0-9]{3}-[0-9]{3}-[0-9]{4}"} required />
                            {errors.phone && <span className="text-red-500">{errors.phone}</span>}
                        </div>

                        {/* Password */}
                        <div className={"flex flex-col"}>
                            <label htmlFor="password">Mot de passe</label>
                            <input type="password" id="password" name="password" onChange={(e) => setregisteringUser({ ...registeringUser, password: e.target.value })} required />
                            {errors.password && <span className="text-red-500">{errors.password}</span>}
                        </div>

                        {/* Confirm Password */}
                        <div className={"flex flex-col"}>
                            <label htmlFor="confirmPassword">Confirmer le mot de passe</label>
                            <input type="password" id="confirmPassword" name="confirmPassword" onChange={(e) => setregisteringUser({ ...registeringUser, confirmPassword: e.target.value })} required />
                            {errors.confirmPassword && <span className="text-red-500">{errors.confirmPassword}</span>}
                        </div>

                        <button type="button" onClick={function () {register()}}  className={"mt-5 w-full bg-blue-500 text-white p-2 rounded-lg border border-2 border-black"}>S'inscrire</button>
                    </form>
                </>
            }
                <button onClick={registering} className={"mt-10 w-1/2 text-white p-2 rounded-lg bg-amber-300 text-black border border-2 border-black"}>{isRegistering ? "Se connecter?" : "S'inscrire?"}</button>
            </div>
        </div>
    );
}

export default Login;
