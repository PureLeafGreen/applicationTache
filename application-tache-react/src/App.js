import logo from './logo.svg';
import './App.css';
import Navbar from "./Components/Navbar";

function App() {
  return (
      <div className={"flex flex-grow top-0 items-center flex-col"}>
          <Navbar />
          <img src={require("./images/Calendar.png")} width={650} height={300} alt={"Photo d'un calendrier"}/>
          <h1 className={"text-3xl font-bold mt-1"}>Bienvenue sur l'application pour la gestion de tache et evenement</h1>
      </div>
  );
}

export default App;
