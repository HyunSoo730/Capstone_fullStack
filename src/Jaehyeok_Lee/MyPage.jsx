import React, { useState, useEffect } from "react";
import Select from 'react-select';
import Avatar from 'react-avatar';
import Styled from 'styled-components';
import useDidMountEffect from "../UseDidMountEffect";
import main_logo from '../logo.png'
import { MenuItems } from "../HomePageMenuItems";
import HomeStyle from '../HomePage.css';

import './MyPage.css';

const StyledAvatar = Styled(Avatar)`
    box-shadow: 2px 2px 5px lightgray;
    height: 105px;
    width: 105px;
    border-radius: 100%;
    object-fit: cover;
    margin-bottom: 10px;
`;

function MyPage(props) {
    const [saved, setSaved] = useState([]);
    //const [startUpInfoList, setStartUpInfoList] = useState([]);
    const [profileImg, setProfileImg] = useState("");
    const [nickname, setNickname] = useState("");
    const [email, setEmail] = useState("");
    const [idPocket, setIdPocket] = useState([]);
    const [borough, setBorough] = useState([]);
    const [dong, setDong] = useState([]);
    const [choiced, setChoiced] = useState([]);
    const [clicked, setClicked] = useState(false);
    const [countList, setCountList] = useState([]);

    const handleClick = () => {setClicked(!clicked);}

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

    const getBasicUserInfo = () => {
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
        })
    }

    const getStartUpUserInfo = () => {
        fetch('/mypage/users/savedata', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({})
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
                response.map((value)=>{
                    setIdPocket(current => [...current, value.reportId]);
                    setBorough(current => [...current, value.borough]);
                    setDong(current => [...current, value.dong]);
                    setChoiced(current => [...current, value.serviceName]);
                })
            }
        })
    }
    
    /*
    const sendUpdatedUserInfo = () => {
        fetch('/mypage/users/me', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({
                kakaoProfileImg: profileImg,
                kakaoNickname: nickname,
            })
        })
    }
    */

    const addStartUpInfo = (idx) => {
        fetch('/mypage/users/save', {
            method : "POST",
            headers : {
                "Content-Type" : "application/json",
                Authorization : localStorage.getItem('login-token'),
            },
            body : JSON.stringify({
                borough: borough[idx],
                dong: dong[idx],
                serviceName: choiced[idx],
            })
        })
    }

    const deleteStartUpInfo = (idx) => {
        console.log("아이디 인덱스 : " + idPocket[idx]);
        fetch(`/mypage/users/${idPocket[idx]}`, {
            method : "DELETE",
            headers : {
                Authorization : localStorage.getItem('login-token'),
            },
        })
        .then(response => {
            if(response.ok) {console.log("삭제 성공")}
        })
    }

    const handleChangeProfileImg = () => {
        /*
        const fileReader = new FileReader();
        fileReader.onload = function() {
            setProfileImg({result: fileReader.result})
        }
        fileReader.readAsDataURL(file);
        */
    };

    const handleChangeNickname = (event) => {
        setNickname(event.target.value);
    };

    const handleChangeBorough = (event) => {
        borough[event.target.name] = event.target.value;
        setBorough(borough);
    };

    const handleChangeDong = (event) => {
        dong[event.target.name] = event.target.value;
        setDong(dong);
    };

    const checkStartUpInfo = (event) => { // 저장 버튼
        addStartUpInfo(event.target.value);
        saved[event.target.value] = true;
        setSaved(saved);
        onUpdateDiv();
    }

    const onAddDiv = () => { 
        if(saved[saved.length - 1] == true || countList.length == 0) {
            let countArr = [...countList];
            let counter = -1;
            if (countArr.length != 0) {
                counter = countArr.slice(-1)[0];
            }
            counter += 1;
            countArr.push(counter);
            setCountList(countArr);
            setSaved(current => [...current, false]);
        }
    }

    const onUpdateDiv = () => {
        let countArr = [...countList];
        setCountList(countArr);
    }

    const onDelDiv = (event) => { // 삭제 버튼
        /*deleteStartUpInfo(event.target.value);*/

        saved.splice(event.target.value, 1);
        setSaved(saved);

        idPocket.splice(event.target.value, 1);
        setIdPocket(idPocket);

        borough.splice(event.target.value, 1);
        setBorough(borough);

        dong.splice(event.target.value, 1);
        setDong(dong);

        choiced.splice(event.target.value, 1);
        setChoiced(choiced);

        let countArr = [...countList];
        let counter = countArr.slice(-1)[0];
        counter -= 1;
        countArr.pop();
        setCountList(countArr);
    }

    /*
    const handleSubmit = (event) => {
        sendUpdatedUserInfo();
        alert(`수정이 완료되었습니다.`);
        event.preventDefault();
    };
    */

    useEffect(() => {

        setSaved([]);
        setProfileImg("");
        setNickname("");
        setEmail("");
        setIdPocket([]);
        setBorough([]);
        setDong([]);
        setChoiced([]);
        setCountList([]);

        getBasicUserInfo();
        getStartUpUserInfo();

        

        borough && borough.map((item, index)=>{
            console.log("아이템이양 " + item);
            setCountList(current => [...current, index]);
            setSaved(current => [...current, true]);
        })
        
        console.log("이름 : " + nickname);
        console.log("이메일 : " + email);
        console.log("아이디 : " + idPocket);
        console.log("자치구 : " + borough);
        console.log("행정동 : " + dong);
        console.log("창업 정보 : " + choiced);
        console.log("창업정보 리스트 : " + countList);
        console.log("세이브 여부 : " + saved);
    },[])

    return (
        <div className="Page">
            <nav className='Navbar'>
                <h1 className="navbar-logo"><img src={main_logo} onClick={() => window.location.href='/'}/></h1>
                <div className='menu-icon' onClick={handleClick}>
                    <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
                </div>
                <ul className="nav-menu-active">
                    {MenuItems.map((item, index)=>{
                        if (localStorage.getItem('login-token') && item.url === `https://kauth.kakao.com/oauth/authorize?client_id=${process.env.REACT_APP_REST_API_KEY}&redirect_uri=http://localhost:3000/auth/kakao/callback&response_type=code`){
                            return;
                        }
                        if(!localStorage.getItem('login-token') && item.url === "mypage"){
                            return;
                        }
                        if(window.location.pathname === '/' + item.url){
                            return (
                                <li class="nav-links-active" key={index}>
                                    <a className="nav-component-active" href={item.url}>{item.title}</a>
                                </li>
                            )
                        }
                        else{
                            return (
                                <li key={index}>
                                    <a className={item.cName} href={item.url}>{item.title}</a>
                                </li>
                            )
                        }
                    })}
                </ul>
            </nav>
            <div className="InPage">
                <div className="SideWrapper">
                    <div className="SideTag">마이 페이지</div>
                </div>
                <div className="Wrapper">
                    <div className="Container">
                        <div className="PostContainer">
                            <div className="BasicInfo">
                                <div className="PhotoContainer">
                                    <h3>기본 정보</h3>
                                    <StyledAvatar src={profileImg}/>
                                    {/*<div className="PhotoButton" title="사진 변경" onClick={handleChangeProfileImg}></div>*/}
                                </div>
                                <div className="InBasicInfo">
                                    <div className="Label">
                                        <div className="Tag">이름</div>
                                        <div className="WriteBasic" type="text" style={{backgroundColor: "white", color: "gray"}}>{nickname}</div>
                                        {/*<input className="WriteBasic" type="text" defaultValue={nickname} onChange={handleChangeNickname}/>*/}
                                    </div>
                                    <div style={{height: "10px"}}/>
                                    <div className="Label">
                                        <div className="Tag">이메일</div>
                                        <div className="WriteBasic" type="text" style={{backgroundColor: "white", color: "gray"}}>{email}</div>
                                    </div>
                                </div>
                            </div>

                            <div className="StartUpInfo" style={{marginBottom: "50px"}}>
                                <h3 style={{marginLeft: "130px", marginBottom: "25px"}}>창업 정보</h3>
                                {/*<span style={{marginLeft: "130px", position: "absolute", top: "495px", color: "lightgray", fontSize: "12px"}}>* 최대 5개까지 등록할 수 있습니다.</span>*/}
                                {countList.map((item, index) => {
                                    return(
                                        <div className="InStartUpInfo" key={index}>
                                            <input className="WriteStartUp" name={index} disabled={saved[index] == true? true:false} type="text" placeholder="자치구" defaultValue={borough[index]} onChange={handleChangeBorough} />
                                            <input className="WriteStartUp" name={index} disabled={saved[index] == true? true:false} type="text" placeholder="행정동" defaultValue={dong[index]} onChange={handleChangeDong} />
                                            <Select
                                                //isDisabled={saved[index] == true? true:false}
                                                label="희망 업종"
                                                placeholder="희망 업종"
                                                options={categoryOption}
                                                defaultValue={{value: choiced[index], label: choiced[index]}}
                                                onChange={(value) => setChoiced(current => [...current, value["value"]])}
                                                styles={{
                                                    //container: provided => ({...provided, }),
                                                    control: provided => ({...provided, width: '230px', height: '48px', textAlign: 'left', paddingLeft: '10px'}),
                                                    menu: provided => ({...provided, width: '240px', textAlign: 'left'}),
                                                    placeholder: (provided) => ({...provided, color: 'gray'})
                                                }}
                                                theme={(theme) => ({
                                                    ...theme,
                                                    borderRadius: 0,
                                                    colors: {
                                                    ...theme.colors,
                                                    primary25: '#D3FFDC',
                                                    primary: '#03CF5D',
                                                    primary50: '#D3FFDC',
                                                    },
                                                })}
                                            />
                                            {/*<select style={{borderRight: "1px solid lightgray"}} className="WriteStartUp" placeholder="희망 업종" onChange={(value) => setChoiced(value["value"])} defaultValue={{value: choiced, label: choiced}}>
                                                {categoryOption.map((item) => (
                                                    <option className="OptionStyle" value={item.value}>{item.value}</option>
                                                ))}
                                                </select>*/}
                                            
                                            {
                                                saved[index] == false?
                                                <button className="CheckButton" type="submit" value={index} onClick={checkStartUpInfo}/>:
                                                <button className="DeleteButton" type="submit" value={index} onClick={onDelDiv}/>
                                            }
                                        </div>
                                    )
                                })}
                                {
                                    countList.length < 5?
                                    <div className="PlusButton" title="저장" onClick={onAddDiv}>
                                        창업 정보 추가 +
                                    </div>
                                    : null
                                }
                            </div>
                            {/*
                            <div className="CompleteButton" type="submit" title="수정 완료" onClick={handleSubmit}>
                                수정 완료
                            </div>
                            */}
                        </div>
                    </div>
                </div>
            </div>
            {/*<div style={{height: "200px", backgroundColor: "#F9FBFC", zIndex: "20"}}/>*/}
            {/*
            <div id={HomeStyle.wrap} style={{zIndex: "20"}}>
                <body id={HomeStyle.body}>
                    <footer id={HomeStyle.footer}>
                        <span>개발자 : Izony | WHO | WANT | ME </span><br/>
                        <span>Github : https://github.com/HyunSoo730/Capstone_fullStack</span><br/>
                        <span>version : 0.1 </span>
                    </footer>
                </body>
            </div>
            */}
        </div>
    );
}

export default MyPage;