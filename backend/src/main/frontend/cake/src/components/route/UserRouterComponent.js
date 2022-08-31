import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import UsersListComponent from "../user/UsersListComponent";
import CreateUserComponent from "../user/CreateUserComponent";
import UpdateUserComponent from "../user/UpdateUserComponent";

const AppRouter = () => {
    return (
        <div>
            <BrowserRouter>
                <div style={style}>
                    <Routes>
                        <Route exact path="/" component={UsersListComponent}/>
                        <Route path="/users" component={UsersListComponent}/>
                        <Route path="/save-user" component={CreateUserComponent}/>
                        <Route path="/update-user" component={UpdateUserComponent}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </div>
    );
}

const style = {
    color: 'red',
    margin: '10px'
}

export default AppRouter;