import React, {useEffect, useState} from 'react';
import { BsChevronLeft, BsChevronRight } from 'react-icons/bs';
import Navbar from "./Navbar";
import {useUserContext} from "../UserContext";
import {useNavigate} from "react-router-dom";

const Calendar = () => {
    const [selectedDate, setSelectedDate] = useState(null);
    const [currentMonth, setCurrentMonth] = useState(new Date().getMonth());
    const [currentYear, setCurrentYear] = useState(new Date().getFullYear());
    const navigate = useNavigate();
    const daysInMonth = (month, year) => {
        return new Date(year, month + 1, 0).getDate();
    };

    const startDayOfMonth = (month, year) => {
        return new Date(year, month, 1).getDay();
    };

    const handleDateClick = (day) => {
        setSelectedDate(day);
        navigate(`/user/${user.id}/dayDetails`, { state: { day, month: currentMonth, year: currentYear } });
        console.log("Selected Date:", day);
    };

    const prevMonth = () => {
        if (currentMonth === 0) {
            setCurrentMonth(11);
            setCurrentYear(currentYear - 1);
        } else {
            setCurrentMonth(currentMonth - 1);
        }
    };

    const nextMonth = () => {
        if (currentMonth === 11) {
            setCurrentMonth(0);
            setCurrentYear(currentYear + 1);
        } else {
            setCurrentMonth(currentMonth + 1);
        }
    };

    const renderCalendar = () => {
        const days = [];
        const totalDays = daysInMonth(currentMonth, currentYear);
        const startDay = startDayOfMonth(currentMonth, currentYear);

        for (let i = 1; i <= totalDays + startDay; i++) {
            if (i > startDay) {
                days.push(
                    <div
                        key={i}
                        className={`w-12 h-12 flex justify-center items-center cursor-pointer border ${
                            selectedDate === i - startDay
                                ? 'bg-blue-500 text-white'
                                : 'bg-white text-gray-800 hover:bg-gray-200'
                        }`}
                        onClick={() => handleDateClick(i - startDay)}
                    >
                        {i - startDay}
                    </div>
                );
            } else {
                days.push(<div key={i} className="w-12 h-12"></div>);
            }
        }

        return days;
    };

    const { user } = useUserContext();

    useEffect(() => {
        console.log("User:", user);
        if (user === null) {
            window.location.href = "/login";
        }
    }, [user]);

    return (
        <div className={"flex flex-grow flex-col h-screen bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar />
            <div className="mx-auto max-w-md p-4">
                <div className="flex justify-between items-center mb-4">
                    <button onClick={prevMonth}>
                        <BsChevronLeft className="h-5 w-5 text-gray-700" />
                    </button>
                    <h2 className="text-lg font-semibold">
                        {new Date(currentYear, currentMonth).toLocaleString('default', {
                            month: 'long',
                            year: 'numeric',
                        })}
                    </h2>
                    <button onClick={nextMonth}>
                        <BsChevronRight className="h-5 w-5 text-gray-700" />
                    </button>
                </div>

                <div className="grid grid-cols-7 gap-2">
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Sun</div>
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Mon</div>
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Tue</div>
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Wed</div>
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Thu</div>
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Fri</div>
                    <div className="w-12 h-12 flex justify-center items-center font-semibold text-gray-600">Sat</div>
                    {renderCalendar()}
                </div>
            </div>
        </div>
    );
};

export default Calendar;
