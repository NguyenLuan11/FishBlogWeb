-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fish_blog_web
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kind_fish`
--

DROP TABLE IF EXISTS `kind_fish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kind_fish` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `description` longtext NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `kind_fish_name` varchar(255) NOT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kind_fish`
--

LOCK TABLES `kind_fish` WRITE;
/*!40000 ALTER TABLE `kind_fish` DISABLE KEYS */;
INSERT INTO `kind_fish` VALUES (1,'2025-03-29 10:32:02','<p><strong>Betta </strong>l&agrave; một chi lớn gồm c&aacute;c lo&agrave;i c&aacute; v&acirc;y tia nước ngọt nhỏ, năng động, thường c&oacute; m&agrave;u sắc sặc sỡ, thuộc họ <em>c&aacute; r&ocirc; (Osphronemidae)</em>. Lo&agrave;i <strong>Betta </strong>nổi tiếng nhất l&agrave; <em>B.splendens</em>, thường được gọi l&agrave; <strong>c&aacute; chọi Xi&ecirc;m</strong> v&agrave; thường được nu&ocirc;i l&agrave;m th&uacute; cưng trong bể c&aacute;.</p>','/upload/imageKindFish/betta.png','Betta','2025-04-26 23:09:21'),(2,'2025-03-29 10:37:29','<p><strong>Guppy (c&aacute; bảy m&agrave;u)</strong> l&agrave; lo&agrave;i c&aacute; nước ngọt nhiều m&agrave;u sắc, đẻ con thuộc họ <em>Poeciliidae</em>, phổ biến như mộtth&uacute; cưng trong bể c&aacute; tại nh&agrave;. C&aacute; bảy m&agrave;u khỏe mạnh, năng động, dễ nu&ocirc;i v&agrave; sinh sản nhiều. C&aacute; bảy m&agrave;u đực, c&oacute; m&agrave;u s&aacute;ng hơn nhiều so với c&aacute; đực, d&agrave;i khoảng 4cm; c&aacute; c&aacute;i lớn hơn v&agrave; c&oacute; m&agrave;u xỉn hơn.</p>','/upload/imageKindFish/guppy.png','Guppy','2025-04-26 23:02:51'),(7,'2025-03-29 23:10:37','<p><strong>C&aacute; chuột</strong>, được biết đến với t&ecirc;n khoa học <strong>Corydoras aeneus</strong>, l&agrave; một trong những lo&agrave;i c&aacute; dọn bể phổ biến được nhiều người chơi c&aacute; cảnh y&ecirc;u th&iacute;ch. Đặc điểm nổi bật của c&aacute; chuột l&agrave; sở th&iacute;ch sinh sống ở tầng đ&aacute;y, l&agrave;m cho ch&uacute;ng trở th&agrave;nh lựa chọn l&yacute; tưởng cho c&aacute;c bể thủy sinh cỡ lớn.</p>','/upload/imageKindFish/ca_chuot.png','Cá Chuột','2025-04-26 23:02:51'),(8,'2025-03-29 23:20:29','<p><strong>C&aacute; K&eacute;t Panda</strong> l&agrave; một loại c&aacute; cảnh nổi tiếng trong giới chơi c&aacute; cảnh v&igrave; m&agrave;u sắc đặc trưng v&agrave; h&igrave;nh d&aacute;ng dễ thương. Ch&uacute;ng c&oacute; t&ecirc;n khoa học l&agrave; <strong>Parrot Fish</strong> v&agrave; được lai tạo từ c&aacute;c d&ograve;ng c&aacute; kh&aacute;c nhau để tạo ra vẻ ngo&agrave;i độc đ&aacute;o. C&aacute; k&eacute;t panda sở hữu m&agrave;u trắng v&agrave; đen, giống với m&agrave;u l&ocirc;ng của gấu tr&uacute;c, do đ&oacute; ch&uacute;ng c&oacute; t&ecirc;n gọi n&agrave;y.</p>','/upload/imageKindFish/ca_ket_panda.png','Két Panda','2025-04-28 08:54:18'),(9,'2025-04-26 09:20:56','<p><strong>Yellow Sentarum</strong>, c&ograve;n được gọi l&agrave; <strong>c&aacute; l&oacute;c vẩy rồng v&agrave;ng</strong>, xuất xứ từ <strong>Indonesia </strong>v&agrave; <strong>Malaysia </strong>v&agrave; đ&atilde; được nhập khẩu v&agrave; nu&ocirc;i dưỡng tại Việt Nam.</p>\r\n<p>Đặc điểm nổi bật của Yellow Sentarum l&agrave; vảy m&agrave;u đen với viền trắng xung quanh. Phần th&acirc;n của c&aacute; c&oacute; m&agrave;u v&agrave;ng v&agrave; bụng m&agrave;u trắng. Khi trưởng th&agrave;nh, c&aacute; c&oacute; thể đạt chiều d&agrave;i từ 45 đến 60cm.</p>','/upload/imageKindFish/1745634055951_ca-loc-vay-rong.jpg','Yellow Sentarum','2025-04-26 09:20:56'),(10,'2025-04-26 09:29:45','<p><strong>C&aacute; L&oacute;c Vảy Rồng Đỏ Red Barito</strong> <em>(t&ecirc;n khoa học l&agrave; Channa micropeltes)</em> l&agrave; một lo&agrave;i c&aacute; thuộc họ C&aacute; l&oacute;c, được biết đến với t&ecirc;n gọi kh&aacute;c như c&aacute; l&oacute;c đỏ, c&aacute; l&oacute;c rồng hay c&aacute; l&oacute;c vảy rồng.</p>\r\n<p>Đ&acirc;y l&agrave; một trong những lo&agrave;i c&aacute; nước ngọt c&oacute; k&iacute;ch thước lớn nhất, c&oacute; thể đạt đến chiều d&agrave;i tới 80cm v&agrave; c&acirc;n nặng l&ecirc;n đến 3kg. Lo&agrave;i c&aacute; n&agrave;y được t&igrave;m thấy chủ yếu ở c&aacute;c v&ugrave;ng nước nhiệt đới v&agrave; cận nhiệt đới của ch&acirc;u &Aacute;.</p>\r\n<p>Với vẻ ngo&agrave;i đặc trưng l&agrave; m&agrave;u sắc đỏ rực v&agrave; vảy lớn giống như rồng, C&aacute; L&oacute;c Vảy Rồng Đỏ Red Barito đ&atilde; thu h&uacute;t sự quan t&acirc;m của nhiều người nu&ocirc;i c&aacute; cảnh&nbsp;v&agrave; người đam m&ecirc; thủy sinh.</p>','/upload/imageKindFish/1745634584940_ca-loc-vay-rong-do.jpg','Red Barito','2025-04-26 09:29:45'),(11,'2025-04-26 22:48:56','<p><strong>C&aacute; l&oacute;c ph&aacute;o hoa đốm v&agrave;ng</strong>&nbsp;c&oacute; m&agrave;u x&aacute;m xanh nhạt tr&ecirc;n th&acirc;n, với nhiều đốm m&agrave;u v&agrave;ng hoặc cam. Đặc biệt, c&aacute; c&oacute; nhiều đốm đen nhỏ từ gốc đu&ocirc;i l&ecirc;n tới đầu.</p>\r <p>C&aacute; l&oacute;c ph&aacute;o hoa đốm v&agrave;ng (<strong><a href=\"https://en.wikipedia.org/wiki/Channa_pulchra\" target=\"_blank\" rel=\"nofollow noopener\">Channa PulChra</a></strong>) l&agrave; một d&ograve;ng c&aacute; l&oacute;c kiểng săn mồi. Năm 2007, d&ograve;ng c&aacute; n&agrave;y được đưa v&agrave;o nu&ocirc;i từ Myanmar v&agrave; nhanh ch&oacute;ng thu h&uacute;t sự quan t&acirc;m của nhiều người chơi c&aacute; l&oacute;c cảnh.</p>','/upload/imageKindFish/1745682535872_ca-loc-phao-hoa-dom-vang-channa-pulchra-1.jpg','Channa Pulchra','2025-04-26 22:52:14'),(12,'2025-04-26 23:22:55','<p><strong>C&aacute; m&uacute;n</strong>&nbsp;c&oacute; t&ecirc;n tiếng Anh l&agrave; <strong>Platy fish</strong> hay c&ograve;n gọi l&agrave; <strong>c&aacute; h&agrave; lan</strong>, <strong>c&aacute; hột lựu</strong> ch&uacute;ng thuộc bộ <em>Cyprinodontiformes (bộ c&aacute; s&oacute;c)</em>, họ <em>Poeciliidae (họ c&aacute; khổng tước)</em>; c&ugrave;ng d&ograve;ng họ với c&aacute; bảy m&agrave;u v&agrave; được ph&acirc;n bố trong tự nhi&ecirc;n, ch&uacute;ng được biết đến nhiều nhất ở Mexico v&agrave; trung Mỹ.</p>\r\n<p>C&aacute; c&oacute; k&iacute;ch thước tương đối b&eacute;, dễ sống, đặc biệt rất th&iacute;ch ăn những loại r&ecirc;u trong bể, v&igrave; vậy đ&acirc;y cũng l&agrave; một lo&agrave;i gi&uacute;p cho bể c&aacute; nh&agrave; bạn th&ecirc;m phần sạch v&agrave; đẹp hơn.</p>\r\n<p>Về ngoại h&igrave;nh, c&oacute; thể thấy rằng c&aacute; m&uacute;n đỏ c&oacute; h&igrave;nh dạng rất giống với c&aacute; đu&ocirc;i kiếm n&ecirc;n c&oacute; thể sẽ c&oacute; người nhầm lẫn. Nhưng so với c&aacute; đu&ocirc;i kiếm th&igrave; c&aacute; m&uacute;n c&oacute; chiều d&agrave;i ngắn hơn v&agrave; đặc biệt đu&ocirc;i của ch&uacute;ng cũng kh&ocirc;ng d&agrave;i nhọn được như c&aacute; đu&ocirc;i kiếm.</p>','/upload/imageKindFish/1751445260749_ca-mun-hat-luu.png','Cá Mún','2025-07-02 15:34:21'),(13,'2025-04-26 23:26:51','<p><strong>C&aacute; sặc gấm</strong> hay c&ograve;n gọi l&agrave; <strong>c&aacute; vạn long</strong> <em>(t&ecirc;n khoa học: Trichogaster lalius)</em>, l&agrave; một trong những lo&agrave;i c&aacute; cảnh phổ biến v&agrave; được ưa chuộng nhất trong giới chơi thủy sinh. Với m&agrave;u sắc rực rỡ v&agrave; h&igrave;nh d&aacute;ng độc đ&aacute;o, c&aacute; sặc gấm&nbsp;kh&ocirc;ng chỉ thu h&uacute;t &aacute;nh nh&igrave;n m&agrave; c&ograve;n dễ chăm s&oacute;c, l&agrave;m cho ch&uacute;ng trở th&agrave;nh lựa chọn l&yacute; tưởng cho nhiều người y&ecirc;u th&iacute;ch c&aacute; cảnh.</p>\r\n<p>C&aacute; sặc gấm xuất ph&aacute;t chủ yếu từ c&aacute;c v&ugrave;ng đất của Nam &Aacute; như Ấn Độ, Pakistan v&agrave; Bangladesh, ch&uacute;ng thuộc về họ Belontiidae, trong đ&oacute; c&oacute; nhiều lo&agrave;i c&aacute; nổi tiếng kh&aacute;c như c&aacute; betta&nbsp;.Những nơi n&agrave;y thường c&oacute; d&ograve;ng nước tĩnh hoặc chảy nhẹ, rất th&iacute;ch hợp cho sự ph&aacute;t triển của lo&agrave;i c&aacute; n&agrave;y.</p>','/upload/imageKindFish/1745684810636_ca-sac-gam.jpg','Cá Sặc Gấm','2025-04-26 23:26:51');
/*!40000 ALTER TABLE `kind_fish` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-11 12:07:36
