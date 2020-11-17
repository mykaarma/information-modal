drop database if exists InformationModalDB;
create database InformationModalDB;
use	InformationModalDB;

CREATE TABLE `ModalInfo`(
	`ID` bigint(20) NOT NULL AUTO_INCREMENT ,
	`Version` bigint(20) DEFAULT 0,
	`AltText` varchar(255) DEFAULT NULL,
	`IdentifierTag` varchar(255) NOT NULL,
	`ModalHeader` varchar(255) DEFAULT NULL,
	`ModalHeightInPx` int(11) NOT NULL,
	`ModalWidthInPx` int(11) NOT NULL,
	`ModalContentType` enum('URL','HTML') NOT NULL,
	`ModalContent` varchar(2048) NOT NULL,
	`UUID` varchar(64) NOT NULL,
	PRIMARY KEY (`ID`),
	UNIQUE KEY `UK_ModalInfo_IdentifierTag` (`IdentifierTag`),
	UNIQUE KEY `UK_ModalInfo_UUID` (`UUID`)
	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ;

CREATE TABLE `Module` (
	`ID` bigint(20) NOT NULL AUTO_INCREMENT,
	`Description` varchar(255) DEFAULT NULL,
	`Name` varchar(255) NOT NULL,
	PRIMARY KEY (`ID`),
	UNIQUE KEY `UK_Module_Name` (`Name`)
	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ;


 CREATE TABLE `ModuleModalInfoMapping` (
 	`ID` bigint(20) NOT NULL AUTO_INCREMENT,
 	`ModalInfoId` bigint(20) NOT NULL,
 	`ModuleId` bigint(20) NOT NULL,
 	PRIMARY KEY (`ID`),
 	UNIQUE KEY `UK_ModuleModalInfoMapping_ModuleId_ModalInfoId` (`ModuleId`,`ModalInfoId`),
 	KEY `FK_ModuleModalInfoMapping_ModalInfoId` (`ModalInfoId`),
 	KEY `FK_ModuleModalInfoMapping_ModuleId` (`ModuleId`),
 	CONSTRAINT `FK_ModuleModalInfoMapping_ModuleId` FOREIGN KEY (`ModuleId`) REFERENCES `Module` (`ID`),
 	CONSTRAINT `FK_ModuleModalInfoMapping_ModalInfoId` FOREIGN KEY (`ModalInfoId`) REFERENCES `ModalInfo` (`ID`)
 	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ;
