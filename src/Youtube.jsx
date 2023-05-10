import {React, useEffect, useState} from "react"
import { MapContainer, TileLayer, GeoJSON } from 'react-leaflet'
import geoData from './LocationData.json'
import { Drawer, Button } from 'rsuite';

import "rsuite/dist/rsuite.css";
import './YoutubeVideoStyle.css';

function Youtube(props){
  const [YoutubePlace, setYoutubePlace] = useState([]);
  const [VideoList, setVideoList] = useState([]);
  const [isDrawerOpen, setDrawerOpen] = useState(false);
  const [DrawerTitle, setDrawerTitle] = useState("DRAWER_TITLE_ERROR");

  const CountYoutubePlace = () => {
    fetch("api/return", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })  
    .then(response => {
      return response.json();
    })
    .then(response => {
      console.log(response)
      response.map((item)=>{
        setYoutubePlace(current => [...current, {name: item["dong"], data: [item["count"]]}]);
      });
    });
  }

  const polystyle = (feature) => {
    for (let i = 0; i < YoutubePlace.length; i++){
      if (feature.properties.EMD_NM.includes(YoutubePlace[i].name)){
        return {
          fillColor: 'rgba(' + 5 * YoutubePlace[i].data[0] + '0, 0, 0.5)',
          weight: 2,
          opacity: 1,
          color: 'white',  //Outline color
          fillOpacity: 0.7
        };
      }
    }
    return {
      fillColor: 'rgba(10, 0, 0, 0.5)',
      weight: 2,
      opacity: 1,
      color: 'white',  //Outline color
      fillOpacity: 0.7
    };
  }

  function whenClicked(e, feature) {
    setDrawerTitle(feature.properties.EMD_NM);
    setDrawerOpen(true);
    //setVideoList([...VideoList, ...result.items]);
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
            <div>
            {VideoList.map((video) => {
            return (
              <li className='youtubeBorder'>
                <img className='youtubeImage' src={video.snippet.thumbnails.default.url} alt=""></img>
                <h5 className='youtubeTitle'>{video.snippet.title}</h5>
                <div className='youtubeChannel'>{video.snippet.channelTitle}</div>
              </li>
              )
            })}
            </div>
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