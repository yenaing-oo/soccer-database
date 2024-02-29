/**
* footballProject.java
*
* COMP 3380 Database Project
* Authors: Safran Bin Kader, Colin McDonell, Ye Naing Oo (Group 79)
* Date: November 22, 2022
* 
* Purpose: Populate and interact with football database using a command-line interface
*/
//Import Statements

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

// test changes to push
// test change

public class footballProject {

    public static void main(String[] args) {
        footballDatabase db = new footballDatabase();
        runConsole(db);
        System.out.println("Exiting...");
    }

    public static void runConsole(footballDatabase db) {
        Scanner console = new Scanner(System.in);
        System.out.print("Welcome to the Football Database Interface! Type h for help. ");
        System.out.print("\nPlease type your command here > ");
        String line = console.nextLine();
        String[] parts;
        String arg = "";


        while (line != null && !line.equals("q")) {
            parts = line.split("\\s+");
            String command;
            if (parts.length > 0) {
                command = parts[0];
            }
            else {
                //execute default case in switch statement if user enters space
                command = "default";
            }
            if (line.indexOf(" ") > 0)
                arg = line.substring(line.indexOf(" ")).trim();

            switch (command) {
                case "h":
                    printHelp();
                    break;
                case "c":
                    if (parts.length >= 2)
                        db.countrySearch(arg);
                    else
                        System.out.println("Require an argument for this command");
                    break;
                case "ci":
                    if (parts.length >= 2)
                        db.citySearch(arg);
                    else
                        System.out.println("Require an argument for this command");
                    break;
                case "l":
                    if (parts.length >= 2)
                        db.leagueSearch(arg);
                    else
                        System.out.println("Require an argument for this command");
                    break;
                case "t":
                    if (parts.length >= 2)
                        db.teamSearch(arg);
                    else
                        System.out.println("Require an argument for this command");
                    break;
                case "tp":
                    try {
                        if (parts.length >= 2)
                            db.teamPlayers(arg);
                        else
                            System.out.println("Require an argument for this command");
                    } catch (Exception e) {
                        System.out.println("id must be an integer");
                    }
                    break;
                case "p":
                    if (parts.length >= 2)
                        db.playerSearch(arg);
                    else
                        System.out.println("Require an argument for this command");
                    break;
                case "pi":
                    try {
                        if (parts.length >= 2)
                            db.playerSearchById(arg);
                        else
                            System.out.println("Require an argument for this command");
                    } catch (Exception e) {
                        System.out.println("id must be an integer");
                    }
                    break;
                case "pa":
                    try {
                        if (parts.length >= 2)
                            db.playerAttributes(arg);
                        else
                            System.out.println("Require an argument for this command");
                    } catch (Exception e) {
                        System.out.println("id must be an integer");
                    }
                    break;
                case "s":
                    try {
                        if (parts.length >= 2)
                            db.stadiumSearch(arg);
                        else
                            System.out.println("Require an argument for this command");
                    } catch (Exception e) {
                        System.out.println("id must be an integer");
                    }
                    break;
                case "m":
                    try {
                        if (parts.length >= 2) {
                            int match_id = Integer.parseInt(arg);
                            db.matchSearch(match_id);
                        } else {
                            System.out.println("Require an argument for this command");
                        }
                    } catch (Exception e) {
                        System.out.println("match_id must be an integer");
                    }
                    break;
                case "mt":
                    db.playedForMostTeams();
                    break;
                case "mm":
                    db.playedMostMatches();
                    break;
                case "mg":
                    db.mostGoals();
                    break;
                case "mga":
                    db.mostGoalsAgainst();
                    break;
                case "mwt":
                    db.mostWinningTeam();
                    break;
                case "mlt":
                    db.mostLosingTeam();
                    break;
                case "ts":
                    try {
                        if (parts.length >= 2)
                            db.teamStats(arg);
                        else
                            System.out.println("Require an argument for this command");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error getting team statistics.");
                    }
                    break;
                case "mus":
                    try {
                        if (parts.length >= 2) {
                            int capacity = Integer.parseInt(parts[1]);
                            db.mostUsedStadium(capacity);
                        } else {
                            System.out.println("Require an argument for this command");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Capacity must be an integer");
                    }
                    break;
                case "hgd":
                    db.highestGoalDifferentialPerSeason();
                    break;
                case "cfn":
                    db.mostCommonFirstNames();
                    break;
                case "cbm":
                    db.mostCommonMonths();
                    break;
                case "ht":
                    db.heaviestTeams();
                    break;
                case "tt":
                    db.tallestTeams();
                    break;
                case "lr":
                    if (parts.length >= 3) {
                        String country = parts[1];
                        String year = parts[2];
                        if (!Pattern.matches("[a-zA-Z]+", year) && year.length() == 4) {
                            db.standings(country, year);
                        } else {
                            System.out.println("Year must be a 4 digit number between 2008 and 2015");
                        }

                    } else {
                        System.out.println("Requires 2 arguments for this command");
                    }
                    break;
                case "init":
                    db.initialize();
                    break;
                default:
                    System.out.println("Read the help with h, or find help somewhere else.");
                    break;
            }

            System.out.print("\nPlease type your command here > ");
            line = console.nextLine();
        }

        console.close();
    }

    private static void printHelp() {
        System.out.println("Library database");
        System.out.println("Commands:");
        System.out.println("h - Get help");
        System.out.println("init - Initialize database");
        System.out.println("c <name> - Search for a country population");
        System.out.println("ci <name> - Search for a city population and the country of the city");
        System.out.println("t <team acronym> - Search for a team's full name");
        System.out.println("tp <team acronym> - Search for all players who have played for the team");
        System.out.println("p <name> - Search for a players birthday, height, weight");
        System.out.println("pi <playerId>  - Search for player's name, birthday, height, weight");
        System.out.println("pa <playerId> - Search for player attributes");
        System.out.println("s <stadiumId> - Search for stadium capacity, city name, country name, built date");
        System.out.println("m <matchId> - Search for  season, league, date, home score, away score, home team name, away team name");

        System.out.println("\nInteresting Queries:");
        System.out.println("mt - Gives top 5 players that have played for the most teams");
        System.out.println("mm - Gives top 5 players that have played in the most matches");
        System.out.println("mg - Gives top 5 teams with the most goals per match");
        System.out.println("mga - Gives top 5 teams with the most goals against per match");
        System.out.println("mwt - Gives top 5 teams with highest wins per match");
        System.out.println("mlt - Gives top 5 teams with highest losses per match");
        System.out.println("ts <teamShortName>- Gives performance statistics of a team");
        System.out.println("mus <capacity>- Gives top 5 most used stadiums for matches under a given capacity");
        System.out.println("hgd - Gives the team with the highest goal differential for each season");
        System.out.println("lr <country> <year> - Final league standings for country's league. Enter year between 2008 and 2015");
        System.out.println("cfn - Gives top 20 most common male professional soccer player first names");
        System.out.println("cbm - Gives percentage of players born in each month ranked from highest to lowest");
        System.out.println("ht - Gives top 5 heaviest teams");
        System.out.println("tt - Gives top 5 tallest teams");
        System.out.println();

        System.out.println("q - Exit the program");

        System.out.println("---- end help ----- ");
    }

}

class footballDatabase {
    private Connection connection;

