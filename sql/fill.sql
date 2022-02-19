INSERT INTO CHAPTER (NAME, PARENT_LEGION)
VALUES ('Paul Atreides', 'Atreides'),
       ('Chani Kynes', 'Fremen'),
       ('Lady Jessica', 'Bene Gesserit'),
       ('Leto Atreides', 'Atreides');

INSERT INTO COORDINATES (X, Y)
VALUES (0, 0),
       (100, 100),
       (200, 200),
       (300, 300),
       (400, 400),
       (500, 500),
       (600, 600),
       (700, 700),
       (800, 800),
       (900, 900);

INSERT INTO SPACE_MARINE (NAME, COORDINATES, CREATION_DATE, HEALTH, HEART_COUNT, LOYAL, CATEGORY, CHAPTER)
VALUES ('Heighliner', 7, '2003-01-01', 4, 3, true , 'APOTHECARY', 12),
       ('Arcadia', 8, '2003-01-01', 11, 2, true, 'HELIX', 12),
       ('Black Hawk', 8, '2003-01-01', 11, 2, true, 'LIBRARIAN', 13),
       ('Charlotte', 9, '2021-01-01', 11, 1, true, 'LIBRARIAN', 14),
       ('Shark', 9, '2004-01-01', 3, 3, true, 'AGGRESSOR', 12);
