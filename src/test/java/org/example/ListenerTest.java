package org.example;

import static org.junit.Assert.*;

import org.junit.Test;

public class ListenerTest {

	private Listener listener = new Listener();

	@Test
	public void testContextDestroyed() {
		listener.contextDestroyed(null);
		assertTrue(true);

	}

	@Test
	public void testContextInitialized() {
		listener.contextInitialized(null);
		assertTrue(true);
	}

}
