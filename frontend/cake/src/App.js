import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';

import NaviHeader from './components/NaviHeader';
import Home from './pages/Home';
import Intro from './pages/Intro';
import Design from './pages/Design';
import Contest from './pages/Contest';
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import Lettering from './pages/Lettering';


function App() {
  window.onscroll = function () {
    scrollFunction();
  };
  
  function scrollFunction() {
    if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
      //document.getElementById("navbar").style.padding = "0";
      //document.getElementById("logo").style.fontSize = "25px";
      document.getElementById("navbar").style.top = "-200px";
    } else {
      //document.getElementById("navbar").style.padding = "10px 10px";
      //document.getElementById("logo").style.fontSize = "35px";
      document.getElementById("navbar").style.top = "0";
    }
  };

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
          <Route path='/letter' element={<Lettering />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
