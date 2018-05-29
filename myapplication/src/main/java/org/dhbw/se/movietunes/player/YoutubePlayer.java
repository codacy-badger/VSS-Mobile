package org.dhbw.se.movietunes.player;

import org.dhbw.se.movietunes.http.HttpCommunication;

public class YoutubePlayer {
    private String songTitle;
    private HttpCommunication httpCommunication;
    private String uri;



    public YoutubePlayer(String songTitle) {
        this.songTitle = songTitle;
        this.uri = createUri();
    }


    public void play(){
        //navigate to the uri
    }


    private String createUri() {
        return "";
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

}
