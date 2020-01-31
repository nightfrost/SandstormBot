package de.nerogar.sandstormBot.system.youtubeDlSystem;

import de.nerogar.sandstormBot.Main;
import de.nerogar.sandstormBot.persistence.Database;
import de.nerogar.sandstormBot.persistence.DatabaseTable;
import de.nerogar.sandstormBotApi.persistence.entities.SongEntity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class YoutubeDlRequestCache {

	private static Database youtubeDlDatabase;

	private static DatabaseTable<QueryCacheEntity> queryCacheTable;
	private static DatabaseTable<SongEntity>       songCacheTable;

	static {
		try {
			youtubeDlDatabase = new Database("database/youtubeDlRequestCache/youtubeDlRequestCache.db", Database.loadMigrations("database/youtubeDlRequestCache/migrations"));
			queryCacheTable = youtubeDlDatabase.attachTable(QueryCacheEntity.class, "QueryCache");
			songCacheTable = youtubeDlDatabase.attachTable(SongEntity.class, "SongCache");
		} catch (FileNotFoundException e) {
			e.printStackTrace(Main.LOGGER.getErrorStream());
		}
	}

	public static synchronized void addQueryEntry(String query, List<String> predictedSongLocations) {
		if (queryCacheTable.select(c -> c.query.equals(query)).size() == 0) {
			for (String predictedSongLocation : predictedSongLocations) {
				queryCacheTable.insert(new QueryCacheEntity(query, predictedSongLocation));
			}
		}
	}

	public static synchronized List<String> getQueryEntry(String query) {
		List<QueryCacheEntity> queryCacheEntities = queryCacheTable.select(c -> c.query.equals(query));

		if (queryCacheEntities.size() > 0) {
			List<String> predictedSongLocations = new ArrayList<>();
			for (QueryCacheEntity queryCacheEntity : queryCacheEntities) {
				predictedSongLocations.add(queryCacheEntity.location);
			}
			return predictedSongLocations;
		} else {
			return null;
		}
	}

	public static synchronized void addSongEntry(String predictedLocation, SongEntity songEntity) {
		if (songCacheTable.select(s -> s.predictedLocation.equals(predictedLocation)).size() == 0) {
			songCacheTable.insert(songEntity.clone());
		}
	}

	public static synchronized SongEntity getSongEntry(String predictedLocation) {
		List<SongEntity> songEntities = songCacheTable.select(s -> s.predictedLocation.equals(predictedLocation));
		if (songEntities.size() > 0) {
			return songEntities.get(0).clone();
		} else {
			return null;
		}
	}

}