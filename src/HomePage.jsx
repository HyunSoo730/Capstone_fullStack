import React from 'react';
import { NavLink } from 'react-router-dom';

const HomePage = () => {
    const REST_API_KEY = "8d2b99868ab67054454a535a7db9fc4f";
    const REDIRECT_URI = "http://localhost:3000/oauth/kakao/callback";
    const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
    
    return (
        <div>
            <a href={KAKAO_AUTH_URL}>Kakao Login</a>
            <NavLink to="/youtube">유튜브 분석</NavLink>
        </div>
    )
};
export default HomePage;