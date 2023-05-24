-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1:3306
-- 產生時間： 2023 年 05 月 24 日 15:53
-- 伺服器版本： 8.0.33
-- PHP 版本： 7.4.3-4ubuntu2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `gsbka_store`
--

-- --------------------------------------------------------

--
-- 資料表結構 `t_cart`
--

CREATE TABLE `t_cart` (
  `cid` int NOT NULL COMMENT '購物車id',
  `uid` int NOT NULL COMMENT '使用者id',
  `pid` int NOT NULL COMMENT '商品id',
  `price` mediumtext COMMENT '商品價格',
  `num` int DEFAULT NULL COMMENT '商品數量',
  `created_user` varchar(20) DEFAULT NULL COMMENT '建立者',
  `created_time` datetime DEFAULT NULL COMMENT '建立時間',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- 資料表結構 `t_order`
--

CREATE TABLE `t_order` (
  `oid` int NOT NULL COMMENT '訂單id',
  `uid` int NOT NULL COMMENT '使用者id',
  `total_price` mediumtext COMMENT '總金額',
  `status` int DEFAULT NULL COMMENT '狀態：0-未支付，1-已支付，2-已取消，3-已關閉，4-已完成',
  `order_time` datetime DEFAULT NULL COMMENT '下單時間',
  `pay_time` datetime DEFAULT NULL COMMENT '支付時間',
  `created_user` varchar(20) DEFAULT NULL COMMENT '建立者',
  `created_time` datetime DEFAULT NULL COMMENT '建立時間',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- 傾印資料表的資料 `t_order`
--

INSERT INTO `t_order` (`oid`, `uid`, `total_price`, `status`, `order_time`, `pay_time`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES
(1, 1, '4800', 0, '2023-05-24 13:53:16', NULL, 'user@gmail.com', '2023-05-24 13:53:16', 'user@gmail.com', '2023-05-24 13:53:16');

-- --------------------------------------------------------

--
-- 資料表結構 `t_order_item`
--

CREATE TABLE `t_order_item` (
  `id` int NOT NULL COMMENT '订单中的商品记录的id',
  `oid` int NOT NULL COMMENT '所归属的订单的id',
  `pid` int NOT NULL COMMENT '商品的id',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `price` bigint DEFAULT NULL COMMENT '商品价格',
  `num` int DEFAULT NULL COMMENT '购买数量',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- 傾印資料表的資料 `t_order_item`
--

INSERT INTO `t_order_item` (`id`, `oid`, `pid`, `title`, `image`, `price`, `num`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES
(1, 1, 10000004, '鋼筆 細 四色', '/images/product/pen/', 580, 8, 'user@gmail.com', '2023-05-24 13:53:17', 'user@gmail.com', '2023-05-24 13:53:17'),
(2, 1, 10000006, '直尺 15公分 二色', '/images/product/ruler/', 60, 2, 'user@gmail.com', '2023-05-24 13:53:18', 'user@gmail.com', '2023-05-24 13:53:18'),
(3, 1, 10000002, 'MONO 橡皮差 限定版 五色', '/images/product/eraser/', 40, 1, 'user@gmail.com', '2023-05-24 13:53:19', 'user@gmail.com', '2023-05-24 13:53:19');

-- --------------------------------------------------------

--
-- 資料表結構 `t_product`
--

CREATE TABLE `t_product` (
  `id` int NOT NULL COMMENT '商品id',
  `title` varchar(100) DEFAULT NULL COMMENT '商品標題',
  `price` bigint DEFAULT NULL COMMENT '商品單價',
  `image` varchar(500) DEFAULT NULL COMMENT '圖片路徑',
  `status` int DEFAULT '1' COMMENT '商品狀態  1：上架   2：下架   3：刪除',
  `created_time` datetime DEFAULT NULL COMMENT '建立時間',
  `modified_time` datetime DEFAULT NULL COMMENT '最後修改時間',
  `created_user` varchar(50) DEFAULT NULL COMMENT '建立人',
  `modified_user` varchar(50) DEFAULT NULL COMMENT '最後修改人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- 傾印資料表的資料 `t_product`
--

INSERT INTO `t_product` (`id`, `title`, `price`, `image`, `status`, `created_time`, `modified_time`, `created_user`, `modified_user`) VALUES
(10000001, '修正帶 6m 四色', 110, '/images/product/correctiontape/', 1, '2023-05-13 20:22:37', '2023-05-13 20:22:53', 'admin', 'admin'),
(10000002, 'MONO 橡皮差 限定版 五色', 40, '/images/product/eraser/', 1, '2023-05-13 20:22:39', '2023-05-13 20:22:55', 'admin', 'admin'),
(10000003, '橫線5mm x 30行筆記本 B5 三色', 90, '/images/product/note/', 1, '2023-05-13 20:22:40', '2023-05-13 20:22:56', 'admin', 'admin'),
(10000004, '鋼筆 細 四色', 580, '/images/product/pen/', 1, '2023-05-13 20:22:43', '2023-05-13 20:22:58', 'admin', 'admin'),
(10000005, '製圖自動鉛筆 0.5mm 五色', 180, '/images/product/pencil/', 1, '2023-05-13 20:22:45', '2023-05-13 20:23:00', 'admin', 'admin'),
(10000006, '直尺 15公分 二色', 60, '/images/product/ruler/', 1, '2023-05-13 20:22:48', '2023-05-13 20:23:01', 'admin', 'admin'),
(10000007, '長刃剪刀 附刻度', 130, '/images/product/scissors/', 1, '2023-05-13 20:22:50', '2023-05-13 20:23:02', 'admin', 'admin'),
(10000008, '可攜式釘書機 四色', 220, '/images/product/stapler/', 1, '2023-05-13 20:22:51', '2023-05-13 20:23:04', 'admin', 'admin');

-- --------------------------------------------------------

--
-- 資料表結構 `t_user`
--

CREATE TABLE `t_user` (
  `uid` int NOT NULL COMMENT '帳號id',
  `username` varchar(20) NOT NULL COMMENT '帳號名稱',
  `password` char(32) NOT NULL COMMENT '密碼',
  `salt` char(36) DEFAULT NULL COMMENT '鹽質',
  `email` varchar(20) DEFAULT NULL COMMENT 'E-mail',
  `is_delete` int DEFAULT NULL COMMENT '是否刪除： 0-未刪除, 1：已刪除',
  `created_user` varchar(20) DEFAULT NULL COMMENT '日誌 - 建立者',
  `created_time` datetime DEFAULT NULL COMMENT '日誌 - 建立時間',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '日誌 - 修改者',
  `modified_time` datetime DEFAULT NULL COMMENT '日誌 - 修改時間'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- 傾印資料表的資料 `t_user`
--

INSERT INTO `t_user` (`uid`, `username`, `password`, `salt`, `email`, `is_delete`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES
(1, 'user@gmail.com', 'EA9124E1B7F5E26C514283C9063A1ABB', '51A18796-1D08-4103-9069-8B4327432D8F', NULL, 0, 'user@gmail.com', '2023-05-24 12:36:43', 'user@gmail.com', '2023-05-24 12:36:43');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `t_cart`
--
ALTER TABLE `t_cart`
  ADD PRIMARY KEY (`cid`);

--
-- 資料表索引 `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`oid`);

--
-- 資料表索引 `t_order_item`
--
ALTER TABLE `t_order_item`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `t_product`
--
ALTER TABLE `t_product`
  ADD PRIMARY KEY (`id`);

--
-- 資料表索引 `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `username` (`username`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `t_cart`
--
ALTER TABLE `t_cart`
  MODIFY `cid` int NOT NULL AUTO_INCREMENT COMMENT '購物車id', AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `t_order`
--
ALTER TABLE `t_order`
  MODIFY `oid` int NOT NULL AUTO_INCREMENT COMMENT '訂單id', AUTO_INCREMENT=2;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `t_order_item`
--
ALTER TABLE `t_order_item`
  MODIFY `id` int NOT NULL AUTO_INCREMENT COMMENT '订单中的商品记录的id', AUTO_INCREMENT=4;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `t_user`
--
ALTER TABLE `t_user`
  MODIFY `uid` int NOT NULL AUTO_INCREMENT COMMENT '帳號id', AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
