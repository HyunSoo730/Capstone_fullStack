import { React, useState } from 'react';
import HomeStyle from './HomePage.css';
import { MenuItems } from "./HomePageMenuItems.jsx";
import "./HomePageNavbar.css"

const HomePage = () => {
    const [clicked, setClicked] = useState(false);

    const handleClick = () => {
        setClicked(!clicked);
    }

    return (
        <div>
            <nav className="Navbar">
                <div className="menu-icon" onClick={handleClick}>
                    <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
                </div>
                <ul className={clicked ? 'nav-menu active' : 'nav-menu'}>
                    {MenuItems.map((item, index)=>{
                        return (
                            <li key={index}>
                                <a className={item.cName} href={item.url}>
                                    {item.title}
                                </a>
                            </li>
                        )
                    })}
                </ul>
            </nav>
            <div id={HomeStyle.wrap}>
                <body id={HomeStyle.body}>
                    <footer id={HomeStyle.footer}>
                        <span>개발자 : Izony | WHO | WANT | ME </span><br/>
                        <span>Github : https://github.com/HyunSoo730/Capstone_fullStack</span><br/>
                        <span>version : 0.1 </span>
                    </footer>
                </body>
            </div>
        </div>
    )
};
export default HomePage;