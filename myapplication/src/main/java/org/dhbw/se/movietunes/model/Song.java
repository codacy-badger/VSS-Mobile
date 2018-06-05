package org.dhbw.se.movietunes.model;



import java.util.List;

/**
 * Created by anastasia.schwed on 11/26/2017.
 */

public class Song {
    private String trackId;
    private String songTitle;
    private String composer;
    private String singer;
    private String duration;
    private String uri;
    private List<Movie> usedInMovies;
    private List<Song> similarSongs;

    public String getDuration() {
        return duration;
    }

    public void setUri(String uri){
        this.uri=uri;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTime() {
        return duration;
    }

    public void setDuration(String time) {
        this.duration = time;
    }

    public List<Movie> getUsedInMovies() {
        return usedInMovies;
    }

    public void setUsedInMovies(List<Movie> usedInMovies) {
        this.usedInMovies = usedInMovies;
    }

    public List<Song> getSimilarSongs() {
        return similarSongs;
    }
    public String getUri(){
        return this.uri;
    }

    public void setSimilarSongs(List<Song> similarSongs) {
        this.similarSongs = similarSongs;
    }
}
