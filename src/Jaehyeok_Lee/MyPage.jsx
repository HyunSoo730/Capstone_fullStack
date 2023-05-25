import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Select from 'react-select';

import './MyPage.css';

function MyPage(props) {
    const navigate = useNavigate();
    const [profileImg, setProfileImg] = useState("");
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [borough, setBorough] = useState("");
    const [dong, setDong] = useState("");
    const [choiced, setChoiced] = useState("");
    const wowfuck = localStorage.getItem('login-token');

    var categoryOption = [
        { value: '한식음식점', label: '한식음식점' },
        { value: '중식음식점', label: '중식음식점' },
        { value: '일식음식점', label: '일식음식점' },
        { value: '양식음식점', label: '양식음식점' },
        { value: '제과점', label: '제과점' },
        { value: '패스트푸드점', label: '패스트푸드점' },
        { value: '치킨전문점', label: '치킨전문점' },
        { value: '분식전문점', label: '분식전문점' },
        { value: '호프-간이주점', label: '호프-간이주점' },
        { value: '커피-음료', label: '커피-음료' },
    ];

    const sendToken = () => {
        fetch('/mypage/users/me', {
            method : "GET",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
        })
        .then(response => {return response.json()})
        .then(response => {
            console.log("꺄르르륵" + response.kakaoEmail);
            setProfileImg(current => [...current, response.kakaoProfileImg]);
            setNickname(response.kakaoNickname);
            setEmail(current => [...current, response.kakaoEmail]);
            setBorough(current => [...current, response.borough]);
            setDong(current => [...current, response.dong]);
            setChoiced(current => [...current, response.serviceName]);
        })
    }

    const handleChangeNickname = (event) => {
        setNickname(event.target.value);
    };

    const handleChangeBorough = (event) => {
        setBorough(event.target.value);
    };

    const handleChangeDong = (event) => {        
        setDong(event.target.value);
    };

    const handleSubmit = (event) => {
        alert(`수정이 완료되었습니다.`);
        // 변경 데이터 저장하기
        event.preventDefault();
    };

    useEffect(() => {
        sendToken();
        console.log("이런 미친 " + wowfuck);
        console.log("꾸웨에에엑" + profileImg);
        console.log("꾸웨에에엑" + nickname);
        console.log("꾸웨에에엑" + email);
        console.log("꾸웨에에엑" + borough);
        console.log("꾸웨에에엑" + dong);
        console.log("꾸웨에에엑" + choiced);
    },[])

    return (
        <form onSubmit={handleSubmit}>
            <div className="Wrapper">
                <div className="Container">
                    <div className="PostContainer">
                        <h3>기본 정보</h3>
                        <div className="Label">
                            <div className="tag">닉 네 임</div>
                            <input className="write" type="text" defaultValue={nickname} onChange={handleChangeNickname}/>
                        </div>
                        <div className="Label">
                            <div className="tag">이 메 일</div>
                            <div className="write" type="text">{email}</div>
                        </div>
                        <p>　</p>

                        <h3>창업 정보</h3>
                        <div className="Label">
                            <div className="tag">자 치 구</div>
                            <input className="write" type="text" defaultValue={borough} onChange={handleChangeBorough} />
                        </div>
                        <div className="Label">
                            <div className="tag">행 정 동</div>
                            <input className="write" type="text" defaultValue={dong} onChange={handleChangeDong} />
                        </div>
                        <div className="Label">
                            <div className="tag">희망업종</div>
                            <Select className="write" options={categoryOption} defaultValue={{value: choiced, label: choiced}} onChange={(value) => setChoiced(value["value"])}/>  
                        </div>
                    </div>
                    <div className="PhotoContainer">
                        <div className="Photo"/>
                        <div
                            className="Button"
                            type="submit"
                            title="사진 변경"
                            onClick={() => {navigate("/");}}
                        >
                            사진 변경
                        </div>
                    </div> 
                </div>
                <div
                    className="Button"
                    type="submit"
                    title="정보 수정"
                    onClick={() => {navigate("/");}}
                >
                    정보 수정
                </div>
            </div>
        </form>
    );
}

export default MyPage;