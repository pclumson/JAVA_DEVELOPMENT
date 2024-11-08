create table Game (
    title varchar(20) not null,
    gameId int not null,
    yearReleased int not null,
    genre varchar(20) not null,

    primary key (gameId),
    check (gameId > 0)
);

create table Player (
    playerName varchar(20) not null,
    email varchar(20),
    playerId int not null,

    primary key (playerId),
    check (playerId > 0)
);

create table OwnsGame (
    playerId int not null,
    gameId int not null,

    foreign key (playerId) references Player (playerId)
        on delete cascade,
    foreign key (gameId) references Game (gameId)
        on delete cascade
);

create table Review (
    reviewId int not null,
    playerId int not null,
    gameId int not null,
    rating float not null,
    recommended boolean not null,
    reviewText varchar(255),

    foreign key (playerId) references Player (playerId)
        on delete cascade,
    foreign key (gameId) references Game (gameId)
        on delete cascade,
    primary key (reviewId),
    check (reviewId > 0)
);

create table Platform (
    platformId int not null,
    platformName varchar(20) not null,
    creator varchar(20) not null,
    forcedDRM boolean not null,

    primary key (platformId),
    check (platformId > 0)
);

create table GamesAreOn (
    gameId int not null,
    platformId int not null,

    foreign key (platformId) references Platform (platformId)
        on delete cascade,
    foreign key (gameId) references Game (gameId)
        on delete cascade
);

create table Developer (
    developerId int not null,
    developerName varchar(20) not null,
    president varchar(20) not null,

    primary key (developerId),
    check (developerId > 0)
);

create table DevelopedBy (
    developerId int not null,
    gameId int not null,

    foreign key (developerId) references Developer (developerId)
        on delete cascade,
    foreign key (gameId) references Game (gameId)
        on delete cascade
);

create table Publisher (
    publisherId int not null,
    publisherName varchar(20) not null,
    president varchar(20) not null,

    primary key (publisherId),
    check (publisherId > 0)
);

create table PublishedBy (
    publisherId int not null,
    gameId int not null,

    foreign key (publisherId) references Publisher (publisherId)
        on delete cascade,
    foreign key (gameId) references Game (gameId)
        on delete cascade
);