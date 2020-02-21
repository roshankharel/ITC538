package main;

public class Light {
	static final int HIGH = 3;
	static final int MEDIUM = 2;
	static final int LOW = 1;
	
	private int light_brightness;
	private boolean light_switch;
	private String light_color;
	
	public Light() {
		light_brightness = LOW;
		light_switch = false;
		light_color = "red";
	}
	
	public int getLightBrightness() {
		return light_brightness;
	}
	
	public Light setLightBrightness(int light_brightness) {
		this.light_brightness = light_brightness;
		
		return this;
	}
	
	public boolean getLightSwitch() {
		return light_switch;
	}
	
	public Light setLightSwitch(boolean light_switch) {
		this.light_switch = light_switch;
		
		return this;
	}
	
	public String getLightColor() {
		return light_color;
	}
	
	public Light setLightColor(String light_color) {
		this.light_color = light_color;
		
		return this;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String toString() {
		
		if(!getLightSwitch()) {
			return "light is off";
		}
		
		return String.format("{ Light Brightness: %d, Light Color: %s }", getLightBrightness(), getLightColor());
	}
}




















