use cs3380;

drop table if exists MatchTeams;
drop table if exists MatchPlayers;
drop table if exists Match;
drop table if exists League;
drop table if exists Stadium;
drop table if exists City;
drop table if exists Country;
drop table if exists Team_attrs;
drop table if exists Team;
drop table if exists Player_attrs;
drop table if exists Player;

CREATE TABLE Country (
	country_id INTEGER PRIMARY KEY IDENTITY(1,1),
	country_name VARCHAR(50),
	country_population INTEGER,
);
CREATE TABLE City (
	city_id	INTEGER PRIMARY KEY IDENTITY(1,1),
	city_name VARCHAR(200),
	country_id	INTEGER,
	city_population	INTEGER,
	FOREIGN KEY(country_id) REFERENCES Country(country_id),
);
CREATE TABLE Stadium (
	stadium_id INTEGER PRIMARY KEY IDENTITY(1,1),
	stadium_name VARCHAR(200),
	capacity INTEGER,
	city_id	INTEGER,
	country_id INTEGER,
	built_in INTEGER,
	FOREIGN KEY(country_id) REFERENCES Country(country_id),
	FOREIGN KEY(city_id) REFERENCES City(city_id),
);
CREATE TABLE League (
	league_id	INTEGER PRIMARY KEY IDENTITY(1,1),
	country_id	INTEGER,
	league_name	VARCHAR(100),
	FOREIGN KEY(country_id) REFERENCES Country(country_id),
);
CREATE TABLE Match (
	match_id INTEGER PRIMARY KEY IDENTITY(1,1),
	league_id INTEGER,
	season VARCHAR(20),
	stage INTEGER,
	stadium_id INTEGER,
	match_date VARCHAR(50),
	home_team_goal INTEGER,
	away_team_goal INTEGER,
	B365H NUMERIC,
	B365D NUMERIC,
	B365A NUMERIC,
	BWH NUMERIC,
	BWD NUMERIC,
	BWA NUMERIC,
	IWH NUMERIC,
	IWD NUMERIC,
	IWA NUMERIC,
	LBH NUMERIC,
	LBD	NUMERIC,
	LBA NUMERIC,
	PSH	NUMERIC,
	PSD NUMERIC,
	PSA	NUMERIC,
	WHH NUMERIC,
	WHD NUMERIC,
	WHA NUMERIC,
	SJH	NUMERIC,
	SJD NUMERIC,
	SJA NUMERIC,
	VCH NUMERIC,
	VCD	NUMERIC,
	VCA NUMERIC,
	GBH NUMERIC,
	GBD NUMERIC,
	GBA NUMERIC,
	BSH NUMERIC,
	BSD NUMERIC,
	BSA NUMERIC,
	FOREIGN KEY(stadium_id) REFERENCES Stadium(stadium_id),
	FOREIGN KEY(league_id) REFERENCES League(league_id),
);
CREATE TABLE Team (
	team_id INTEGER NOT NULL,
	team_long_name VARCHAR(100),
	team_short_name	VARCHAR(10),
	PRIMARY KEY(team_id)
);
CREATE TABLE Team_attrs (
	team_id	INTEGER NOT NULL,
	retrieval_date VARCHAR(10) NOT NULL,
	buildUpPlaySpeed INTEGER,
	buildUpPlaySpeedClass VARCHAR(20),
	buildUpPlayDribbling INTEGER,
	buildUpPlayDribblingClass VARCHAR(20),
	buildUpPlayPassing INTEGER,
	buildUpPlayPassingClass VARCHAR(20),
	buildUpPlayPositioningClass	VARCHAR(20),
	chanceCreationPassing INTEGER,
	chanceCreationPassingClass VARCHAR(20),
	chanceCreationCrossing INTEGER,
	chanceCreationCrossingClass VARCHAR(20),
	chanceCreationShooting INTEGER,
	chanceCreationShootingClass VARCHAR(20),
	chanceCreationPositioningClass VARCHAR(20),
	defencePressure	INTEGER,
	defencePressureClass VARCHAR(20),
	defenceAggression INTEGER,
	defenceAggressionClass VARCHAR(20),
	defenceTeamWidth INTEGER,
	defenceTeamWidthClass VARCHAR(20),
	defenceDefenderLineClass VARCHAR(20),
	FOREIGN KEY(team_id) REFERENCES Team(team_id),
	Constraint PK_Team_attrs PRIMARY KEY(team_id,retrieval_date)
);
CREATE TABLE MatchTeams (
	match_id INTEGER NOT NULL REFERENCES Match(match_id),
	team_id	INTEGER NOT NULL REFERENCES Team(team_id),
	side VARCHAR(20),
	PRIMARY KEY (match_id,team_id)
);
CREATE TABLE Player (
	player_id INTEGER NOT NULL,
	player_name VARCHAR(50),
	birthday VARCHAR(20),
	player_height DECIMAL(6,2),
	player_weight INTEGER,
	PRIMARY KEY(player_id)
);
CREATE TABLE Player_attrs (
	player_id INTEGER NOT NULL,
	retrieval_date varchar(10) NOT NULL,
	overall_rating INTEGER,
	potential INTEGER,
	preferred_foot VARCHAR(20),
	attacking_work_rate VARCHAR(20),
	defensive_work_rate	VARCHAR(20),
	crossing INTEGER,
	finishing INTEGER,
	heading_accuracy INTEGER,
	short_passing INTEGER,
	volleys INTEGER,
	dribbling INTEGER,
	curve INTEGER,
	free_kick_accuracy INTEGER,
	long_passing INTEGER,
	ball_control INTEGER,
	acceleration INTEGER,
	sprint_speed INTEGER,
	agility INTEGER,
	reactions INTEGER,
	balance INTEGER,
	shot_power INTEGER,
	jumping INTEGER,
	stamina	INTEGER,
	strength INTEGER,
	long_shots INTEGER,
	aggression INTEGER,
	interceptions INTEGER,
	positioning INTEGER,
	vision INTEGER,
	penalties INTEGER,
	marking INTEGER,
	standing_tackle INTEGER,
	sliding_tackle INTEGER,
	gk_diving INTEGER,
	gk_handling	INTEGER,
	gk_kicking INTEGER,
	gk_positioning INTEGER,
	gk_reflexes INTEGER,
	FOREIGN KEY(player_id) REFERENCES Player(player_id),
	Constraint PK_Player_attrs PRIMARY KEY(player_id,retrieval_date)
);
CREATE TABLE MatchPlayers (
	match_id INTEGER NOT NULL REFERENCES Match(match_id),
	player_id	integer NOT NULL REFERENCES Player(player_id),
	side VARCHAR(20),
	Constraint PK_MatchPlayer PRIMARY KEY(match_id,player_id)
);