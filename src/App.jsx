import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { New, List, Error} from "./pages";
import { Navigation } from './Components/Navigation';
import { Link } from 'react-router-dom';

export const App = () => {
    return (
        <BrowserRouter>
            <Navigation>
                {/* Manages Navigation Bar */}
                <section className='nav-section'>
                    <div className='nav-item'>
                        <Link className="nav-item" to="/">New Reimbursement</Link>
                    </div>
                    <div className='nav-item'>
                        <Link className="nav-item" to="/list">List</Link>
                    </div>
                </section>
            </Navigation>
            {/* Manages url's */}
            <Routes>
                <Route path="/" element={<New />} />
                <Route path="/list" element={<List />} />
                <Route path="*" element={<Error />} />
            </Routes>
        </BrowserRouter>
    );
}

