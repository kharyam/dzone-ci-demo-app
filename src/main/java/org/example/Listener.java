package org.example;

import javax.servlet.ServletContextEvent;

public class Listener implements javax.servlet.ServletContextListener {

	public int thisIsBad = 0;

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("CI/CD Application initialized");

	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("CI/CD Application exiting");

	}

}
