create table Song
(
    "SongId"                 integer primary key autoincrement,
    "AudioTrackProviderName" text      not null,
    "Location"               text      not null,
    "PredictedLocation"      text      not null,
    "PlaylistId"             integer   not null,
    "Title"                  text      not null,
    "Artist"                 text      null,
    "Album"                  text      null,
    "Duration"               integer   not null,
    "Query"                  text      not null,
    "User"                   text      not null,
    "PlayCount"              integer   not null,
    "LastPlayed"             timestamp not null
);

create table Playlist
(
    "PlaylistId"      integer primary key autoincrement,
    "Name"            text    not null,
    "Order"           text    not null,
    "CurrentPosition" integer not null
);

create table Playlists
(
    "PlaylistsId"     integer primary key autoincrement,
    "CurrentPlaylist" integer not null
);

insert into Playlist ("Name", "Order", "CurrentPosition") values
('default playlist', 'DEFAULT', -1);