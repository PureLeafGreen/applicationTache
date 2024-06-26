import React, {useState} from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { UserProvider } from './UserContext';
import Login from "./Components/Login";
import Dashboard from "./Components/Dashboard";
import NotFound from "./Components/NotFound";
import {Toaster} from "react-hot-toast";
import Calendar from "./Components/Calendar";
import DayDetails from "./Components/DayDetails";
import EventForm from "./Components/EventForm";
import EventsPage from "./Components/EventsPage";
import GroupePage from "./Components/GroupePage";
import MyGroupePage from "./Components/MyGroupePage";
import GroupeEventsPage from "./Components/GroupeEventsPage";
import GroupeEventsFormPage from "./Components/GroupeEventsFormPage";
import MyTask from "./Components/MyTask";
import MyTaskForm from "./Components/MyTaskForm";
import GroupeTaskPage from "./Components/GroupeTaskPage";
import GroupeTaskFormPage from "./Components/GroupeTaskFormPage";
import Chat from "./Components/Chat";
import EventModifyPage from "./Components/EventModifyPage";
import TaskModifyPage from "./Components/TaskModifyPage";



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
        element: <NotFound />,
    },
    {
        path: '/user/:id/dashboard',
        element: <Dashboard />,
    },
    {
        path: '/user/:id/calendar',
        element: <Calendar />,
    },
    {
        path: '/user/:id/dayDetails',
        element: <DayDetails />,
    },
    {
        path: '/user/:id/dayDetails/addEvent',
        element: <EventForm />,
    },
    {
        path: '/user/:id/events',
        element: <EventsPage />,
    },
    {
        path: '/user/group',
        element: <GroupePage />,
    },
    {
        path: '/user/mygroup',
        element: <MyGroupePage />,
    },
    {
        path: '/user/groupeEvent/:id',
        element: <GroupeEventsPage />,
    },
    {
        path: '/user/groupeEventForm/:id',
        element: <GroupeEventsFormPage />,
    },
    {
        path: '/user/:id/taches',
        element: <MyTask />,
    },
    {
        path: '/user/:id/taches/add',
        element: <MyTaskForm />,
    },
    {
        path: '/user/groupeTask/:id',
        element: <GroupeTaskPage />,
    },
    {
        path: '/user/groupeTaskForm/:id',
        element: <GroupeTaskFormPage />,
    },
    {
        path: '/user/chat',
        element: <Chat />,
    },
    {
        path: '/user/groupe/:groupeid/modifyEvent/:eventid',
        element: <EventModifyPage />,
    },
    {
        path: '/user/modifyEvent/:eventid',
        element: <EventModifyPage />,
    },
    {
        path: '/user/groupe/:groupeid/modifyTask/:taskid',
        element: <TaskModifyPage />,
    },
    {
        path: '/user/modifyTask/:taskid',
        element: <TaskModifyPage />,
    },
]);

const root = ReactDOM.createRoot(document.getElementById('root'));

function AppWrapper() {
    return (
        <React.StrictMode>
            <UserProvider>
                <div className={"flex flex-grow flex-col h-screen bg-[linear-gradient(to_right,theme(colors.indigo.400),theme(colors.indigo.100),theme(colors.sky.400),theme(colors.fuchsia.400))] background-animate"}>
                    <Toaster position={"top-right"} containerStyle={{top: 100}} />
                    <RouterProvider router={routes} />
                </div>
            </UserProvider>
        </React.StrictMode>
    );
}

root.render(<AppWrapper />);
reportWebVitals();
