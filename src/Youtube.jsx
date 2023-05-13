import {React, useEffect, useState, useCallback} from "react"
import { MapContainer, TileLayer, GeoJSON } from 'react-leaflet'
import geoData from './LocationData.json'
import { Drawer, Button } from 'rsuite';
import { locationData } from "./LocationDataItems";

import "rsuite/dist/rsuite.css";
import './YoutubeVideoStyle.css';

function Youtube(props){
  const [YoutubePlace, setYoutubePlace] = useState([]);
  const [VideoList, setVideoList] = useState([]);
  const [isDrawerOpen, setDrawerOpen] = useState(false);
  const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERROR");

  const [isToggleOn, setToggle] = useState([]);

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
        break;
      }
    }
  }

  const onEachFeature = (feature, layer) => {
    if(feature.properties){
      layer.bindPopup(feature.properties.EMD_NM);
    }
    layer.on({
      click: (e) => {whenClicked(e, feature)}
    });
  }

  useEffect(() => {
    CountYoutubePlace();
  }, [])
  
  if (YoutubePlace){
    return(
      <div>
        <div>
        <MapContainer
          center={[37.541, 126.986]}
          zoom={12}
          scrollWheelZoom={true}
          style={{ width: "100%", height: "calc(100vh - 0rem)" }}>
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
              {console.log(VideoList)}
            {VideoList.length !== 0 ? VideoList.map((video) => {
              const videoId = video.videoLink;
            return (
              <div>
                <li className='youtubeBorder' >
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
                {isToggleOn.includes(videoId) && <iframe 
                  width="90%" height="auto"
                  src={`https://www.youtube.com/embed/${videoId}`}
                  title="YouTube video player" frameborder="0" 
                  allow="accelerometer; autoplay; clipboard-write; 
                  encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                }
                </div>
              </div>
              )
            }) : <div className='youtubeEmpty'>데이터가 없습니다.</div>}
            </ul>
          </Drawer.Body>
        </Drawer>
        </div>
      </div>
    );
  }
  else{
    <div>EMPTY</div>
  };
}
export default Youtube;