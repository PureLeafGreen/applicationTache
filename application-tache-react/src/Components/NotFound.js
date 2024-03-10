import {useNavigate} from "react-router-dom";
import {useUserContext} from "../UserContext";

function NotFound() {
    const navigate = useNavigate();
    let { user } = useUserContext();
    return (
        <div className={"flex flex-grow flex-col h-screen justify-center items-center bg-gradient-to-r from-red-300 to-red-500"}>
            <h1 className={"text-5xl"}>Page non trouvée</h1>
            <button onClick={function () { user ? navigate("/user/" + user.id + "/dashboard" ) : navigate("/login")}} className={"mt-20 w-40 bg-blue-500 text-white p-2 rounded-lg border-2 border-black hover:bg-blue-200 hover:text-black"}>Retour</button>
        </div>
    );
}

export default NotFound;