CREATE TABLE DISTRICT(
	DISTRICT_ID INTEGER PRIMARY KEY,
	DISTRICT_NAME NVARCHAR(30) NOT NULL
);

INSERT INTO DISTRICT(DISTRICT_NAME) VALUES
('Авиастроительный'),
('Вахитовский'),
('Кировский'),
('Московский'),
('Ново-Савиновский'),
('Приволжский'),
('Советский');

CREATE TABLE CINEMA(
	CINEMA_ID INTEGER PRIMARY KEY,
	CINEMA_NAME NVARCHAR(30) NOT NULL,
    ADDRESS NVARCHAR(30) NOT NULL,
	PHONE_NUMBER CHAR(12) NOT NULL,
	DISTRICT_ID INTEGER NOT NULL,
	FOREIGN KEY(DISTRICT_ID)
		REFERENCES DISTRICT(DISTRICT_ID)
			ON DELETE RESTRICT
			ON UPDATE CASCADE
);

INSERT INTO CINEMA(CINEMA_NAME, ADDRESS, PHONE_NUMBER, DISTRICT_ID) VALUES
('Кинотеатр Ленина', 'Копылова, 2а', '+78432463242', 1),
('Алмаз Синема Родина', '​Баумана, 44', '+78432456356', 2),
('Кинотеатр Корстон', 'Николая Ершова, 1а', '+78432793079', 2),
('Мир', '​Достоевского, 30', '+78432380950', 2),
('КАРО', '​Петербургская, 1', '+78432380522', 2),
('Relax-cinema', '​Спартаковская, 2 к1', '+78432475447', 2),
('АЛМАЗ СИНЕМА ГРАНД', 'Спартаковская, 6', '+78435265333', 2),
('Киномакс-Тандем', 'Ибрагимова проспект, 56', '+78435189270', 4),
('Смотри и пой', '​Ибрагимова проспект, 56а', '+78432483000', 4),
('Синема 5-Парк Хаус', '​Ямашева проспект, 46', '+78435280545', 5),
('Алмаз Синема Порт', 'Фатыха Амирхана, 1Б', '+78435265629', 5),
('Смотри и пой', '​Ямашева проспект, 97', '+78432483000', 5),
('Киномакс-Club Казань', 'Павлюхина, 91', '+78432333912', 6),
('Алмаз Синема Сувар', '​Хусаина Мавлютова, 45', '+78432007828', 6),
('Love Cinema', 'Патриса Лумумбы, 47 ст1', '', 7),
('Киномакс-Club Казань', 'Победы проспект, 91', '+78432333912', 7);

CREATE TABLE HALL(
    HALL_ID INTEGER PRIMARY KEY,
    CAPACITY INTEGER NOT NULL,
    CINEMA_ID INTEGER NOT NULL,
    FOREIGN KEY(CINEMA_ID)
        REFERENCES CINEMA(CINEMA_ID)
			ON DELETE RESTRICT
			ON UPDATE CASCADE
    CHECK(CAPACITY >= 0)
);

CREATE TABLE GENRE(
    GENRE_ID INTEGER PRIMARY KEY,
    GENRE_NAME NVARCHAR(30) NOT NULL
);

CREATE TABLE MOVIE(
    MOVIE_ID INTEGER PRIMARY KEY,
    MOVIE_NAME NVARCHAR(30) NOT NULL,
    RELEASE INTEGER NOT NULL,
    DURATION INTEGER NOT NULL,
    RATING REAL NOT NULL
    CHECK(RATING >= 0 AND RATING <= 10 AND DURATION >= 0 AND RELEASE >= 2000)
);

CREATE TABLE MOVIE_GENRE(
    MOVIE_ID INTEGER NOT NULL,
    GENRE_ID INTEGER NOT NULL,
    PRIMARY KEY(MOVIE_ID, GENRE_ID),
    FOREIGN KEY(MOVIE_ID)
        REFERENCES MOVIE(MOVIE_ID)
			ON DELETE RESTRICT
			ON UPDATE CASCADE,
    FOREIGN KEY(GENRE_ID)
        REFERENCES GENRE(GENRE_ID)
			ON DELETE RESTRICT
			ON UPDATE CASCADE
);

CREATE TABLE SESSION(
    SESSION_ID INTEGER PRIMARY KEY,
    DATE_TIME DATETIME NOT NULL,
    NUMBER_FREE_PLACES INTEGER NOT NULL,
    HALL_ID INTEGER NOT NULL,
    MOVIE_ID INTEGER NOT NULL,
    FOREIGN KEY(HALL_ID)
        REFERENCES HALL(HALL_ID)
			ON DELETE RESTRICT
			ON UPDATE CASCADE,
    FOREIGN KEY(MOVIE_ID)
        REFERENCES MOVIE(MOVIE_ID)
			ON DELETE RESTRICT
			ON UPDATE CASCADE
    CHECK(NUMBER_FREE_PLACES >= 0)
);

