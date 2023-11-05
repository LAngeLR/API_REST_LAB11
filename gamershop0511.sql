-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: gameshop4
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `distribuidoras`
--

DROP TABLE IF EXISTS `distribuidoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `distribuidoras` (
  `iddistribuidora` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `fundacion` int NOT NULL,
  `web` varchar(200) DEFAULT NULL,
  `idsede` int DEFAULT NULL,
  PRIMARY KEY (`iddistribuidora`),
  KEY `FK_distribuidoras_1` (`idsede`),
  CONSTRAINT `FK_distribuidoras_1` FOREIGN KEY (`idsede`) REFERENCES `paises` (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuidoras`
--

LOCK TABLES `distribuidoras` WRITE;
/*!40000 ALTER TABLE `distribuidoras` DISABLE KEYS */;
INSERT INTO `distribuidoras` VALUES (1,'Nintendo','Es una empresa de entretenimiento dedicada a la investigación y desarrollo, producción y distribución de software y hardware de videojuegos, y juegos de cartas, con sede en Kioto, Japón.',1879,'http://nintendo.com/',114),(2,'Electronic Arts','Es una empresa estadounidense desarrolladora y distribuidora de videojuegos para ordenador y videoconsolas fundada por Trip Hawkins',1982,'https://www.ea.com/es-es',75),(3,'Sony Interactive Entertainment','Es una empresa multinacional dedicada a los videojuegos y subsidiaria de Sony Corporation. ',1993,'http://www.sie.com/',114),(4,'Activision','Es una empresa de videojuegos estadounidense. Fue el primer desarrollador y distribuidor independiente de este tipo de juegos, fundado el 1 de octubre de 1979',2008,'http://www.activision.com',75),(5,'Ubisoft','Es una compañía francesa desarrolladora y distribuidora de videojuegos, fundada en 1986 en Carentoir, en Bretaña.',1986,'https://www.ubisoft.com/',82),(6,'Bandai Namco Entertainment','Es una empresa japonesa de desarrollo y publicación de videojuegos.',2006,'https://www.bandainamcoent.com/es/',114);
/*!40000 ALTER TABLE `distribuidoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editoras`
--

DROP TABLE IF EXISTS `editoras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editoras` (
  `ideditora` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ideditora`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editoras`
--

LOCK TABLES `editoras` WRITE;
/*!40000 ALTER TABLE `editoras` DISABLE KEYS */;
INSERT INTO `editoras` VALUES (1,'Nintendo','Se ha dedicado a la producción de software y hardware para videojuegos, creciendo progresivamente hasta convertirse en la compañía más exitosa en la industria de los videojuegos.'),(2,'Sony Corporation','Es una de las empresas más grandes del mundo, de origen japonés y uno de los fabricantes líder en la electrónica de consumo, el audio y el vídeo profesional, los videojuegos.'),(3,'Take-Two Interactive','Es una empresa estadounidense desarrolladora y distribuidora de videojuegos. Su base central está en Nueva York, Estados Unidos, con una base internacional en Ginebra, Suiza.'),(4,'Infinity Ward','Es un estudio desarrollador de videojuegos con sede en Encino, California');
/*!40000 ALTER TABLE `editoras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `idfactura` int NOT NULL AUTO_INCREMENT,
  `fechaEnvio` varchar(50) NOT NULL,
  `tarjeta` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `codigoVerificacion` varchar(5) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `direccion` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `monto` float NOT NULL,
  `idjuegosxusuario` int DEFAULT NULL,
  PRIMARY KEY (`idfactura`),
  KEY `fk_factura_juegosxusuario1` (`idjuegosxusuario`),
  CONSTRAINT `fk_factura_juegosxusuario1` FOREIGN KEY (`idjuegosxusuario`) REFERENCES `juegosxusuario` (`idjuegosxusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (1,'20/12/2014','1234567891234567891','123','Gral Miller 2020',219.8,1),(2,'20/12/2014','2234567891234567891','234','Arequipa 2300',200,2),(3,'20/12/2014','3234567891234567891','345','Principal 500',200,3),(4,'20/12/2014','3234567891234567891','456','Javier Prado 1987',200,4);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `generos` (
  `idgenero` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idgenero`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (1,'Acción ','Videojuegos de acción con lucha y disparos.'),(2,'Simulación ','Este género se caracteriza en marcar un aspecto de la vida real, llevado a un juego, donde se tiene total control de lo que ocurre.  '),(3,'Agilidad ','El objetivo aquí es resolver ejercicios con dificultad progresiva para desarrollar la habilidad mental.'),(4,'Acción-Aventura','Combina elementos del género aventura con elementos del género acción'),(5,'RPG','Es un género de videojuegos donde el jugador controla las acciones de un personaje inmerso en algún mundo detallado.');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegos`
--

DROP TABLE IF EXISTS `juegos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegos` (
  `idjuego` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(448) DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `image` varchar(400) DEFAULT NULL,
  `idgenero` int DEFAULT NULL,
  `idplataforma` int DEFAULT NULL,
  `ideditora` int DEFAULT NULL,
  `iddistribuidora` int DEFAULT NULL,
  PRIMARY KEY (`idjuego`),
  KEY `FK_juegos_1` (`iddistribuidora`),
  KEY `FK_juegos_2` (`ideditora`),
  KEY `FK_juegos_3` (`idgenero`),
  KEY `FK_juegos_4` (`idplataforma`),
  CONSTRAINT `FK_juegos_1` FOREIGN KEY (`iddistribuidora`) REFERENCES `distribuidoras` (`iddistribuidora`),
  CONSTRAINT `FK_juegos_2` FOREIGN KEY (`ideditora`) REFERENCES `editoras` (`ideditora`),
  CONSTRAINT `FK_juegos_3` FOREIGN KEY (`idgenero`) REFERENCES `generos` (`idgenero`),
  CONSTRAINT `FK_juegos_4` FOREIGN KEY (`idplataforma`) REFERENCES `plataformas` (`idplataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegos`
--

LOCK TABLES `juegos` WRITE;
/*!40000 ALTER TABLE `juegos` DISABLE KEYS */;
INSERT INTO `juegos` VALUES (1,'FIFA 21','El juego es la 28.ª entrega de la serie de videojuegos de FIFA. Es el primer videojuego de la serie FIFA para PlayStation 5 y Xbox Series X|S.',149.9,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRec6M4WbTPy03DFswDEaaYYMB46DU7IJ962Q&usqp=CAU',1,5,2,2),(2,'Call of Duty : Black Ops II','Es el segundo juego de la saga Call of Duty en presentar la futura tecnología de la guerra, y el primero en presentar historias de ramificación impulsados por elección del jugador, así como la selección de armas antes de comenzar las misiones del modo historia.',70,'https://store-images.s-microsoft.com/image/apps.6920.64998372950229933.77155980-5b6a-4296-88e2-959fda591dee.9f5a56f8-8520-4a1e-aa4e-ab5b607e5fb1?mode=scale&q=90&h=225&w=150',1,3,4,4),(3,'Assassin Creed Valhalla','Es una serie de videojuegos de acción-aventura histórica con tintes de ciencia ficción, ambientada en la era de los Vikingos.',100,'https://sm.ign.com/t/ign_es/screenshot/a/assassins-/assassins-creed-valhalla-box-art_rua2.1080.jpg',1,2,4,3),(4,'Resident Evil 6','Es un videojuego de acción en tercera persona del género dramatic horror publicado el 2 de octubre de 2012 por la empresa Capcom.',120,'https://media.vandal.net/m/15505/20121217145531_1.jpg',1,3,3,4),(5,'The Legend of Zelda Breath of the Wild','Es un videojuego de acción-aventura de 2017 de la serie The Legend of Zelda. El jugador controla a Link, que despierta en un mundo postapocalíptico después de estar cien años durmiendo para derrotar a Ganon y salvar al reino de Hyrule. ',200,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQnkWD6WJC3KwfqIg3d7EQKPKxzatyX6gnKLw&usqp=CAU',4,4,1,1),(6,'Xenoblade Chronicles: Definitive Edition','Versión remasterizada llamada Xenoblade Chronicles: Definitive Edition para Nintendo Switch con fecha tentativa 2020. ',250,'https://media.vandal.net/m/77170/xenoblade-chronicles-definitive-edition-202032711321150_3.jpg',5,4,NULL,1),(7,'Leyendas Pokémon: Arceus','Este videojuego de rol, que se desarrollará en Sinnoh, nos llevará a una época pasada de inspiración feudal, apostando por el mundo abierto tridimensional e incorporando nuevas mecánicas de captura y combate que buscan renovar para siempre la saga de cara al futuro.',160,'https://media.vandal.net/m/96931/leyendas-pokemon-arceus-202152616392725_1.jpg',5,4,NULL,1),(8,'Horizon Forbidden West','Narra una épica historia de ciencia ficción post-apocalíptica en la que la humanidad intenta sobrevivir tras la aparición de una serie de máquinas y robots que han sustituido a los seres vivos como especie dominante en la Tierra.',210,'https://as01.epimg.net/meristation/imagenes/2020/06/22/game_cover/320469661592861003.jpg',5,5,NULL,3);
/*!40000 ALTER TABLE `juegos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegosxusuario`
--

DROP TABLE IF EXISTS `juegosxusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `juegosxusuario` (
  `idjuegosxusuario` int NOT NULL AUTO_INCREMENT,
  `idjuego` int NOT NULL,
  `idusuario` int NOT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`idjuegosxusuario`),
  KEY `FK_juegosxusuario_2` (`idjuego`),
  KEY `fk_juegosxusuario_usuarios1` (`idusuario`),
  CONSTRAINT `FK_juegosxusuario_2` FOREIGN KEY (`idjuego`) REFERENCES `juegos` (`idjuego`),
  CONSTRAINT `fk_juegosxusuario_usuarios1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegosxusuario`
--

LOCK TABLES `juegosxusuario` WRITE;
/*!40000 ALTER TABLE `juegosxusuario` DISABLE KEYS */;
INSERT INTO `juegosxusuario` VALUES (1,1,1,2),(2,4,1,1),(3,2,3,1),(4,3,4,1),(5,8,5,2),(6,5,6,1),(7,6,6,1),(8,7,6,1);
/*!40000 ALTER TABLE `juegosxusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paises` (
  `idpais` int NOT NULL AUTO_INCREMENT,
  `iso` char(2) DEFAULT NULL,
  `nombre` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES (1,'AF','Afganistán'),(2,'AX','Islas Gland'),(3,'AL','Albania'),(4,'DE','Alemania'),(5,'AD','Andorra'),(6,'AO','Angola'),(7,'AI','Anguilla'),(8,'AQ','Antártida'),(9,'AG','Antigua y Barbuda'),(10,'AN','Antillas Holandesas'),(11,'SA','Arabia Saudí'),(12,'DZ','Argelia'),(13,'AR','Argentina'),(14,'AM','Armenia'),(15,'AW','Aruba'),(16,'AU','Australia'),(17,'AT','Austria'),(18,'AZ','Azerbaiyán'),(19,'BS','Bahamas'),(20,'BH','Bahréin'),(21,'BD','Bangladesh'),(22,'BB','Barbados'),(23,'BY','Bielorrusia'),(24,'BE','Bélgica'),(25,'BZ','Belice'),(26,'BJ','Benin'),(27,'BM','Bermudas'),(28,'BT','Bhután'),(29,'BO','Bolivia'),(30,'BA','Bosnia y Herzegovina'),(31,'BW','Botsuana'),(32,'BV','Isla Bouvet'),(33,'BR','Brasil'),(34,'BN','Brunéi'),(35,'BG','Bulgaria'),(36,'BF','Burkina Faso'),(37,'BI','Burundi'),(38,'CV','Cabo Verde'),(39,'KY','Islas Caimán'),(40,'KH','Camboya'),(41,'CM','Camerún'),(42,'CA','Canadá'),(43,'CF','República Centroafricana'),(44,'TD','Chad'),(45,'CZ','República Checa'),(46,'CL','Chile'),(47,'CN','China'),(48,'CY','Chipre'),(49,'CX','Isla de Navidad'),(50,'VA','Ciudad del Vaticano'),(51,'CC','Islas Cocos'),(52,'CO','Colombia'),(53,'KM','Comoras'),(54,'CD','República Democrática del Congo'),(55,'CG','Congo'),(56,'CK','Islas Cook'),(57,'KP','Corea del Norte'),(58,'KR','Corea del Sur'),(59,'CI','Costa de Marfil'),(60,'CR','Costa Rica'),(61,'HR','Croacia'),(62,'CU','Cuba'),(63,'DK','Dinamarca'),(64,'DM','Dominica'),(65,'DO','República Dominicana'),(66,'EC','Ecuador'),(67,'EG','Egipto'),(68,'SV','El Salvador'),(69,'AE','Emiratos Árabes Unidos'),(70,'ER','Eritrea'),(71,'SK','Eslovaquia'),(72,'SI','Eslovenia'),(73,'ES','España'),(74,'UM','Islas ultramarinas de Estados Unidos'),(75,'US','Estados Unidos'),(76,'EE','Estonia'),(77,'ET','Etiopía'),(78,'FO','Islas Feroe'),(79,'PH','Filipinas'),(80,'FI','Finlandia'),(81,'FJ','Fiyi'),(82,'FR','Francia'),(83,'GA','Gabón'),(84,'GM','Gambia'),(85,'GE','Georgia'),(86,'GS','Islas Georgias del Sur y Sandwich del Sur'),(87,'GH','Ghana'),(88,'GI','Gibraltar'),(89,'GD','Granada'),(90,'GR','Grecia'),(91,'GL','Groenlandia'),(92,'GP','Guadalupe'),(93,'GU','Guam'),(94,'GT','Guatemala'),(95,'GF','Guayana Francesa'),(96,'GN','Guinea'),(97,'GQ','Guinea Ecuatorial'),(98,'GW','Guinea-Bissau'),(99,'GY','Guyana'),(100,'HT','Haití'),(101,'HM','Islas Heard y McDonald'),(102,'HN','Honduras'),(103,'HK','Hong Kong'),(104,'HU','Hungría'),(105,'IN','India'),(106,'ID','Indonesia'),(107,'IR','Irán'),(108,'IQ','Iraq'),(109,'IE','Irlanda'),(110,'IS','Islandia'),(111,'IL','Israel'),(112,'IT','Italia'),(113,'JM','Jamaica'),(114,'JP','Japón'),(115,'JO','Jordania'),(116,'KZ','Kazajstán'),(117,'KE','Kenia'),(118,'KG','Kirguistán'),(119,'KI','Kiribati'),(120,'KW','Kuwait'),(121,'LA','Laos'),(122,'LS','Lesotho'),(123,'LV','Letonia'),(124,'LB','Líbano'),(125,'LR','Liberia'),(126,'LY','Libia'),(127,'LI','Liechtenstein'),(128,'LT','Lituania'),(129,'LU','Luxemburgo'),(130,'MO','Macao'),(131,'MK','ARY Macedonia'),(132,'MG','Madagascar'),(133,'MY','Malasia'),(134,'MW','Malawi'),(135,'MV','Maldivas'),(136,'ML','Malí'),(137,'MT','Malta'),(138,'FK','Islas Malvinas'),(139,'MP','Islas Marianas del Norte'),(140,'MA','Marruecos'),(141,'MH','Islas Marshall'),(142,'MQ','Martinica'),(143,'MU','Mauricio'),(144,'MR','Mauritania'),(145,'YT','Mayotte'),(146,'MX','México'),(147,'FM','Micronesia'),(148,'MD','Moldavia'),(149,'MC','Mónaco'),(150,'MN','Mongolia'),(151,'MS','Montserrat'),(152,'MZ','Mozambique'),(153,'MM','Myanmar'),(154,'NA','Namibia'),(155,'NR','Nauru'),(156,'NP','Nepal'),(157,'NI','Nicaragua'),(158,'NE','Níger'),(159,'NG','Nigeria'),(160,'NU','Niue'),(161,'NF','Isla Norfolk'),(162,'NO','Noruega'),(163,'NC','Nueva Caledonia'),(164,'NZ','Nueva Zelanda'),(165,'OM','Omán'),(166,'NL','Países Bajos'),(167,'PK','Pakistán'),(168,'PW','Palau'),(169,'PS','Palestina'),(170,'PA','Panamá'),(171,'PG','Papúa Nueva Guinea'),(172,'PY','Paraguay'),(173,'PE','Perú'),(174,'PN','Islas Pitcairn'),(175,'PF','Polinesia Francesa'),(176,'PL','Polonia'),(177,'PT','Portugal'),(178,'PR','Puerto Rico'),(179,'QA','Qatar'),(180,'GB','Reino Unido'),(181,'RE','Reunión'),(182,'RW','Ruanda'),(183,'RO','Rumania'),(184,'RU','Rusia'),(185,'EH','Sahara Occidental'),(186,'SB','Islas Salomón'),(187,'WS','Samoa'),(188,'AS','Samoa Americana'),(189,'KN','San Cristóbal y Nevis'),(190,'SM','San Marino'),(191,'PM','San Pedro y Miquelón'),(192,'VC','San Vicente y las Granadinas'),(193,'SH','Santa Helena'),(194,'LC','Santa Lucía'),(195,'ST','Santo Tomé y Príncipe'),(196,'SN','Senegal'),(197,'CS','Serbia y Montenegro'),(198,'SC','Seychelles'),(199,'SL','Sierra Leona'),(200,'SG','Singapur'),(201,'SY','Siria'),(202,'SO','Somalia'),(203,'LK','Sri Lanka'),(204,'SZ','Suazilandia'),(205,'ZA','Sudáfrica'),(206,'SD','Sudán'),(207,'SE','Suecia'),(208,'CH','Suiza'),(209,'SR','Surinam'),(210,'SJ','Svalbard y Jan Mayen'),(211,'TH','Tailandia'),(212,'TW','Taiwán'),(213,'TZ','Tanzania'),(214,'TJ','Tayikistán'),(215,'IO','Territorio Británico del Océano Índico'),(216,'TF','Territorios Australes Franceses'),(217,'TL','Timor Oriental'),(218,'TG','Togo'),(219,'TK','Tokelau'),(220,'TO','Tonga'),(221,'TT','Trinidad y Tobago'),(222,'TN','Túnez'),(223,'TC','Islas Turcas y Caicos'),(224,'TM','Turkmenistán'),(225,'TR','Turquía'),(226,'TV','Tuvalu'),(227,'UA','Ucrania'),(228,'UG','Uganda'),(229,'UY','Uruguay'),(230,'UZ','Uzbekistán'),(231,'VU','Vanuatu'),(232,'VE','Venezuela'),(233,'VN','Vietnam'),(234,'VG','Islas Vírgenes Británicas'),(235,'VI','Islas Vírgenes de los Estados Unidos'),(236,'WF','Wallis y Futuna'),(237,'YE','Yemen'),(238,'DJ','Yibuti'),(239,'ZM','Zambia'),(240,'ZW','Zimbabue');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataformas`
--

DROP TABLE IF EXISTS `plataformas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plataformas` (
  `idplataforma` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idplataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataformas`
--

LOCK TABLES `plataformas` WRITE;
/*!40000 ALTER TABLE `plataformas` DISABLE KEYS */;
INSERT INTO `plataformas` VALUES (1,'Wii','Es una videoconsola producida por Nintendo y estrenada el 19 de noviembre de 2006 en Norteamérica y el 8 de diciembre del mismo año en Europa.'),(2,'PlayStation 4','Es la cuarta videoconsola de sobremesa fabricada por Sony Computer Entertainment y la sucesora de la PlayStation 3 como parte de la marca PlayStation.'),(3,'Xbox 360','Xbox 360 es la segunda videoconsola de sobremesa de la marca Xbox producida por Microsoft. Fue desarrollada en colaboración con IBM y ATI (AMD) .'),(4,'Nintendo Switch','Es la novena consola de videojuegos principal desarrollada por Nintendo.  Se dio a conocer en octubre de 2016 y fue lanzada mundialmente el 3 de marzo de 2017.'),(5,'PlayStation 5','Es una consola de videojuegos de sobremesa desarrollada por Sony Interactive Entertainment. Se lanzó el 12 de noviembre de 2020 en Norteamérica, Japón, Australasia y Corea del Sur.'),(10,'Xbox Serie X ','Videoconsola de sobremesa desarrollada por Microsoft y la cuarta generación de la familia de consolas Xbox. Se lanzó el 10 de noviembre de 2020'),(17,'Nintendo Switch Lite','Versión remasterizada llamada Xenoblade Chronicles: Definitive Edition para Nintendo Switch con fecha tentativa 2020. ');
/*!40000 ALTER TABLE `plataformas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(45) DEFAULT NULL,
  `nombres` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `password` varchar(65) DEFAULT NULL,
  `autorizacion` varchar(20) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `numerocuenta` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Barturen','Jose','jbarturen@pucp.pe','$2a$10$jzeb5RKOyW2DrnarFZfsJ.HQVgdERtf404H7TX1vVcJ/hZEI3Wkoy','USER',_binary '','111222333','80164295093852716312215'),(2,'Larios','Enrique','elarios@pucp.pe','$2a$10$jzeb5RKOyW2DrnarFZfsJ.HQVgdERtf404H7TX1vVcJ/hZEI3Wkoy','ADMIN',_binary '','222333444','39901760581240142789035'),(3,'Retes','Manuel','mretes@pucp.pe','$2a$10$jzeb5RKOyW2DrnarFZfsJ.HQVgdERtf404H7TX1vVcJ/hZEI3Wkoy','USER',_binary '','333444555','75418936074521638503102'),(4,'Perez','Oscar','operez@pucp.pe','$2a$10$jzeb5RKOyW2DrnarFZfsJ.HQVgdERtf404H7TX1vVcJ/hZEI3Wkoy','USER',_binary '','444555666','62493507192456159708342'),(5,'Marquina','Magda','mmarquina@pucp.pe','$2a$10$jzeb5RKOyW2DrnarFZfsJ.HQVgdERtf404H7TX1vVcJ/hZEI3Wkoy','USER',_binary '',NULL,NULL),(6,'Llanos','Susana','sllanos@pucp.pe','$2a$10$jzeb5RKOyW2DrnarFZfsJ.HQVgdERtf404H7TX1vVcJ/hZEI3Wkoy','USER',_binary '',NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-05  3:51:52
