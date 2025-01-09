package com.topjava.webapp;

import com.topjava.webapp.model.*;
import com.topjava.webapp.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import static com.topjava.webapp.model.ContactType.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = fillResume("12345", "Григорий Кислин");
        System.out.println(resume);
    }

    public static Resume fillResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        // fill Contacts
        resume.addContact(new Contact(MOBILE_PHONE, "+7(921) 855-0482"));
        resume.addContact(new Contact(SKYPE, "skype:grigory.kislin"));
        resume.addContact(new Contact(LINKEDIN, "https://www.linkedin.com/in/gkislin"));
        resume.addContact(new Contact(GITHUB, "https://github.com/gkislin"));
        resume.addContact(new Contact(STACKOVERFLOW, "https://stackoverflow.com/users/548473"));
        resume.addContact(new Contact(HOME_PAGE, "http://gkislin.ru/"));

        // fill OBJECTIVE("Позиция")
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения " +
                                                                 "по Java Web и Enterprise технологиям"));
        // fill PERSONAL("Личные качества")
        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, " +
                                                                "креативность, инициативность. Пурист кода и архитектуры."));

        // fill ACHIEVEMENT("Достижения")
        Section achievement = new ListSection(fillAchievements());
        resume.addSection(SectionType.ACHIEVEMENT, achievement);

        // fill QUALIFICATIONS("Квалификация")
        Section qualifications = new ListSection(fillQualifications());
        resume.addSection(SectionType.QUALIFICATIONS, qualifications);

        // fill EXPERIENCE("Опыт работы")
        Section experience = new CompanySection(fillExperience());
        resume.addSection(SectionType.EXPERIENCE, experience);

        // fill EDUCATION("Образование")
        Section education = new CompanySection(fillEducation());
        resume.addSection(SectionType.EDUCATION, education);

        return resume;
    }

    private static List<String> fillAchievements() {
        List<String> items = new ArrayList<>();
        items.add("""
                Организация команды и успешная реализация Java проектов для сторонних заказчиков:
                приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга
                показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2,
                многомодульный Spring Boot + Vaadin проект для комплексных DIY смет
                """);
        items.add("""
                С 2013 года: разработка проектов "Разработка Web приложения","Java Enterprise",
                "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP).
                Удаленное взаимодействие (JMS/AKKA)". Организация онлайн стажировок и ведение проектов.
                Более 3500 выпускников.
                """);
        items.add("""
                Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike.
                Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.
                """);
        items.add("""
                Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM.
                Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке:
                Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей,
                интеграция CIFS/SMB java сервера.
                """);
        items.add("""
                Реализация c нуля Rich Internet Application приложения на стеке технологий
                JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.
                """);
        items.add("""
                Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов
                (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и
                информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента
                для администрирования и мониторинга системы по JMX (Jython/ Django).
                """);
        items.add("""
                Реализация протоколов по приему платежей всех основных платежных системы России
                (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.)
                """);

        return items;
    }

    private static List<String> fillQualifications() {
        List<String> items = new ArrayList<>();
        items.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        items.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        items.add("""
                DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,
                MySQL, SQLite, MS SQL, HSQLDB
                """);
        items.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        items.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        items.add("""
                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis,
                Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice,
                GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT,
                JUnit, Selenium (htmlelements).
                """);
        items.add("Python: Django.");
        items.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        items.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        items.add("""
                Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX,
                SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB,
                CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.
                """);
        items.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        items.add("""
                Администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway,
                Nagios, iReport, OpenCmis, Bonita, pgBouncer
                """);
        items.add("""
                Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования,
                архитектурных шаблонов, UML, функционального программирования
                """);
        items.add("Родной русский, английский \"upper intermediate\"");
        return items;
    }

    private static List<Company> fillExperience() {
        Company javaOnlineProjects = new Company("Java Online Projects", "http://javaops.ru/");
        javaOnlineProjects.addPeriod(new Company.Period("Автор проекта",
                DateUtil.of(2015, 10), DateUtil.of(11111, 1),
                "Создание, организация и проведение Java онлайн проектов и стажировок."));
        Company wrike = new Company("Wrike", "https://www.wrike.com/");
        wrike.addPeriod(new Company.Period("Старший разработчик (backend)",
                DateUtil.of(2014, 10), DateUtil.of(2016, 1),
                """
                        Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis,
                        Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.
                        """));
        List<Company> companies = new ArrayList<>();
        companies.add(javaOnlineProjects);
        companies.add(wrike);
        return companies;
    }

    private static List<Company> fillEducation() {
        Company ifmo = new Company("""
                "Санкт-Петербургский национальный исследовательский университет информационных
                технологий, механики и оптики"
                """,
                "http://www.ifmo.ru"
        );
        ifmo.addPeriod(new Company.Period("Аспирантура (программист С, С++)",
                DateUtil.of(1993, 9), DateUtil.of(1996, 7), null));
        ifmo.addPeriod(new Company.Period("Инженер (программист Fortran, C)",
                DateUtil.of(1987, 9), DateUtil.of(1993, 7), null));
        Company mipt = new Company("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/");
        mipt.addPeriod(new Company.Period("Закончил с отличием",
                DateUtil.of(1984, 9), DateUtil.of(1987, 6), null));
        List<Company> companies = new ArrayList<>();
        companies.add(ifmo);
        companies.add(mipt);
        return companies;

    }
}