import {React, useEffect, useState} from "react"
import axios from "axios";
import { locationData } from "./LocationDataItems";



function CountYoutubeHotPlace(title) {
  for(let i = 0; i < locationData.length; i++){
    console.log(i);
    if(title.includes(locationData[i])){
      return locationData[i];
    }
  }
  return 0;
}

function Youtube(props){
    const [YoutubeMostPopular, setYoutubeMostPopular] = useState([]);
    const [YoutubeHotPlace, setYoutubeHotPlace] = useState([]);
    const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;

    const CountYoutubeHotPlace = (items) => {
      for (let i = 0; i < items.length; i++){
        for(let j = 0; j < locationData.length; j++){
          if(items[i].snippet.title.includes(locationData[j])){
            setYoutubeHotPlace([...YoutubeHotPlace, locationData[j]]);
            break
          }
        }
      }
    }

    useEffect(() => {
      axios
        .get(
          //"https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=3&regionCode=KR&key=" + API_KEY
          "https://www.googleapis.com/youtube/v3/search?part=snippet&q=핫플&maxResults=3&regionCode=KR&key=" + API_KEY
        )
        .then((res) => {
          CountYoutubeHotPlace(res.data.items)
        })
        .catch(() => {});
    }, []);

    return(
        <div>
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
            {YoutubeHotPlace.map((item, index)=>{
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