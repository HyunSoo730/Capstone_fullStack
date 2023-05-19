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

  const [SaveColor, setSaveColor] = useState();

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
    CountYoutubePlace();
  }, [])

  useEffect(() => {
    CountYoutubePlace();
  }, [SaveColor])
  
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
              <div>인기 동영상 TOP 10</div>
            {VideoList.length !== 0 ? VideoList.map((video) => {
              const videoId = video.videoLink;
              const tagList = video.tag.split('#');
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
                {isToggleOn.includes(videoId) && <div style={{backgroundColor:"rgb(249, 249, 249)", width:"90%"}}>
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
            <div>인기 급상승 동영상 TOP 3</div>
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