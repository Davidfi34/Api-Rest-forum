ALTER TABLE responses ADD active tinyint;
UPDATE responses SET active = 1;