package controller.entities;

import static org.junit.Assert.assertTrue;
import model.entities.ReticleModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controller.MainControllerFactory;
import factories.entities.KeyboardReticleFactory;

public class KeyboardReticleControllerTest {
	KeyboardReticleController krc;
	ReticleModel rm;

	@Before
	public void setUp() throws Exception {
		krc = MainControllerFactory.get().instantiate(
				new KeyboardReticleFactory());
		rm = krc.getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testKeyboardReticleControllerAndGetModel() {
		assertTrue(krc.getModel() == rm);
		rm = new ReticleModel();
		krc = new KeyboardReticleController(rm);
		assertTrue(krc.getModel() == rm);
	}

	@Test
	public void testSetPositionAndGetPosition() {
		Vector2f pos = new Vector2f(20.0f, 20.0f);
		krc.setPosition(pos);
		assertTrue(Tools.distanceBetween(pos, krc.getPosition()) <= 0.001);

		pos = new Vector2f(20.0f, 10.0f);
		krc.setPosition(pos);
		assertTrue(Tools.distanceBetween(pos, krc.getPosition()) <= 0.001);
	}

}
