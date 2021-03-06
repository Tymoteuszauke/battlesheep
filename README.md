### This is readme file for Battleships game (redo).
#### How to run:
##### - Installation:
    - mvn clean install
##### - Run unit tests:
    - mvn test
    
##### - Run integration tests:
    - mvn failsafe:integration-test

##### - generate test coverage:
    - mvn jacoco:report

##### - run client application
    - mvn exec:java -pl client -Dexec.mainClass=com.blackship.battlesheep.AppClient
    
##### - run server application
    - mvn exec:java -pl server -Dexec.mainClass=com.blackship.battlesheep.AppServer
    
 - to generate sonar statistic, first download sonar https://www.sonarqube.org/downloads/ , run SonarServer.bat or SonarServer.sh from sonar folder location/bin ,
 then proceed via terminal in project folder:
    `- mvn sonar:sonar`
    or run our script located in scripts folder `start_sonar.sh`

- to generate FindBugs report:
     `- mvn site`

- to generate checkstyle report:
    `- mvn checkstyle:checkstyle`

### For code reviewers
 run script with all plugins: inside main folder

    - run './scripts/generate_reports.sh ../ -j -f -c' generate jacoco, findbugs, checkstyle reports
    - run './scripts/start_sonar.sh ../' run sonar server and run mvn sonar:sonar in all modules
    - run './scripts/stop_sonar.sh' stop sonar server
    - it requires UNIX 64bit (with wget and unzip)

#### Requirements:

##### - Functional requirements:
        1. One game only
        2. 10x10 board
        3. Fleet consists of: 4-mast ship, 2 3-mast ships, 3 2-mast ships and 4 1-mast ships.
        4. Winner has ships remaining while loser has none.
        5. Game messages have configurable target: console (System.out, System.err) or logs or external printer.
        6. We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable.

##### - Game features:
        1. Drawing the boards for a player (fleet board has player's fleet and where opponent shot, "seen" board has where player fired and what he has shot).
        2. Placing the fleet - diagonal placing is disallowed, only horizontal and vertical. Humans can place ships but they can also choose to randomize placement. Ships cannot touch (no adjacent field to a ship can have a ship). Ships can be partially vertical and partially horizontal, if they have the length.
        3. Firing the shot - choose a place, shoot. If you hit, you repeat the shot. You can repeat as many times as you hit.
        4. Hitting the ship - hit happens when place chosen has enemy ship. Mark this part of ship as hit, ask for another shot. One can repeat the shot into already hit (or even sunken) ship, but this doesn't give the right to another shot.
        5. Missing the ship - misses are marked on "seen" board. One can shoot twice in the same place if it's a miss.
        6. Sinking the ship - if all masts of a ship are hit, ship sinks. Once the ship has sunk, mark all adjacent fields as "missed", since none of them can have a ship anyway.
        7. Sinking last ship, that is, winning.
