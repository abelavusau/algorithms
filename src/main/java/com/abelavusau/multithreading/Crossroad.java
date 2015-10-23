package com.abelavusau.multithreading;

import java.util.Timer;
import java.util.TimerTask;

import com.abelavusau.multithreading.Crossroad.LightColor;

public class Crossroad {
	public enum LightColor {
		GREEN, RED;
	}

	public static void main(String[] args) {
		final TrafficLight trafficLight = TrafficLight.getInstance();
		Car car = new Car();
		new Thread(new RoadThread(car)).start();
		new Thread(new RoadThread(car)).start();

		Timer lightSwitcher = new Timer();
		lightSwitcher.schedule(new TimerTask() {
			@Override
			public void run() {
				synchronized (trafficLight) {
					trafficLight.invertLight();
					trafficLight.notifyAll();
				}
			}
		}, 0, 30000);
	}
}

class TrafficLight {
	private LightColor lightColor = LightColor.RED;

	private TrafficLight() {
	}
	
	private static class Holder {
		private static final TrafficLight INSTANCE = new TrafficLight();
	}

	public static TrafficLight getInstance() {
		return Holder.INSTANCE;
	}

	public LightColor getLightColor() {
		return lightColor;
	}

	public void invertLight() {
		lightColor = lightColor == LightColor.RED ? LightColor.GREEN : LightColor.RED;
	}
}

class Car {
	public void stopAndGo() {
		final TrafficLight trafficLight = TrafficLight.getInstance();
		while (true) {
			synchronized (trafficLight) {
				if (trafficLight.getLightColor() == LightColor.RED) {
					try {
						System.out.println(String.format("Light for %s is red", Thread.currentThread().getName()));
						System.out.println(String.format("Thread %s has stopped", Thread.currentThread().getName()));
						trafficLight.wait();
					} catch (InterruptedException e) {
					}
				} else {
					try {
						System.out.println(String.format("Light for %s is green", Thread.currentThread().getName()));
						System.out.println(String.format("Thread %s has started", Thread.currentThread().getName()));
						trafficLight.wait();
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}
}

class RoadThread implements Runnable {
	private final Car car;

	public RoadThread(Car car) {
		this.car = car;
	}

	@Override
	public void run() {
		car.stopAndGo();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
	}
}
