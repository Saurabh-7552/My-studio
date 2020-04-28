package com.projects;
import com.projects.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) {

      data_source data_Source = new data_source();
      List<Artist> artists=data_Source.getArtist();
      for(Artist a:artists)
       {
          System.out.println(a.getId()+" "+a.getName());
       }
      List<Song>songs=data_Source.getSongs();
        for(Song a:songs)
        {
            System.out.println(a.getTrack()+" "+a.getTitle());
        }
      List<Album> albums=data_Source.getAlbums();
       for(Album a:albums)
        {
            System.out.println(a.getId()+" "+a.getName());
        }
        try{
            boolean open = data_Source.open();
            if(open)
            {
                Statement statement = data_Source.get_statment();
                ResultSet sets = statement.executeQuery("SELECT artists._id,artists.name,albums.name,songs.title from songs inner join albums ON "+data_source.songs_album+"=albums._id " +
                        "inner join artists ON "+data_source.albums_artist+"=artists._id ORDER by artists._id");
                while (sets.next()) {
                    System.out.println(sets.getInt(data_source.artists_id) + " " + sets.getString(data_source.albums_name) + " " + sets.getString(data_source.songs_title));
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("Error : "+e.getMessage());
        }
    }


}
