import './App.css';
import { Route, Routes, BrowserRouter } from 'react-router-dom';
import HomePage from './HomePage'
import Youtube from './Youtube';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<HomePage />}/>
          <Route path="/youtube" element={<Youtube />}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}
export default App;