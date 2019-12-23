package Mod3.sample;

public class MethodSample {

	public void greet(){
		System.out.println("Hello!");
	}
	
	public static void greet(String name){
		System.out.println("Hello " + name + "!");
	}
	
	public int sum(int x, int y){
		return x + y;
	}
	
	public static void main(String arg[]){
		MethodSample sample = new MethodSample();
		MethodSample sample2 = new MethodSample();
		
		MethodSample.greet("Java Student");
		greet("Java Teacher");
		MethodSample.greet("Java Teacher");
		sample.greet();
		sample2.greet(" John");
		System.out.println("The sum of 1 and 2 is " + sample.sum(1, 2));
		
	}
}
