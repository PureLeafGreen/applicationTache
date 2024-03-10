import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Navbar from './Components/Navbar';
import { UserProvider } from './UserContext';
import Login from "./Components/Login";
import Dashboard from "./Components/Dashboard";

const routes = createBrowserRouter([
    {
        path: '/',
        element: <App />,
    },
    {
        path: '/login',
        element: <Login />,
    },
    {
        path: '/*',
        element: <h1>Not Found</h1>,
    },
    {
        path: '/user/:id/dashboard',
        element: <Dashboard />,
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));

function AppWrapper() {
    return (
        <React.StrictMode>
            <div className={"flex flex-grow flex-col h-screen bg-[linear-gradient(to_right,theme(colors.indigo.400),theme(colors.indigo.100),theme(colors.sky.400),theme(colors.fuchsia.400))] background-animate"}>
                <Navbar />
                <UserProvider>
                    <RouterProvider router={routes} />
                </UserProvider>
            </div>
        </React.StrictMode>
    );
}

root.render(<AppWrapper />);
reportWebVitals();
