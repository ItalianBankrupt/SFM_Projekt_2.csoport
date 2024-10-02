-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Okt 02. 20:32
-- Kiszolgáló verziója: 10.4.32-MariaDB
-- PHP verzió: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `sfm_project`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `restaurant`
--

CREATE TABLE `restaurant` (
  `Item` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL,
  `Type` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `restaurant`
--

INSERT INTO `restaurant` (`Item`, `Price`, `Type`) VALUES
('Sajt-Sonka izelítő', 2380, 'Előétel'),
('Buffalo mozzarella caprese', 2480, 'Előétel'),
('Eperkrémleves', 1450, 'Leves'),
('Mediterrán sültzöldség-krémlev', 1570, 'Leves'),
('Húsleves gazdagon', 1700, 'Leves'),
('Provance sous vide csirkemell', 4190, 'Főétel'),
('Grill camambert', 3980, 'Főétel'),
('Pulled Prok szendvics', 3950, 'Főétel'),
('Sous vide kacsamell steak', 4850, 'Főétel'),
('Tarja steak', 4680, 'Főétel'),
('\"Géza konyhája grill tál\"', 8990, 'Főétel'),
('Fehérboros fekete kagyló', 5190, 'Hal étel'),
('Tenger gyümölcsei spagetti', 4990, 'Hal étel'),
('Csirkemell csíkos tagliatelle', 3180, 'Tészta'),
('Boloognai spagetti', 2790, 'Tészta'),
('Kókuszos-ananászos Panna cotta', 1280, 'Desszert'),
('Csoki sufflé vaníillia öntette', 1580, 'Desszert'),
('Espresso', 520, 'Kávé'),
('Forró csoki', 550, 'Kávé'),
('Cappuccion', 590, 'Kávé'),
('Szentkirályi mentes', 450, 'Üdítő'),
('Szentkirályi szénsavas', 450, 'Üdítő'),
('Cappy', 690, 'Üdítő'),
('Coca-Cola', 590, 'Üdítő'),
('Kinley Gyömbér', 450, 'Üdítő'),
('Sprite', 450, 'Üdítő'),
('Limonádé', 890, 'Üdítő'),
('Staropramen', 1150, 'Sör'),
('Belle-Vue kriek', 1600, 'Sör');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `services`
--

CREATE TABLE `services` (
  `Item` varchar(30) NOT NULL,
  `Price` int(11) NOT NULL,
  `Type` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `services`
--

INSERT INTO `services` (`Item`, `Price`, `Type`) VALUES
('Felnőtt élményfürdő belépő', 5500, 'Belépő'),
('Diák élményfürdő belépő', 3900, 'Belépő'),
('Gyermek belépő', 2600, 'Belépő'),
('Fürdő belépő', 4700, 'Belépő'),
('Nyugdíjas belépő', 3900, 'Belépő'),
('Diák/Gyermek belépő', 3800, 'Belépő'),
('Regisztrálciós jegy', 300, 'Belépő'),
('7 napos felnőtt bérlet', 28200, 'Bérlet'),
('7 napos nyugdíjas bérlet', 36000, 'Bérlet'),
('7 napos gyermek/diák bérlet', 23400, 'Bérlet'),
('Szauna', 1300, 'Szolgáltatás'),
('Szauna bérlet (7 napos)', 7800, 'Szolgáltatás'),
('Értékmegőrző', 700, 'Szolgáltatás'),
('Pihenőágy', 1300, 'Szolgáltatás'),
('Napozóágy', 1600, 'Szolgáltatás'),
('Napozóágy a tengerparton', 2000, 'Szolgáltatás'),
('Baldachin a tengerparton', 8000, 'Szolgáltatás'),
('Aquapark normal belépő', 5300, 'Belépő'),
('Aquapark minimal belépő', 3400, 'Belépő'),
('Prémium belépőjegy', 6500, 'Belépő'),
('Cuccmegőrző', 700, 'Szolgáltatás');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
