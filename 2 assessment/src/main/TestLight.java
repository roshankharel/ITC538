package main;

public class TestLight {

	public static void main(String[] args) {
		Light light1 = new Light();
		Light light2 = new Light();
		
		light1.setLightBrightness(Light.HIGH).setLightColor("orange").setLightSwitch(true);
		light2.setLightBrightness(Light.MEDIUM).setLightColor("green").setLightSwitch(false);
		
		System.out.println(light1.toString());
		System.out.println(light2.toString());
	}

}
