import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Select from 'react-select';
import Avatar from 'react-avatar';

import './MyPage.css';

function MyPage(props) {
    const navigate = useNavigate();
    const [profileImg, setProfileImg] = useState("");
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [borough, setBorough] = useState("");
    const [dong, setDong] = useState("");
    const [choiced, setChoiced] = useState("");

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

    const getUserInfo = () => {
        fetch('/mypage/users/me', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({})
        })
        .then(response => {return response.json()})
        .then(response => {
            setProfileImg(response.kakaoProfileImg);
            setNickname(response.kakaoNickname);
            setEmail(response.kakaoEmail);
            setBorough(response.borough);
            setDong(response.dong);
            setChoiced(response.serviceName);
            console.log("이건 프사야 " + profileImg);
        })
    }

    const sendUpdatedUserInfo = () => {
        fetch('/mypage/users/save', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({
                borough: borough,
                dong: dong,
                serviceName: choiced,
            })
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
        sendUpdatedUserInfo();
        alert(`수정이 완료되었습니다.`);
        event.preventDefault();
    };

    useEffect(() => {getUserInfo();},[])

    return (
        <div className="Page">
            <div className="SideWrapper">
                <div className="SideTag">마이 페이지</div>
            </div>
            <div className="Wrapper">
                <div className="Container">
                    <div className="PostContainer">
                        <div className="BasicInfo">
                            <div className="PhotoContainer">
                                <h3>기본 정보</h3>
                                <Avatar className="Photo" src={profileImg}/>
                                <div
                                    className="PhotoButton"
                                    type="submit"
                                    title="사진 변경"
                                    onClick={() => {}}
                                >
                                    뀨
                                </div>
                            </div>
                            <div className="InBasicInfo">
                                <div className="Label">
                                    <div className="Tag">이름</div>
                                    <input className="Write" type="text" defaultValue={nickname} onChange={handleChangeNickname}/>
                                </div>
                                <div style={{height: "10px"}}/>
                                <div className="Label">
                                    <div className="Tag">이메일</div>
                                    <div className="Write" type="text" style={{backgroundColor: "white", color: "gray"}}>{email}</div>
                                </div>
                            </div>
                        </div>

                        <div className="StartUpInfo">
                            <h3 style={{marginLeft: "130px"}}>창업 정보</h3>
                            <div className="InStartUpInfo">
                                <div className="Label">
                                    <div className="Tag">자치구</div>
                                    <input className="Write" type="text" defaultValue={borough} onChange={handleChangeBorough} />
                                </div>
                                <div style={{height: "10px"}}/>
                                <div className="Label">
                                    <div className="Tag">행정동</div>
                                    <input className="Write" type="text" defaultValue={dong} onChange={handleChangeDong} />
                                </div>
                                <div style={{height: "10px"}}/>
                                <div className="Label">
                                    <div className="Tag">희망 업종</div>
                                    <Select className="WriteSelect" options={categoryOption} defaultValue={{value: choiced, label: choiced}} onChange={(value) => setChoiced(value["value"])}/>
                                </div>
                                <div className="SaveButton" title="저장" onClick={() => {}}>
                                    저장
                                </div>
                            </div>
                        </div>

                        <div className="CompleteButton" type="submit" title="수정 완료" onClick={handleSubmit}>
                            수정 완료
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MyPage;