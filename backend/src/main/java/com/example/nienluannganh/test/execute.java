package com.example.nienluannganh.test;

public class execute {
	public int a=4;
	public void  b() {
		test t= () ->{
			System.out.println("thành công");
				System.out.println("tôur sinhv iên : "+this.a);
		};
		t.in();
	}
	 public static void main(String[] args) {
//		 execute t1= new execute();
//		 t1.b();
		 
		test t1= new test() {
			public int b;
			@Override
			public void in() {
				
				System.out.println("test t1");
				System.out.println("gia tri" +this.a);
			}
		};
		System.out.println(t1.getClass());
		t1.in();
	}
}
