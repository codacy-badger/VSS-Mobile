package org.dhbw.se.movietunes.http;

import org.dhbw.se.movietunes.extract.Extractor;
import org.dhbw.se.movietunes.model.Song;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SpotifyCommunication {

    String token;
    Extractor extractor;


    public SpotifyCommunication() {
        token = fetchToken();
        extractor = new Extractor();
    }

    private static final String HEADER_AUTHORIZATION = "Basic M2ZkOWFjYmJjZmY4NDVlNThhZTAxOGUwYTYwZjliMzU6ZTllODJjNmZjMDI0NGFiYWI1NWRiMTRjNGEzNjFhNzc=";

    public String fetchToken() {
        MediaType mediaType = MediaType.parse("x-www-form-urlencoded");
        RequestBody formBody = new FormBody.Builder().add("grant_type", "client_credentials").build();

        // RequestBody body = RequestBody.create( null, "grant_type:client_credentials");
        Request request = new Request.Builder()
                .url("https://accounts.spotify.com/api/token")
                .addHeader("Authorization", HEADER_AUTHORIZATION)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(formBody)
                .build();

        String responseBody = null;

        Response response = HttpCommunication.executeRequest(request);
        try {
            responseBody = response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("token req to spotify failed ", e);
        }

        JsonElement root = new JsonParser().parse(responseBody);
        String token = root.getAsJsonObject().get("access_token").getAsString();
        return token;
    }


    public String getResponseBodyForSearch(String searchString) {
        Request request = new Request.Builder()
                .url("https://api.spotify.com/v1/search?q=" + searchString + "&type=playlist")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        String responseBody = null;

        Response response = HttpCommunication.executeRequest(request);
        try {
            responseBody = response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("token req to spotify failed ", e);
        }
        return responseBody;
    }


    public PlaylistKey findPlaylist (String searchString){
        String responseBody = getResponseBodyForSearch(searchString);
        String playlistId = extractor.extractPlaylistIdFromSearchResult(responseBody);
        String userId = extractor.extractUserIdFromSearchResult(responseBody);
        return new PlaylistKey(userId, playlistId) ;
    }


    public String getSoundtrackJson(PlaylistKey playlistKey) {
        String user=playlistKey.getUserId();
        String playlist=playlistKey.getPlaylistId();
        String url = "https://api.spotify.com/v1/users/" + user + "/playlists/" + playlist + "/tracks?fields=items.track(name,duration_ms,artists)";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();

        String responseBody = null;

        Response response = HttpCommunication.executeRequest(request);
        try {
            responseBody = response.body().string();
        } catch (Exception e) {
            throw new RuntimeException("token req to spotify failed ", e);
        }
        return responseBody;
    }

    public List<Song> getSongsFromPlaylist (PlaylistKey playlistKey){
        String body = getSoundtrackJson(playlistKey);
        return extractor.extractSongs(body);
    }

}
