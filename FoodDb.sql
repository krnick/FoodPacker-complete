-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- 主機: localhost
-- 產生時間： 2016 年 12 月 04 日 12:59
-- 伺服器版本: 5.7.16-0ubuntu0.16.04.1
-- PHP 版本： 7.0.8-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `FoodDb`
--

-- --------------------------------------------------------

--
-- 資料表結構 `DataAnalize`
--

CREATE TABLE `DataAnalize` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `designation` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `salary` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fbid` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lat` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lng` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `restaurant` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fee` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `DataAnalize`
--

INSERT INTO `DataAnalize` (`id`, `name`, `designation`, `salary`, `fbid`, `lat`, `lng`, `restaurant`, `address`, `fee`, `score`) VALUES
(6, 'Nick Sung', '摩斯火雞堡 x 1\n薑燒珍珠堡 x 1\n蜜汁烤雞堡 x 1\n', '220元', '1210838185657232', '22.727507', '120.306939', '遠得要命餐廳-羊寶寶', '臺北', '200', '3.2'),
(7, 'Nick Sung', '海洋珍珠堡 x 1\n摩斯火雞堡 x 1\n薑燒珍珠堡 x 1\n', '230元', '1210838185657232', '22.727507', '120.306939', '遠得要命餐廳-羊寶寶', '台東', '100', '3.2'),
(8, 'Nick Sung', '摩斯火雞堡 x 2\n厚切培根和牛堡 x 2\n蜜汁烤雞堡 x 2\n', '470元', '1210838185657232', '22.734355', '120.284497', '超好吃餐廳', '高雄', '20', '3.2'),
(9, 'Nick Sung', '燒肉珍珠堡 x 1\n厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n黃金炸蝦堡 x 1\n', '310元', '1210838185657232', '22.734355', '120.284497', '超好吃餐廳', '高雄', '200', '3.2'),
(10, '宋駿瑋', '黃金炸蝦堡 x 1\n摩斯熱狗堡 x 1\n', '130元', '1826650987617556', '22.734355', '120.284497', '超好吃餐廳', '', '', '3.6');

-- --------------------------------------------------------

--
-- 資料表結構 `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `designation` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `salary` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `fbid` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lat` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `lng` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `restaurant` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `fee` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `employee`
--

INSERT INTO `employee` (`id`, `name`, `designation`, `salary`, `fbid`, `lat`, `lng`, `restaurant`, `address`, `fee`, `score`) VALUES
(6, 'Nick Sung', '摩斯火雞堡 x 1\n薑燒珍珠堡 x 1\n蜜汁烤雞堡 x 1\n', '220元', '1210838185657232', '22.727507', '120.306939', '遠得要命餐廳-羊寶寶', '臺北', '200', '3.2'),
(7, 'Nick Sung', '海洋珍珠堡 x 1\n摩斯火雞堡 x 1\n薑燒珍珠堡 x 1\n', '230元', '1210838185657232', '22.727507', '120.306939', '遠得要命餐廳-羊寶寶', '台東', '100', '3.2'),
(8, 'Nick Sung', '摩斯火雞堡 x 2\n厚切培根和牛堡 x 2\n蜜汁烤雞堡 x 2\n', '470元', '1210838185657232', '22.734355', '120.284497', '超好吃餐廳', '高雄', '20', '3.2'),
(9, 'Nick Sung', '燒肉珍珠堡 x 1\n厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n黃金炸蝦堡 x 1\n', '310元', '1210838185657232', '22.734355', '120.284497', '超好吃餐廳', '高雄', '200', '3.2'),
(11, '宋駿瑋', '黃金炸蝦堡 x 1\n摩斯熱狗堡 x 1\n', '130元', '1826650987617556', '22.734355', '120.284497', '超好吃餐廳', '', '', '3.6'),
(12, 'Benson Chen', 'OREO鮮奶油鬆餅 x 2\n火腿蔥抓餅 x 3\n', '176元', '1494307333917594', '22.732163', '120.285291', '樂活早午餐', 'my place in CY', '200', '3.1'),
(13, 'Benson Chen', '鍋貼 x 1\n牛肉捲餅 x 2\n蔥油餅 x 2\n酸辣湯 x 3\n鍋貼 x 1\n牛肉捲餅 x 2\n蔥油餅 x 2\n酸辣湯 x 4\n', '305元', '1494307333917594', '22.727507', '120.306939', '遠得要命餐廳-羊寶寶', 'nanzi train station', '30', '3.1'),
(14, '游依絃', '摩斯熱狗堡 x 4\n', '280元', '1294800520601900', '22.734355', '120.284497', '超好吃餐廳', '高雄大學圖資', '20', '4.0');

