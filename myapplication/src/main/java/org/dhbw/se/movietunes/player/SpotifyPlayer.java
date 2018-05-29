package org.dhbw.se.movietunes.player;

import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.http.HttpCommunication;
import org.dhbw.se.movietunes.http.SpotifyCommunication;

public class SpotifyPlayer  {

    private String songTitle;
    private HttpCommunication httpCommunication;
    private String uri;




    public SpotifyPlayer(String songTitle) {
        this.songTitle = songTitle;
        this.uri = createUri();
    }

   public SpotifyPlayer(String songTitle, String uri){
        this.songTitle = songTitle;
        this.uri = uri;

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