CREATE TRIGGER CHECK_CAPACITY
    BEFORE INSERT ON SESSION
    WHEN(NEW.NUMBER_FREE_PLACES > (SELECT CAPACITY FROM HALL WHERE HALL_ID = NEW.HALL_ID))
BEGIN
    SELECT RAISE(ABORT,'Неверное значение свободных мест.');
END;

INSERT INTO HALL(CAPACITY, CINEMA_ID) VALUES
(100, 1),
(100, 1),
(100, 1),
(100, 1),
(25, 1),
(90, 2),
(90, 2),
(100, 3),
(100, 3),
(25, 3),
(100, 4),
(60, 5),
(70, 6),
(90, 7),
(90, 7),
(100, 8),
(50, 8),
(90, 8),
(95, 9),
(10, 9),
(90, 10),
(100, 10),
(100, 11),
(40, 11),
(100, 12),
(60, 12),
(100, 13),
(100, 13),
(90, 14),
(90, 15),
(90, 15),
(25, 16),
(100, 16),
(100, 16);

INSERT INTO GENRE(GENRE_NAME) VALUES
('Боевик'),
('Вестерн'),
('Детектив'),
('Драма'),
('Исторический'),
('Комедия'),
('Мелодрама'),
('Трагедия'),
('Триллер'),
('Фэнтези'),
('Ужасы'),
('Приключения'),
('Фантастика');

INSERT INTO MOVIE(MOVIE_NAME, RELEASE, DURATION, RATING) VALUES
('Гренландия', 2020, 119, 6.3),
('Рождественские хроники 2', 2020, 112, 6.1),
('1+1', 2011, 112, 8.5),
('Человек-паук: Вдали от дома', 2019, 129, 7.5),
('Мстители: Финал', 2019, 181, 8.4),
('Стражи Галактики 2', 2017, 136, 7.6),
('Разлом', 2018, 104, 5.7),
('Джанго освобожденный', 2012, 165, 8.4),
('Уондер', 2020, 94, 5.1),
('Интерстеллар', 2014, 169, 8.6),
('Гамильтон', 2020, 160, 8.6),
('Они никогда не станут старше', 2018, 99, 8.3),
('Пара на праздники', 2020, 103, 6.1),
('Джон Уик 3', 2019, 130, 7.4),
('Оно', 2017, 135, 7.3),
('Звонок из прошлого', 2020, 112, 7.2);

INSERT INTO MOVIE_GENRE(MOVIE_ID, GENRE_ID) VALUES
(1, 1),
(2, 6),
(2, 10),
(2, 12),
(3, 4),
(3, 6),
(4, 1),
(4, 13),
(4, 6),
(4, 12),
(5, 1),
(5, 13),
(5, 12),
(5, 10),
(6, 1),
(6, 13),
(6, 12),
(6, 6),
(7, 2),
(8, 2),
(8, 4),
(8, 6),
(8, 12),
(9, 3),
(9, 9),
(10, 4),
(10, 13),
(10, 12),
(11, 4),
(11, 5),
(12, 5),
(13, 6),
(13, 7),
(14, 1),
(14, 4),
(14, 9),
(15, 4),
(15, 11),
(15, 12),
(16, 9),
(16, 11),
(16, 3);

INSERT INTO SESSION(DATE_TIME, NUMBER_FREE_PLACES, HALL_ID, MOVIE_ID) VALUES
('2020-12-08 14:30', 50, 1, 1),
('2020-11-25 18:00', 60, 7, 2),
('2011-09-23 17:00', 6, 7, 3),
('2011-09-24 12:30', 1, 9, 3),
('2019-06-28 19:00', 0, 11, 4),
('2019-04-24 20:00', 0, 15, 5),
('2017-04-10 15:00', 0, 18, 6),
('2017-09-10 23:00', 0, 20, 15),
('2020-03-31 20:00', 10, 32, 16),
('2019-05-16 19:00', 7, 32, 14),
('2020-07-03 20:00', 40, 29, 11);

SELECT CINEMA_NAME 
    FROM CINEMA C INNER JOIN DISTRICT D
    ON C.DISTRICT_ID = D.DISTRICT_ID 
    WHERE D.DISTRICT_ID = 6;

SELECT DATE_TIME, NUMBER_FREE_PLACES 
    FROM SESSION 
    WHERE HALL_ID IN 
    (SELECT HALL_ID FROM HALL WHERE CINEMA_ID = 2);

SELECT MOVIE_NAME
    FROM MOVIE M INNER JOIN MOVIE_GENRE MG
    ON M.MOVIE_ID = MG.MOVIE_ID
    WHERE GENRE_ID = 12;

SELECT MOVIE_NAME
    FROM MOVIE M
    WHERE M.RELEASE = 2020;

SELECT DISTINCT MOVIE_NAME
    FROM MOVIE M INNER JOIN MOVIE_GENRE MG
    ON M.MOVIE_ID = MG.MOVIE_ID
    INNER JOIN GENRE G
    ON MG.GENRE_ID = G.GENRE_ID
    WHERE RATING >= 6;
