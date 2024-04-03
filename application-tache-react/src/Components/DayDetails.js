import React, { useState, useEffect } from 'react';
import {useLocation, useNavigate} from 'react-router-dom';
import Navbar from "./Navbar";
import {useUserContext} from "../UserContext";
import {getAllEventsByDate, getEvents} from "../api/EventAPI";
import {toast} from "react-hot-toast";

const DayDetails = () => {
    const [selectedDate, setSelectedDate] = useState('');
    const [month, setMonth] = useState('');
    const [year, setYear] = useState('');
    const [events, setEvents] = useState([]);
    const { user } = useUserContext();

    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        if (location.state) {
            const { day, month, year } = location.state;
            setSelectedDate(day);
            setMonth(month);
            setYear(year);
        }
    }, [location.state]);

    useEffect(() => {
        if (selectedDate) {
            const date = `${year}-${(month + 1).toString().padStart(2, '0')}-${selectedDate.toString().padStart(2, '0')}`;
            //const dateObject = { "neededDate": dateString };

            getAllEventsByDate(date)
                .then(response => {
                    setEvents(response.data);
                    console.log(response.data);
                })
                .catch(error => {
                    console.log(error.response.data);
                    toast.error(error.response?.data || "An error occurred");
                });
        }
    }, [selectedDate, month, year]);



    const handleAddEvent = () => {
        navigate('/user/'+ user.id +'/dayDetails/addEvent', { state: { selectedDate, month, year } })
    };

    const handleRemoveEvent = () => {
        console.log("Remove Event clicked");
    };

    const handleGoBack = () => {
        window.history.back();
    };

    return (
        <div className={"flex flex-grow flex-col h-screen bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <div className="mt-8 flex flex-col w-full items-center">
                <h3 className="text-lg font-semibold mb-4">{`${selectedDate}, ${new Date(year, month).toLocaleString('default', { month: 'long' })} ${year}`}</h3>
                <div className="w-1/2 h-2/3 bg-white rounded-lg p-4 overflow-y-auto">
                    {events.length === 0 ? <p>No events for this day</p> : events.map(event => (
                        <div key={event.id} className="bg-gray-100 p-2 rounded-lg mb-2 justify-around flex flex-row">
                            <div>
                                <h4 className="text-lg font-semibold">{event.nom}</h4>
                                <p>{event.description}</p>
                                <p>{event.dateDebut} - {event.dateFin}</p>
                            </div>
                            <div className={"content-center"}>
                                <button className="bg-blue-500 text-white px-4 py-2 rounded-lg mr-2">Edit</button>
                                <button className="bg-red-500 text-white px-4 py-2 rounded-lg">Delete</button>
                            </div>
                        </div>
                    ))}
                </div>
                <div className="flex justify-around h-12 w-1/2 mt-12">
                    <button
                        onClick={handleAddEvent}
                        className="bg-green-500 text-white px-4 py-2 rounded-lg mr-2 border-black border-2"
                    >
                        Add Event
                    </button>
                </div>
                <div className="flex justify-center mt-4">
                    <button
                        onClick={handleGoBack}
                        className="bg-blue-500 text-white px-4 py-2 rounded-lg border-black border-2"
                    >
                        Go Back to Calendar
                    </button>
                </div>
            </div>
        </div>
    );
};

export default DayDetails;