    public footballDatabase() {
        try {
            Properties prop = new Properties();
            String fileName = "auth.cfg";
            try {
                FileInputStream configFile = new FileInputStream(fileName);
                prop.load(configFile);
                configFile.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Could not find config file.");
                System.exit(1);
            } catch (IOException ex) {
                System.out.println("Error reading config file.");
                System.exit(1);
            }
            String username = (prop.getProperty("username"));
            String password = (prop.getProperty("password"));

            if (username == null || password == null) {
                System.out.println("Username or password not provided.");
                System.exit(1);
            }

            String connectionUrl =
                    "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;"
                            + "database=cs3380;"
                            + "user=" + username + ";"
                            + "password=" + password + ";"
                            + "encrypt=false;"
                            + "trustServerCertificate=false;"
                            + "loginTimeout=30;";

            //Create a connection to the database
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    //Database Initialization
    public void initialize() {
        String line;
        String sqlStatement = "";
        final int MAX_ROWS_PER_INSERT = 1000;
        String[] sqlFiles = {"SQL Data Files/createTables.sql", "SQL Data Files/locationData.sql",
                "SQL Data Files/teamAndPlayersData.sql", "SQL Data Files/matchData.sql"};
        String[] processingMessages = {"Creating tables...", "Inserting Country, City, Stadium and League information...",
                "Inserting Team and Player information...", "Inserting Match data..."};

        try {
            Statement statement = connection.createStatement();
            //populate database
            System.out.println("Initializing database ...");
            for (int i = 0; i < sqlFiles.length; i++) {
                try {
                    BufferedReader inputFile = new BufferedReader(new FileReader(sqlFiles[i]));
                    System.out.println(processingMessages[i]);

                    String insertStmt = "";
                    int lineCounter = 1;
                    while ((line = inputFile.readLine()) != null) {
                        if (line.contains("INSERT INTO")) {
                            insertStmt = line.split("VALUES")[0] + " VALUES ";
                        }
                        if (line.contains(";")) {
                            sqlStatement += line + "\n";
                            //System.out.println(sqlStatement);
                            statement.executeUpdate(sqlStatement);
                            connection.commit();
                            sqlStatement = "";
                            lineCounter = 1;
                        } else if (lineCounter >= MAX_ROWS_PER_INSERT) {
                            sqlStatement += line.substring(0, line.length() - 1) + ";";
                            //System.out.println(sqlStatement);
                            statement.executeUpdate(sqlStatement);
                            connection.commit();
                            sqlStatement = insertStmt;
                            lineCounter = 2;
                        } else {
                            sqlStatement += line + "\n";
                        }
                        lineCounter++;
                    }
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error initializing database.");
        }
    }

    //Query Functions

    public void countrySearch(String country) {
        //gives population of country
        try {
            String sql = "select country_population from Country where country_name = ?";
            String countryName = country.substring(0, 1).toUpperCase() + country.substring(1).toLowerCase();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, countryName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("The population of " + countryName + " is " +
                        resultSet.getString("country_population") + " million.");

            }
            else {
                System.out.println("No country found with the name \"" + countryName + "\".");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void citySearch(String city) {
        //gives country and population of city
        try {
            String sql = "select country_name, city_population from City\n" +
                    "inner join Country on City.country_id = Country.country_id\n" +
                    "where City.city_name = ?";
            String cityName = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("The population of " + cityName + " is " +
                        resultSet.getString("city_population") + ".\n" +
                        cityName + " is in " + resultSet.getString("country_name") + ".");

            }
            else {
                System.out.println("No city found with the name \"" + cityName + "\".");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void leagueSearch(String country) {
        //gives league of country and all teams that have played in the league
        try {
            String leagueSql = "select League.league_name from Country\n" +
                    "inner join League on League.country_id = Country.country_id\n" +
                    "where Country.country_name = ?";
            String countryName = country.substring(0, 1).toUpperCase() + country.substring(1).toLowerCase();
            PreparedStatement leagueStatement = connection.prepareStatement(leagueSql);
            leagueStatement.setString(1, countryName);
            ResultSet leagueResult = leagueStatement.executeQuery();

            if (leagueResult.next()) {
                System.out.println("The League in " + countryName + " is the " +
                        leagueResult.getString("league_name") + ".\n");

                String teamsSql = "with cLeague as (select League.league_id from Country\n" +
                        "inner join League on League.country_id = Country.country_id\n" +
                        "where Country.country_name = ?)\n" +
                        "select Team.team_id, Team.team_long_name, Team.team_short_name, count(distinct Match.season) yearsPlayed from cLeague\n" +
                        "inner join Match on Match.league_id = cLeague.league_id\n" +
                        "inner join MatchTeams on MatchTeams.match_id = Match.match_id\n" +
                        "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                        "group by Team.team_id, Team.team_long_name, Team.team_short_name\n" +
                        "order by yearsPlayed desc";

                PreparedStatement teamsStatement = connection.prepareStatement(teamsSql);
                teamsStatement.setString(1, countryName);
                ResultSet teamsResult = teamsStatement.executeQuery();

                if (teamsResult.next()) {
                    System.out.println("Teams that have played in the " + leagueResult.getString("league_name"));
                    System.out.format("%-25s%-20s%-30s%n", "Team Long Name", "Team Short Name", "Years in the League");
                    System.out.println("-----------------------------------------------------------------------------");
                    do  {
                        System.out.format("%-25s%-20s%-10d%n", teamsResult.getString("team_long_name"),
                                teamsResult.getString("team_short_name"),
                                teamsResult.getInt("yearsPlayed"));
                    } while (teamsResult.next());
                }
                else {
                    System.out.println("No teams have played in " + leagueResult.getString("league_name"));
                }
            }
            else {
                System.out.println("No country found with the name \"" + countryName + "\".");
            }
            leagueResult.close();
            leagueStatement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    private int getTeamID(String shortName) {
        int id = -1;
        try {
            String uniqueCheck = "Select count(*) as numTeams from Team where Team.team_short_name = ?";
            PreparedStatement uniqueCheckStatement = connection.prepareStatement(uniqueCheck);
            String teamName = shortName.toUpperCase();
            uniqueCheckStatement.setString(1, teamName);
            ResultSet resultUnique = uniqueCheckStatement.executeQuery();

            if (resultUnique.next()) {

                if (resultUnique.getInt("numTeams") == 1) {
                    String idSql = "Select team_id from Team where Team.team_short_name = ?";
                    PreparedStatement idStatement = connection.prepareStatement(idSql);
                    idStatement.setString(1, teamName);
                    ResultSet idResult = idStatement.executeQuery();

                    if (idResult.next()) {
                        id = idResult.getInt("team_id");
                    } else {
                        System.out.println("Error getting team ID for team with short name '" + shortName + "'");
                    }
                    idStatement.close();
                    idResult.close();
                } else if(resultUnique.getInt("numTeams") > 1) {
                    String idSql = "Select team_id, team_short_name, team_long_name from Team where Team.team_short_name = ?";
                    PreparedStatement idStatement = connection.prepareStatement(idSql);
                    idStatement.setString(1, teamName);
                    ResultSet idResult = idStatement.executeQuery();
                    ArrayList<Integer> ids = new ArrayList<>();

                    if (idResult.next()) {
                        System.out.println("There are multiple teams with the short name '" + shortName + "'");
                        System.out.format("%-10s%-20s%-35s%n", "Team ID", "Short Name", "Long Name");
                        System.out.println("----------------------------------------------------------------");
                        do {
                            ids.add(idResult.getInt("team_id"));
                            System.out.format("%-10d%-20s%-35s%n", idResult.getInt("team_id"), idResult.getString("team_short_name"),
                                    idResult.getString("team_long_name"));
                        } while (idResult.next());
                    }
                    do {
                        System.out.print("Please specify desired team by entering their Team ID > ");
                        Scanner console = new Scanner(System.in);
                        try {
                            id = Integer.parseInt(console.nextLine());
                            if (!ids.contains(id)) {
                                System.out.println("Please enter an ID from the above table.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter an integer.");
                            id = -1;
                        }
                    } while (!ids.contains(id));
                }
            }
        }catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return id;
    }

    public void teamSearch(String shortName) {
        //gives team long name, the league they play in, and the country they are from. It will also give the average
        //fifa video game statistics for the team
        try {
            int id = getTeamID(shortName);
            String teamName = shortName.toUpperCase();
            if (id != -1) {
                String getInfo = "select top 1 League.league_name, Country.country_name, Team.team_long_name from Team\n" +
                        "inner join MatchTeams on MatchTeams.team_id = Team.team_id\n" +
                        "inner join Match on Match.match_id = MatchTeams.match_id\n" +
                        "inner join League on League.league_id = Match.league_id\n" +
                        "inner join Country on Country.country_id = League.league_id\n" +
                        "where Team.team_id = ?";

                PreparedStatement infoStatement = connection.prepareStatement(getInfo);
                infoStatement.setInt(1, id);
                ResultSet resultInfo = infoStatement.executeQuery();

                if (resultInfo.next()) {
                    System.out.println("Team Info\n-------------------------------------");
                    System.out.println("Full Name: " + resultInfo.getString("team_long_name") +
                            "\nLeague: " + resultInfo.getString("league_name") +
                            "\nCountry: " + resultInfo.getString("country_name") + "\n");

                    String getAttrs = "with attributes as (select Team.team_id,\n" +
                            "team_long_name,\n" +
                            "team_short_name,\n" +
                            "retrieval_date,\n" +
                            "buildUpPlaySpeed,\n" +
                            "buildUpPlaySpeedClass,\n" +
                            "buildUpPlayDribbling,\n" +
                            "buildUpPlayDribblingClass,\n" +
                            "buildUpPlayPassing,\n" +
                            "buildUpPlayPassingClass,\n" +
                            "buildUpPlayPositioningClass,\n" +
                            "chanceCreationPassing,\n" +
                            "chanceCreationPassingClass,\n" +
                            "chanceCreationCrossing,\n" +
                            "chanceCreationCrossingClass,\n" +
                            "chanceCreationShooting,\n" +
                            "chanceCreationShootingClass,\n" +
                            "chanceCreationPositioningClass,\n" +
                            "defencePressure,\n" +
                            "defencePressureClass,\n" +
                            "defenceAggression,\n" +
                            "defenceAggressionClass,\n" +
                            "defenceTeamWidth,\n" +
                            "defenceTeamWidthClass,\n" +
                            "defenceDefenderLineClass\n" +
                            "from Team\n" +
                            "inner join Team_attrs on Team.team_id  = Team_attrs.team_id\n" +
                            "where Team.team_id = ?)\n" +
                            "\n" +
                            "select attributes.team_id,\n" +
                            "AVG(attributes.buildUpPlaySpeed) as buildUpPlaySpeed,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + BUPSClass\n" +
                            "\tfrom (select distinct attributes.buildUpPlaySpeedClass as BUPSClass from attributes) as distAttrsSpeed\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as buildUpPlaySpeedClass,\n" +
                            "AVG(attributes.buildUpPlayDribbling) as buildUpPlayDribbling,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + BUPDClass\n" +
                            "\tfrom (select distinct attributes.buildUpPlayDribblingClass as BUPDClass from attributes) as distAttrsDribbling\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as buildUpPlayDribblingClass,\n" +
                            "AVG(attributes.buildUpPlayPassing) as buildUpPlayPassing,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + BUPPClass\n" +
                            "\tfrom (select distinct attributes.buildUpPlayPassingClass as BUPPClass from attributes) as distAttrsPassing\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as buildUpPlayPassingClass,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + BUPPosClass\n" +
                            "\tfrom (select distinct attributes.buildUpPlayPositioningClass as BUPPosClass from attributes) as distAttrsPosition\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as buildUpPlayPositioningClass,\n" +
                            "AVG(attributes.chanceCreationPassing) as chanceCreationPassing,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + CCPClass\n" +
                            "\tfrom (select distinct attributes.chanceCreationPassingClass as CCPClass from attributes) as distAttrsChancePassing\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as chanceCreationPassingClass,\n" +
                            "AVG(attributes.chanceCreationCrossing) as chanceCreationCrossing,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + CCCClass\n" +
                            "\tfrom (select distinct attributes.chanceCreationCrossingClass as CCCClass from attributes) as distAttrsChanceCrossing\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as chanceCreationCrossingClass,\n" +
                            "AVG(attributes.chanceCreationShooting) as chanceCreationShooting,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + CCShClass\n" +
                            "\tfrom (select distinct attributes.chanceCreationShootingClass as CCShClass from attributes) as distAttrsChanceShooting\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as chanceCreationShootingClass,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + CCPosClass\n" +
                            "\tfrom (select distinct attributes.chanceCreationPositioningClass as CCPosClass from attributes) as distAttrsChancePosition\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as chanceCreationPositioningClass,\n" +
                            "AVG(attributes.defencePressure) as defencePressure,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + DPClass\n" +
                            "\tfrom (select distinct attributes.defencePressureClass as DPClass from attributes) as distAttrsDefencePressure\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as defencePressureClass,\n" +
                            "AVG(attributes.defenceAggression) as defenceAggression,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + DAClass\n" +
                            "\tfrom (select distinct attributes.defenceAggressionClass as DAClass from attributes) as distAttrsDefenceAggression\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as defenceAggressionClass,\n" +
                            "AVG(attributes.defenceTeamWidth) as defenceTeamWidth,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + DTWClass\n" +
                            "\tfrom (select distinct attributes.defenceTeamWidthClass as DTWClass from attributes) as distAttrsDefenceTeamWidth\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as defenceTeamWidthClass,\n" +
                            "STUFF(\n" +
                            "\t(select ', ' + DLClass\n" +
                            "\tfrom (select distinct attributes.defenceDefenderLineClass as DLClass from attributes) as distAttrsDefenceLine\n" +
                            "\tfor xml path(''))\n" +
                            "\t, 1, 2, '') as defenceDefenderLineClass\n" +
                            "from attributes\n" +
                            "group by attributes.team_id";

                    PreparedStatement attrsStatement = connection.prepareStatement(getAttrs);
                    attrsStatement.setInt(1, id);
                    ResultSet resultAttrs = attrsStatement.executeQuery();

                    if (resultAttrs.next()) {
                        System.out.println("FIFA Video Game Team Attributes\n------------------------------------------------");
                        System.out.format("%-35s%-10d%n","buildUpPlaySpeed:",resultAttrs.getInt("buildUpPlaySpeed"));
                        System.out.format("%-35s%-300s%n","buildUpPlaySpeedClass:",resultAttrs.getString("buildUpPlaySpeedClass"));
                        System.out.format("%-35s%-10d%n","buildUpPlayDribbling:",resultAttrs.getInt("buildUpPlayDribbling"));
                        System.out.format("%-35s%-300s%n","buildUpPlayDribblingClass",resultAttrs.getString("buildUpPlayDribblingClass"));
                        System.out.format("%-35s%-10d%n","buildUpPlayPassing:",resultAttrs.getInt("buildUpPlayPassing"));
                        System.out.format("%-35s%-300s%n","buildUpPlayPassingClass:",resultAttrs.getString("buildUpPlayPassingClass"));
                        System.out.format("%-35s%-300s%n","buildUpPlayPositioningClass:",resultAttrs.getString("buildUpPlayPositioningClass"));
                        System.out.format("%-35s%-10d%n","chanceCreationPassing:",resultAttrs.getInt("chanceCreationPassing"));
                        System.out.format("%-35s%-300s%n","chanceCreationPassingClass:",resultAttrs.getString("chanceCreationPassingClass"));
                        System.out.format("%-35s%-10d%n","chanceCreationCrossing:",resultAttrs.getInt("chanceCreationCrossing"));
                        System.out.format("%-35s%-300s%n","chanceCreationCrossingClass:",resultAttrs.getString("chanceCreationCrossingClass"));
                        System.out.format("%-35s%-10d%n","chanceCreationShooting:",resultAttrs.getInt("chanceCreationShooting"));
                        System.out.format("%-35s%-300s%n","chanceCreationShootingClass:",resultAttrs.getString("chanceCreationShootingClass"));
                        System.out.format("%-35s%-10d%n","defencePressure:",resultAttrs.getInt("defencePressure"));
                        System.out.format("%-35s%-300s%n","defencePressureClass:",resultAttrs.getString("defencePressureClass"));
                        System.out.format("%-35s%-10d%n","defenceAggression:",resultAttrs.getInt("defenceAggression"));
                        System.out.format("%-35s%-300s%n","defenceAggressionClass:",resultAttrs.getString("defenceAggressionClass"));
                        System.out.format("%-35s%-10d%n","defenceTeamWidth:",resultAttrs.getInt("defenceTeamWidth"));
                        System.out.format("%-35s%-300s%n","defenceTeamWidthClass:",resultAttrs.getString("defenceTeamWidthClass"));
                        System.out.format("%-35s%-300s%n","defenceDefenderLineClass:",resultAttrs.getString("defenceDefenderLineClass"));
                        System.out.println();
                    }
                    else {
                        System.out.println("Team attributes not found for \"" + resultInfo.getString("team_long_name") + "\".");
                    }
                    resultAttrs.close();
                    attrsStatement.close();
                }
                else {
                    System.out.println("No team found with short name \"" + teamName + "\".");
                }
                resultInfo.close();
                infoStatement.close();
            }
            else {
                System.out.println("Could not find team with the short name '" + teamName + "'");
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void teamPlayers(String shortName) {
        //gives all players that have played for the team
        try {
            int id = getTeamID(shortName);
            String teamName = shortName.toUpperCase();
            if (id != -1) {
                String getName = "select Team.team_long_name from Team where Team.team_id = ?";

                PreparedStatement nameStatement = connection.prepareStatement(getName);
                nameStatement.setInt(1, id);
                ResultSet nameSet = nameStatement.executeQuery();

                if (nameSet.next()) {
                    System.out.println("Players who have played on " + nameSet.getString("team_long_name") + "\n");

                    String getAttrs = "with teamPlayers as (select MatchPlayers.match_id, MatchPlayers.player_id from MatchPlayers\n" +
                            "inner join MatchTeams on MatchTeams.match_id = MatchPlayers.match_id and MatchTeams.side = MatchPlayers.side\n" +
                            "where MatchTeams.team_id = ?)\n" +
                            "select Player.player_id, Player.player_name, count(match_id) as gamesPlayed from teamPlayers\n" +
                            "inner join Player on Player.player_id = teamPlayers.player_id\n" +
                            "group by Player.player_id, Player.player_name\n" +
                            "order by gamesPlayed desc;";

                    PreparedStatement playersStatement = connection.prepareStatement(getAttrs);
                    playersStatement.setInt(1, id);
                    ResultSet playerResults = playersStatement.executeQuery();

                    if (playerResults.next()) {
                        System.out.format("%-35s%-15s%n", "Player Name", "Games Played");
                        System.out.println("--------------------------------------------------------");
                        do {
                            System.out.format("%-35s%-15d%n", playerResults.getString("player_name"),
                                    playerResults.getInt("gamesPlayed"));
                        } while (playerResults.next());
                    }
                    else {
                        System.out.println("Players not found for \"" + nameSet.getString("team_long_name") + "\".");
                    }
                    playerResults.close();
                    playersStatement.close();
                }
                else {
                    System.out.println("No team found with short name \"" + teamName + "\".");
                }
                nameSet.close();
                nameStatement.close();
            }
            else {
                System.out.println("Could not find team with the short name '" + teamName + "'");
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void playerSearch(String name) {
        //gives player birthday, height, weight
        try {
            String sqlQuery = "select birthday, player_height, player_weight from Player where convert(VARCHAR, player_name) = ?";
            String player_Name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, player_Name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Birthday of  " + player_Name + " is " +
                        resultSet.getString("birthday") + ", player height is: " + resultSet.getString("player_height") + "cm, " +
                        "player weight is: " + resultSet.getString("player_weight") + "lbs.");

            }
            else {
                System.out.println("No player found with the name \"" + player_Name + "\".");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

    }

    public void playerSearchById(String id) {
        //gives player name, birthday, height, weight
        try {
            String sqlQuery = "select player_name, birthday, player_height, player_weight from Player where convert(VARCHAR, player_id) = ?";
            int player_id = Integer.parseInt(id);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, player_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Name of the player with id:"+ player_id + " is: "+ resultSet.getString("player_name") + ", " +
                        "birthday of " + resultSet.getString("player_name") + " is " + resultSet.getString("birthday") +
                        ", player height is: " + resultSet.getString("player_height") + "cm, " +
                        "player weight is: " + resultSet.getString("player_weight") + "lbs.");

            }
            else {
                System.out.println("No player found with id \"" + player_id + "\".");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void matchSearch(int match_id) {
        //gives season, league, stage, date, stadium_name, home_score, away_score, home_team_name, away_team_name and list of players on each side
        try {
            String matchSQL = "select Match.match_id, league_name, season, stage,\n" +
                    "match_date, stadium_name, home_team_goal, away_team_goal,team_long_name\n" +
                    "from Match inner join League on Match.league_id = league.league_id\n" +
                    "inner join Stadium on Match.stadium_id = Stadium.stadium_id\n" +
                    "inner join MatchTeams on Match.match_id = MatchTeams.match_id\n" +
                    "inner join Team on MatchTeams.team_id = Team.team_id\n" +
                    "where Match.match_id = ?";
            PreparedStatement matchStatement = connection.prepareStatement(matchSQL);
            matchStatement.setInt(1, match_id);
            ResultSet matchSet = matchStatement.executeQuery();
            String homeTeamName, awayTeamName;

            if(matchSet.next()) {


                System.out.println("\nRetrieving match information of match_id : " + match_id);
                System.out.println("League : " + matchSet.getString("league_name") + "\n"
                                + "Season : " + matchSet.getString("season") + "\n"
                                + "Stage : " + matchSet.getString("stage") + "\n"
                                + "Date : " + matchSet.getString("match_date") + "\n"
                                + "Stadium Name : " + matchSet.getString("stadium_name") + "\n"
                                + "Score : " + matchSet.getString("home_team_goal")
                                + " - " + matchSet.getString("away_team_goal")
                        );
                homeTeamName = matchSet.getString("team_long_name");
                if(matchSet.next()) {
                    awayTeamName = matchSet.getString("team_long_name");

                    System.out.println("Home Team : " + homeTeamName);
                    System.out.println("Away Team : " + awayTeamName);
                };
            }
            else {
                System.out.println("No match information found for matchID: " + match_id);
            }

            matchStatement.close();
            matchSet.close();

            String playerSQL = "select Player.player_name, side\n" +
                    "from Match inner join MatchPlayers on Match.match_id = MatchPlayers.match_id\n" +
                    "inner join Player on MatchPlayers.player_id = Player.player_id\n" +
                    "where Match.match_id = ? and side = ?";

            String[] sides = {"home", "away"};
            for(String side: sides) {
                PreparedStatement statement = connection.prepareStatement(playerSQL);
                statement.setInt(1, match_id);
                statement.setString(2, side);
                ResultSet playerSet = statement.executeQuery();
                if (playerSet.next()) {
                    System.out.println("\nList of players in " + side + " team : ");
                    do {
                        System.out.println(playerSet.getString("player_name"));
                    } while (playerSet.next());
                }

                statement.close();
                playerSet.close();
            }
            System.out.println();

        } catch(SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void playerAttributes(String id) {
        //gives player attributes

        try {
            String sqlQuery = "select overall_rating, potential, preferred_foot, attacking_work_rate, defensive_work_rate, crossing, finishing, heading_accuracy," +
                    " short_passing, volleys, dribbling, curve, free_kick_accuracy, long_passing, ball_control, acceleration, sprint_speed, agility, reactions," +
                    " balance, shot_power, jumping, stamina, strength, long_shots, aggression, interceptions, positioning, vision, penalties, marking, standing_tackle," +
                    " sliding_tackle, gk_diving, gk_handling, gk_kicking, gk_positioning, gk_reflexes from Player_attrs where convert(VARCHAR, player_id) = ?";

            int player_id = Integer.parseInt(id);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, String.valueOf(player_id));
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("The following are the attributes of Player " + player_id + ": \n"
                        + "Overall Rating: " + resultSet.getString("overall_rating") + "\n"
                        + "Potential: " + resultSet.getString("potential") + "\n"
                        + "Preferred Foot: " + resultSet.getString("preferred_foot") + "\n"
                        + "Attacking Work Rate: " + resultSet.getString("attacking_work_rate") + "\n"
                        + "Defensive Work Rate: " +resultSet.getString("defensive_work_rate") + "\n"
                        + "Crossing: " + resultSet.getString("crossing") + "\n"
                        + "Finishing: " + resultSet.getString("finishing") + "\n"
                        + " Heading Accuracy: " + resultSet.getString("heading_accuracy") + "\n"
                        + "Short Passing: " + resultSet.getString("short_passing") + "\n"
                        + "Volleys: " + resultSet.getString("volleys") + "\n"
                        + "Dribbling: " + resultSet.getString("dribbling") + "\n"
                        + "Curve: "   + resultSet.getString("curve") + "\n"
                        + "Free Kick Accuracy: " + resultSet.getString("free_kick_accuracy") + "\n"
                        + "Long Passing: " + resultSet.getString("long_passing") + "\n"
                        + "Ball Control: " + resultSet.getString("ball_control") + "\n"
                        + "Acceleration: " + resultSet.getString("acceleration") + "\n"
                        + "Sprint Speed: " + resultSet.getString("sprint_speed")+ "\n"
                        + "Agility: " + resultSet.getString("agility")+ "\n"
                        + "Reactions: " + resultSet.getString("reactions")+ "\n"
                        + "Balance: " + resultSet.getString("balance") + "\n"
                        + "Shot Power: " + resultSet.getString("shot_power") + "\n"
                        + "Jumping: " + resultSet.getString("jumping")+ "\n"
                        + "Stamina: " + resultSet.getString("stamina") + "\n"
                        + "Strength: " + resultSet.getString("strength") + "\n"
                        + "Long Shots: " + resultSet.getString("long_shots") + "\n"
                        + "Aggression: " + resultSet.getString("aggression") + "\n"
                        + "Interceptions: " + resultSet.getString("interceptions") + "\n"
                        + "Positioning: " + resultSet.getString("positioning") + "\n"
                        + "Vision: " + resultSet.getString("vision") + "\n"
                        + "Penalties: " + resultSet.getString("penalties") + "\n"
                        + "Marking: " + resultSet.getString("marking") + "\n"
                        + "Standing Tackle: " + resultSet.getString("standing_tackle") + "\n"
                        + "Sliding Tackle: " + resultSet.getString("sliding_tackle")+ "\n"
                        + "GK Diving: " + resultSet.getString("gk_diving") + "\n"
                        + "GK Handling: " + resultSet.getString("gk_handling") + "\n"
                        + "GK Kicking: " + resultSet.getString("gk_kicking") + "\n"
                        + "GK Positioning: " + resultSet.getString("gk_positioning") + "\n"
                        + "GK Reflexes: " + resultSet.getString("gk_reflexes")+ "\n"
                        + "______END OF PLAYER ATTRIBUTES______"
                );
            }
            else {
                System.out.println("No player found with id \"" + player_id + "\".");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void stadiumSearch(String id) {
        //gives stadium capacity, city_name, country_name, built_date
        try {
            String sqlQuery = "select stadium_name, capacity, city_name, country_name, built_in from Stadium\n" +
                    "inner join City on Stadium.city_id = City.city_id inner join Country on Stadium.country_id=Country.country_id\n" +
                    "where CONVERT (VARCHAR, stadium_id)= ?";
            int stadium_id = Integer.parseInt(id);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, stadium_id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Details of the Stadium with id:" + stadium_id + "\n"
                        + "Stadium Name: " + resultSet.getString("stadium_name") + "\n"
                        + "Capacity: " + resultSet.getString("capacity") + "\n"
                        + "City: " + resultSet.getString("city_name") + "\n"
                        + "Country: " + resultSet.getString("country_name") + "\n"
                        + "Built in: " + resultSet.getString("built_in"));

            } else {
                System.out.println("No stadium found with id \"" + stadium_id + "\".");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    // Interesting queries
    public void teamStats(String name) {
        try {
            String teamShortName = name.trim().toUpperCase();
            int teamID = getTeamID(name);
            boolean teamExists = false;

            if(teamID == -1) {
                System.out.println("No team found with short name: " + name);
            }
            else {
                teamExists = printTeamGoalStats(teamID);
            }

            if (teamExists) {
                printTeamRecord(teamID);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    private boolean printTeamGoalStats(int teamID) throws SQLException {
        boolean teamExists = false;
        String sql = "select side, sum(home_team_goal) as homeTeamGoals, sum(away_team_goal) as awayTeamGoals, team_long_name " +
                "from Team inner join MatchTeams on Team.team_id = MatchTeams.team_id " +
                "inner join Match on MatchTeams.match_id = Match.match_id " +
                "where Team.team_id = ? group by side, team_long_name";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, teamID);
        System.out.println("Finding stats for team with id: " + teamID);
        ResultSet resultSet = statement.executeQuery();
        int goalsScored = 0;
        int goalsTaken = 0;

        if (resultSet.next()) {
            teamExists = true;
            System.out.println("Team Name: " + resultSet.getString("team_long_name"));
            do {
                if (resultSet.getString("side").equals("home")) {
                    goalsScored += resultSet.getInt("homeTeamGoals");
                    goalsTaken += resultSet.getInt("awayTeamGoals");
                } else if (resultSet.getString("side").equals("away")) {
                    goalsScored += resultSet.getInt("awayTeamGoals");
                    goalsTaken += resultSet.getInt("homeTeamGoals");
                }
            } while (resultSet.next());

            System.out.println("Total goals scored: " + goalsScored);
            System.out.println("Total goals conceded: " + goalsTaken);
        } else {
            System.out.println("No team found with the team ID \"" + teamID + "\".");
        }

        statement.close();
        resultSet.close();

        return teamExists;
    }

    private void printTeamRecord(int teamID) throws SQLException {
        String baseSQL = "select count(Match.match_id)\n" +
                "from Team inner join MatchTeams on Team.team_id = MatchTeams.team_id\n" +
                "inner join Match on MatchTeams.match_id = Match.match_id\n" +
                "where Team.team_id = ? and home_team_goal %s away_team_goal";

        String[] teamSide = {"home", "away"};
        String[] inequality = {">", "<"};

        int numWins = 0;
        int numLosses = 0;
        int numDraws = 0;

        // get number of wins and losses
        for(String side : teamSide) {
            String sql = baseSQL + " and side = ?";

            for (String symbol : inequality) {
                String newSQL = String.format(sql, symbol);
                PreparedStatement statement = connection.prepareStatement(newSQL);
                statement.setInt(1, teamID);
                statement.setString(2, side);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int numGames = resultSet.getInt(1);
                    if ((side.equals("home") && symbol.equals(">")) || (side.equals("away") && symbol.equals("<"))) {
                        numWins += numGames;
                    } else {
                        numLosses += numGames;
                    }
                }
                statement.close();
                resultSet.close();
            }
        }

        // get number of draws
        String drawSQL = String.format(baseSQL, "=");
        PreparedStatement statement = connection.prepareStatement(drawSQL);
        statement.setInt(1, teamID);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            numDraws = resultSet.getInt(1);
        }

        System.out.printf("Wins: %d, Losses: %d, Draws: %d%n", numWins, numLosses, numDraws);
        statement.close();
        resultSet.close();
    }

    public void playedMostMatches() {
        // gives top 5 players that played the most matches
        try {
            String sql = "select top 5 Player.player_id, Player.player_name, count(match_id) as numMatches\n" +
                    "from Player inner join MatchPlayers on Player.player_id = MatchPlayers.player_id\n" +
                    "group by Player.player_id, Player.player_name order by numMatches desc;";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet playerSet = statement.executeQuery();

            if (playerSet.next()) {
                System.out.println("Players who have played the most matches");
                System.out.format("%-15s%-35s%-50s%n", "Player ID", "Name", "Number of Matches Played");
                System.out.println("----------------------------------------------------------------");
                do  {

                    System.out.format("%-15d%-35s%-50d%n", playerSet.getInt("player_id"),
                            playerSet.getString("player_name"),
                            playerSet.getInt("numMatches"));

                } while (playerSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            playerSet.close();
            statement.close();

        } catch(SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostUsedStadium(int capacity) {
        try {
            String sql = "select top 5 Stadium.stadium_id, stadium_name, capacity, count(match_id) as numMatches\n" +
                    "from Match inner join Stadium on Match.stadium_id = Stadium.stadium_id\n" +
                    "where capacity < ?\n" +
                    "group by Stadium.stadium_id, stadium_name, capacity\n" +
                    "order by numMatches desc;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,capacity);
            ResultSet stadiumSet = statement.executeQuery();

            if (stadiumSet.next()) {
                System.out.println("Stadiums used in most matches under capacity of " + capacity);
                String header = String.format("%-15s%-30s%-15s%-15s%n", "Stadium ID", "Stadium Name", "Capacity", "Number of Matches");
                System.out.print(header);
                System.out.println("-".repeat(header.length()));

                do  {
                    System.out.format("%-15s%-30s%-15s%-15s%n", stadiumSet.getInt("stadium_id"),
                            stadiumSet.getString("stadium_name"),
                            stadiumSet.getInt("capacity"),
                            stadiumSet.getInt("numMatches")
                            );

                } while (stadiumSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void highestGoalDifferentialPerSeason() {
        try {
            String seasonSQL = "select distinct(season) from Match order by season asc;";

            String baseSQL = "with sidesGoals as (select Team.team_id, side, sum(home_team_goal) as homeTeamGoals, sum(away_team_goal) as awayTeamGoals, season\n" +
                    "from Team inner join MatchTeams on Team.team_id = MatchTeams.team_id inner join Match on MatchTeams.match_id = Match.match_id\n" +
                    "where season = ? group by Team.team_id, side, season),\n" +
                    "teamAsAway as (\n" +
                    "select team_id, homeTeamGoals as loss, awayTeamGoals as gain from sidesGoals\n" +
                    "where sidesGoals.side = 'away'),\n" +
                    "teamAsHome as (\n" +
                    "select team_id, homeTeamGoals as gain, awayTeamGoals as loss from sidesGoals\n" +
                    "where sidesGoals.side = 'home'),\n" +
                    "differentialScore as (\n" +
                    "select teamAsHome.team_id, (teamAsHome.gain + teamAsAway.gain) as gains, (teamAsHome.loss + teamAsAway.loss) as losses, (teamAsHome.gain + teamAsAway.gain - teamAsHome.loss - teamAsAway.loss) as goalDifferential from\n" +
                    "(teamAsHome inner join teamAsAway on teamAsHome.team_id = teamAsAway.team_id)\n" +
                    ")\n" +
                    "select top 1 Team.team_id, Team.team_short_name, Team.team_long_name, gains, losses, goalDifferential\n" +
                    "from differentialScore inner join Team on differentialScore.team_id = Team.team_id order by goalDifferential desc;";

            PreparedStatement statement = connection.prepareStatement(seasonSQL);
            ResultSet seasonSet = statement.executeQuery();

            if(seasonSet.next()) {
                System.out.println("Team with highest goal differential for each season");
                String header = String.format("%-15s%-10s%-20s%-25s%-20s%-25s%-20s%n",
                        "Season", "Team ID", "Team Short Name", "Team Long Name", "Total Goals Scored",
                        "Total Goals Conceded", "Goal Differential");
                System.out.print(header);
                System.out.println("-".repeat(header.length()));

                do {
                    String season = seasonSet.getString("season");
                    PreparedStatement statement2 = connection.prepareStatement(baseSQL);
                    statement2.setString(1, season);
                    ResultSet topTeamSet = statement2.executeQuery();

                    if(topTeamSet.next()) {
                        System.out.format("%-15s%-10s%-20s%-25s%-20s%-25s%-20s%n",
                                season,
                                topTeamSet.getInt("team_id"),
                                topTeamSet.getString("team_short_name"),
                                topTeamSet.getString("team_long_name"),
                                topTeamSet.getInt("gains"),
                                topTeamSet.getInt("losses"),
                                topTeamSet.getInt("goalDifferential")
                        );
                    }
                    else {
                        System.out.println("No top team found for season " + season);
                    }
                    statement2.close();
                    topTeamSet.close();
                } while (seasonSet.next());
            }
            else {
                System.out.println("No seasons found");
            }
            statement.close();
            seasonSet.close();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void playedForMostTeams() {
        //gives top 5 players that have played for the most teams
        try {
            String sql = "with mostTeams as (select top 5 MatchPlayers.player_id, count(distinct MatchTeams.team_id) as numTeams from MatchTeams\n" +
                    "inner join MatchPlayers on MatchPlayers.match_id = MatchTeams.match_id and MatchPlayers.side = MatchTeams.side\n" +
                    "group by player_id\n" +
                    "order by numTeams desc)\n" +
                    "select Player.player_id, Player.player_name, numTeams from mostTeams\n" +
                    "inner join Player on Player.player_id = mostTeams.player_id\n" +
                    "order by mostTeams.numTeams desc";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet playerSet = statement.executeQuery();

            if (playerSet.next()) {
                System.out.println("Players who have played for the most teams");
                System.out.format("%-15s%-35s%-50s%n", "Player ID", "Name", "Number of Teams Played For");
                System.out.println("----------------------------------------------------------------");
                do  {

                    System.out.format("%-15d%-35s%-50d%n", playerSet.getInt("player_id"),
                            playerSet.getString("player_name"),
                            playerSet.getInt("numTeams"));

                } while (playerSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            playerSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostGoals() {
        //gives top 5 teams with the most goals per game
        try {
            String sql = "with sidesGoals as (select Team.team_id, side, sum(home_team_goal) as homeTeamGoals, sum(away_team_goal) as awayTeamGoals\n" +
                    "from Team inner join MatchTeams on Team.team_id = MatchTeams.team_id inner join Match on MatchTeams.match_id = Match.match_id\n" +
                    "group by Team.team_id, side),\n" +
                    "totalSideGoals as (\n" +
                    "select team_id, awayTeamGoals as goals from sidesGoals\n" +
                    "where sidesGoals.side = 'away'\n" +
                    "union\n" +
                    "select team_id, homeTeamGoals as goals from sidesGoals\n" +
                    "where sidesGoals.side = 'home'),\n" +
                    "totalGoals as (\n" +
                    "select team_id, sum(goals) as goalTotal from totalSideGoals\n" +
                    "group by team_id),\n" +
                    "totalMatches as (select MatchTeams.team_id, count(MatchTeams.team_id) as matchTotal from MatchTeams\n" +
                    "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                    "group by MatchTeams.team_id)\n" +
                    "select top 5 Team.team_id, Team.team_short_name, Team.team_long_name, goalTotal, matchTotal,\n" +
                    "CONVERT(DECIMAL(3,2), CONVERT(DECIMAL(5,2),goalTotal)/CONVERT(DECIMAL(5,2),matchTotal)) as goalsPerMatch from Team\n" +
                    "inner join totalGoals on totalGoals.team_id = Team.team_id\n" +
                    "inner join totalMatches on totalMatches.team_id = Team.team_id\n" +
                    "order by goalsPerMatch desc;";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet teamSet = statement.executeQuery();

            if (teamSet.next()) {
                System.out.println("Teams with the most goals per match");
                System.out.format("%-15s%-25s%-15s%-20s%-15s%n", "Short Name", "Long Name", "Goals", "Matches Played", "Goals Per Match");
                System.out.println("--------------------------------------------------------------------------------------------------");
                do  {

                    System.out.format("%-15s%-25s%-15d%-20d%-15.2f%n", teamSet.getString("team_short_name"),
                            teamSet.getString("team_long_name"),
                            teamSet.getInt("goalTotal"),
                            teamSet.getInt("matchTotal"),
                            teamSet.getDouble("goalsPerMatch"));

                } while (teamSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            teamSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostGoalsAgainst() {
        //gives top 5 teams with the most goals against per game
        try {
            String sql = "with sidesGoals as (select Team.team_id, side, sum(home_team_goal) as homeTeamGoals, sum(away_team_goal) as awayTeamGoals\n" +
                    "from Team inner join MatchTeams on Team.team_id = MatchTeams.team_id inner join Match on MatchTeams.match_id = Match.match_id\n" +
                    "group by Team.team_id, side),\n" +
                    "totalSideGoals as (\n" +
                    "select team_id, awayTeamGoals as goals from sidesGoals\n" +
                    "where sidesGoals.side = 'home'\n" +
                    "union\n" +
                    "select team_id, homeTeamGoals as goals from sidesGoals\n" +
                    "where sidesGoals.side = 'away'),\n" +
                    "totalGoals as (\n" +
                    "select team_id, sum(goals) as goalTotal from totalSideGoals\n" +
                    "group by team_id),\n" +
                    "totalMatches as (select MatchTeams.team_id, count(MatchTeams.team_id) as matchTotal from MatchTeams\n" +
                    "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                    "group by MatchTeams.team_id)\n" +
                    "select top 5 Team.team_id, Team.team_short_name, Team.team_long_name, goalTotal, matchTotal,\n" +
                    "CONVERT(DECIMAL(3,2), CONVERT(DECIMAL(5,2),goalTotal)/CONVERT(DECIMAL(5,2),matchTotal)) as goalsPerMatch from Team\n" +
                    "inner join totalGoals on totalGoals.team_id = Team.team_id\n" +
                    "inner join totalMatches on totalMatches.team_id = Team.team_id\n" +
                    "order by goalsPerMatch desc;";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet teamSet = statement.executeQuery();

            if (teamSet.next()) {
                System.out.println("Teams with the most goals against per match");
                System.out.format("%-15s%-25s%-15s%-20s%-20s%n", "Short Name", "Long Name", "Goals Against", "Matches Played", "Goals Against Per Match");
                System.out.println("--------------------------------------------------------------------------------------------------");
                do  {
                    System.out.format("%-15s%-25s%-15d%-20d%-15.2f%n", teamSet.getString("team_short_name"),
                            teamSet.getString("team_long_name"),
                            teamSet.getInt("goalTotal"),
                            teamSet.getInt("matchTotal"),
                            teamSet.getDouble("goalsPerMatch"));

                } while (teamSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            teamSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostWinningTeam() {
        //Gives top 5 teams with the highest wins per match ratio
        try {
            String sql = "with teamWins as (select Team.team_id, Team.team_short_name, Team.team_long_name, side, home_team_goal, away_team_goal, (\n" +
                    "select case when ((side = 'home' and home_team_goal > away_team_goal) or (side = 'away' and away_team_goal > home_team_goal))\n" +
                    "then 'win'\n" +
                    "else NULL end) as outcome\n" +
                    "from Team \n" +
                    "inner join MatchTeams on Team.team_id = MatchTeams.team_id \n" +
                    "inner join Match on MatchTeams.match_id = Match.match_id),\n" +
                    "totalMatches as (select MatchTeams.team_id, count(MatchTeams.team_id) as matchTotal from MatchTeams\n" +
                    "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                    "group by MatchTeams.team_id),\n" +
                    "totalWins as (select teamWins.team_id, count(outcome) as wins from teamWins\n" +
                    "group by teamWins.team_id)\n" +
                    "select top 5 Team.team_id, Team.team_short_name, Team.team_long_name, wins, matchTotal,\n" +
                    "CONVERT(DECIMAL(3,2), CONVERT(DECIMAL(5,2),wins)/CONVERT(DECIMAL(5,2),matchTotal)) as winsPerMatch from Team\n" +
                    "inner join totalWins on totalWins.team_id = Team.team_id\n" +
                    "inner join totalMatches on totalMatches.team_id = Team.team_id\n" +
                    "order by winsPerMatch desc";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet teamSet = statement.executeQuery();

            if (teamSet.next()) {
                System.out.println("Teams with the highest win ratio");
                System.out.format("%-15s%-25s%-15s%-20s%-30s%n", "Short Name", "Long Name", "Wins", "Matches Played", "Wins Per Match Played");
                System.out.println("--------------------------------------------------------------------------------------------------");
                do  {
                    System.out.format("%-15s%-25s%-15d%-20d%-30.2f%n", teamSet.getString("team_short_name"),
                            teamSet.getString("team_long_name"),
                            teamSet.getInt("wins"),
                            teamSet.getInt("matchTotal"),
                            teamSet.getDouble("winsPerMatch"));
                } while (teamSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            teamSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostLosingTeam() {
        //Gives top 5 teams with the highest losses per match ratio
        try {
            String sql = "with teamLosses as (select Team.team_id, Team.team_short_name, Team.team_long_name, side, home_team_goal, away_team_goal, (" +
                    "select case when ((side = 'home' and home_team_goal < away_team_goal) or (side = 'away' and away_team_goal < home_team_goal))\n" +
                    "then 'loss'\n" +
                    "else NULL end) as outcome\n" +
                    "from Team\n" +
                    "inner join MatchTeams on Team.team_id = MatchTeams.team_id\n" +
                    "inner join Match on MatchTeams.match_id = Match.match_id),\n" +
                    "totalMatches as (select MatchTeams.team_id, count(MatchTeams.team_id) as matchTotal from MatchTeams\n" +
                    "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                    "group by MatchTeams.team_id),\n" +
                    "totalLosses as (select teamLosses.team_id, count(outcome) as losses from teamLosses\n" +
                    "group by teamLosses.team_id)\n" +
                    "select top 5 Team.team_id, Team.team_short_name, Team.team_long_name, losses, matchTotal,\n" +
                    "CONVERT(DECIMAL(3,2), CONVERT(DECIMAL(5,2),losses)/CONVERT(DECIMAL(5,2),matchTotal)) as lossesPerMatch from Team\n" +
                    "inner join totalLosses on totalLosses.team_id = Team.team_id\n" +
                    "inner join totalMatches on totalMatches.team_id = Team.team_id\n" +
                    "order by lossesPerMatch desc";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet teamSet = statement.executeQuery();

            if (teamSet.next()) {
                System.out.println("Teams with the highest loss ratio");
                System.out.format("%-15s%-25s%-15s%-20s%-30s%n", "Short Name", "Long Name", "Losses", "Matches Played", "Losses Per Match Played");
                System.out.println("--------------------------------------------------------------------------------------------------");
                do  {
                    System.out.format("%-15s%-25s%-15d%-20d%-30.2f%n", teamSet.getString("team_short_name"),
                            teamSet.getString("team_long_name"),
                            teamSet.getInt("losses"),
                            teamSet.getInt("matchTotal"),
                            teamSet.getDouble("lossesPerMatch"));
                } while (teamSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            teamSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void standings(String country, String year) {
        try {
            String standingsSQL = "with teamPoints as (select Team.team_id, Team.team_short_name, Team.team_long_name, side, home_team_goal, away_team_goal, season, league_id, (\n" +
                    "select case when ((side = 'home' and home_team_goal > away_team_goal) or (side = 'away' and away_team_goal > home_team_goal))\n" +
                    "then 3\n" +
                    "when (home_team_goal = away_team_goal)\n" +
                    "then 1\n" +
                    "when ((side = 'home' and home_team_goal < away_team_goal) or (side = 'away' and away_team_goal < home_team_goal))\n" +
                    "then 0\n" +
                    "else NULL end) as point\n" +
                    "from Team \n" +
                    "inner join MatchTeams on Team.team_id = MatchTeams.team_id \n" +
                    "inner join Match on MatchTeams.match_id = Match.match_id),\n" +
                    "standings as (select team_id, season, league_id, count(point) as GP, count(case when point = 3 then 3 end) as W,\n" +
                    "count(case when point = 1 then 1 end) as D, count(case when point = 0 then 0 end) as L, SUM(tp.point) as points from teamPoints as tp\n" +
                    "group by team_id,season,league_id)\n" +
                    "select League.league_name, season, Team.team_long_name, GP, W, D, L, points from standings \n" +
                    "inner join Team on Team.team_id = standings.team_id\n" +
                    "inner join League on League.league_id = standings.league_id\n" +
                    "inner join Country on Country.country_id = League.country_id\n" +
                    "where Country.country_name = ? and season like ?\n" +
                    "order by standings.league_id asc, season asc, points desc";

            String countryName = country.substring(0, 1).toUpperCase() + country.substring(1).toLowerCase();
            PreparedStatement statement = connection.prepareStatement(standingsSQL);
            statement.setString(1, countryName);
            statement.setString(2, year+"%");
            ResultSet standingsSet = statement.executeQuery();

            if (standingsSet.next()) {
                int place = 1;
                System.out.println("Showing league results for the " + standingsSet.getString("league_name") +
                        " (" + standingsSet.getString("season") + " season)\n");
                System.out.format("%-10s%-30s%-4s%-4s%-4s%-4s%-8s%n", "Rank", "Team", "GP", "W", "D", "L", "Points");
                System.out.println("----------------------------------------------------------------");
                do {
                    System.out.format("%-10d%-30s%-4s%-4s%-4s%-4s%-8s%n",
                            place,
                            standingsSet.getString("team_long_name"),
                            standingsSet.getInt("GP"), standingsSet.getInt("W"),
                            standingsSet.getInt("D"), standingsSet.getInt("L"),
                            standingsSet.getInt("points"));
                    place++;
                } while (standingsSet.next());
            }
            else {
                System.out.println("League results for " + countryName + " in " + year + " could not be found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostCommonFirstNames() {
        //give most common soccer player first name
        try {
            String sql = "with firstNames as (select player_id, convert(VARCHAR,SUBSTRING(player_name,0,CHARINDEX(' ',player_name))) as firstName from Player)\n" +
                    "select top 20 firstName, count(player_id) as frequency from firstNames\n" +
                    "where firstName != ''\n" +
                    "group by firstName\n" +
                    "order by frequency desc";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet playerSet = statement.executeQuery();

            if (playerSet.next()) {
                int rank = 1;
                System.out.println("20 Most Popular First Names of Professional Soccer Players");
                System.out.format("%-10s%-15s%-30s%n", "Rank", "First Name", "Number of Players with Name");
                System.out.println("---------------------------------------------------");
                do  {
                    System.out.format("%-10d%-15s%-30d%n", rank, playerSet.getString("firstName"),
                            playerSet.getInt("frequency"));
                    rank++;
                } while (playerSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            playerSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void mostCommonMonths() {
        //give percentage of players born in each month. You will notice that players are more likely to be born in the
        //beginning of the year than the end
        try {
            String sql = "with months as (select player_id, datename(month, birthday) as bMonth from Player),\n" +
                    "births as (select bMonth, convert(decimal(10,2), count(player_id)) as bMonthFrequency from months\n" +
                    "group by bMonth),\n" +
                    "totalPlayers as (select convert(decimal(10,2), sum(bMonthFrequency)) as total from births)\n" +
                    "select bMonth, convert(decimal(4,2),(bMonthFrequency/total*100)) as perc from births,totalPlayers\n" +
                    "order by perc desc;";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet monthSet = statement.executeQuery();

            if (monthSet.next()) {
                int rank = 1;
                System.out.println("Percentage of Soccer Players Born in Each Month");
                System.out.format("%-10s%-10s%-10s%n", "Rank", "Month", "%");
                System.out.println("--------------------");
                do  {
                    System.out.format("%-10d%-10s%-10.2f%n", rank, monthSet.getString("bMonth"),
                            monthSet.getDouble("perc"));
                    rank++;
                } while (monthSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            monthSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void heaviestTeams() {
        //give top 5 heaviest teams
        try {
            String sql = "select top 5 MatchTeams.team_id, Team.team_long_name, convert(decimal(5,2),avg(cast(Player.player_weight as decimal))) as avgWeight from MatchPlayers\n" +
                    "inner join MatchTeams on MatchTeams.match_id = MatchPlayers.match_id and MatchTeams.side = MatchPlayers.side\n" +
                    "inner join Player on Player.player_id = MatchPlayers.player_id\n" +
                    "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                    "group by MatchTeams.team_id, Team.team_long_name\n" +
                    "order by avgWeight desc";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet monthSet = statement.executeQuery();

            if (monthSet.next()) {
                int rank = 1;
                System.out.println("Top 5 Heaviest Teams");
                System.out.format("%-10s%-35s%-20s%n", "Rank", "Team Name", "Average Weight (lbs)");
                System.out.println("--------------------------------------------------------");
                do  {
                    System.out.format("%-10d%-35s%-10.2f%n", rank, monthSet.getString("team_long_name"),
                            monthSet.getDouble("avgWeight"));
                    rank++;
                } while (monthSet.next());
            }

            else {
                System.out.println("Query did not work.");
            }
            monthSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }

    public void tallestTeams() {
        //give top 5 heaviest teams
        try {
            String sql = "select top 5 MatchTeams.team_id, Team.team_long_name, convert(decimal(5,2),avg(cast(Player.player_height as decimal))) as avgHeight from MatchPlayers\n" +
                    "inner join MatchTeams on MatchTeams.match_id = MatchPlayers.match_id and MatchTeams.side = MatchPlayers.side\n" +
                    "inner join Player on Player.player_id = MatchPlayers.player_id\n" +
                    "inner join Team on Team.team_id = MatchTeams.team_id\n" +
                    "group by MatchTeams.team_id, Team.team_long_name\n" +
                    "order by avgHeight desc";

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet monthSet = statement.executeQuery();

            if (monthSet.next()) {
                int rank = 1;
                System.out.println("Top 5 Tallest Teams");
                System.out.format("%-10s%-35s%-15s%n", "Rank", "Team Name", "Average Height (cm)");
                System.out.println("--------------------------------------------------------");
                do  {
                    System.out.format("%-10d%-35s%-10.2f%n", rank, monthSet.getString("team_long_name"),
                            monthSet.getDouble("avgHeight"));
                    rank++;
                } while (monthSet.next());
            }
            else {
                System.out.println("Query did not work.");
            }
            monthSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
    }
}