-- --------------------------------------------------------

--
-- 資料表結構 `HistoryOrder`
--

CREATE TABLE `HistoryOrder` (
  `Myname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Dealname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Menu` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `price` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `HistoryOrder`
--

INSERT INTO `HistoryOrder` (`Myname`, `Dealname`, `Menu`, `price`, `time`, `id`) VALUES
('宋駿瑋', 'Nick Sung', '蜜汁烤雞堡 x 1\n黃金炸蝦堡 x 1\n', '140元', '2016-12-02 03:42:20', 25),
('Nick Sung', '宋駿瑋', '摩斯火雞堡 x 1\n', '90元', '2016-12-02 13:55:51', 26),
('Nick Sung', '宋駿瑋', '厚切培根和牛堡 x 1\n', '100元', '2016-12-02 13:59:53', 27),
('Nick Sung', '宋駿瑋', '薑燒珍珠堡 x 1\n', '65元', '2016-12-02 14:03:36', 28),
('Nick Sung', '宋駿瑋', '薑燒珍珠堡 x 1\n', '65元', '2016-12-02 14:15:57', 29),
('Nick Sung', '宋駿瑋', '厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n', '165元', '2016-12-02 14:25:37', 30),
('宋駿瑋', 'Nick Sung', '蜜汁烤雞堡 x 1\n摩斯鱈魚堡 x 1\n', '135元', '2016-12-02 14:30:43', 31),
('Nick Sung', '宋駿瑋', '燒肉珍珠堡 x 1\n', '70元', '2016-12-02 14:34:48', 32),
('Nick Sung', '游依絃', '薑燒珍珠堡 x 1\n', '65元', '2016-12-02 16:18:27', 33),
('Nick Sung', '游依絃', '薑燒珍珠堡 x 1\n厚切培根和牛堡 x 1\n', '165元', '2016-12-02 16:22:00', 34),
('Nick Sung', '宋駿瑋', '薑燒珍珠堡 x 1\n', '65元', '2016-12-02 16:27:28', 35),
('Nick Sung', '宋駿瑋', '厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n', '165元', '2016-12-02 16:34:43', 36),
('Nick Sung', '游依絃', '厚切培根和牛堡 x 1\n', '100元', '2016-12-02 16:45:02', 37),
('Nick Sung', 'null', '薑燒珍珠堡 x 1\n', '65元', '2016-12-03 03:40:19', 38),
('Nick Sung', '宋駿瑋', '薑燒珍珠堡 x 1\n', '65元', '2016-12-03 03:49:48', 39),
('Nick Sung', 'Benson Chen', '摩斯火雞堡 x 1\n厚切培根和牛堡 x 1\n', '190元', '2016-12-03 06:24:45', 40),
('Benson Chen', 'Jing-ting Zeng', '燒肉珍珠堡 x 1\n厚切培根和牛堡 x 2\n', '220元', '2016-12-03 06:27:41', 41),
('Benson Chen', 'Jing-ting Zeng', '燒肉珍珠堡 x 1\n厚切培根和牛堡 x 2\n', '220元', '2016-12-03 06:27:50', 42),
('Jing-ting Zeng', 'Nick Sung', '辣味吉利熱狗堡 x 2\n辛味章魚堡 x 4\n', '440元', '2016-12-03 06:31:32', 43),
('Jing-ting Zeng', 'Nick Sung', '燒肉珍珠堡 x 2\n', '140元', '2016-12-03 06:37:38', 44),
('Nick Sung', '游依絃', '厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n', '165元', '2016-12-03 06:40:18', 45),
('Benson Chen', '游依絃', '摩斯火雞堡 x 2\n辛味章魚堡 x 1\n柚香大阪燒珍珠堡 x 1\n', '320元', '2016-12-03 07:46:04', 46),
('Nick Sung', '宋駿瑋', '薑燒珍珠堡 x 2\n厚切培根和牛堡 x 2\n', '290元', '2016-12-03 10:52:51', 47),
('宋駿瑋', 'Nick Sung', '厚切培根和牛堡 x 2\n摩斯鱈魚堡 x 2\n', '290元', '2016-12-03 11:03:35', 48),
('Nick Sung', '宋駿瑋', '薑燒珍珠堡 x 1\n厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n', '230元', '2016-12-03 11:06:26', 49),
('Nick Sung', '宋駿瑋', '厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n摩斯鱈魚堡 x 1\n', '235元', '2016-12-03 11:18:36', 50),
('宋駿瑋', 'Nick Sung', '薑燒珍珠堡 x 1\n蜜汁烤雞堡 x 1\n', '130元', '2016-12-03 11:23:00', 51),
('游依絃', 'Benson Chen', '薑燒珍珠堡 x 1\n', '65元', '2016-12-03 14:21:07', 52);

-- --------------------------------------------------------

--
-- 資料表結構 `JsonDataTable`
--

CREATE TABLE `JsonDataTable` (
  `auto` int(6) NOT NULL,
  `id` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `menu_page2` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `JsonDataTable`
--

INSERT INTO `JsonDataTable` (`auto`, `id`, `name`, `phone_number`, `subject`, `menu_page2`) VALUES
(6, '2016-12-03 12:12:13', 'Nick Sung', '1210838185657232', '遠得要命餐廳-羊寶寶', '摩斯火雞堡 x 1\n薑燒珍珠堡 x 1\n蜜汁烤雞堡 x 1\n'),
(7, '2016-12-03 12:12:35', 'Nick Sung', '1210838185657232', '遠得要命餐廳-羊寶寶', '海洋珍珠堡 x 1\n摩斯火雞堡 x 1\n薑燒珍珠堡 x 1\n'),
(8, '2016-12-03 12:30:46', 'Nick Sung', '1210838185657232', '超好吃餐廳', '摩斯火雞堡 x 2\n厚切培根和牛堡 x 2\n蜜汁烤雞堡 x 2\n'),
(9, '2016-12-03 12:42:21', 'Nick Sung', '1210838185657232', '超好吃餐廳', '燒肉珍珠堡 x 1\n厚切培根和牛堡 x 1\n蜜汁烤雞堡 x 1\n黃金炸蝦堡 x 1\n'),
(11, '2016-12-03 14:28:36', '宋駿瑋', '1826650987617556', '超好吃餐廳', '黃金炸蝦堡 x 1\n摩斯熱狗堡 x 1\n'),
(12, '2016-12-03 18:08:55', 'Benson Chen', '1494307333917594', '超好吃餐廳', '好吃火雞堡 x 1\n厚切培根和牛堡 x 2\n蜜汁烤雞堡 x 2\n好吃火雞堡 x 1\n厚切培根和牛堡 x 2\n蜜汁烤雞堡 x 1\n好吃火雞堡 x 1\n厚切培根和牛堡 x 3\n蜜汁烤雞堡 x 1\n好吃火雞堡 x 1\n厚切培根和牛堡 x 4\n蜜汁烤雞堡 x 1\n好吃火雞堡 x 0\n厚切培根和牛堡 x 4\n蜜汁烤雞堡 x 1\n好吃火雞堡 x 1\n厚切培根和牛堡 x 3\n蜜汁烤雞堡 x 2\n'),
(13, '2016-12-03 18:10:00', 'Benson Chen', '1494307333917594', '樂活早午餐', 'OREO鮮奶油鬆餅 x 2\n火腿蔥抓餅 x 3\n'),
(14, '2016-12-03 22:19:48', 'Benson Chen', '1494307333917594', '遠得要命餐廳-羊寶寶', '鍋貼 x 1\n牛肉捲餅 x 2\n蔥油餅 x 2\n酸辣湯 x 3\n鍋貼 x 1\n牛肉捲餅 x 2\n蔥油餅 x 2\n酸辣湯 x 4\n'),
(15, '2016-12-04 02:58:52', '游依絃', '1294800520601900', '超好吃餐廳', '摩斯熱狗堡 x 4\n');

-- --------------------------------------------------------

--
-- 資料表結構 `Rating`
--

CREATE TABLE `Rating` (
  `Myname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Cname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `value` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- 資料表的匯出資料 `Rating`
--

INSERT INTO `Rating` (`Myname`, `Cname`, `value`, `comment`, `time`) VALUES
('ben', 'nick', 'test', 'good', '2016-12-02 16:29:31'),
('å®‹é§¿ç‘‹', 'Nick Sung', '4.0', 'å¾ˆæ£’', '2016-12-02 16:29:31'),
('æŸ¯å°šå®', 'Nick Sung', '5.0', '', '2016-12-02 16:29:31'),
('æŸ¯å°šå®', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '2.5', '', '2016-12-02 16:29:31'),
('Benson Chen', 'Nick Sung', '3.0', '', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '3.0', '', '2016-12-02 16:29:31'),
('å®‹é§¿ç‘‹', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '很棒', '2016-12-02 16:29:31'),
('Nick Sung', 'Nick Sung', '4.0', '超棒', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '3.5', 'good', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '3.0', '123', '2016-12-02 16:29:31'),
('Benson Chen', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('Nick Sung', '陳廷維', '0.0', '', '2016-12-02 16:29:31'),
('柯尚宏', 'Nick Sung', '3.0', '讓人', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '3.5', '', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '4.5', '嗯嗯', '2016-12-02 16:29:31'),
('柯尚宏', 'Nick Sung', '3.0', '', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '0.0', '', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '5.0', '', '2016-12-02 16:29:31'),
('柯尚宏', 'Nick Sung', '0.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '3.0', '哈哈', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '0.0', '', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '5.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '3.5', 'fffc', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '4.0', '小浣熊', '2016-12-02 16:29:31'),
('柯尚宏', 'Nick Sung', '4.0', '棒', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '5.0', '哈哈哈', '2016-12-02 16:29:31'),
('Nick Sung', '柯尚宏', '0.0', '', '2016-12-02 16:29:31'),
('柯尚宏', 'Nick Sung', '3.5', '', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '3.5', '123hh', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '4.0', 'ok', '2016-12-02 16:29:31'),
('Nick Sung', 'Benson Chen', '1.5', 'nono', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '3.0', '', '2016-12-02 16:29:31'),
('Nick Sung', '宋駿瑋', '3.0', 'per', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '3.5', '很棒', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '棒', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '好', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('Nick Sung', '宋駿瑋', '3.0', '', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '', '2016-12-02 16:29:31'),
('游依絃', 'Nick Sung', '4.0', '笨蛋哈哈哈哈', '2016-12-02 16:29:31'),
('游依絃', 'Nick Sung', '4.0', '呢誒誒', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '4.0', '恩恩', '2016-12-02 16:29:31'),
('宋駿瑋', 'Nick Sung', '5.0', '很好歐歐歐歐歐歐', '2016-12-02 16:34:55'),
('游依絃', 'Nick Sung', '4.0', '好窩', '2016-12-02 16:45:10'),
('宋駿瑋', 'Nick Sung', '5.0', '好的', '2016-12-03 03:49:57'),
('Benson Chen', 'Nick Sung', '5.0', '讚讚美國人生觀點滴答案件件件', '2016-12-03 06:25:00'),
('Jing-ting Zeng', 'Benson Chen', '3.5', '很開心哦哦哦', '2016-12-03 06:28:01'),
('Nick Sung', 'Jing-ting Zeng', '5.0', '快速讚讚', '2016-12-03 06:31:45'),
('Nick Sung', 'Jing-ting Zeng', '5.0', '', '2016-12-03 06:37:43'),
('游依絃', 'Nick Sung', '5.0', '交給你了', '2016-12-03 06:40:24'),
('游依絃', 'Benson Chen', '3.0', '謝謝', '2016-12-03 07:46:24'),
('宋駿瑋', 'Nick Sung', '3.5', '謝啦', '2016-12-03 10:52:59'),
('Nick Sung', '宋駿瑋', '3.0', 'perfect', '2016-12-03 11:03:49'),
('宋駿瑋', 'Nick Sung', '4.0', '哈哈', '2016-12-03 11:06:42'),
('宋駿瑋', 'Nick Sung', '3.0', '棒', '2016-12-03 11:18:53'),
('Nick Sung', '宋駿瑋', '4.0', 'perfext', '2016-12-03 11:23:07'),
('Benson Chen', '游依絃', '0.5', '不錯', '2016-12-03 14:21:16');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `DataAnalize`
--
ALTER TABLE `DataAnalize`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `HistoryOrder`
--
ALTER TABLE `HistoryOrder`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `JsonDataTable`
--
ALTER TABLE `JsonDataTable`
  ADD PRIMARY KEY (`auto`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `DataAnalize`
--
ALTER TABLE `DataAnalize`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- 使用資料表 AUTO_INCREMENT `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- 使用資料表 AUTO_INCREMENT `HistoryOrder`
--
ALTER TABLE `HistoryOrder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- 使用資料表 AUTO_INCREMENT `JsonDataTable`
--
ALTER TABLE `JsonDataTable`
  MODIFY `auto` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
