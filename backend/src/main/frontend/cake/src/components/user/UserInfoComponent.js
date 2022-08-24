import { useState } from "react";
import './App.css'
import { hello, getUser } from "../Api";

function App() {
    const [state, setState] = useState("");
    const [user, setUser] = useState({email:null, nickname:null})

    function handleClick() {
        hello().then(res => {
            console.log(res);
            setState(res);
        })
    }

    function handleClickUser() {
        getUser().then(res => {
            console.log(res);
            setUser(res);
        })
    }

    return (
        <div>
            <button onClick={handleClick}>Hello</button>
            <p>
                {state}
            </p>
            <button onClick={handleClickUser}>User</button>
            <p>
                email: {user.email}
                nickname: {user.nickname}
            </p>
        </div>
    )
}

export default App;

