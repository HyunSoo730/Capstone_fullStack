import axios from 'axios';
import {React, useEffect} from 'react';
import { useLocation, useNavigate } from 'react-router-dom';


const Auth = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const AUTH_CODE = params.get('code');

    useEffect(() => {
        fetch(`https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://localhost:3000/oauth/kakao/callback&code=${AUTH_CODE}`, {
          method: 'GET',
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        })
          .then(res => res.json())
          .then(data => {
            if (data.access_token) {
              console.log(data.access_token);
              fetch('/auth/kakao/token', {
                method: 'POST',
                headers: {
                  Authorization: data.access_token,
                  'Content-Type': 'application/x-www-form-urlencoded'
                },
              })
                .then(response => response.json())
                .then(result => {
                  if (result.access_token) {
                    alert('WELCOME');
                    //navigate('/');
                  } else {
                    alert('NONONO');
                    //navigate('/');
                  }
                });
            }
          });
      }, []);
    return (
        <div>
        </div>
    );
};
export default Auth;