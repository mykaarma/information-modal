Insert ignore into Module(`Name`,`Description`)
	values ("Spring","Spring Frameworks"), ("Netflix", "Frameworks created by Netflix"), ("myKaarma", "Frameworks created by myKaarma");


insert ignore into ModalInfo(`AltText`, `IdentifierTag`, `ModalContent`, `ModalContentType`,`ModalHeader`, `ModalHeightInPx`, `ModalWidthInPx`, `UUID`)

 values ("click here to know more about Netflix Zuul ","Zuul", "https://spring.io/guides/gs/routing-and-filtering/", "URL", "Zuul Gateway", "1920", "1080", UUID()),
("click here to know more about Spring Valut","Spring_Vault", "https://spring.io/projects/spring-vaultm", "URL", "Spring Vault", "1280", "720", UUID());



Insert ignore into ModuleModalInfoMapping (`ModalInfoId`,`ModuleId`) select mi.ID, m.ID from ModalInfo mi join Module m where m.Name='Spring';

Insert ignore into ModuleModalInfoMapping (`ModalInfoId`,`ModuleId`) select mi.ID, m.ID from ModalInfo mi join Module m where m.Name='Netflix' and mi.IdentifierTag='Zuul';
