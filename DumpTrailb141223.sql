-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: trailb
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aula`
--

DROP TABLE IF EXISTS `aula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aula` (
  `id` bigint NOT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aula`
--

LOCK TABLES `aula` WRITE;
/*!40000 ALTER TABLE `aula` DISABLE KEYS */;
INSERT INTO `aula` VALUES (10,'Aula 3 de seguran├ºa portuaria','string'),(58,'string','string'),(59,'string','string'),(60,'Aula 1 - Atribui├º├Áes da CIPA','https://www.youtube.com/watch?v=mY93A4GZ0bM'),(61,'Aula 2','https://www.youtube.com/watch?v=T_wPyyORADk'),(62,'Aula 3','https://www.youtube.com/watch?v=El13N7YfveQ'),(63,'Aula 4','https://www.youtube.com/watch?v=YMVgdx0JKEw'),(64,'Programa├º├úo Java no lado do servidor','https://www.youtube.com/watch?v=7VgWAxEkv_U&list=PLbEOwbQR9lqz9AnwhrrOLz9cz1-TxoiUg'),(65,' Setup do ambiente de desenvolvimento (JDK-Eclipse-Tomcat-MySQL)','https://www.youtube.com/watch?v=dVRYwQc8uYc&list=PLbEOwbQR9lqz9AnwhrrOLz9cz1-TxoiUg&index=3'),(66,' Introdu├º├úo ao Servlet - Hello World - #3','https://www.youtube.com/watch?v=dIKMwVNqS-I&list=PLbEOwbQR9lqz9AnwhrrOLz9cz1-TxoiUg'),(67,'Projeto Java WEB - Agenda de contatos','https://www.youtube.com/watch?v=xzY94OyZK8c&list=PLbEOwbQR9lqz9AnwhrrOLz9cz1-TxoiUg'),(68,'Tipos de Navio','https://www.youtube.com/watch?v=boFg0-GAxiQ&list=PLP_05TDwvi2nBOphuJ3_KH6ykgu-qI_Dt'),(69,' Nr 30','https://www.youtube.com/watch?v=jl0ZMXeJhOg&list=PLP_05TDwvi2nBOphuJ3_KH6ykgu-qI_Dt'),(70,'SEGURAN├çA NA NAVEGA├ç├âO MAR├ìTIMA','https://www.youtube.com/watch?v=K4Jv54zE_vg&list=PLP_05TDwvi2nBOphuJ3_KH6ykgu-qI_Dt'),(71,'Cabos De A├ºo','https://www.youtube.com/watch?v=pNj_AYh0kjc&list=PLP_05TDwvi2nBOphuJ3_KH6ykgu-qI_Dt'),(72,'string','string'),(74,'string','string'),(76,'string','string'),(78,'string','string'),(81,'string','string'),(83,'string','string'),(85,'string','string'),(87,'string','string'),(89,'string','string'),(91,'string','string'),(93,'string','string'),(95,'string','string'),(99,'string','string');
/*!40000 ALTER TABLE `aula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificado`
--

DROP TABLE IF EXISTS `certificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificado` (
  `id` bigint NOT NULL,
  `data_emissao` date DEFAULT NULL,
  `curso_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `nota` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKklajj9bwprcorx6koygdtcry8` (`curso_id`),
  KEY `FK1u799gx24iq9bkkxeifw5k5yy` (`usuario_id`),
  CONSTRAINT `FK1u799gx24iq9bkkxeifw5k5yy` FOREIGN KEY (`usuario_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FKklajj9bwprcorx6koygdtcry8` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificado`
--

LOCK TABLES `certificado` WRITE;
/*!40000 ALTER TABLE `certificado` DISABLE KEYS */;
INSERT INTO `certificado` VALUES (137,'2023-12-14',3,7,6),(140,'2023-12-14',12,7,10);
/*!40000 ALTER TABLE `certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` bigint NOT NULL,
  `adm` bit(1) NOT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `data_nascimento` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nome` varchar(180) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `classificacao` int DEFAULT NULL,
  `setor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7wflw78ibh162cmq12ii6ffly` (`cpf`),
  UNIQUE KEY `UK_1c96wv36rk2hwui7qhjks3mvg` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,_binary '\0','09594773499','2023-11-25','strin@g','string','string','string',0,'string'),(4,_binary '','99944926329','2023-11-27','string@calabreso.com','string','string','string',0,'string'),(6,_binary '','62506683496','2023-11-27','string@calabresso.com','string','string','string',0,'string'),(7,_binary '\0','57571667895','2023-11-28','joaoaraujomn@gmail.com','http://res.cloudinary.com/dbpsqttrs/image/upload/v1702297332/photo_ejobaq.jpg','joao araujo melo neto','$2a$10$tGSWNjVkGeAJElgUPAyv3uDgtipwVXaMeTFOsRaNsmFUcBMyNRvN2',175,'Pr├ítico de barra'),(28,_binary '','11932479422','2023-11-29','pxleonarddo@gmail.com','string','leopx','aDceu52b',0,'string'),(48,_binary '','05613295409','2023-12-08','carolina@larre.com.br','string','string','$2a$10$OOpvMv2/gS36z.tYQVnCveYMDQV57hUr3S3juEyA.mdD32S4jKAWq',0,'string'),(49,_binary '\0','41339428091','2023-02-07','Celular@celular.com',NULL,'Teste pelo celular','$2a$10$FDG92.iilk4vXs76nduvheTrzM.ZqA1/DWuyHteaSpFjwNeZCX/He',0,'Porto'),(50,_binary '\0','15021203487','1952-11-09','carol.larre@gmail.com','http://res.cloudinary.com/dbpsqttrs/image/upload/v1702292358/photo_oo4slu.jpg','Carolina larre','$2a$10$0o4XLjJPmI/o6XcT3i2FTe4iRHYQk9gsYfusJ19Koz26lrMm60s.O',0,'Medica'),(124,_binary '\0','13112914074','1986-07-18','carol.larre5@gmail.com','http://res.cloudinary.com/dbpsqttrs/image/upload/v1702464323/photo_p6pzgv.jpg','Carol','$2a$10$9AyMDdHcVElSHX.OFRR9luG5zQ8m.z3upK6nwCO8NvoPwwwUODfVq',0,'Medica');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes_cursos`
--

DROP TABLE IF EXISTS `clientes_cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes_cursos` (
  `usuario_id` bigint NOT NULL,
  `cursos_id` bigint NOT NULL,
  KEY `FKk38fslt3006udlhuwamxpsmc9` (`cursos_id`),
  KEY `FKiiae6fkb2o3mly0fjgfadnfsy` (`usuario_id`),
  CONSTRAINT `FKiiae6fkb2o3mly0fjgfadnfsy` FOREIGN KEY (`usuario_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FKk38fslt3006udlhuwamxpsmc9` FOREIGN KEY (`cursos_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes_cursos`
--

LOCK TABLES `clientes_cursos` WRITE;
/*!40000 ALTER TABLE `clientes_cursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes_cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso` (
  `id` bigint NOT NULL,
  `area` varchar(255) DEFAULT NULL,
  `carga_horaria` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `nome` varchar(180) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bdhliwglt8i7q1v80fb95vea9` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (3,'Tecnologia','60H','Curso de desenvolvimento web para solu├º├Áes portuarias.','Desenvolvimento Web','https://img.freepik.com/vetores-gratis/banner-de-desenvolvimento-de-sites_33099-1687.jpg'),(12,'seguran├ºa portuaria','60H','Curso de seguran├ºa portuaria para profissionais do porto de suape, onde ser├í abordada as principais praticas de seguran├ºa','Seguran├ºa no porto de suape','https://www.aen.pr.gov.br/sites/default/arquivos_restritos/files/imagem/2021-11/img_3278a.jpg'),(14,'seguran├ºa portuaria','60H','Curso da CIPA nivel 2','CIPA em Ambiente Portu├írio','https://ead.medpon.com.br/files/1642451320imagemcursocipagraudersco2.jpg');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_aulas`
--

DROP TABLE IF EXISTS `curso_aulas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_aulas` (
  `curso_id` bigint NOT NULL,
  `aulas_id` bigint NOT NULL,
  UNIQUE KEY `UK_87dbklsnewj1xc1js72u9ed13` (`aulas_id`),
  KEY `FK62mjoebgbq5v8wxpv01bxdv2b` (`curso_id`),
  CONSTRAINT `FK62mjoebgbq5v8wxpv01bxdv2b` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `FKge49w9jr85f4h5cuewx4vi4km` FOREIGN KEY (`aulas_id`) REFERENCES `aula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_aulas`
--

LOCK TABLES `curso_aulas` WRITE;
/*!40000 ALTER TABLE `curso_aulas` DISABLE KEYS */;
INSERT INTO `curso_aulas` VALUES (3,64),(3,65),(3,66),(3,67),(12,68),(12,69),(12,70),(12,71),(14,60),(14,61),(14,62),(14,63);
/*!40000 ALTER TABLE `curso_aulas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (141);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pergunta`
--

DROP TABLE IF EXISTS `pergunta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pergunta` (
  `id` bigint NOT NULL,
  `alternativa_correta` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `curso_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK19pu616fxwtjjnmy3ql42mhxl` (`curso_id`),
  CONSTRAINT `FK19pu616fxwtjjnmy3ql42mhxl` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pergunta`
--

LOCK TABLES `pergunta` WRITE;
/*!40000 ALTER TABLE `pergunta` DISABLE KEYS */;
INSERT INTO `pergunta` VALUES (15,'Investiga├º├úo de Acidentes','O que a CIPA realiza para entender as causas de incidentes no local de trabalho?',14),(16,'Inspe├º├Áes de Seguran├ºa','Que atividade a CIPA realiza para identificar poss├¡veis riscos no ambiente de trabalho?',14),(17,'Estat├¡sticas de Acidentes','O que a CIPA analisa para melhorar as condi├º├Áes de seguran├ºa no trabalho?',14),(18,'Comunica├º├úo Interna','Qual aspecto a CIPA prioriza para garantir a dissemina├º├úo eficaz de informa├º├Áes sobre seguran├ºa?',14),(19,'Ergonomia','O que a CIPA considera ao avaliar as condi├º├Áes f├¡sicas dos postos de trabalho?',14),(73,'Spring','Qual dos seguintes frameworks Java ├® comumente usado para o desenvolvimento de aplicativos web?',3),(75,'<h:form>','Em JavaServer Faces (JSF), qual componente ├® usado para criar formul├írios web?',3),(77,'Servlet API','Qual API Java ├® frequentemente usada para manipular solicita├º├Áes e respostas HTTP em aplicativos web?',3),(79,'Model-View-Controller','O que o acr├┤nimo \'MVC\' significa no contexto de desenvolvimento web?',3),(82,'GetMapping','Em uma aplica├º├úo Spring, qual anota├º├úo ├® usada para indicar que um m├®todo responde a solicita├º├Áes HTTP GET?',3),(84,'GetMapping','Em uma aplica├º├úo Spring, qual anota├º├úo ├® usada para indicar que um m├®todo responde a solicita├º├Áes HTTP GET?',3),(86,'@GetMapping','Em uma aplica├º├úo Spring, qual anota├º├úo ├® usada para indicar que um m├®todo responde a solicita├º├Áes HTTP GET?',3),(88,'JPA (Java Persistence API)','Qual tecnologia Java ├® comumente usada para implementar a l├│gica de persist├¬ncia em aplicativos web?',3),(90,'pom.xml','Qual arquivo de configura├º├úo ├® comumente usado em projetos Java para declarar depend├¬ncias e configura├º├Áes do projeto?',3),(92,'application.properties','Em um ambiente Spring Boot, qual arquivo ├® usado para definir as propriedades de configura├º├úo do aplicativo?',3),(94,'<include>','Em JavaServer Pages (JSP), qual tag ├® usada para incluir o conte├║do de outro arquivo JSP em uma p├ígina?',3),(96,'JTA (Java Transaction API)','Qual API Java ├® usada para manipular transa├º├Áes em um contexto de desenvolvimento web?',3),(108,'Controle de Acesso','Qual aspecto ├® fundamental para garantir a seguran├ºa portu├íria?',12),(109,'Inspe├º├úo de Cont├¬ineres','Qual atividade ├® crucial para prevenir o contrabando em portos?',12),(110,'Materiais T├│xicos','Quais tipos de carga representam riscos ├á sa├║de dos trabalhadores portu├írios?',12),(111,'Plano de Conting├¬ncia','O que ├® essencial para lidar com emerg├¬ncias no ambiente portu├írio?',12),(112,'C├ómeras de Vigil├óncia','Qual tecnologia ├® comumente usada para monitorar ├íreas portu├írias?',12),(113,'Sinaliza├º├úo Mar├¡tima','O que auxilia na navega├º├úo segura dentro do porto?',12),(114,'Hidrantes de Inc├¬ndio','Qual dispositivo ├® fundamental para o combate a inc├¬ndios em portos?',12),(115,'Manifesto de Carga','O que cont├®m informa├º├Áes detalhadas sobre a carga transportada em um navio?',12),(116,'Seguran├ºa do Trabalho','Qual ├® um dos pilares fundamentais para o bom funcionamento de um porto?',12),(117,'Controle Aduaneiro','Qual processo ├® crucial para a fiscaliza├º├úo de mercadorias que entram e saem do porto?',12),(118,'Preven├º├úo de Acidentes','Qual ├® o principal objetivo da CIPA?',14),(119,'Equipamentos de Prote├º├úo Individual','O que a CIPA enfatiza para garantir a seguran├ºa dos trabalhadores?',14),(120,'Primeiros Socorros','O que a CIPA recomenda para lidar com emerg├¬ncias m├®dicas no local de trabalho?',14),(121,'Fiscaliza├º├úo de Seguran├ºa','Qual ├® a principal responsabilidade da CIPA no ambiente de trabalho?',14),(122,'Controle de Acesso','Qual medida a CIPA pode recomendar para garantir a seguran├ºa nas instala├º├Áes?',14);
/*!40000 ALTER TABLE `pergunta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pergunta_alternativa`
--

DROP TABLE IF EXISTS `pergunta_alternativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pergunta_alternativa` (
  `pergunta_id` bigint NOT NULL,
  `alternativa` varchar(255) DEFAULT NULL,
  KEY `FKn2cmyrjydi04f3myuestxjk3t` (`pergunta_id`),
  CONSTRAINT `FKn2cmyrjydi04f3myuestxjk3t` FOREIGN KEY (`pergunta_id`) REFERENCES `pergunta` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pergunta_alternativa`
--

LOCK TABLES `pergunta_alternativa` WRITE;
/*!40000 ALTER TABLE `pergunta_alternativa` DISABLE KEYS */;
INSERT INTO `pergunta_alternativa` VALUES (73,'Hibernate'),(73,'Spring'),(73,'JavaFX'),(73,'JavaBeans'),(75,'<html:form>'),(75,'<web:form>'),(75,'<h:form>'),(75,'<form:jsf>'),(77,'Servlet API'),(77,'JDBC API'),(77,'JavaFX API'),(77,'JPA API'),(79,'Model-View-Controller'),(79,'Model-View-Component'),(79,'Multi-Version-Control'),(79,'Markup-Validation-Code'),(82,'RequestMapping'),(82,'GetMapping'),(82,'RequestMethod'),(82,'RequestAction'),(84,'RequestMapping'),(84,'GetMapping'),(84,'RequestMethod'),(84,'@RequestAction'),(86,'@RequestMapping'),(86,'@GetMapping'),(86,'@RequestMethod'),(86,'@RequestAction'),(88,'JPA (Java Persistence API)'),(88,'JSP (JavaServer Pages)'),(88,'JMS (Java Message Service)'),(88,'JTA (Java Transaction API)'),(90,'package.json'),(90,'build.gradle'),(90,'pom.xml'),(90,'dependencies.cfg'),(92,'application.properties'),(92,'config.xml'),(92,'settings.ini'),(92,'app.config'),(94,'<import>'),(94,'<include>'),(94,'<merge>'),(94,'<content>'),(96,'JTA (Java Transaction API)'),(96,'JCA (Java Connector Architecture)'),(96,'JMS (Java Message Service)'),(96,'JPA (Java Persistence API)'),(108,'Controle de Acesso'),(108,'Vigil├óncia Mar├¡tima'),(108,'Combate a Inc├¬ndios'),(108,'Gest├úo Ambiental'),(109,'Manifesta├º├úo de Carga'),(109,'Inspe├º├úo de Cont├¬ineres'),(109,'Movimenta├º├úo de Carga'),(109,'Armazenamento de Carga'),(110,'Radia├º├úo Nuclear'),(110,'Gases Inflam├íveis'),(110,'Materiais T├│xicos'),(110,'Explosivos'),(111,'Plano de Conting├¬ncia'),(111,'Plano de Carga'),(111,'Plano de Manobra'),(111,'Plano de Risco'),(112,'Cercas El├®tricas'),(112,'C├ómeras de Vigil├óncia'),(112,'Luz de Per├¡metro'),(112,'Sensores de Movimento'),(113,'Sinaliza├º├úo Mar├¡tima'),(113,'Sinaliza├º├úo Sonora'),(113,'Sinaliza├º├úo Luminosa'),(113,'Sinaliza├º├úo de Tr├ífego'),(114,'Extintores de Inc├¬ndio'),(114,'Mangueiras de Inc├¬ndio'),(114,'Alarmes de Inc├¬ndio'),(114,'Hidrantes de Inc├¬ndio'),(115,'Certificado de Navega├º├úo'),(115,'Documento de Arquea├º├úo'),(115,'Manifesto de Carga'),(115,'Certificado Internacional de Carga'),(116,'Sustentabilidade Portu├íria'),(116,'Efici├¬ncia Operacional'),(116,'Inova├º├úo Tecnol├│gica'),(116,'Seguran├ºa do Trabalho'),(117,'Inspe├º├úo de Passageiros'),(117,'Controle Aduaneiro'),(117,'Controle de Peso'),(117,'Inspe├º├úo de Bagagem'),(118,'Preven├º├úo de Acidentes'),(118,'Seguran├ºa Alimentar'),(118,'Sustentabilidade'),(118,'Atendimento ao Cliente'),(119,'Equipamentos de Prote├º├úo Individual'),(119,'Controle de Qualidade'),(119,'Estoque de Produtos'),(119,'Higiene no Ambiente de Trabalho'),(120,'Primeiros Socorros'),(120,'Hor├írio de Trabalho'),(120,'Benef├¡cios Corporativos'),(120,'Programas de Treinamento'),(121,'Fiscaliza├º├úo de Produ├º├úo'),(121,'Fiscaliza├º├úo de Recursos Humanos'),(121,'Fiscaliza├º├úo de Seguran├ºa'),(121,'Fiscaliza├º├úo Financeira'),(15,'Treinamento de Novos Funcion├írios'),(15,'Investiga├º├úo de Acidentes'),(15,'Organiza├º├úo de Eventos Corporativos'),(15,'Controle de Estoque'),(16,'Inspe├º├Áes de Seguran├ºa'),(16,'Atividades Recreativas'),(16,'Avalia├º├úo de Desempenho'),(16,'Campanhas Publicit├írias'),(17,'Estat├¡sticas de Produ├º├úo'),(17,'Relat├│rios Financeiros'),(17,'Estat├¡sticas de Acidentes'),(17,'Relat├│rios de Recursos Humanos'),(18,'Gest├úo de Projetos'),(18,'Comunica├º├úo Interna'),(18,'Rela├º├Áes P├║blicas'),(18,'Gest├úo de Conflitos'),(19,'Ergonomia'),(19,'Marketing Digital'),(19,'Log├¡stica'),(19,'Gest├úo de Qualidade'),(122,'Controle de Estoque'),(122,'Controle de Acesso'),(122,'Controle de Qualidade'),(122,'Controle Financeiro');
/*!40000 ALTER TABLE `pergunta_alternativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prova`
--

DROP TABLE IF EXISTS `prova`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prova` (
  `id` bigint NOT NULL,
  `pontuacao` int NOT NULL,
  `curso_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8vin5jn9vr3lsy7nk6d5wmv0f` (`curso_id`),
  KEY `FKtl2i4vrt73fugesqgw9cm1c1p` (`usuario_id`),
  CONSTRAINT `FK8vin5jn9vr3lsy7nk6d5wmv0f` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  CONSTRAINT `FKtl2i4vrt73fugesqgw9cm1c1p` FOREIGN KEY (`usuario_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prova`
--

LOCK TABLES `prova` WRITE;
/*!40000 ALTER TABLE `prova` DISABLE KEYS */;
INSERT INTO `prova` VALUES (100,0,3,NULL),(101,0,12,NULL),(102,0,14,NULL);
/*!40000 ALTER TABLE `prova` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prova_perguntas`
--

DROP TABLE IF EXISTS `prova_perguntas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prova_perguntas` (
  `prova_id` bigint NOT NULL,
  `perguntas_id` bigint NOT NULL,
  UNIQUE KEY `UK_gj2orgqtqgn8rruyn71cho0fr` (`perguntas_id`),
  KEY `FKrs44nd2im9txmluyhly9wvrdl` (`prova_id`),
  CONSTRAINT `FKf4ob3ru2q9bb8muceo1kqt2b1` FOREIGN KEY (`perguntas_id`) REFERENCES `pergunta` (`id`),
  CONSTRAINT `FKrs44nd2im9txmluyhly9wvrdl` FOREIGN KEY (`prova_id`) REFERENCES `prova` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prova_perguntas`
--

LOCK TABLES `prova_perguntas` WRITE;
/*!40000 ALTER TABLE `prova_perguntas` DISABLE KEYS */;
INSERT INTO `prova_perguntas` VALUES (100,73),(100,75),(100,77),(100,79),(100,86),(100,88),(100,90),(100,92),(100,94),(100,96),(101,108),(101,109),(101,110),(101,111),(101,112),(101,113),(101,114),(101,115),(101,116),(101,117),(102,15),(102,16),(102,17),(102,18),(102,19),(102,118),(102,119),(102,120),(102,121),(102,122);
/*!40000 ALTER TABLE `prova_perguntas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_curso`
--

DROP TABLE IF EXISTS `usuario_curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_curso` (
  `usuario_id` bigint NOT NULL,
  `cursos_id` bigint NOT NULL,
  KEY `FKiniy17hd513hm228vlj1vgxig` (`cursos_id`),
  KEY `FK8bdx4rkntv5cyn66vgoygh15u` (`usuario_id`),
  CONSTRAINT `FK8bdx4rkntv5cyn66vgoygh15u` FOREIGN KEY (`usuario_id`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FKiniy17hd513hm228vlj1vgxig` FOREIGN KEY (`cursos_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_curso`
--

LOCK TABLES `usuario_curso` WRITE;
/*!40000 ALTER TABLE `usuario_curso` DISABLE KEYS */;
INSERT INTO `usuario_curso` VALUES (1,3);
/*!40000 ALTER TABLE `usuario_curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_cursos`
--

DROP TABLE IF EXISTS `usuario_cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_cursos` (
  `usuario_id` bigint NOT NULL,
  `cursos_id` bigint NOT NULL,
  KEY `FKt73kq6qbltprhy59gaedcq32u` (`cursos_id`),
  CONSTRAINT `FKt73kq6qbltprhy59gaedcq32u` FOREIGN KEY (`cursos_id`) REFERENCES `curso` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_cursos`
--

LOCK TABLES `usuario_cursos` WRITE;
/*!40000 ALTER TABLE `usuario_cursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_cursos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-14 11:14:07
