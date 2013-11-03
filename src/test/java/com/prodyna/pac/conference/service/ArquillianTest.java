package com.prodyna.pac.conference.service;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;

public abstract class ArquillianTest {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "conference.war").addPackages(true, "com.prodyna.pac.conference");
        war.addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml");
        war.merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                "/", Filters.includeAll());
        war.addPackages(true, "org.slf4j");
        return war;
    }

}
