import React, { useState, useEffect } from 'react';
import Navbar from "./Navbar";

const Chat = () => {
    const [messages, setMessages] = useState([]);
    const [messageInput, setMessageInput] = useState('');
    const [webSocket, setWebSocket] = useState(null);

    useEffect(() => {
        const socket = new WebSocket('ws://localhost:8080/api/messages/chat');
        // Update with your backend WebSocket URL

        socket.onopen = () => {
            console.log('Connected to WebSocket server');
            setWebSocket(socket);
        };

        socket.onmessage = (event) => {
            const message = JSON.parse(event.data);
            setMessages([...messages, message]);
        };

        socket.onclose = () => {
            console.log('Disconnected from WebSocket server');
        };

        return () => {
            if (webSocket) {
                webSocket.close();
            }
        };
    }, [messages, webSocket]);

    const handleMessageInputChange = (event) => {
        setMessageInput(event.target.value);
    };

    const sendMessage = () => {
        if (messageInput.trim() !== '') {
            const message = {
                content: messageInput,
                sender: 'user', // You can replace 'user' with actual user information
            };
            webSocket.send(JSON.stringify(message));
            setMessageInput('');
        }
    };

    return (
        <div className={"flex flex-grow flex-col h-screen items-center bg-gradient-to-r from-blue-300 to-gray-500"}>
            <Navbar/>
            <div className="max-w-lg mx-auto">
                <div className="border border-gray-300 p-4 rounded-lg mb-4">
                    <div className="overflow-y-auto" style={{ maxHeight: '300px' }}>
                        {messages.map((message, index) => (
                            <div key={index} className="mb-2">
                                <strong>{message.sender}: </strong>
                                <span>{message.content}</span>
                            </div>
                        ))}
                    </div>
                    <div className="mt-4 flex">
                        <input
                            type="text"
                            value={messageInput}
                            onChange={handleMessageInputChange}
                            placeholder="Type your message..."
                            className="flex-1 border border-gray-400 rounded-lg p-2 focus:outline-none"
                        />
                        <button
                            onClick={sendMessage}
                            className="ml-2 bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
                        >
                            Send
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Chat;
