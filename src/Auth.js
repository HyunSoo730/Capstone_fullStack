import axios from 'axios';
import {React, useEffect} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';


const Auth = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const AUTH_CODE = params.get('code');

    useEffect(() => {
      console.log(`http://3.39.41.194:8080/auth/kakao/callback?code=${AUTH_CODE}`)
        fetch(`/auth/kakao/callback?code=${AUTH_CODE}`, {
          method: 'GET',
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
        .then(res => {console.log(res)})
      }, []);
    return (
        <div>
          
        </div>
    );
};
export default Auth;