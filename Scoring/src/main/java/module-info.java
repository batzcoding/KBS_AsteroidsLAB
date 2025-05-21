module Scoring {
    requires spring.web;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context; // Needed for Spring context lifecycle
    requires spring.core;    // Needed because Spring's CGLIB uses spring-core for reflection

    opens dk.sdu.cbse.scoringsystem to spring.core;
}