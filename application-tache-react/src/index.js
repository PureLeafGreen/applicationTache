
import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom"
import App from './App';
import reportWebVitals from './reportWebVitals';
import Navbar from "./Components/Navbar";
import Login from "./Components/Login";

const routes = createBrowserRouter([
    {
        path: "/",
        element: <App />,
    },
    {
        path: "/login",
        element : <Login/>,
    },
    {
        path: "/*",
        element: <h1>Not Found</h1>,
    }
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(

  <React.StrictMode>
      <div className={"flex flex-grow flex-col h-screen bg-[linear-gradient(to_right,theme(colors.indigo.400),theme(colors.indigo.100),theme(colors.sky.400),theme(colors.fuchsia.400))] background-animate"}>
          <Navbar />
          <RouterProvider router={routes} />
      </div>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
