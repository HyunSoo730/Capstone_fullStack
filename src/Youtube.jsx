import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";

const style = {
  backgroundColor : 'white',
  border: '1px solid black',
}

function Youtube(props){
    const [YoutubeHotPlace, setYoutubeHotPlace] = useState({});
    const [YoutubeMostPopular, setYoutubeMostPopular] = useState([]);
    const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;

    const CountYoutubeHotPlace = (items) => {
      for (let i = 0; i < items.length; i++){
        for(let j = 0; j < locationData.length; j++){
          if(items[i].snippet.title.includes(locationData[j])){
            if (YoutubeHotPlace[locationData[j]] === undefined){
              setYoutubeHotPlace({...YoutubeHotPlace, [locationData[j]]: 1});
            }
            else {
              YoutubeHotPlace[locationData[j]]++;
            }
            break;
          }
        }
      }
    }

    useEffect(() => {
      axios
        .get(
          //"https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=5&regionCode=KR&key=" + API_KEY
          "https://www.googleapis.com/youtube/v3/search?part=snippet&q=핫플&maxResults=20&regionCode=KR&key=" + API_KEY
        )
        .then((res) => {
          CountYoutubeHotPlace(res.data.items)
          console.log(res.data.items)
        })
        .catch(() => {});
    }, []);

    return(
        <div style={style}>
          <div>
          Youtube Trends
            {YoutubeMostPopular.map((item, index)=>{
              return (
                <li key={index}>
                    <a>
                      {item.snippet.title}
                    </a>
                </li>
              )
            })}
          </div>
          <div>
          Youtube HotPlace
            {Object.keys(YoutubeHotPlace).map((item, index)=>{
              console.log(YoutubeHotPlace)
              return(
                <li key={index}>
                    <a>
                      {item}
                    </a>
                </li>
              )
            })}
          </div>
        </div>
    );

}
export default Youtube;