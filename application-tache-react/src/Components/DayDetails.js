import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import Navbar from "./Navbar";

const DayDetails = () => {
    const [selectedDate, setSelectedDate] = useState('');
    const [month, setMonth] = useState('');
    const [year, setYear] = useState('');
    const [events, setEvents] = useState([]);

    const location = useLocation();

    useEffect(() => {
        if (location.state) {
            const { day, month, year } = location.state;
            setSelectedDate(day);
            setMonth(month);
            setYear(year);
        }
    }, [location.state]);

    const handleAddEvent = () => {
        console.log("Add Event clicked");
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
                <div className="overflow-y-auto h-1/2 w-1/2  border rounded-lg">
                    {/* Display hours of the day */}
                    {[...Array(24)].map((_, index) => (
                        <div key={index} className="flex flex-row w-full text-center">
                            <span>{`${index.toString().padStart(2, '0')}:00`}</span>
                            {/* You can display events for each hour here */}
                        </div>
                    ))}
                </div>
                <div className="flex justify-around h-12 w-1/2">
                    <button
                        onClick={handleAddEvent}
                        className="bg-green-500 text-white px-4 py-2 rounded-lg mr-2"
                    >
                        Add Event
                    </button>
                    <button
                        onClick={handleRemoveEvent}
                        className="bg-red-500 text-white px-4 py-2 rounded-lg"
                    >
                        Remove Event
                    </button>
                </div>
                <div className="flex justify-center mt-4">
                    <button
                        onClick={handleGoBack}
                        className="bg-blue-500 text-white px-4 py-2 rounded-lg"
                    >
                        Go Back to Calendar
                    </button>
                </div>
            </div>
        </div>
    );
};

export default DayDetails;
