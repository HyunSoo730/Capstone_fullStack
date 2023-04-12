import {React, useEffect, useState} from "react"
import axios from "axios";

function Youtube(props){
    const [mostPopular, setMostPopular] = useState([]);
    const API_KEY = process.env.REACT_APP_YOUTUBE_API_KEY;
    useEffect(() => {
      axios
        .get(
          "https://www.googleapis.com/youtube/v3/videos?part=snippet&chart=mostPopular&maxResults=3&regionCode=KR&key=" + API_KEY
        )
        .then((res) => {
          console.log(res);
          setMostPopular(res.data.items);
        })
        .catch(() => {});
    }, []);

    return(
        <div>
          Youtube
            {API_KEY}
            {console.log(mostPopular)}
        </div>
    );

}
export default Youtube;