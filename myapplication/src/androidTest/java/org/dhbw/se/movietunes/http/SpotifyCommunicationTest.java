package org.dhbw.se.movietunes.http;

import android.support.test.runner.AndroidJUnit4;

import org.dhbw.se.movietunes.model.Song;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SpotifyCommunicationTest {

    private SpotifyCommunication classUnderTest = new SpotifyCommunication();

    @Test
    public void testFetchToken() {
        String token = classUnderTest.fetchToken();
        assertTrue(token.length() > 20);
    }


    @Test
    public void testFindPlaylist() {
        PlaylistKey playlistKey = classUnderTest.findPlaylist("terminator");
        assertTrue(playlistKey.getPlaylistId() != "");
        assertTrue(playlistKey.getUserId() != "");
    }

    @Test
    public void testGetSongsFromPlaylist() {
        PlaylistKey key = new PlaylistKey("moyomba", "6lwDOP2ZW0h2jOccLB0342", "");

        List<Song> songsFromPlaylist = classUnderTest.getSongsFromPlaylist(key);

        assertNotNull(songsFromPlaylist);
        assertTrue(songsFromPlaylist.size() > 0);
        Song song = songsFromPlaylist.get(0);
        assertNotNull(song.getSongTitle() != null);
    }

    @Test
    public void testGetRecommendations() {


        List<Song> songsFromPlaylist = classUnderTest.getRecommendations("0c6xIDDpzE81m2q797ordA");

        assertNotNull(songsFromPlaylist);
        assertTrue(songsFromPlaylist.size() > 0);
        Song song = songsFromPlaylist.get(0);
        assertNotNull(song.getSongTitle() != null);
    }


}
