/*Saurabh*/
package com.projects.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/* this is a package*/
public class data_source {
    public static final String songs_table="songs";
    public static final String albums_table="albums";
    public static final String artists_table="artists";

    public static final String path_name="jdbc:sqlite:C:\\Users\\User\\music.db";     //path where database file is stored  .

    public static final String songs_track="track";
    public static final String songs_title="title";
    public static final String songs_id="_id";
    public static final String songs_album="songs.album";

    public static final String albums_id="_id";
    public static final String albums_name="name";
    public static final String albums_artist="albums.artist";

    public static final String artists_id="_id";
    public static final String artists_name="name";
    private Connection con;
    public boolean open() {
        try {
            con = DriverManager.getConnection(path_name);
            return true;
        } catch (SQLException e) {
            System.out.println("Error 404 : " + e.getMessage());
            return false;
        }
    }
     public void close()
        {
            try
            {
                con.close();
            }
            catch (SQLException e)
            {
                System.out.println("Error 404 : "+e.getMessage());

            }

        }
        public Statement get_statment() throws SQLException
        {
            return con.createStatement();
        }
        public List<Artist> getArtist()
        {
            try
            {
            con = DriverManager.getConnection(path_name);
            Statement statement = con.createStatement();
            ResultSet sets = statement.executeQuery("SELECT * FROM " + artists_table + " order by "+artists_id);
            List<Artist> artists=new ArrayList<>();
            while(sets.next())
            {
                Artist artist=new Artist();
                artist.setId(sets.getInt(artists_id));
                artist.setName(sets.getString(artists_name));
                artists.add(artist);

            }
            sets.close();
            statement.close();
            close();
            return artists;
           }
        catch (SQLException e)
        {
            System.out.println("Error: "+e.getMessage());
        }
            return null;
        }
        public List<Song> getSongs()
        {
            try
            {
                con = DriverManager.getConnection(path_name);
                Statement statement = con.createStatement();
                ResultSet sets = statement.executeQuery("SELECT * FROM " + songs_table + " order by "+songs_track);
                List<Song> songs=new ArrayList<>();
                while(sets.next())
                {
                    Song song=new Song();
                    song.setTrack(sets.getInt(songs_track));
                    song.setTitle(sets.getString(songs_title));
                    songs.add(song);
                }
                sets.close();
                statement.close();
                close();
                return songs;
            }
            catch (SQLException e)
            {
                System.out.println("Error: "+e.getMessage());
            }
            return null;
        }
        public List<Album> getAlbums()
        {
            try
            {
                con = DriverManager.getConnection(path_name);
                Statement statement = con.createStatement();
                ResultSet sets = statement.executeQuery("SELECT * FROM " + albums_table + " order by "+albums_id);
                List<Album> albums=new ArrayList<>();
                while(sets.next())
                {
                    Album album=new Album();
                    album.setId(sets.getInt(albums_id));
                    album.setName(sets.getString(albums_name));
                    albums.add(album);
                }
                sets.close();
                statement.close();
                close();
                return albums;
            }
            catch (SQLException e)
            {
                System.out.println("Error: "+e.getMessage());
            }
            return null;

        }
     }


