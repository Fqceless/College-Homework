drop table teamseasonplayer;
drop table battingstats;
drop table catchingstats;
drop table fieldingstats;
drop table pitchingstats;
drop table playerseason;
drop table playerposition;
drop table player;
drop table teamseason;
drop table team;

create table team (
	teamId			numeric(10,0) 	IDENTITY(10000,5) primary key,
	name			varchar(50)		not null,
	league			varchar(2)		not null,
	yearFounded		numeric(4,0),
	yearLast		numeric(4,0));
	
create table player (
	playerId		numeric(10,0) 	IDENTITY(10000,5) primary key,
	name			varchar(100)		not null,
	givenName		varchar(255),
	birthDay		date,
	deathDay		date,
	battingHand		varchar(1)		check (battingHand in ('L', 'R', 'S')),
	throwingHand   	varchar(1)		check (throwingHand in ('L', 'R')),
	birthCity		varchar(50),
	birthState		varchar(50),
  birthCountry  varchar(50),
	firstGame		date,
	lastGame		date,
	CONSTRAINT uniqueConstraint_Player UNIQUE (name, birthDay, deathDay));
	
create table teamseason (
	teamId			numeric(10,0)	references team on delete cascade,
	year			numeric(4,0),
	gamesPlayed		numeric(3,0),
	wins			numeric(4,0),
	losses			numeric(4,0),
	rank			numeric(2,0),
	totalAttendance numeric(7,0),
	primary key (teamId, year));	
	
create table playerposition (
	playerId		numeric(10,0),
	position		varchar(10),
	primary key(playerId, position),
	foreign key(playerId) references player on delete cascade);
	
create table playerseason (
	playerId		numeric(10,0)	references player on delete cascade,
	year			numeric(4,0),
	gamesPlayed		numeric(3,0),
	salary			numeric(12,2),
	primary key (playerId, year));

create table battingstats (
	playerId		numeric(10,0),
	year			numeric(4,0),
	atBats			numeric(4,0),
	hits			numeric(4,0),
	doubles			numeric(4,0),
	triples			numeric(4,0),
	homeRuns		numeric(4,0),
	runsBattedIn	numeric(4,0),
	strikeouts		numeric(4,0),
	walks			numeric(4,0),
	hitByPitch		numeric(4,0),
	intentionalWalks		numeric(4,0),
	steals			numeric(4,0),
	stealsAttempted	numeric(4,0),
	primary key(playerId, year),
	foreign key(playerId, year) references playerseason on delete cascade);
	
create table catchingstats (
	playerId		numeric(10,0),
	year			numeric(4,0),
	passedBalls		numeric(4,0),
	wildPitches		numeric(4,0),
	stealsAllowed	numeric(4,0),
	stealsCaught	numeric(4,0),
	primary key(playerId, year),
	foreign key(playerId, year) references playerseason on delete cascade);

create table fieldingstats (
	playerId		numeric(10,0),
	year			numeric(4,0),
	errors			numeric(4,0),
	putOuts			numeric(4,0),
	primary key(playerId, year),
	foreign key(playerId, year) references playerseason on delete cascade);

create table pitchingstats (
	playerId		numeric(10,0),
	year			numeric(4,0),
	outsPitched		numeric(4,0),
	earnedRunsAllowed	numeric(4,0),
	homeRunsAllowed		numeric(4,0),
	strikeouts		numeric(4,0),
	walks			numeric(4,0),
	wins			numeric(4,0),
	losses			numeric(4,0),
	wildPitches		numeric(4,0),
	battersFaced	numeric(4,0),
	hitBatters		numeric(4,0),
	saves			numeric(4,0),
	primary key(playerId, year),
	foreign key(playerId, year) references playerseason on delete cascade);	
	
create table teamseasonplayer (
	playerId		numeric(10,0),
	teamId			numeric(10,0),
	year			numeric(4,0),
	primary key(playerId, teamId, year),
	foreign key(playerId) references player on delete cascade,
	foreign key(teamId, year) references teamseason on delete cascade);