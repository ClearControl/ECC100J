package ecc100.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ecc100.ECC100Axis;
import ecc100.ECC100Controller;

public class Ecc100ControllerAndAxisDemo
{

	@Test
	public void demo() throws InterruptedException
	{
		ECC100Controller lECC100Controller = new ECC100Controller();

		assertTrue(lECC100Controller.open());

		List<Integer> lDeviceIdList = lECC100Controller.getDeviceIdList();
		assertTrue(lDeviceIdList.size() > 0);

		int lDeviceId = lDeviceIdList.get(0);
		ECC100Axis lAxis = lECC100Controller.getAxis(lDeviceId, 0);
		System.out.println(lAxis);

		System.out.println("getCurrentPosition=" + lAxis.getCurrentPosition());

		lAxis.controlOutputRelais(true);
		lAxis.continuous(true, true);
		Thread.sleep(1000);
		lAxis.continuous(false, true);

		System.out.println("getCurrentPosition=" + lAxis.getCurrentPosition());
		lAxis.goToPositionAndWait(2000);
		System.out.println("getCurrentPosition=" + lAxis.getCurrentPosition());
		lAxis.goToPositionAndWait(0);
		System.out.println("getCurrentPosition=" + lAxis.getCurrentPosition());

		lECC100Controller.close();
	}

}
