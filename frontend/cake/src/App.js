import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import NaviHeader from './components/NaviHeader';
import Home from './pages/Home';
import Intro from './pages/Intro';
import Design from './pages/Design';
import Contest from './pages/Contest';
import Login from './pages/Login';
import SignUp from './pages/SignUp';


function App() {
  return (
    <div className="App">
      <NaviHeader />
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/intro' element={<Intro />} />
          <Route path='/design' element={<Design />} />
          <Route path='/contest' element={<Contest />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signUp' element={<SignUp />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
