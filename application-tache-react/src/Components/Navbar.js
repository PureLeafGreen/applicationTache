import React, {useEffect, useState} from 'react';
import {useUserContext} from "../UserContext";
import {toast} from "react-hot-toast";
import {useNavigate} from "react-router-dom";
function Navbar() {
    const navigate = useNavigate();
    const { user, setUser } = useUserContext();
    const [isNavOpen, setIsNavOpen] = useState(false);
    const [isUserNavOpen, setIsUserNavOpen] = useState(false);

    useEffect(() => {
        console.log("User:", user);
    }, [user]);
    const toggleNav = () => {
        setIsNavOpen(!isNavOpen);
    };

    const toggleUserNav = () => {
        setIsUserNavOpen(!isUserNavOpen);
    }
    function logoutUser() {
        toast.success("Vous êtes déconnecté");
        setUser(null);
        localStorage.setItem('user', null);
        localStorage.setItem('auth_token', null);
        navigate("/login");
    }

    return (
        <nav className="bg-white border-gray-200 dark:bg-gray-900 w-screen">
            <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
                <a onClick={function () {navigate("/")}} className="flex items-center space-x-3 rtl:space-x-reverse">
                    <span className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">EventFlow Planner</span>
                </a>
                <button  onClick={toggleNav} data-collapse-toggle="navbar-default" type="button" className="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600" aria-controls="navbar-default" aria-expanded="false">
                    <span className="sr-only">Open main menu</span>
                    <svg className="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 17 14">
                        <path stroke="currentColor" d="M1 1h15M1 7h15M1 13h15"/>
                    </svg>
                </button>
                <div className={`w-full md:block md:w-auto ${isNavOpen ? 'block' : 'hidden'}`} id="navbar-default">
                    <ul className="font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                        <li>
                            <a onClick={function () {navigate("/")}} className="block py-2 px-3 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 dark:text-white md:dark:text-blue-500" aria-current="page">Acceuil</a>
                        </li>
                        { !user ? <></> :
                            <li>
                                <a  onClick={function () {navigate("/user/" + user.id + "/dashboard")}} className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Dashboard</a>
                            </li>

                        }
                        { user ? <></> :
                            <li>
                                <a  onClick={function () {navigate("/login")}} className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Se connecter</a>
                            </li>
                        }
                        { !user ? <></> :
                            <li>
                                <a onClick={function () {navigate("/user/group")}} className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Groupe</a>
                            </li>
                        }
                        {user && (
                            <li className="relative group">
                                <button onClick={() => setIsUserNavOpen(!isUserNavOpen)} className="flex items-center focus:outline-none">
                                    <img src={require("../images/userImg.png")} alt="Profile" className="w-8 h-8 rounded-full" />
                                </button>
                                <div className={`absolute md:right-0 mt-2 p-2 bg-white border rounded-lg shadow-md ${isUserNavOpen ? 'block' : 'hidden'}`}>
                                    <span className="block px-4 py-2 text-gray-900 cursor-default rounded text-center">{user.email}</span>
                                    <span className="block px-4 py-2 text-gray-900 cursor-default rounded text-center">{user.prenom} {user.nom}</span>
                                    <button onClick={logoutUser} className="block px-4 py-2 bg-red-500 text-gray-900 hover:bg-red-600 rounded w-full">Déconnexion</button>
                                </div>
                            </li>
                        )}
                    </ul>
                </div>
            </div>
        </nav>

    );
}

export default Navbar;