import { useEffect, useRef, useState } from "react";
import React from 'react';

import './Sidebar.css';

function Sidebar() {
    const [salesRankUp, setSalesRankUp] = useState([]);
    const [salesUp, setSalesUp] = useState([]);
    const [salesIncreased, setSalesIncreased] = useState([]);
    const [salesRankDown, setSalesRankDown] = useState([]);
    const [salesDown, setSalesDown] = useState([]);
    const [salesDecreased, setSalesDecreased] = useState([]);

    const [floatingPopRankUp, setFloatingPopRankUp] = useState([]);
    const [floatingPopUp, setFloatingPopUp] = useState([]);
    const [floatingPopIncreased, setFloatingPopIncreased] = useState([]);
    const [floatingPopRankDown, setFloatingPopRankDown] = useState([]);
    const [floatingPopDown, setFloatingPopDown] = useState([]);
    const [floatingPopDecreased, setFloatingPopDecreased] = useState([]);

    const [rentalFeeRankUp, setRentalFeeRankUp] = useState([]);
    const [rentalFeeUp, setRentalFeeUp] = useState([]);
    const [rentalFeeIncreased, setRentalFeeIncreased] = useState([]);
    const [rentalFeeRankDown, setRentalFeeRankDown] = useState([]);
    const [rentalFeeDown, setRentalFeeDown] = useState([]);
    const [rentalFeeDecreased, setRentalFeeDecreased] = useState([]);

    const [recommendIndexRank, setRecommendIndexRank] = useState([]);

   const data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    const findSalesRankUp = () => {
        fetch('/api/local-commerce/커피-음료/top_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setSalesRankUp(current => [...current, rank.dong]);
        })}
        })
    }

    const findSalesUp = () => {
        fetch('/api/local-commerce/커피-음료/top_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((value)=>{
            setSalesUp(current => [...current, value.quarterFourTotal]);
        })}
    })
    }

    const findSalesIncreased = () => {
        fetch('/api/local-commerce/커피-음료/top_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((increased)=>{
            setSalesIncreased(current => [...current, increased.growthRateFigures]);
        })}
    })
    }

    const findSalesRankDown = () => {
        fetch('/api/local-commerce/커피-음료/low_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setSalesRankDown(current => [...current, rank.dong]);
        })}
    })
    }

    const findSalesDown = () => {
        fetch('/api/local-commerce/커피-음료/low_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((value)=>{
            setSalesDown(current => [...current, value.quarterFourTotal]);
        })}
    })
    }

    const findSalesDecreased = () => {
        fetch('/api/local-commerce/커피-음료/low_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((decreased)=>{
            setSalesDecreased(current => [...current, decreased.growthRateFigures]);
        })}
    })
    }

    const findFloatingPopRankUp = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setFloatingPopRankUp(current => [...current, rank.areaName]);
        })}
    })
    }

    const findFloatingPopUp = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((value)=>{
            setFloatingPopUp(current => [...current, value.floating2022]);
        })}
    })
    }

    const findFloatingPopIncreased = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((increased)=>{
            setFloatingPopIncreased(current => [...current, increased.riseRate]);
        })
    }})
    }

    const findFloatingPopRankDown = () => {
        fetch('/api/rank/floating/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setFloatingPopRankDown(current => [...current, rank.areaName]);
        })}
    })
    }

    const findFloatingPopDown = () => {
        fetch('/api/rank/floating/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((value)=>{
            setFloatingPopDown(current => [...current, value.floating2022]);
        })}
    })
    }

    const findFloatingPopDecreased = () => {
        fetch('/api/rank/floating/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((decreased)=>{
            setFloatingPopDecreased(current => [...current, decreased.riseRate]);
        })}
    })
    }

    const findRentalFeeRankUp = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setRentalFeeRankUp(current => [...current, rank.areaName]);
        })}
    })
    }

    const findRentalFeeUp = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((value)=>{
            setRentalFeeUp(current => [...current, value.rentalFee22]);
        })
    }})
    }

    const findRentalFeeIncreased = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((increased)=>{
            setRentalFeeIncreased(current => [...current, increased.riseRate]);
        })}
    })
    }

    const findRentalFeeRankDown = () => {
        fetch('/api/rank/rentalfee/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setRentalFeeRankDown(current => [...current, rank.areaName]);
        })}
    })
    }

    const findRentalFeeDown = () => {
        fetch('/api/rank/rentalfee/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((value)=>{
            setRentalFeeDown(current => [...current, value.rentalFee22]);
        })}
    })
    }

    const findRentalFeeDecreased = () => {
        fetch('/api/rank/rentalfee/lower', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((decreased)=>{
            setRentalFeeDecreased(current => [...current, decreased.riseRate]);
        })}
    })
    }

    
    const findRecommendIndexRank = () => {
        fetch('/api/rank/floating-rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {
            if(Array.isArray(response)){
            response.map((rank)=>{
            setRecommendIndexRank(current => [...current, rank.areaName]);
        })}
    })
    }
    

    useEffect(() => {
        findSalesRankUp()
        findSalesUp()
        findSalesIncreased()
        findSalesRankDown()
        findSalesDown()
        findSalesDecreased()

        findFloatingPopRankUp()
        findFloatingPopUp()
        findFloatingPopIncreased()
        findFloatingPopRankDown()
        findFloatingPopDown()
        findFloatingPopDecreased()

        findRentalFeeRankUp()
        findRentalFeeUp()
        findRentalFeeIncreased()
        findRentalFeeRankDown()
        findRentalFeeDown()
        findRentalFeeDecreased()

        findRecommendIndexRank()
    },[])

    const [btnActivate, setBtnActivate] = useState("sales");
    const toggleActivate = (e) => {
        setBtnActivate((prev) => {return e.target.value;});
    };

    return (
        <div className = "sidebar">

            <div className = "side_filter">
                <button value = "sales" className = {"container" + (btnActivate == "sales" ? " activated" : "")} onClick={toggleActivate}>매출</button>
                <button value = "floating" className = {"container" + (btnActivate == "floating" ? " activated" : "")} onClick={toggleActivate}>유동인구</button>
                <button value = "rental" className = {"container" + (btnActivate == "rental" ? " activated" : "")} onClick={toggleActivate}>임대료</button>
                {<button value = "recommend" className = {"container" + (btnActivate == "recommend" ? " activated" : "")} onClick={toggleActivate}>추천 지역</button>}
            </div>

            <div className = "side_rankup">
                {
                    btnActivate === "sales" ?
                    <div>
                        <h5>매출 증가량 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {salesRankUp[idx - 1]}/
                                        {Math.round(salesUp[idx - 1] / 100000000)}억원/
                                        {Math.round(salesIncreased[idx - 1] * 10) / 10}%↑
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
                {
                    btnActivate === "floating" ?
                    <div>
                        <h5>유동 인구 증가량 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {floatingPopRankUp[idx - 1]}/
                                        {Math.round(floatingPopUp[idx - 1] / 10000)}만명/
                                        {Math.round(floatingPopIncreased[idx - 1] * 10) / 10}%↑
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
                {
                    btnActivate === "rental" ?
                    <div>
                        <h5>임대료 증가량 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {rentalFeeRankUp[idx - 1]}/
                                        {rentalFeeUp[idx - 1]}원/
                                        {Math.round(rentalFeeIncreased[idx - 1] * 10) / 10}%↑
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
                {
                    btnActivate === "recommend" ?
                    <div>
                        <h5>창업 추천 지역 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {recommendIndexRank[idx - 1]}
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
            </div>

            <div className = "side_rankdown">
                {
                    btnActivate === "sales" ?
                    <div>
                        <h5>매출 감소량 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {salesRankDown[idx - 1]}/
                                        {Math.round(salesDown[idx - 1] / 100000000)}억원/
                                        {Math.round(salesDecreased[idx - 1] * 10) / 10}%↑
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
                {
                    btnActivate === "floating" ?
                    <div>
                        <h5>유동 인구 감소량 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {floatingPopRankDown[idx - 1]}/
                                        {Math.round(floatingPopDown[idx - 1] / 10000)}만명/
                                        {Math.round(floatingPopDecreased[idx - 1] * 10) / 10}%↑
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
                {
                    btnActivate === "rental" ?
                    <div>
                        <h5>임대료 감소량 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {rentalFeeRankDown[idx - 1]}/
                                        {rentalFeeDown[idx - 1]}원/
                                        {Math.round(rentalFeeDecreased[idx - 1] * 10) / 10}%↑
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
                {
                    btnActivate === "recommend" ?
                    <div>
                        <h5>창업 비추천 지역 랭킹 TOP10</h5>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {recommendIndexRank[idx - 1]}
                                    </div>
                                )
                            })}
                        </p>
                    </div>
                    : null
                }
            </div>

        </div>
    )
}

export default Sidebar;