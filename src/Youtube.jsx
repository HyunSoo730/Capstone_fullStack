import {React, useEffect, useState, useCallback} from "react"
import { MapContainer, TileLayer, GeoJSON } from 'react-leaflet'
import geoData from './LocationData.json'
import { Drawer, Button } from 'rsuite';
import { locationData } from "./LocationDataItems";

import { Swiper, SwiperSlide } from "swiper/react";
import { Navigation } from "swiper";

import { MenuItems } from "./HomePageMenuItems";

import main_logo from "./logo.png"

import "swiper/css";
import "swiper/css/navigation";
import "rsuite/dist/rsuite.css";
import './YoutubeVideoStyle.css';

function Youtube(props){
  const [YoutubePlace, setYoutubePlace] = useState([]);
  const [VideoList, setVideoList] = useState([]);
  const [TrendVideoList, setTrendVideoList] = useState([]);
  const [isDrawerOpen, setDrawerOpen] = useState(false);
  const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERROR");

  const [isToggleOn, setToggle] = useState([]);

  const [SaveColor, setSaveColor] = useState();

  const [SlideIndex, setSlideIndex] = useState(0);
  const [Slider, setSlider] = useState(null);

  const [clicked, setClicked] = useState(false);

    const handleClick = () => {
        setClicked(!clicked);
    }


  const CountYoutubePlace = () => {
    fetch("api/youtube/return", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })  
    .then(response => {
      return response.json();
    })
    .then(response => {
      response.map((item)=>{
        setYoutubePlace(current => [...current, {name: item["dong"], data: [item["count"]]}]);
      });
    });
  }

  const polystyle = (feature) => {
    for (let i = 0; i < YoutubePlace.length; i++){
      if (feature.properties.EMD_NM.includes(YoutubePlace[i].name.slice(0,2))){
        return {
          fillColor: 'rgba(' + YoutubePlace[i].data[0] + '0, 0, 0.5)',
          weight: 2,
          opacity: 1,
          color: 'white',  //Outline color
          fillOpacity: 0.7
        };
      }
    }
    return {
      fillColor: 'rgba(0, 0, 0, 0.5)',
      weight: 2,
      opacity: 1,
      color: 'white',  //Outline color
      fillOpacity: 0.7
    };
  }

  function whenClicked(e, feature) {
    setVideoList([]);
    setTrendVideoList([]);
    setToggle([]);
    setDrawerTitle(feature.properties.EMD_NM);
    setDrawerOpen(true);
    for(let i = 0; i < locationData.length; i++) {
      if (feature.properties.EMD_NM.includes(locationData[i].slice(0,2))) {
        fetch(`/api/youtube/find-entity/${locationData[i]}`, {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        })  
        .then(response => {
          return response.json()
        })
        .then(response => {
          setVideoList([...VideoList, ...response]);
        });
      fetch(`/api/youtube/find-popular/${locationData[i]}`, {
        method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then(response => {
          return response.json()
        })
        .then(response => {
          setTrendVideoList([...TrendVideoList, ...response])
        })
        break;
      }
    }
  }

  const onEachFeature = (feature, layer) => {
    layer.on('click', function (e) {
      whenClicked(e, feature)
      layer.setStyle({ fillColor: 'rgba(1,1,1,0)' });
      layer.bindPopup(feature.properties.EMD_NM).openPopup();
    });
    layer.on('mouseover', function (e) {
      layer.setStyle({ fillColor: 'rgba(1,1,1,0)' });
      layer.bindPopup(feature.properties.EMD_NM).openPopup();
    });
    layer.on('mouseout', function (e) {
      setSaveColor(feature.properties.EMD_NM);
    });
  }

  useEffect(() => {
    if(Slider){
      Slider.slideTo(SlideIndex);
    }
  }, [SlideIndex])

  useEffect(() => {
    CountYoutubePlace();
  }, [])

  useEffect(() => {
    CountYoutubePlace();
  }, [SaveColor])
  
  if (YoutubePlace){
    return(
      <div>
        <nav className='Navbar' style={{position: "static"}}>
        <h1 className="navbar-logo"><img src={main_logo} /></h1>
          <div className='menu-icon' onClick={handleClick}>
              <i className={clicked ? 'fas fa-times' : 'fas fa-bars'}></i>
          </div>
          <ul className={clicked ? 'nav-menu active' : 'nav-menu'} style={clicked ? {color:"red"} : {color:"black"}}>
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
        <div>
        <MapContainer
          center={[37.541, 126.986]}
          zoom={12}
          scrollWheelZoom={true}
          style={{ position: "static", width: "100%", height: "calc(100vh - 80px)" }}>
          <TileLayer
            url="http://{s}.tile.osm.org/{z}/{x}/{y}.png"
            attribution='&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          />
          <GeoJSON data={geoData} style={polystyle} onEachFeature={onEachFeature}/>
        </MapContainer>

        <Drawer placement='right' open={isDrawerOpen} onClose={() => setDrawerOpen(false)}>
          <Drawer.Header>
            <Drawer.Title>{DrawerTitle}</Drawer.Title>
            <Drawer.Actions>
              <Button onClick={() => setDrawerOpen(false)}>Cancel</Button>
              <Button onClick={() => setDrawerOpen(false)} appearance="primary">Confirm</Button>
            </Drawer.Actions>
          </Drawer.Header>
          <Drawer.Body>
            <ul className='youtubeList'>
                <Swiper navigation={true} modules={[Navigation]} className="menu" onSlideChange={(e) => setSlideIndex(e.activeIndex)}>
                  <SwiperSlide className="menu-contents"><h5>인기 동영상 TOP 10</h5></SwiperSlide>
                  <SwiperSlide className="menu-contents"><h5>인기 <span style={{color: "red"}}>급상승</span> 동영상 TOP 3</h5></SwiperSlide>
                </Swiper>
                <Swiper navigation={{nextEl: null,
          prevEl: null,disabledClass: "swiper-button-disabled"}} modules={[Navigation]} className="noarrow-menu" onSwiper={setSlider}>
                  <SwiperSlide>
                    <br/>
                  {VideoList.length !== 0 ? VideoList.map((video, index) => {
                  const videoId = video.videoLink;
                  const tagList = video.tag.split('#');
                  return (
                    <div>
                      <li className='youtubeBorder'>
                      <div className='youtubeRankingOut'>
                        <div className='youtubeRankingIn'>{index + 1}</div>
                      </div>
                        <img className='youtubeImage' src={video.thumbnail} alt=""></img>
                        <h5 className='youtubeTitle'>{video.name}<br/>
                          <h6 className='youtubeView'> 👍{video.likes === null? 0 : video.likes}</h6><br/>
                          <h6 className='youtubeView'> 👀{video.views}</h6>
                        </h5>
                        
                        <button 
                          onClick={() => !isToggleOn.includes(videoId) ? setToggle([...isToggleOn, videoId]) : setToggle(isToggleOn.filter((b) => b !== video.videoLink))}>
                          {isToggleOn.includes(videoId) ? "-":"+"}
                        </button>
                      </li>
                      <div>
                      {isToggleOn.includes(videoId) && <div style={{backgroundColor:"rgb(249, 249, 249)", width:"100%"}}>
                      {tagList.map((item, index) => {
                          if (item === ""){
                            return null;
                          }
                          else{
                            return(
                              <button className='youtubeTag' key={index}>{'#' + item}</button>
                              )
                            }
                          }
                        )} 
                        <iframe 
                        className='iframe16To9'
                        src={`https://www.youtube.com/embed/${videoId}`}
                        title="YouTube video player" frameborder="0" 
                        allow="accelerometer; autoplay; clipboard-write; 
                        encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                        </div>
                      }
                      </div>
                      </div>
                    )
                  }) : <div className='youtubeEmpty'>데이터가 없습니다.</div>}
                  </SwiperSlide>
                  <SwiperSlide>
                    <br/>
                  {TrendVideoList.length !== 0 ? TrendVideoList.map((video, index) => {
                  const videoId = video.videoLink;
                  const tagList = video.tag.split('#');
                return (
                  <div>
                    <li className='youtubeBorder' >
                    <div className='youtubeRankingOut'>
                      <div className='youtubeRankingIn'>{index + 1}</div>
                    </div>
                      <img className='youtubeImage' src={video.thumbnail} alt=""></img>
                      <h5 className='youtubeTitle'>{video.name}<br/>
                        <h6 className='youtubeView'> 👍{video.likes === null? 0 : video.likes}</h6><br/>
                        <h6 className='youtubeView'> 👀{video.views}</h6>
                      </h5>
                      
                      <button 
                        onClick={() => !isToggleOn.includes(videoId) ? setToggle([...isToggleOn, videoId]) : setToggle(isToggleOn.filter((b) => b !== video.videoLink))}>
                        {isToggleOn.includes(videoId) ? "-":"+"}
                      </button>
                    </li>
                    <div>
                    {isToggleOn.includes(videoId) && <div style={{backgroundColor:"rgb(249, 249, 249)", width:"100%"}}>
                    {tagList.map((item, index) => {
                        if (item === ""){
                          return null;
                        }
                        else{
                          return(
                            <button className='youtubeTag' key={index}>{'#' + item}</button>
                            )
                          }
                        }
                      )} 
                      <iframe 
                      className='iframe16To9'
                      src={`https://www.youtube.com/embed/${videoId}`}
                      title="YouTube video player" frameborder="0" 
                      allow="accelerometer; autoplay; clipboard-write; 
                      encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                      </div>
                    }
                    </div>
                    </div>
                  )
                }) : <div className='youtubeEmpty'>데이터가 없습니다.</div>}
                  </SwiperSlide>
                </Swiper>
            </ul>
          </Drawer.Body>
        </Drawer>
        </div>
      </div>
    );
  }
  else{
    <div>데이터가 없습니다.</div>
  };
}
export default Youtube;