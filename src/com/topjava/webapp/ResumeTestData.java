package com.topjava.webapp;

import com.topjava.webapp.model.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume testResume = new Resume("Григорий Кислин");

        // fill Contacts
        Contact mainPhone = new Contact("Тел.", "+7(921) 855-0482");
        testResume.addContact(mainPhone);
        Contact mainEmail = new Contact("Skype", "skype:grigory.kislin", "skype:grigory.kislin");
        testResume.addContact(mainEmail);
        Contact linkedIn = new Contact("Профиль", "LinkedIn", "https://www.linkedin.com/in/gkislin");
        testResume.addContact(linkedIn);
        Contact gitHub = new Contact("Профиль", "GitHub", "https://github.com/gkislin");
        testResume.addContact(gitHub);
        Contact stackOverflow = new Contact("Профиль", "Stackoverflow", "https://stackoverflow.com/users/548473");
        testResume.addContact(stackOverflow);
        Contact homePage = new Contact(null, "Домашняя страница", "http://gkislin.ru/");
        testResume.addContact(homePage);

        // fill OBJECTIVE("Позиция")
        Section objective = new Section(SectionType.OBJECTIVE);
        InfField objSingle_1 = new InfField("Ведущий стажировок и корпоративного обучения по Java Web" +
                                            " и Enterprise технологиям");
        objective.addBlock(objSingle_1);
        testResume.addSection(objective);

        // fill PERSONAL("Личные качества")
        Section personal = new Section(SectionType.PERSONAL);
        InfField persSingle_1 = new InfField("Аналитический склад ума, сильная логика, креативность, инициативность. " +
                                           "Пурист кода и архитектуры.");
        personal.addBlock(persSingle_1);
        testResume.addSection(personal);

        // fill ACHIEVEMENT("Достижения")
        Section achievement = new Section(SectionType.ACHIEVEMENT);
        InfField achtTextBlocks = new InfField();
        achtTextBlocks.addTextBlock("Организация команды и успешная реализация Java проектов для сторонних заказчиков:" +
                                   " приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга" +
                                   " показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2," +
                                   " многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        achtTextBlocks.addTextBlock("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\"," +
                                   " \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP)." +
                                   " Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов." +
                                   " Более 3500 выпускников.");
        achtTextBlocks.addTextBlock("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike." +
                                   " Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achtTextBlocks.addTextBlock("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM." +
                                   " Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:" +
                                   " Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей," +
                                   " интеграция CIFS/SMB java сервера.");
        achtTextBlocks.addTextBlock("Реализация c нуля Rich Internet Application приложения на стеке технологий" +
                                   " JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achtTextBlocks.addTextBlock("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов" +
                                   " (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и" +
                                   " информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента" +
                                   " для администрирования и мониторинга системы по JMX (Jython/ Django).");
        achtTextBlocks.addTextBlock("Реализация протоколов по приему платежей всех основных платежных системы России" +
                                   " (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achievement.addBlock(achtTextBlocks);

    }
}