package org.dhbw.se.movietunes.extract;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.dhbw.se.movietunes.model.Song;

import java.util.ArrayList;
import java.util.List;

public class Extractor {

    private JsonElement getFirstPlaylistElement(String playlistSearchResult) {
        JsonElement root = new JsonParser().parse(playlistSearchResult);
        JsonObject playlists = root.getAsJsonObject().getAsJsonObject("playlists");

        JsonArray items = playlists.getAsJsonArray("items");
        if (items.size() < 0) {
            throw new RuntimeException("no playlists found");
        }

        JsonElement firstPlaylist = items.get(0);

        return firstPlaylist;
    }


    public String extractPlaylistIdFromSearchResult(String searchResult) {

        JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);

        String id = firstPlaylist.getAsJsonObject().getAsJsonPrimitive("id").getAsString();

        return id;

    }


    public String extractUserIdFromSearchResult(String searchResult) {
        JsonElement firstPlaylist = getFirstPlaylistElement(searchResult);
        String userID = firstPlaylist.getAsJsonObject().getAsJsonObject("owner").getAsJsonPrimitive("id").getAsString();
        return userID;
    }


    public Song extractSingleSong(JsonElement item){
        JsonObject track=item.getAsJsonObject().getAsJsonObject("track");
        String songTitle=track.getAsJsonPrimitive("name").getAsString();
        String duration=track.getAsJsonPrimitive("duration_ms").getAsString();
        String artist=extractArtistName(track);

        Song result = new Song();
        result.setSongTitle(songTitle);
        result.setDuration(duration);
        result.setSinger(artist);
        return result;
    }

    private String extractArtistName(JsonObject track) {
        JsonArray artists=track.getAsJsonArray("artists");
        if(artists.size()<0){
            throw new RuntimeException("No artists found");
        }
        JsonElement singleArtist=artists.get(0);
        String name=singleArtist.getAsJsonObject().getAsJsonPrimitive("name").getAsString();
        return name;

       // JsonArray artists=item.getAsJsonObject().getAsJsonObject("artists").getAsJsonArray();

    }

    public List<Song> extractSongs(String tracklistDetailsResponse){
        JsonElement root = new JsonParser().parse(tracklistDetailsResponse);
        JsonArray items = root.getAsJsonObject().getAsJsonArray("items");
        if (items.size() < 0) {
            throw new RuntimeException("no items found");
        }


       List<Song> songs=new ArrayList<>();
        for(JsonElement item:items){
            songs.add(extractSingleSong(item));
        }
        return songs;
    }




}