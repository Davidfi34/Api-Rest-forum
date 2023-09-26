ALTER TABLE topics ADD active tinyint;
UPDATE topics SET active = 1;