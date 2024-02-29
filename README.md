<!-- omit in toc -->
# FootBall Project Database Guide
<!-- omit in toc -->
## Table of Contents
- [General Information](#general-information)
- [Running the Program and Populating the Database](#running-the-program-and-populating-the-database)
- [Scope of Functionality](#scope-of-functionality)
- [Examples of Use](#examples-of-use)
- [Project Status](#project-status)
- [Sources](#sources)
- [Contributors](#contributors)

## General Information

The database created for this project contains data for european football (soccer) matches between 2008 and 2015.
The database contains match, team, player, stadium, league, city and country information. The database is initialized and
populated using sql files and uses a command-line interface to access and interact with database. The interface was created
using the Java programming language.

## Running the Program and Populating the Database

1. Before running the program, edit the ```auth.cfg``` file with your username and password to connect to the database server.
2. The program can be run using the command: ```make run```
3. Once the program is running, type ```init``` to populate the database. The population process takes approximately 20 minutes so only do this once.
4. The database is now setup and ready to use!
   
## Scope of Functionality

The database interface implements the following queries:

1. ```init``` - Initialize database
2. ```c <name>``` - Search for a country population
3. ```ci <name>``` - Search for a city population and the country of the city
4. ```t <team acronym>``` - Search for a team's full name
5. ```tp <team acronym>``` - Search for all players who have played for the team
6. ```p <name>``` - Search for a player's birthday, height, weight
7. ```pi <player ID>```  - Search for player's name, birthday, height, weight
8. ```pa <player ID>``` - Search for player attributes
9. ```s <stadium ID>``` - Search for stadium capacity, city name, country name, built date
10. ```m <match ID>``` - Search for  season, league, date, home score, away score, home team name, away team name
11. ```mt``` - Gives top 5 players that have played for the most teams
12. ```mm``` - Gives top 5 players that have played in the most matches
13. ```mg``` - Gives top 5 teams with the most goals per match
14. ```mga``` - Gives top 5 teams with the most goals against per match
15. ```mwt``` - Gives top 5 teams with highest wins per match
16. ```mlt``` - Gives top 5 teams with highest losses per match
17. ```ts``` < teamShortName >- Gives performance statistics of a team
18. ```mus``` <capacity>- Gives top 5 most used stadiums for matches under a given capacity
19. ```hgd``` - Gives the team with the highest goal differential for each season
20. ```lr``` < country > < year > - Final league standings for country's league. Enter year between 2008 and 2015
21. ```cfn``` - Gives top 20 most common male professional soccer player first names
22. ```cbm``` - Gives percentage of players born in each month ranked from highest to lowest
23. ```ht``` - Gives top 5 heaviest teams
24. ```tt``` - Gives top 5 tallest teams


## Examples of Use

Here are some examples of user inputs and program outputs using the
command-line interface:

- **input:** ```c Belgium``` <br>
  **output:** ```The population of Belgium is 11 million.```
- **input:** ```p Cristiano Ronaldo``` <br>
  **output:** ```Birthday of  Cristiano ronaldo is 1985-02-05, player height is: 185cm, player weight is: 176lbs.```
- **input:** ```t MOU``` <br>
  **output:**
  ```
  Team Info 
  ------------------------
  Full Name: Royal Excel Mouscron
  League: Belgium Jupiler League
  Country: Belgium

  FIFA Video Game Team Attributes
  -------------------------------------------
  buildUpPlaySpeed:               50
  buildUpPlaySpeedClass:          Balanced

  buildUpPlayDribbling            50      
  buildUpPlayDribblingClass:      Normal

  buildUpPlayPassing:             50      
  buildUpPlayPassingClass:        Mixed

  buildUpPlayPositioningClass:    Organised

  chanceCreationPassing:          50       
  chanceCreationPassingClass:     Normal

  chanceCreationCrossing:         50       
  chanceCreationCrossingClass:    Normal

  chanceCreationShooting:         50       
  chanceCreationShootingClass:    Normal

  defencePressure:                45       
  defencePressureClass:           Medium

  defenceAggression:              45       
  defenceAggressionClass:         Press

  defenceTeamWidth:               50       
  defenceTeamWidthClass:	        Normal

  defenceDefenderLineClass:       Cover    


- **input:** ```mg``` <br>https://github.com/yenaing-oo/soccer-database/blob/main/README.md
  **output:** 
  <!-- omit in toc -->
  ```
  Teams with the most goals per match
  Short Name  Long Name          Goals  Matches Played   Goals Per Match
  -----------------------------------------------------------------------
  BAR         FC Barcelona       849    304              2.79     
  REA         Real Madrid CF     843    304              2.77    
  PSV         PSV                652    272              2.40     
  BMU         FC Bayern Munich   653    272              2.40    
  AJA         Ajax               647    272              2.38     

- **input:** ```ht``` <br>
  **output:** 
  <!-- omit in toc -->
  ```
  Top 5 Heaviest Teams
  Rank  Team Name                   Average Weight (lbs)
  -------------------------------------------------
  1     1. FC Nürnberg              181.17          
  2     Wolverhampton Wanderers     180.31          
  3     FC Schalke 04               180.22          
  4     Portsmouth                  179.75          
  5     FC St. Pauli                179.51          



## Project Status

The project is fully functional, and working as expected.

## Sources

The sources for the project can be found at the following links
* https://www.kaggle.com/datasets/hugomathien/soccer
* https://en.wikipedia.org/wiki/List_of_cities_in_the_European_Union_by_population_within_city_limits
* https://en.wikipedia.org/wiki/List_of_European_stadiums_by_capacity

## Contributors

Safran Bin Kader, Colin McDonell, Ye Naing (Kelvin) Oo
