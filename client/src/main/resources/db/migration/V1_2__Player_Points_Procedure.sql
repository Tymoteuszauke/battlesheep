delimiter //
CREATE PROCEDURE updateOrInsertPlayer
(winner CHAR(50))
BEGIN
   IF EXISTS (SELECT player_name FROM players WHERE player_name = winner LIMIT 1)
	THEN UPDATE players SET points = points + 1 WHERE player_name = winner;
	ELSE
	INSERT INTO players(player_name, points) VALUES (winner, 0);
    END IF;
END//
SET SQL_SAFE_UPDATES = 0;