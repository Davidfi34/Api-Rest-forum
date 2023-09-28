ALTER TABLE courses ADD active tinyint;
UPDATE courses SET active = 1;