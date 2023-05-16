import { useEffect, useRef, useState } from "react";
import React from 'react';

import './Sidebar.css';

function Sidebar() {
    const [salesRank, setSalesRank] = useState([]);
    const [sales, setSales] = useState([]);
    const [salesIncreased, setSalesIncreased] = useState([]);
    const [floatingPopRank, setFloatingPopRank] = useState([]);
    const [floatingPop, setFloatingPop] = useState([]);
    const [floatingPopIncreased, setFloatingPopIncreased] = useState([]);
    const [rentalFeeRank, setRentalFeeRank] = useState([]);
    const [rentalFee, setRentalFee] = useState([]);
    const [rentalFeeIncreased, setRentalFeeIncreased] = useState([]);
    const [recommendIndexRank, setRecommendIndexRank] = useState([]);
    const [recommendIndex, setRecommendIndex] = useState([]);

	const data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

    const findSalesRank = () => {
        fetch('/api/local-commerce/커피-음료/top_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((rank)=>{
            setSalesRank(current => [...current, rank.dong]);
        })})
    }

    const findSales = () => {
        fetch('/api/local-commerce/커피-음료/top_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((value)=>{
            setSales(current => [...current, value.quarterFourTotal]);
        })})
    }

    const findSalesIncreased = () => {
        fetch('/api/local-commerce/커피-음료/top_rank', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((increased)=>{
            setSalesIncreased(current => [...current, increased.growthRateFigures]);
        })})
    }

    const findFloatingPopRank = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((rank)=>{
            setFloatingPopRank(current => [...current, rank.areaName]);
        })})
    }

    const findFloatingPop = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((value)=>{
            setFloatingPop(current => [...current, value.floating2022]);
        })})
    }

    const findFloatingPopIncreased = () => {
        fetch('/api/rank/floating', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((increased)=>{
            setFloatingPopIncreased(current => [...current, increased.riseRate]);
        })})
    }

    const findRentalFeeRank = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((rank)=>{
            setRentalFeeRank(current => [...current, rank.areaName]);
        })})
    }

    const findRentalFee = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((value)=>{
            setRentalFee(current => [...current, value.rentalFee22]);
        })})
    }

    const findRentalFeeIncreased = () => {
        fetch('/api/rank/rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((increased)=>{
            setRentalFeeIncreased(current => [...current, increased.riseRate]);
        })})
    }

    const findRecommendIndexRank = () => {
        fetch('/api/rank/floating-rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((rank)=>{
            setRecommendIndexRank(current => [...current, rank.areaName]);
        })})
    }

    const findRecommendIndex = () => {
        fetch('/api/rank/floating-rentalfee', {
            method : "GET",
            headers : {"Content-Type" : "application/json"},
        })
        .then(response => {return response.json()})
        .then(response => {response.map((value)=>{
            setRecommendIndex(current => [...current, value.floatingDivRentalFee]);
        })})
    }

    useEffect(() => {
        findSalesRank()
        findSales()
        findSalesIncreased()
        findFloatingPopRank()
        findFloatingPop()
        findFloatingPopIncreased()
        findRentalFeeRank()
        findRentalFee()
        findRentalFeeIncreased()
        findRecommendIndexRank()
        findRecommendIndex()
    },[])

    const [btnActivate, setBtnActivate] = useState("");
    const toggleActivate = (e) => {
        setBtnActivate((prev) => {return e.target.value;});
    };

    return (
        <div className = "sidebar">

            <div className = "side_filter">
                <button value = "sales" className = {"container" + (btnActivate == "sales" ? " activated" : "")} onClick={toggleActivate}>매출</button>
                <button value = "floating" className = {"container" + (btnActivate == "floating" ? " activated" : "")} onClick={toggleActivate}>유동인구</button>
                <button value = "rental" className = {"container" + (btnActivate == "rental" ? " activated" : "")} onClick={toggleActivate}>임대료</button>
                <button value = "recommend" className = {"container" + (btnActivate == "recommend" ? " activated" : "")} onClick={toggleActivate}>추천지수</button>
            </div>

            <div className = "side_rank">
                {
                    btnActivate === "sales" ?
                    <div>
                        <p>매출 증가량 랭킹 TOP10</p>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {salesRank[idx - 1]}/
                                        {Math.round(sales[idx - 1] / 10000)}만명/
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
                        <p>유동 인구 증가량 랭킹 TOP10</p>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {floatingPopRank[idx - 1]}/
                                        {Math.round(floatingPop[idx - 1] / 10000)}만명/
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
                        <p>임대료 증가량 랭킹 TOP10</p>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {rentalFeeRank[idx - 1]}/
                                        {Math.round(rentalFee[idx - 1] / 10000)}만명/
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
                        <p>창업 추천지수 랭킹 TOP10</p>
                        <p>
                            {data.map((idx) => { 
                                return(
                                    <div>
                                        {idx}. 
                                        {recommendIndexRank[idx - 1]}/
                                        {Math.round(recommendIndex[idx - 1] * 10) / 10}
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