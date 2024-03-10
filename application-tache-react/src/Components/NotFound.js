import {useNavigate} from "react-router-dom";
import {useUserContext} from "../UserContext";
import Navbar from "./Navbar";

function NotFound() {
    const navigate = useNavigate();
    let { user } = useUserContext();
    return (

        <div className={"flex flex-grow top-0 items-center flex-col bg-gradient-to-r from-red-300 to-red-500"}>
            <Navbar />
            <div className={"flex flex-grow flex-col justify-center items-center"}>
                <h1 className={"text-5xl"}>Page non trouvée</h1>
                <button onClick={function () { user ? navigate("/user/" + user.id + "/dashboard" ) : navigate("/login")}} className={"mt-20 w-40 bg-blue-500 text-white p-2 rounded-lg border-2 border-black hover:bg-blue-200 hover:text-black"}>Retour</button>
            </div>
        </div>
    );
}

export default NotFound;